package com.hsbc.team4.ordersystem.users.service;

import com.hsbc.team4.ordersystem.users.domain.UserInfo;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.users
 * @Description :
 * @Date : 2018/8/5
 */
public interface IUserInfoService {
    /**
     *  findByUsername
     * @param username
     * @return  UserInfo
     */
    UserInfo findByUsername(String username);

    /**
     * addEntity
     * @param userInfo
     * @return UserInfo
     */
    UserInfo addUserInfo(UserInfo userInfo);

    /**
     *  updateUserInfo
     * @param userInfo
     * @return UserInfo
     */
    UserInfo updateUserInfo(UserInfo userInfo);

    /**
     *  findById
     * @param id
     * @return UserInfo
     */
    UserInfo findById(String id);
}
