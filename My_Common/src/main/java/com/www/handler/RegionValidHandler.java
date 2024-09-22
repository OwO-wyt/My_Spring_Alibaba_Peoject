package com.www.handler;

import com.google.common.primitives.Ints;
import com.www.annotation.RegionValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @Author erdon
 * @Date 2022-03-09 14:52
 * @Version 1.0
 * @Description 判断值是否在可选范围内校验处理器
 */
public class RegionValidHandler implements ConstraintValidator<RegionValid, Integer> {

    private List<Integer> regionValueList;

    @Override
    public void initialize(RegionValid constraintAnnotation) {
        regionValueList = Ints.asList(constraintAnnotation.value());
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        return regionValueList.contains(value);
    }
}
