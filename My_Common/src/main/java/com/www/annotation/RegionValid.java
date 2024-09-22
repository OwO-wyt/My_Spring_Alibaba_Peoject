package com.www.annotation;


import com.www.handler.RegionValidHandler;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @Author erdon
 * @Date 2022-03-09 14:44
 * @Version 1.0
 * @Description 校验特定字段的值是否在可选范围内(整形)
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(RegionValid.List.class)
@Documented
@Constraint(validatedBy = RegionValidHandler.class)
public @interface RegionValid {

    String message() default "value not in region";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * data must in this value array
     *
     * @return
     */
    int[] value();

    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface List {
        RegionValid[] value();
    }

}
