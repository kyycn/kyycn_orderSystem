package com.hsbc.team4.ordersystem.users.repository;

import com.hsbc.team4.ordersystem.common.base.IBaseRepository;
import com.hsbc.team4.ordersystem.users.domain.User;
import org.springframework.stereotype.Repository;

/**
 * @author : Kevin
 * @version :
 * @Project :ordersystem
 * @Package : com.hsbc.team4.ordersystem.products.repository
 * @Description :
 * @Date : 2018/8/1
 */
@Repository
public interface IUserRepository extends IBaseRepository<User,String> {
    /**
     * findByPhone
     * @param phone
     * @return User
     */
    User findByPhone(String phone);

    /**
     *  findByEmail
     * @param email
     * @return  User
     */
    User findByEmail(String email);

    /**
     *  findByUsername
     * @param username
     * @return User
     */
    User findByUsername(String username);
}
