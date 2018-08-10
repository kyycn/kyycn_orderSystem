package com.hsbc.team4.ordersystem.roles;

import com.hsbc.team4.ordersystem.common.base.IBaseService;
import org.springframework.data.domain.Page;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.roles
 * @Description :
 * @Date : 2018/8/2
 */
public interface IRoleService extends IBaseService<Role>{
    /**
     *  findRoleLikeRoleName
     * @param roleName
     * @param status
     * @param current
     * @param pageSize
     * @return
     */
    Page<Role> findRoleLikeRoleName(String roleName,int status,int current,int pageSize);
}
