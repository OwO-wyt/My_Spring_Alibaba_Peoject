package com.www.security.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import com.www.security.entity.ApiWhiteList;
import com.www.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author zhen yang
 * @Date 2022-03-08 19:38
 * @Version 1.0
 * @Description 网关全局拦截器
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class SecurityGlobalFilter implements GlobalFilter, Ordered {

    @Autowired
    ApiWhiteList apiWhiteList;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        ServerHttpRequest request = exchange.getRequest();
        // 判断是否含有token
        String token = request.getHeaders().getFirst(SecurityUtils.TOKEN_HEADER);
        String path = request.getURI().getPath();
        // 对于白名单中的接口，需要将请求header中的token删除
        if (StrUtil.isNotBlank(token)) {
            for (String api : apiWhiteList.getWhiteList()) {
                if (pathMatcher.match(api, path)) {
                    // 移除token
                    request.mutate().headers(k -> k.remove(SecurityUtils.TOKEN_HEADER));
                    // 尝试解析token，解析成功则将用户信息放入header的payload
                    try {
                        // 解析token
                        token = StrUtil.replaceIgnoreCase(token, SecurityUtils.BEARER, Strings.EMPTY);
                        // 校验token是否已过期，过期会抛出异常
                        JWTValidator.of(token).validateDate();
                        JSONObject payloads = JWTUtil.parseToken(token).getPayloads();
                        // 将解析后的token存入请求header
                        request.mutate().header(SecurityUtils.PAYLOAD, JSONUtil.toJsonStr(payloads)).build();
                    } catch (Throwable t) {
                        // noting to do
                    }
                }
            }
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
