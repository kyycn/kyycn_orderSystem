package com.hsbc.team4.ordersystem.aop;

import com.hsbc.team4.ordersystem.aop.annotations.HasLogin;
import com.hsbc.team4.ordersystem.common.utils.Global;
import com.hsbc.team4.ordersystem.exception.UserNotLoginException;
import com.hsbc.team4.ordersystem.users.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.aop
 * @Description :
 * @Date : 2018/8/12
 */
@Component
@Aspect
@Slf4j
public class HasLoginAspect {

    private final Global global;

    @Autowired
    public HasLoginAspect(Global global) {
        this.global = global;
    }

    @Pointcut("execution(* com.hsbc.team4.ordersystem..*Controller..*(..))")
    public void controllerAspect() {
    }

    @Before("controllerAspect()")
    public void handleControllerMethod(JoinPoint joinPoint) throws Throwable {
        Object target=joinPoint.getTarget();
        String methodName=joinPoint.getSignature().getName();
        Method method=getMethodByClassAndName(target.getClass(), methodName);
        HasLogin hasLogin = (HasLogin)getAnnotationByMethod(method ,HasLogin.class );
        if(hasLogin!=null) {
            User user = global.getUserByToken();
            if (StringUtils.isEmpty(user)) {
                throw new UserNotLoginException(hasLogin.message());
            }
        }
    }

    /**
     * getMethodByClassAndName
     * @param c
     * @param methodName
     * @return
     */
    public Method getMethodByClassAndName(Class c , String methodName){
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            if(method.getName().equals(methodName)){
                return method ;
            }
        }
        return null;
    }

    /**
     *  getAnnotationByMethod
     * @param method
     * @param annoClass
     * @return
     */
    public Annotation getAnnotationByMethod(Method method , Class annoClass){
        Annotation all[] = method.getAnnotations();
        for (Annotation annotation : all) {
            if (annotation.annotationType() == annoClass) {
                return annotation;
            }
        }
        return null;
    }
}
