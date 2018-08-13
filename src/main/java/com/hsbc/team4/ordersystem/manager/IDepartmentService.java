package com.hsbc.team4.ordersystem.manager;

import com.hsbc.team4.ordersystem.common.base.IBaseService;

/**
 * @author: Cady
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem.manager
 * @Description:
 * @Date date: 2018-08-13
 */
public interface IDepartmentService extends IBaseService<Department> {
    /**
     * @Description findByName
     * @Date: 14:54 2018-08-13
     * @Param name
     * @return com.hsbc.team4.ordersystem.manager.Department
     */
    Department findByName(String name);
}
