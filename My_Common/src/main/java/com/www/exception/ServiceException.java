package com.www.exception;

import com.www.response.ResultMsgEnum;
import lombok.Getter;

/**
 * @Author erdon
 * @Date 2022-03-05 15:42
 * @Version 1.0
 * @Description 自定义业务异常，继承 BaseException，在CustomExceptionHandler中定义相关异常处理器
 *
 */
@Getter
public class ServiceException extends BaseException{

    public ServiceException(ResultMsgEnum resultMsgEnum) {
        super(resultMsgEnum);
    }

    public ServiceException(Integer code, String message) {
        super(code, message);
    }

    public ServiceException(String message) {
        super(message);
    }
}
