package com.hsbc.team4.ordersystem.common.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.common.utils
 * @Description :
 * @Date : 2018/8/13
 */
public class ReflectUtils {

    /**
     *  getMethodByClassAndName
     * @param c
     * @param methodName
     * @return  Method
     */
    public static Method getMethodByClassAndName(Class c , String methodName){
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            if(method.getName().equals(methodName)){
                return method ;
            }
        }
        return null;
    }

    /**
     * getAnnotationByMethod
     * @param method
     * @param annoClass
     * @return Annotation
     */
    public static Annotation getAnnotationByMethod(Method method , Class annoClass){
        Annotation[] all = method.getAnnotations();
        for (Annotation annotation : all) {
            if (annotation.annotationType() == annoClass) {
                return annotation;
            }
        }
        return null;
    }
}
