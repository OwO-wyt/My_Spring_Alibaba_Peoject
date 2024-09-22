package com.www.utils;

import cn.hutool.json.JSONUtil;
import com.www.response.Response;
import com.www.response.ResultMsgEnum;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @Author zhen yang
 * @Date 2022-03-08 20:54
 * @Version 1.0
 * @Description 返回响应工具类
 */
public class ResponseUtils {
    public static Mono<Void> write(ServerHttpResponse response, ResultMsgEnum resultMsgEnum) {
        response.setStatusCode(HttpStatus.valueOf(resultMsgEnum.getCode()));

        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.setStatusCode(HttpStatus.valueOf(resultMsgEnum.getCode()));
        response.getHeaders().set("Access-Control-Allow-Origin", "*");
        response.getHeaders().set("Cache-Control", "no-cache");
        String body = JSONUtil.toJsonStr(Response.error(resultMsgEnum.getCode(), resultMsgEnum.getMessage()));
        DataBuffer dataBuffer = response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(dataBuffer))
                .doOnError(error -> DataBufferUtils.release(dataBuffer));
    }
}
