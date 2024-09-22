package com.www.exception;

import com.www.response.ResultMsgEnum;
import lombok.Getter;

import static com.www.response.ResultMsgEnum.DEFAULT_EXCEPTION;

/**
 * @Author erdon
 * @Date 2022-03-05 15:36
 * @Version 1.0
 * @Description 自定义异常基类
 */
@Getter
public abstract class BaseException extends RuntimeException {

    private int code;

    /**
     * 使用枚举类的异常类型
     *
     * @param resultMsgEnum
     */
    public BaseException(ResultMsgEnum resultMsgEnum) {
        super(resultMsgEnum.getMessage());
        this.code = resultMsgEnum.getCode();
    }

    /**
     * 自定义异常类型
     *
     * @param code
     * @param message
     */
    public BaseException(int code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 自定义异常信息
     * @param message
     */
    public BaseException(String message) {
        super(message);
        this.code = DEFAULT_EXCEPTION.getCode();
    }
}
