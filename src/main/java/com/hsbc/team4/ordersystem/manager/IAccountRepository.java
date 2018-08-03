package com.hsbc.team4.ordersystem.manager;

import com.hsbc.team4.ordersystem.common.base.IBaseRepository;

/**
 * @author:Cady
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem.manager
 * @Description:
 * @Date date: 2018-08-03
 */
public interface IAccountRepository extends IBaseRepository<Account,String> {

    /**
     * @Description //TODO
     * @Date: 12:44 2018-08-03
     * @Param name
     * @return com.hsbc.team4.ordersystem.manager.Account
     */
    Account findByName(String name);
}
