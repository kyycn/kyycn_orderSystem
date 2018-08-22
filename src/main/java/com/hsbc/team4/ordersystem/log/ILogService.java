package com.hsbc.team4.ordersystem.log;

import org.springframework.data.domain.Page;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.log
 * @Description :
 * @Date : 2018/8/6
 */
public interface ILogService {
    /**
     * insertLog
     * @param log
     * @return  Log
     */
    Log insertLog(Log log);
    /**
     *  findByOperateTypeContains
     * @param operateType
     * @param current
     * @param pageSize
     * @return
     */
    Page<Log> findByOperateTypeContains(String operateType,int current,int pageSize);

    /**
     * findAll
     * @param current
     * @param pageSize
     * @return
     */
    Page<Log> findAll(int current,int pageSize);

}
