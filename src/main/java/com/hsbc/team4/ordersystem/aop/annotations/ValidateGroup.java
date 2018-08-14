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
public @interface ValidateGroup {
    ValidateFiled[] fileds() ;
}
