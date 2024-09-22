package com.www.security.config;

import cn.hutool.core.convert.Convert;
import com.www.response.ResultMsgEnum;
import com.www.security.entity.ApiWhiteList;
import com.www.security.handler.ResourceServerManager;
import com.www.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import reactor.core.publisher.Mono;


@EnableWebFluxSecurity
@RefreshScope
public class SecurityConfig {
    @Autowired
    ApiWhiteList apiWhiteList;
    @Autowired
    private ResourceServerManager resourceServerManager;

    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange()
//                .pathMatchers("/admin/**").authenticated()
                .pathMatchers(Convert.toStrArray(apiWhiteList.getWhiteList())).permitAll()
                .anyExchange().access(resourceServerManager)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .authenticationEntryPoint(authenticationEntryPoint())// 匿名访问
                .and().csrf().disable(); // 不支持跨域
        return http.build();
    }

    /**
     * 自定义未授权访问
     *
     * @return
     */
    @Bean
    ServerAccessDeniedHandler accessDeniedHandler() {
        return (exchange, e) -> {
            Mono<Void> mono = Mono.defer(() -> Mono.just(exchange.getResponse())
                    .flatMap(response -> ResponseUtils.write(response, ResultMsgEnum.UN_AUTHORIZED)));
            return mono;
        };
    }


    /**
     * token无效或已过期自定义相应
     *
     * @return
     */
    @Bean
    ServerAuthenticationEntryPoint authenticationEntryPoint() {
        return (exchange, e) -> {
            Mono<Void> mono = Mono.defer(() -> Mono.just(exchange.getResponse()))
                    .flatMap(response -> ResponseUtils.write(response, ResultMsgEnum.INVALID_TOKEN));
            return mono;
        };
    }

//    @Bean
//    public ReactiveAuthenticationManager reactiveAuthenticationManager() {
//        LinkedList<ReactiveAuthenticationManager> managers = new LinkedList<>();
//        managers.add(authentication -> {
//            // 其他登录方式
//            return Mono.empty();
//        });
//
//        managers.add(new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService));
//
//        return new DelegatingReactiveAuthenticationManager(managers);
//    }
}
