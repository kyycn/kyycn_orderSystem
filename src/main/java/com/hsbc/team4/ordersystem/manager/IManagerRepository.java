package com.hsbc.team4.ordersystem.manager;

import com.hsbc.team4.ordersystem.common.base.IBaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author:Cady
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem.manager
 * @Description:
 * @Date date: 2018-08-02
 */
@Repository
public interface IManagerRepository extends IBaseRepository<Manager,String> {
    /**
     * @Description find manager by name
     * @Date: 20:16 2018-08-02
     * @Param name
     * @return com.hsbc.team4.ordersystem.manager.Manager
     */
    Manager findByName(String name);
}
