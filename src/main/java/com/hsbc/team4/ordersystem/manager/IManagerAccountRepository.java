package com.hsbc.team4.ordersystem.manager;

import com.hsbc.team4.ordersystem.common.base.IBaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author:Cady
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem.manager
 * @Description:
 * @Date date: 2018-08-03
 */
@Repository
public interface IManagerAccountRepository extends IBaseRepository<ManagerAccount,String> {

    /**
     * @Description //TODO
     * @Date: 12:44 2018-08-03
     * @Param name
     * @return com.hsbc.team4.ordersystem.manager.ManagerAccount
     */
    ManagerAccount findByName(String name);
}
