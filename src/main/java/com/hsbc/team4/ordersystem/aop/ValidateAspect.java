package com.hsbc.team4.ordersystem.aop;

import com.google.common.collect.Lists;
import com.hsbc.team4.ordersystem.aop.annotations.ValidateFiled;
import com.hsbc.team4.ordersystem.aop.annotations.ValidateGroup;
import com.hsbc.team4.ordersystem.exception.ValidateFiledException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class ValidateAspect {

    /**
     *  validateGroupAround
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @SuppressWarnings({ "finally", "rawtypes" })
    @Around("execution(* com.hsbc.team4.ordersystem..*Controller..*(..))")
    public void validateGroupAround(ProceedingJoinPoint joinPoint) throws Throwable  {
        Object[] args= joinPoint.getArgs();
        Object target= joinPoint.getTarget();
        String methodName= joinPoint.getSignature().getName();
        Method method= getMethodByClassAndName(target.getClass(), methodName);
        ValidateGroup validateGroup = (ValidateGroup)getAnnotationByMethod(method ,ValidateGroup.class );
        if(validateGroup!=null){
            Map<String,Object> map= validateFiled(Lists.newArrayList(validateGroup.fileds()) , args);
            if(!CollectionUtils.isEmpty(map)){
                throw new ValidateFiledException("The params validate failure,please check you params",map);
            }
        }else {
            ValidateFiled validateFiled = (ValidateFiled)getAnnotationByMethod(method ,ValidateFiled.class );
            if(validateFiled!=null){
                Map<String,Object> map= validateFiled(Lists.newArrayList(validateFiled) , args);
                if(!CollectionUtils.isEmpty(map)){
                    throw new ValidateFiledException("The params validate failure,please check you params",map);
                }
            }
        }

    }

    /**
     * validateFiled
     * @param valiedatefiles
     * @param args
     * @return
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public Map<String,Object> validateFiled(List<ValidateFiled> valiedatefiles , Object[] args) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Map<String,Object> errors=new HashMap<>();
        for (ValidateFiled validateFiled : valiedatefiles) {
            Object object=args[validateFiled.index()];
            //notNull
            if(validateFiled.notNull()){
                if(object == null ) {
                    errors.put("msg",validateFiled.message());
                }else {
                    //maxLen
                    if(validateFiled.maxLen() > 0){
                        if(((String)object).length() > validateFiled.maxLen()) {
                            errors.put("maxLength",validateFiled.maxLen());
                        }
                    }
                    //minLen
                    if(validateFiled.minLen() > 0){
                        if(((String)object).length() < validateFiled.minLen()) {
                            errors.put("minLength",validateFiled.minLen());
                        }
                    }
                    //maxVal
                    if(validateFiled.maxVal() != -1){
                        if( (int)object > validateFiled.maxVal()) {
                            errors.put("maxValue",validateFiled.maxVal());
                        }
                    }
                    //minVal
                    if(validateFiled.minVal() != -1){
                        if((int)object < validateFiled.minVal()) {
                            errors.put("minLength",validateFiled.minVal());
                        }
                    }
                    //regStr
                    if(!"".equals(validateFiled.regStr())){
                        if(object instanceof String){
                            if(!((String)object).matches(validateFiled.regStr())) {
                                errors.put("regularStr","regular is not match");
                            }
                        }else{
                            errors.put("matchMessage","the param is not a String ");
                        }
                    }
                }
            }
        }
        return errors;
    }

    /**
     * getAnnotationByMethod
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



}
