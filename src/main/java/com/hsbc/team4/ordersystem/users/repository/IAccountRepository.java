package com.hsbc.team4.ordersystem.users.repository;

import com.hsbc.team4.ordersystem.common.base.IBaseRepository;
import com.hsbc.team4.ordersystem.users.domain.Account;
import org.springframework.stereotype.Repository;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.users
 * @Description :
 * @Date : 2018/8/3
 */
@Repository
public interface IAccountRepository extends IBaseRepository<Account,String> {
    /**
     *  findByUsername
     * @param username
     * @return Account
     */
    Account findByUsername(String username);

    /**
     * realName
     * @param realName
     * @return  Account
     */
    Account findByRealName(String realName);
}
