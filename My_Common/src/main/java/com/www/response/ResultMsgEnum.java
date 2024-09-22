package com.www.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author erdon
 * @Date 2022-03-04 17:15
 * @Description 返回值枚举类
 * <p>
 * * code区间规范(参考HTTP状态码)：
 * <p>
 * * 1xx: 信息       服务器接收到请求，需要请求者继续执行操作
 * * 2xx: 成功       请求被成功接收并处理
 * * 3xx: 重定向     需要进一步操作以完成请求
 * * 4xx: 客户端错误  请求包含语法错误或无法完成请求
 * * 5xx: 服务器错误  服务器在处理时发生错误
 */
@Getter
@AllArgsConstructor
public enum ResultMsgEnum {

    // common code
    SUCCESS(200, "操作成功"),
    FAIL(-1, "操作失败"),
    UN_AUTHORIZED(401, "请求未授权"),
    INVALID_TOKEN(401, "token无效或已过期"),
    FORBIDDEN(403, "访问被禁止"),
    NOT_FOUND(404, "资源不存在"),
    REQUEST_TIMEOUT(405, "请求超时"),
    SERVER_ERROR(500, "服务器异常"),
    DEFAULT_EXCEPTION(501, "程序异常"),
    AUTH_ERROR(502, "权限认证失败"),
    SERVER_BUSY(503, "服务器正忙，请稍后再试"),

    // message code
    UNIFY_LOG_PUSH_SUCCESS(80100, "日志已接收"),
    MSG_PUSH_SUCCESS(80200, "消息已接收"),
    MSG_FORMAT_ERROR(80400, "参数格式错误"),
    MSG_CHANNEL_AUTH(80401, "渠道无权限"),
    MSG_ACCESS_AUTH(80402, "推送任务授权Key或Secret错误"),
    MSG_IP_WHITELIST(80403, "IP白名单过滤"),
    MSG_TASK_ID_NO_EXIST(80404, "推送任务ID不能为空"),
    MSG_REPEAT(80405, "消息重复"),
    MSG_PERSON_AUTH(80406, "您不是消息接收人，无权反馈"),
    MSG_PERSON_LIMIT(80407, "消息推送人数已达上限"),
    MSG_MATCH_TASK(80408, "当前消息不属于当前推送任务"),
    MSG_QUERY_LIMIT(80409, "消息查询数已达上限"),
    MSG_NOT_NEED_FEEDBACK(80410, "消息无权反馈"),
    MSG_COMMON_AUTH(80411, "消息Header鉴权参数缺失"),
    APP_SECRET_ERROR(80412, "应用Secret错误"),
    FLOW_EXCEPTION(80521, "资源被限流，请稍后再试"),
    DEGRADE_EXCEPTION(80522, "资源被熔断，请稍后再试"),
    PARAM_FLOW_EXCEPTION(80523, "热点规则异常，请稍后再试"),
    SYSTEM_BLOCK_EXCEPTION(80524, "系统规则异常，请稍后再试"),
    AUTHORITY_EXCEPTION(80525, "授权规则异常，请稍后再试");

    private int code;
    private String message;
}
