package com.hsbc.team4.ordersystem.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.exception
 * @Description :
 * @Date : 2018/8/12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ValidateFiledException  extends RuntimeException {
    private static final long serialVersionUID = -6925278824391495118L;

    private Map<String,Object> map=new HashMap<>();
    /**
     *  ValidateFiledException
     * @param message
     */
    public ValidateFiledException(String message,Map<String,Object> map){
        super(message);
        this.map=map;
    }


}
