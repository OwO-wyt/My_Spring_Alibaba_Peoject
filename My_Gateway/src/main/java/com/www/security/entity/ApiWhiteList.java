package com.www.security.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @Author zhen yang
 * @Date 2022-03-09 16:40
 * @Version 1.0
 * @Description 白名单API接口
 */
@Component
//@ConfigurationProperties(prefix = "security")
public class ApiWhiteList {
    @Setter
    @Getter
    private List<String> whiteList = Arrays.asList("/gateway/auth/auth/**");
}
