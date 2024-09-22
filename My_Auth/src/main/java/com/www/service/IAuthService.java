package com.www.service;


import com.www.pojo.TokenVo;

/**
 * 认证模块控制层
 *
 * @author zane
 */
public interface IAuthService {

    /**
     * 使用身份证和密码登录
     *
     * @param username
     * @param password
     * @return
     */
    TokenVo login(String username, String password);

    /**
     * 刷新token
     *
     * @param refreshToken
     * @return
     */
    TokenVo refreshToken(String refreshToken);
}