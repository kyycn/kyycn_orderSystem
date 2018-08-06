package com.hsbc.team4.ordersystem.users.repository;

import com.hsbc.team4.ordersystem.users.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.users
 * @Description :
 * @Date : 2018/8/5
 */
@Repository
public interface IUserInfoRepository extends JpaRepository<UserInfo,String> {
    /**
     * findByUsername
     * @param username
     * @return  UserInfo
     */
    UserInfo findByUsername(String username);
}
