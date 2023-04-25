package com.spring.studentmanagement.security.utils;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created at 4/25/2023 by Darius
 **/
@Component
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Security {

    @AliasFor(
            annotation = Component.class
    )
    String value() default "";
}
