package com.zcj.mybatisplus.annotation;


import java.lang.annotation.*;

/**
 * 给对象的某个字段标记该字段所代表意思
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD})
public @interface AttributeMean {
        String name() default "" ;
}
