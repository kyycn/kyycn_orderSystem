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
     * @Description findByWorkNumber
     * @Date: 23:07 2018-08-06
     * @Param workNumber
     * @return com.hsbc.team4.ordersystem.manager.Manager
     */
    Manager findByWorkNumber(String workNumber);
}
