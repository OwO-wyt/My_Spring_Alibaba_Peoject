package com.www.handler;

import cn.hutool.core.util.IdcardUtil;
import com.www.annotation.IdcardValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Author erdon
 * @Date 2022-03-11 14:19
 * @Version 1.0
 * @Description 身份证号码校验注解处理器
 */
public class IdcardValidHandler implements ConstraintValidator<IdcardValid, String> {

    @Override
    public boolean isValid(String var, ConstraintValidatorContext constraintValidatorContext) {
        return IdcardUtil.isValidCard(var);
    }
}
