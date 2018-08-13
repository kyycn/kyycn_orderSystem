package com.hsbc.team4.ordersystem.users.service;

import com.hsbc.team4.ordersystem.users.domain.Account;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.users
 * @Description :
 * @Date : 2018/8/3
 */
public interface IAccountService {
    /**
     * addAccount
     * @param account
     * @return  Account
     */
    Account addAccount(Account account);

    /**
     *  updateAccount
     * @param account
     * @return  Account
     */
    Account updateAccount(Account account);

    /**
     *  findByUsername
     * @param id
     * @return Account
     */
    Account findByUsername(String id);

    /**
     *  updateStatusById
     * @param id
     * @param status
     * @return int
     */
    int updateStatusById(String id, int status);

}
