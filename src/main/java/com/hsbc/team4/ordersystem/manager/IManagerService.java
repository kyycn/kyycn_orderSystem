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
     * @Description find manager by name
     * @Date: 20:17 2018-08-02
     * @Param name
     * @return com.hsbc.team4.ordersystem.manager.Manager
     */
    Manager findByName(String name);
}
