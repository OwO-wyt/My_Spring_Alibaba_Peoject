package com.www.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.net.URLDecoder;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.www.pojo.MbUserInfo;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Author zhen yang
 * @Date 2022-03-08 19:42
 * @Version 1.0
 * @Description security常量
 */
public class SecurityUtils {
    public final static String TOKEN_HEADER = "Authorization";
    public final static String BEARER = "Bearer ";
    public final static String INFO = "info";
    public final static String AUTHORITIES = "authorities";
    public final static String ROLES = "roles";
    public final static String PASSWORD = "password";
    public final static String USERNAME = "username";
    public final static String PAYLOAD = "payload";
    public final static String AUTH_USERID = "Auth-UserId";
    public final static String MB_USER_INFO = "mbUserInfo";
    public final static String AUTH_USERNAME = "Auth-UserName";

    /**
     * token加密密钥
     */
    public final static byte[] JWT_KEY = StrUtil.bytes("3b03a1eb-cb4a-4edf-a7ed-40fd50cab816");

    /**
     * 获取用户信息
     *
     * @return
     */
    public static MbUserInfo getUserInfo() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String parameter = request.getHeader(SecurityUtils.PAYLOAD);
        MbUserInfo mbUserInfo = new MbUserInfo();
        BeanUtil.copyProperties(JSONUtil.parseObj(parameter).getJSONObject(SecurityUtils.MB_USER_INFO), mbUserInfo, SecurityUtils.PASSWORD);

        // 将用户名进行UTF-8编码，不然前端将其放入token中时，后端节收到的姓名乱码
        mbUserInfo.setUserName(URLDecoder.decode(mbUserInfo.getUserName(), StandardCharsets.UTF_8));

        return mbUserInfo;
    }

    /**
     * 获取用户权限列表
     */
    public static List<String> getUserAuthorities() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String parameter = request.getHeader(SecurityUtils.PAYLOAD);
        JSONArray jsonArray = JSONUtil.parseObj(parameter).getJSONArray(SecurityUtils.AUTHORITIES);
        return jsonArray.toList(String.class);
    }

    /**
     * 获取用户角色列表
     */
    public static List<String> getUserRoles() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String parameter = request.getHeader(SecurityUtils.PAYLOAD);
        JSONArray jsonArray = JSONUtil.parseObj(parameter).getJSONArray(SecurityUtils.ROLES);
        return jsonArray.toList(String.class);
    }
}
