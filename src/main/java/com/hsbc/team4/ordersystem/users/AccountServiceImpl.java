package com.hsbc.team4.ordersystem.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class AccountServiceImpl implements IAccountService,UserDetailsService {

    private final IAccountRepository iAccountRepository;

    @Autowired
    public AccountServiceImpl(IAccountRepository iAccountRepository) {
        this.iAccountRepository = iAccountRepository;
    }

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


    @Override
    public String register(User user) {
        return null;
    }

    @Override
    public String login(String username, String password) {
        return null;
    }

    @Override
    public String refresh(String oldToken) {
        return null;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
