package com.hsbc.team4.ordersystem.aop.annotations;

import java.lang.annotation.*;

/**
 * log
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value() default "";
}
