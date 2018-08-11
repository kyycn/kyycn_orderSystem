package com.hsbc.team4.ordersystem.manager;

import com.hsbc.team4.ordersystem.common.base.IBaseService;

/**
 * @author:Cady
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem.manager
 * @Description:
 * @Date date: 2018-08-02
 */
public interface IManagerService extends IBaseService<Manager> {


    /**
     * findByWordNumber
     * @param wordNumber
     * @return
     */
    Manager findByWordNumber(String wordNumber);
}
