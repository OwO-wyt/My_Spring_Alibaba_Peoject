package com.www.handler;

import javax.validation.groups.Default;

/**
 * @Author erdon
 * @Date 2022-03-16 16:42
 * @Description 自定义分组校验规则
 */
public interface CustomValidGroup extends Default {

    interface MessageGroup extends CustomValidGroup {

        interface Push extends MessageGroup {
        }

        interface Feedback extends MessageGroup {
        }

        interface Query extends MessageGroup {
        }
    }
}
