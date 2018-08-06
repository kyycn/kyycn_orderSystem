package com.hsbc.team4.ordersystem.common.adapt;

import org.springframework.beans.BeanUtils;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.common.adapt
 * @Description :
 * @Date : 2018/8/3
 */
public class BeanAdapter{

    /**
     * dtoAdapter
     * @param dtoObject
     * @param daoObject
     * @return
     */
    public Object dtoAdapter(Object dtoObject, Object daoObject){
        BeanUtils.copyProperties(dtoObject,daoObject);
        return daoObject;
    }

    /**
     * daoAdapter
     * @param daoObject
     * @param dtoObject
     * @return
     */
    public Object daoAdapter(Object daoObject, Object dtoObject){
        BeanUtils.copyProperties(daoObject,dtoObject);
        return dtoObject;
    }

}
