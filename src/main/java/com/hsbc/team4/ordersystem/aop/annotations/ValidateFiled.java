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
    public int index() default -1 ;

    /**
     * regStr
     */
    public String regStr() default "";
    /**
     * notNull
     */
    public boolean notNull() default false;

    /**
     * maxLen
     */
    public int maxLen() default -1 ;

    /**
     * minLen
     */
    public int minLen() default -1 ;

    /**
     * maxVal
     */
    public int maxVal() default -1 ;

    /**
     *minVal
     */
    public int minVal() default -1 ;

    public String message() default "";

}
