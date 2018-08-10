package com.hsbc.team4.ordersystem.aop;

import com.hsbc.team4.ordersystem.common.factory.UUIDFactory;
import com.hsbc.team4.ordersystem.common.utils.LoggerUtil;
import com.hsbc.team4.ordersystem.log.ILogService;
import com.hsbc.team4.ordersystem.log.Log;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 *
 * @author chenRenXun
 * @date 2018/1/11 0011
 * log manage
 */

@Configuration
@Aspect
@Slf4j
public class SystemLogAspect {

    private final ILogService iLogService;
    private final LoggerUtil loggerUtil;
    private final UUIDFactory uuidFactory;

    @Autowired
    public SystemLogAspect(ILogService iLogService, LoggerUtil loggerUtil, UUIDFactory uuidFactory) {
        this.iLogService = iLogService;
        this.loggerUtil = loggerUtil;
        this.uuidFactory = uuidFactory;
    }

    @Pointcut("execution(* com.hsbc.team4.ordersystem..*Controller..*(..))")
    public void controllerAspect() {
    }

    /**
     * doBefore
     * @param joinPoint
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
            Log logger = loggerUtil.getLog(request);
            StringBuilder params=new StringBuilder();
            if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
                for(int i = 0; i < joinPoint.getArgs().length; ++i) {
                    params.append(joinPoint.getArgs()[i]);
                    params.append(";");
                }
            }
            try {
                log.info("=====start=====");
                log.info("method :" + joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()");
                //log.info("method  description :" + getControllerMethodDescription(joinPoint));
                log.info("operator :" + logger.getOperateName());
                log.info("operate IP:" + logger.getOperateIP());
                log.info("operate params:" + params);
                logger.setId(uuidFactory.getUUID());
                //logger.setOperationDescribe(getControllerMethodDescription(joinPoint));
                this.iLogService.insertLog(logger);
                System.out.println("=====end=====");
            } catch (Exception var7) {
                log.error("==exception==");
                log.error("exception message:{}", var7.getMessage());
            }

        }
    }

    /**
     *  getControllerMethodDescription
     * @param joinPoint
     * @return
     * @throws Exception
     */
    private static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = (method.getAnnotation(SysLog.class)).value();
                    break;
                }
            }
        }

        return description;
    }
}

