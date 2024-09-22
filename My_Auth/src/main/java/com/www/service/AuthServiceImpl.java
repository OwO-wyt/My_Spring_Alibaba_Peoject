package com.www.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.core.net.URLDecoder;
import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import com.www.pojo.TokenVo;
import com.www.security.SecurityUserDetails;
import com.www.security.UserDetailsServiceImpl;
import com.www.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

/**
 * 认证模块服务层
 *
 * @author zane
 */
@Service
@RefreshScope
public class AuthServiceImpl implements IAuthService {
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;

    private Long tokenExpire = 1 * 60 * 60 *1000L;

    private Long refreshTokenExpire =  30 * 60 *1000L;

    /**
     * 使用身份证和密码登录
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public TokenVo login(String username, String password) {
        SecurityUserDetails userDetails = (SecurityUserDetails) userDetailsService.loadUserByUsername(username);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            response.setStatus(HttpStatus.HTTP_FORBIDDEN);
        }
        return doCreateToken(userDetails);
    }

    /**
     * 使用refreshToken刷新token
     *
     * @param refreshToken
     * @return
     */
    @Override
    public TokenVo refreshToken(String refreshToken) {
        JSONObject payloads = new JSONObject();
        // 校验token是否已过期，过期会抛出异常
        try {
            JWTValidator.of(refreshToken).validateDate();
            payloads = JWTUtil.parseToken(refreshToken).getPayloads();
        } catch (ValidateException e) {
        }

        String username = payloads.getStr(SecurityUtils.USERNAME);
        SecurityUserDetails userDetails = (SecurityUserDetails) userDetailsService.loadUserByUsername(username);
        return doCreateToken(userDetails);
    }

    /**
     * 创建token
     *
     * @param userDetails
     * @return
     */
    private TokenVo doCreateToken(SecurityUserDetails userDetails) {
        Map<String, Object> map = BeanUtil.beanToMap(userDetails);
        map.remove(SecurityUtils.PASSWORD);
        map.put(SecurityUtils.AUTHORITIES, AuthorityUtils.authorityListToSet(userDetails.getAuthorities()));

        Date now = new Date();
        Date tokenExpireDate = new Date(now.getTime() + tokenExpire);
        String token = JWT.create()
                .setExpiresAt(tokenExpireDate)
                .addPayloads(map)
                .setKey(SecurityUtils.JWT_KEY)
                .sign();

        Date refreshTokenExpireDate = new Date(now.getTime() + refreshTokenExpire);
        String refreshToken = JWT.create()
                .setExpiresAt(refreshTokenExpireDate)
                .addPayloads(map)
                .setKey(SecurityUtils.JWT_KEY)
                .sign();

        TokenVo tokenVo = new TokenVo();
        tokenVo.setToken(token);
        tokenVo.setRefreshToken(refreshToken);
        TokenVo.TokenInfo tokenInfo = new TokenVo.TokenInfo();
        tokenInfo.setIdno(userDetails.getMbUserInfo().getUserCode());
        tokenInfo.setMobile(userDetails.getMbUserInfo().getMobile());

        // 将UTF-8编码的用户名进行UTF-8转码，放在明文json体中，返回给前端使用
        tokenInfo.setName(URLDecoder.decode(userDetails.getMbUserInfo().getUserName(), StandardCharsets.UTF_8));

        tokenInfo.setAuthorities(AuthorityUtils.authorityListToSet(userDetails.getAuthorities()));
        tokenInfo.setRoles(CollUtil.newHashSet(userDetails.getRoles()));
        tokenInfo.setDepCode(userDetails.getMbUserInfo().getDepCode());
        tokenInfo.setDepName(userDetails.getMbUserInfo().getDepName());
        tokenInfo.setManageDepCode(userDetails.getManageDepCode());
        tokenVo.setInfo(tokenInfo);
        return tokenVo;
    }
}
