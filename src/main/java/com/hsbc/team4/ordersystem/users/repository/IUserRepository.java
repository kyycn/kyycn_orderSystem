package com.hsbc.team4.ordersystem.users.repository;

import afu.org.checkerframework.checker.oigj.qual.Modifier;
import com.hsbc.team4.ordersystem.common.base.IBaseRepository;
import com.hsbc.team4.ordersystem.users.domain.User;
import org.springframework.data.jpa.repository.Query;
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

    /**
     * findByIdAndPassword
     * @param id
     * @param password
     * @return User
     */
    User findByIdAndPassword(String id,String password);

    /**
     * updatePassword
     * @param id
     * @param password
     * @return User
     */
    @Modifier
    @Query(" update User u set u.password=?2 where u.id=?1")
    int updatePassword(String id,String password);

}
