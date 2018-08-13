package com.hsbc.team4.ordersystem.aop;

import com.hsbc.team4.ordersystem.aop.annotations.HasLogin;
import com.hsbc.team4.ordersystem.aop.annotations.SysLog;
import com.hsbc.team4.ordersystem.common.factory.UUIDFactory;
import com.hsbc.team4.ordersystem.common.utils.Global;
import com.hsbc.team4.ordersystem.common.utils.LoggerUtil;
import com.hsbc.team4.ordersystem.common.utils.ReflectUtils;
import com.hsbc.team4.ordersystem.exception.UserNotLoginException;
import com.hsbc.team4.ordersystem.log.ILogService;
import com.hsbc.team4.ordersystem.log.Log;
import com.hsbc.team4.ordersystem.users.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
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
    private final Global global;

    @Autowired
    public SystemLogAspect(ILogService iLogService, LoggerUtil loggerUtil, UUIDFactory uuidFactory, Global global) {
        this.iLogService = iLogService;
        this.loggerUtil = loggerUtil;
        this.uuidFactory = uuidFactory;
        this.global = global;
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
            log.info("=====request start=====");
            log.info("method :" + joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()");
            log.info("method  description :" + getControllerMethodDescription(joinPoint));
            log.info("operator :" + logger.getOperateName());
            log.info("operate IP:" + logger.getOperateIP());
            log.info("operate params:" + params);
            logger.setId(uuidFactory.getUUID());
            logger.setOperationDescribe(getControllerMethodDescription(joinPoint));
            this.iLogService.insertLog(logger);
            System.out.println("=====end=====");
        }
    }

    /**
     *  getControllerMethodDescription
     * @param joinPoint
     * @return
     * @throws Exception
     */
    private static String getControllerMethodDescription(JoinPoint joinPoint) {
        String description="";
        Object target=joinPoint.getTarget();
        String methodName=joinPoint.getSignature().getName();
        Method method= ReflectUtils.getMethodByClassAndName(target.getClass(), methodName);
        SysLog sysLog = (SysLog)ReflectUtils.getAnnotationByMethod(method ,SysLog.class );
        if(sysLog!=null) {
            description=sysLog.value();
        }
        return description;
    }
}

