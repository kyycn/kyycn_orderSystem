package com.hsbc.team4.ordersystem.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.aop
 * @Description :
 * @Date : 2018/8/9
 */
@Aspect
@Slf4j
public class TimeAspect {

    @Pointcut("execution(* com.hsbc.team4.ordersystem..*Controller..*(..))")
    public void controllerAspect() {
    }

    @Around("controllerAspect()")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        log.info("time aspect start");
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            log.info("arg is "+arg);
        }
        long startTime = System.currentTimeMillis();
        Object object = pjp.proceed();
        long endTime =System.currentTimeMillis();
        log.info("time aspect 耗时:"+ (endTime - startTime));
        log.info("time aspect end");
        return object;
    }



}
