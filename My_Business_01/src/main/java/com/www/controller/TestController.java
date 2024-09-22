package com.www.controller;

import com.www.pojo.SystemLogEntity;
import com.www.pojo.TestEntity;
import com.www.pojo.UserInfoEntity;
import com.www.response.Response;
import com.www.service.SystemLogService;
import com.www.service.TestService;
import com.www.service.UserInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author wyt
 * @date 2024/5/12 0:13
 * @description
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Resource
    private TestService testService;
    @Resource
    private SystemLogService systemLogService;
    @Resource
    private UserInfoService userInfoService;

    @PostMapping("/saveToTest")
    public Response saveToTest(@RequestBody TestEntity testEntity) {
        testService.save(testEntity);
        return Response.ok();
    }

    @GetMapping("/getTest")
    public Response getTest() {
        return Response.ok(testService.list());
    }

    @PostMapping("/saveToSystemLog")
    public Response saveToSystemLog(@RequestBody SystemLogEntity systemLogEntity) {
        systemLogService.save(systemLogEntity);
        return Response.ok();
    }

    @GetMapping("/getSystemLog")
    public Response getSystemLog() {
        return Response.ok(systemLogService.list());
    }

    @PostMapping("/saveToUserInfo")
    public Response saveToUserInfo(@RequestBody UserInfoEntity userInfoEntity) {
        userInfoService.save(userInfoEntity);
        return Response.ok();
    }


    @GetMapping("/getUserInfo")
    public Response getUserInfo() {
        return Response.ok(userInfoService.list());
    }





    @GetMapping("/test1")
    public Response test() {
        return Response.ok("成功");
    }





}
