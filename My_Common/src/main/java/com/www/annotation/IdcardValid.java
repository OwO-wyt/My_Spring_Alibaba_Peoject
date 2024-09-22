package com.www.annotation;

import com.www.handler.IdcardValidHandler;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @Author erdon
 * @Date 2022-03-11 14:18
 * @Version 1.0
 * @Description 身份证号码校验注解
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(IdcardValid.List.class)
@Documented
@Constraint(validatedBy = IdcardValidHandler.class)
public @interface IdcardValid {

    String message() default "IdCard not match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface List {
        IdcardValid[] value();
    }
}
