package com.hsbc.team4.ordersystem.common.utils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.*;

/**
 * @author:Kevin
 * @version:
 * @Project: permission_manage
 * @Package: com.hsbc.dev.teamo4.sms.common.utils.data
 * @Description: BeanValidator
 * @Date date: 2018/7/28
 */
@Slf4j
public class BeanValidator {
    private static javax.validation.ValidatorFactory ValidatorFactory= Validation.buildDefaultValidatorFactory();

    /**
     * get Validator
     * @return Validator
     */
    private static Validator getValidator(){
        return ValidatorFactory.getValidator();
    }

    /**
     * validate
     * @param t
     * @param groups
     * @param <T>
     * @return Map<String,String>
     */
    private static <T> Map<String,String> validate(T t, Class... groups){
        Validator validator=getValidator();
        Set validateResult=validator.validate(t,groups);
        if(validateResult.isEmpty()){
            return Collections.emptyMap();
        }else {
            LinkedHashMap<String,String> errors= Maps.newLinkedHashMap();
            for (Object aValidateResult : validateResult) {
                ConstraintViolation violation = (ConstraintViolation) aValidateResult;
                log.info(violation.getPropertyPath().toString() + "-->" + violation.getMessage() + "\n");
                errors.put(violation.getPropertyPath().toString(), violation.getMessage());
            }
            return errors;
        }
    }

    /**
     * validateList
     * @param collection
     * @return Map<String,String>
     */
    private static Map<String,String>  validateList(Collection<?> collection){
        Preconditions.checkNotNull(collection);
        Iterator iterator=collection.iterator();
        Map<String,String> errors;
        do {
            if(!iterator.hasNext()){
                return Collections.emptyMap();
            }
            Object object=iterator.next();
            errors=validate(object);
        }while (errors.isEmpty());
        return errors;
    }

    /**
     * validateObject
     * @param first
     * @param objects
     * @return Map<String,String>
     */
    public static Map<String,String> validateObject(Object first,Object... objects){
        if(objects!=null&&objects.length>0){
            return validateList(Lists.asList(first,objects));
        }else {
            return validate(first);
        }
    }


}
