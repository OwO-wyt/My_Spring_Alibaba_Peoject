package com.www.security.config;

import cn.hutool.core.convert.Convert;
import com.www.security.UserDetailsServiceImpl;
import com.www.security.filter.GlobalAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private List<String> whiteList = Arrays.asList("/auth/**" , "/doc.html" , "/swagger-resources/**" , "/swagger-ui.html/**" , "/webjars/**","/v2/**");

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    GlobalAuthenticationFilter globalAuthenticationFilter;


    /**
     * 配置登陆页面、登录成功handler、登录失败handler
     * 配置需要放行的白名单接口
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // //禁用跨站csrf攻击防御
        http.csrf().disable();
        // 不需要session，基于token无状态登录
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // whiteList不需要验证就可访问
        http.authorizeRequests().antMatchers(Convert.toStrArray(whiteList)).permitAll()
         .anyRequest().authenticated(); // 剩余接口都要登录

        // 未登录情况下访问资源时，security会302重定向到登录页面，前后端分离情况下，需要修改
        // 未登录请求资源时，由AuthenticationEntryPoint处理
        http.exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint());

        // 自定义filter放在默认filter前面执行， globalAuthenticationFilter拦截请求，判断是否含有token，并对token进行鉴权
        http.addFilterBefore(globalAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 登录处理类，配置内存中的用户，配置userDetailService
     * <p>
     * 登录后进入userDetailService进行用户验证
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    /**
     * 自定义AuthenticationFilter时，需要显示的指定AuthenticationManager，这里创建一个AuthenticationManager的Bean，供AuthenticationFilter使用
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
