package com.hsbc.team4.ordersystem.users.service.impl;

import com.hsbc.team4.ordersystem.users.domain.Account;
import com.hsbc.team4.ordersystem.users.service.IAccountService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.users
 * @Description :
 * @Date : 2018/8/3
 */
@Service
public class AccountServiceImpl implements IAccountService {

    @Override
    public Page<Account> findByStatus(int current, int pageSize, int status) {
        return null;
    }

    @Override
    public Account addEntity(Account account) {
        return null;
    }

    @Override
    public int updateStatusById(String id, int status) {
        return 0;
    }

    @Override
    public Account updateEntity(Account account) {
        return null;
    }

    @Override
    public Account findById(String id) {
        return null;
    }
}
