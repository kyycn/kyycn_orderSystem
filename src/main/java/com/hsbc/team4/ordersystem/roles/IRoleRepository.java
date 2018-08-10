package com.hsbc.team4.ordersystem.roles;

import com.hsbc.team4.ordersystem.common.base.IBaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.roles
 * @Description :
 * @Date : 2018/8/2
 */
@Repository
public interface IRoleRepository  extends IBaseRepository<Role,String>{
    /**
     * findByRoleNameContains
     * @param roleName
     * @param status
     * @param pageable
     * @return
     */
    Page<Role> findByRoleNameContainsAndStatus(String roleName,int status, Pageable pageable);

}
