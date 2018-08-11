package com.hsbc.team4.ordersystem.common.base;

import org.springframework.data.domain.Page;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.common.base
 * @Description :
 * @Date : 2018/8/1
 */
public interface IBaseService<T> {
    /**
     * findByStatus
     * @param current
     * @param pageSize
     * @param status
     * @return Page<T>
     */
    Page<T> findByStatus(int current, int pageSize, int status);

    /**
     * addEntity
     * @param t t
     * @return
     *
     */
    T addEntity(T t);

    /**
     * updateStatusById
     * @param id
     * @param status
     * @return
     */
    int updateStatusById(String id,int status);
    /**
     * updateEntity
     * @param t
     * @return T
     */
    T updateEntity(T t);

    /**
     * findById
     * @param id
     * @return T
     */
    T findById(String id);


}
