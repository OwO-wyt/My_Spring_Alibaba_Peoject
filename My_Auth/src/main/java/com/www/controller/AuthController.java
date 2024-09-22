package com.www.controller;

import com.www.pojo.TokenVo;
import com.www.response.Response;
import com.www.service.AuthServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@Api(tags = "登录模块")
@Slf4j
public class AuthController {
    @Autowired
    AuthServiceImpl authService;

    @PostMapping("/login")
    @ApiOperation("登录 - 获取token")
    public Response<TokenVo> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return Response.ok(authService.login(username, password));
    }

    @GetMapping("/refresh")
    @ApiOperation("登录 - 刷新token")
    public Response<TokenVo> refreshToken(@RequestParam("refreshToken") String refreshToken) {
        return Response.ok(authService.refreshToken(refreshToken));
    }



    @GetMapping("/test")
    public Object test() {

        log.info("===========");

//        System.out.println(SecurityUtils.getUserInfo());
//        System.out.println(SecurityUtils.getUserAuthorities());
//        System.out.println(SecurityUtils.getUserRoles());
        return "==========test==========" + System.currentTimeMillis();
    }
}
