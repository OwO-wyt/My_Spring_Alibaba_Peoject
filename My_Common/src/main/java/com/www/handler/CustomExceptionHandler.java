package com.www.handler;

import com.www.exception.ServiceException;
import com.www.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.message.AuthException;
import java.util.HashMap;
import java.util.Map;

import static com.www.response.ResultMsgEnum.*;

/**
 * @Author erdon
 * @Date 2022-03-04 18:05
 * @Description 自定义异常处理器
 */
@Slf4j
@RestControllerAdvice
@ConditionalOnClass(javax.servlet.ServletException.class)
public class CustomExceptionHandler {

    /**
     * 自定义异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public Response customException(ServiceException e) {
        return Response.error(e.getCode(), e.getMessage());
    }

    /**
     * 权限相关异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AuthException.class)
    public Response clientException(AuthException e) {
        log.error(AUTH_ERROR.getMessage(), e);
        return Response.error(AUTH_ERROR.getCode(), AUTH_ERROR.getMessage());
    }

    /**
     * 全局异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Response serverException(Exception e) {
        log.error(SERVER_ERROR.getMessage(), e);
        return Response.error(SERVER_ERROR.getCode(), SERVER_ERROR.getMessage());
    }

    /**
     * 参数校验异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleValidationException(MethodArgumentNotValidException e) {
        Map<String, String> errorMap = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorMap.put(fieldName, errorMessage);
        });
        return Response.error(MSG_FORMAT_ERROR.getCode(), MSG_FORMAT_ERROR.getMessage(), errorMap);
    }
}
