package com.hsbc.team4.ordersystem.aop.annotations;

import java.lang.annotation.*;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.aop
 * @Description :
 * @Date : 2018/8/12
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidateFiled {
    /**
     * index
     * @return int
     */
    int index() default -1 ;

    /**
     * regStr
     */
    String regStr() default "";
    /**
     * notNull
     */
    boolean notNull() default false;

    /**
     * maxLen
     */
    int maxLen() default -1 ;

    /**
     * minLen
     */
    int minLen() default -1 ;

    /**
     * maxVal
     */
    int maxVal() default -1 ;

    /**
     *minVal
     */
    int minVal() default -1 ;

    String message() default "";

}
