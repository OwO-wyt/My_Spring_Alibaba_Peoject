package com.www.security.filter;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.www.utils.SecurityUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 在请求进入时进行拦截，判断是有含有token，若有token，则取出token
 * <p>
 * 这个filter在filterChain中需要放在默认filter的前面
 */
@Component
public class GlobalAuthenticationFilter extends OncePerRequestFilter {

    /**
    * @Author: wyt
    * @Description: 
    * @DateTime: 2024/5/9 22:26
    */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String payloadStr = request.getHeader(SecurityUtils.PAYLOAD);
        // 判断是否含有网关解析后的token数据
        if (StrUtil.isNotBlank(payloadStr)) {
            JSONObject payloads = JSONUtil.parseObj(payloadStr);
            JSONArray authorities = payloads.getJSONArray(SecurityUtils.AUTHORITIES);
            List<String> userAuthoritiesList = authorities.toList(String.class);
            String username = payloads.getStr(SecurityUtils.USERNAME);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, AuthorityUtils.createAuthorityList(Convert.toStrArray(userAuthoritiesList)));
            // 设置为已登录
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }


}
