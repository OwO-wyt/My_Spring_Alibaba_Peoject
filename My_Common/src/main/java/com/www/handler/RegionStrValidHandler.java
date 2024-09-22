package com.www.handler;

import com.www.annotation.RegionStrValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

/**
 * @Author erdon
 * @Date 2022-03-09 14:52
 * @Version 1.0
 * @Description 判断值是否在可选范围内校验处理器
 */
public class RegionStrValidHandler implements ConstraintValidator<RegionStrValid, String> {

    private List<String> regionValueList;

    @Override
    public void initialize(RegionStrValid constraintAnnotation) {
        regionValueList = Arrays.asList(constraintAnnotation.value());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return regionValueList.contains(value);
    }
}
