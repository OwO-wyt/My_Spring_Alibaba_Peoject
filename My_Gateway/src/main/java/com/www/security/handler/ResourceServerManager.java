package com.www.security.handler;

import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import com.www.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 登录后访问受保护的接口，请求会在这里处理
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ResourceServerManager implements ReactiveAuthorizationManager<AuthorizationContext> {
//    @Resource
//    AuthMapper authMapper;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        // 预检请求放行
        if (request.getMethod() == HttpMethod.OPTIONS) {
            return Mono.just(new AuthorizationDecision(true));
        }
        String method = request.getMethodValue();
        String path = request.getURI().getPath();
        // 根据该路径判断用户是否有权限访问接口，用户权限 <=> 接口权限
        String restFulPath = method + ":" + path;

        // 解析token，鉴权
        String token = request.getHeaders().getFirst(SecurityUtils.TOKEN_HEADER);
        // token不合法，认证失败
        if (StrUtil.isBlank(token) || !StrUtil.startWithIgnoreCase(token, SecurityUtils.BEARER)) {
            return Mono.just(new AuthorizationDecision(false));
        }

        // 删除token的前缀
        token = StrUtil.replaceIgnoreCase(token, SecurityUtils.BEARER, Strings.EMPTY);

        // 校验token是否已过期，过期会抛出异常
        try {
            JWTValidator.of(token).validateDate();
        } catch (ValidateException e) {
            return Mono.just(new AuthorizationDecision(false));
        }

        JSONObject payloads = JWTUtil.parseToken(token).getPayloads();
        // 将解析后的token存入请求header
        request.mutate().header(SecurityUtils.PAYLOAD, JSONUtil.toJsonStr(payloads))
                .header(SecurityUtils.AUTH_USERID, payloads.getJSONObject(SecurityUtils.MB_USER_INFO).getStr("userCode"))
                .header(SecurityUtils.AUTH_USERNAME, payloads.getJSONObject(SecurityUtils.MB_USER_INFO).getStr("userName"))
                .build();
//        // 查询访问接口需要的权限
//        List<String> apiPermAuthorities = authMapper.getApiPermAuthoritiesByUserCode(restFulPath);
        // 人拥有的权限
        JSONArray authoritiesJsonArray = payloads.getJSONArray(SecurityUtils.AUTHORITIES);
        List<String> userAuthoritiesList = authoritiesJsonArray.toList(String.class);
        // 访问权限校验
        // 若接口未配置权限，则认为访问该接口不需要权限
//        if (CollUtil.isEmpty(apiPermAuthorities)) {
//            return Mono.just(new AuthorizationDecision(true));
//        }
//
//        // 判读接口需要的权限和人拥有的权限是否有交集，若有交集则放行
//        for (String auth : userAuthoritiesList) {
//            if (apiPermAuthorities.contains(auth)) {
//                return Mono.just(new AuthorizationDecision(true));
//            }
//        }
        return Mono.just(new AuthorizationDecision(true));
    }
}
