package com.www.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.www.pojo.SystemLogEntity;
import com.www.response.Response;
import com.www.service.SystemLogService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wyt
 * @date 2024/7/18 11:19
 * @description
 */
@RestController
@RequestMapping("systemLog")
@Api(tags = {"日志"})
public class SystemLogController {

    @Resource
    private SystemLogService systemLogService;


    @PostMapping("/getById")
    public Response getById(Long id) {
        return Response.ok(systemLogService.getById(id));
    }


    @PostMapping("/getByTime")
    public Response getByTime(String start , String end) {
        LambdaQueryWrapper<SystemLogEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.between(SystemLogEntity::getCreateTime , start , end);
        return Response.ok(systemLogService.list(queryWrapper));
    }


    @PostMapping("/getByInId")
    public Response getByInId(@RequestBody  List<Long> ids) {
        LambdaQueryWrapper<SystemLogEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SystemLogEntity::getLogId , ids);
        return Response.ok(systemLogService.list(queryWrapper));
    }

    @PostMapping("/getCountByUserCode")
    public Response getCountByUserCode(String userCode) {

        if (StringUtils.isNotBlank(userCode)) {
            LambdaQueryWrapper<SystemLogEntity> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SystemLogEntity::getUserCode , userCode);
            return Response.ok(systemLogService.count(queryWrapper));
        }
        return Response.ok(systemLogService.count());
    }








}
