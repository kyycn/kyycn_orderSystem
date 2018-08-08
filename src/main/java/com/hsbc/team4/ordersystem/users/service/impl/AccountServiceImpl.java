package com.hsbc.team4.ordersystem.users.service.impl;

import com.hsbc.team4.ordersystem.users.domain.Account;
import com.hsbc.team4.ordersystem.users.repository.IAccountRepository;
import com.hsbc.team4.ordersystem.users.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final IAccountRepository iAccountRepository;

    @Autowired
    public AccountServiceImpl(IAccountRepository iAccountRepository) {
        this.iAccountRepository = iAccountRepository;
    }

    @Override
    public Account addAccount(Account account) {
        return iAccountRepository.save(account);
    }

    @Override
    public int updateStatusById(String id, int status) {
        return iAccountRepository.updateStatusById(id,status);
    }

    @Override
    public Account updateAccount(Account account) {
        return iAccountRepository.saveAndFlush(account);
    }

    @Override
    public Account findByUsername(String username) {
        return iAccountRepository.findByUsername(username);
    }
}
