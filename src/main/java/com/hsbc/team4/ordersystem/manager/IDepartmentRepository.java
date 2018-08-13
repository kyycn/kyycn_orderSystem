package com.hsbc.team4.ordersystem.manager;

import com.hsbc.team4.ordersystem.common.base.IBaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Cady
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem.manager
 * @Description:
 * @Date date: 2018-08-13
 */
@Repository
public interface IDepartmentRepository extends IBaseRepository<Department, String> {
    /**
     * @Description findByName
     * @Date: 14:53 2018-08-13
     * @Param name
     * @return com.hsbc.team4.ordersystem.manager.Department
     */
    Department findByName(String name);
}
