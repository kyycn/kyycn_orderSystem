package com.hsbc.team4.ordersystem.users;

import com.hsbc.team4.ordersystem.common.base.IBaseService;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.users
 * @Description :
 * @Date : 2018/8/3
 */
public interface IAccountService extends IBaseService<Account>{
    /**
     * register
     * @param user user object
     * @return string
     */
    String register(User user);

    /**
     * login
     * @param username username
     * @param password password
     * @return String
     */
    String login(String username,String password);

    /**
     * oldToken
     * @param oldToken oldToken
     * @return new newToken
     */
    String refresh(String oldToken);

}
