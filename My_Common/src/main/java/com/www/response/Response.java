package com.www.response;

import cn.hutool.core.collection.ListUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.www.response.ResultMsgEnum.FAIL;
import static com.www.response.ResultMsgEnum.SUCCESS;
/**
 * @Author erdon
 * @Date 2022-03-04 17:05
 * @Description 统一响应类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "统一返回对象 Response")
public class Response<T> {

    @ApiModelProperty(value = "状态码", notes = "200, 操作成功 -1, 操作失败")
    private int code;
    @ApiModelProperty(value = "提示信息")
    private String message;
    @ApiModelProperty(value = "数据")
    private T data;
    @ApiModelProperty(value = "分页信息")
    private Page page;

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功code 200
     *
     * @param <T>
     * @return
     */
    public static <T> Response<T> ok() {
        return new Response<>(SUCCESS.getCode(), SUCCESS.getMessage(), null);
    }

    public static <T> Response<T> ok(T data) {
        return new Response<>(SUCCESS.getCode(), SUCCESS.getMessage(), data);
    }

    public static <T> Response<T> ok(T data, String message) {
        return new Response<>(SUCCESS.getCode(), message, data);
    }

    public static <T> Response<T> ok(T data, ResultMsgEnum resultMsgEnum) {
        return new Response<>(resultMsgEnum.getCode(), resultMsgEnum.getMessage(), data);
    }

    /**
     * 分页返回
     *
     * @param data
     * @param page
     * @param <T>
     * @return
     */
    public static <T> Response<T> ok(T data, Page page) {
        return new Response<>(SUCCESS.getCode(), SUCCESS.getMessage(), data, page.setRecords(ListUtil.empty()));
    }

    public static <T> Response<T> ok(T data, String message, Page page) {
        return new Response<>(SUCCESS.getCode(), message, data, page.setRecords(ListUtil.empty()));
    }

    public static <T> Response<T> ok(Page page) {
        return new Response(SUCCESS.getCode(), SUCCESS.getMessage(), page.getRecords(), page.setRecords(ListUtil.empty()));
    }


    /**
     * 失败code -1
     *
     * @param <T>
     * @return
     */
    public static <T> Response<T> error() {
        return new Response<>(FAIL.getCode(), FAIL.getMessage());
    }

    public static <T> Response<T> error(String message) {
        return new Response<>(FAIL.getCode(), message);
    }

    public static <T> Response<T> error(T data, String message) {
        return new Response<>(FAIL.getCode(), message, data);
    }

    /**
     * 自定义错误(自定义异常处理使用)
     *
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Response<T> error(int code, String message) {
        return new Response<>(code, message);
    }

    public static <T> Response<T> error(int code, String message, T data) {
        return new Response<>(code, message, data);
    }

}
