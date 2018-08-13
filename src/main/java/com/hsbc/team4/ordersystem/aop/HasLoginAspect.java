package com.hsbc.team4.ordersystem.aop;

import com.hsbc.team4.ordersystem.aop.annotations.HasLogin;
import com.hsbc.team4.ordersystem.common.utils.Global;
import com.hsbc.team4.ordersystem.common.utils.ReflectUtils;
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
        Method method= ReflectUtils.getMethodByClassAndName(target.getClass(), methodName);
        HasLogin hasLogin = (HasLogin)ReflectUtils.getAnnotationByMethod(method ,HasLogin.class );
        if(hasLogin!=null) {
            User user = global.getUserByToken();
            if (StringUtils.isEmpty(user)) {
                throw new UserNotLoginException(hasLogin.message());
            }
        }
    }

}
