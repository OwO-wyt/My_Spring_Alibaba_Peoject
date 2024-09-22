package com.www.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;

/**
 * @author wyt
 * @date 2024/5/8 22:27
 * @description
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${info.name}")
    private String name;

    @GetMapping("/test1")
    public String test1() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return name;
    }

    @GetMapping("/test2")
    public String test2() {
        return name;
    }


}
