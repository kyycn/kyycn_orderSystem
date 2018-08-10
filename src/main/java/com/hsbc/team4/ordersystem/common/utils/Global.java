package com.hsbc.team4.ordersystem.common.utils;

import com.hsbc.team4.ordersystem.users.domain.User;
import com.hsbc.team4.ordersystem.users.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.common.utils
 * @Description :
 * @Date : 2018/8/9
 */
@Component
public class Global {

    private final IUserRepository iUserRepository;

    @Autowired
    public Global(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    /**
     * get User by Token
     * @return User
     */
    public User getUserByToken(){
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName;
        if(user==null){
            return null;
        }
        if (user instanceof UserDetails) {
            userName= ((UserDetails)user).getUsername();
        } else {
            userName= (user.toString());
        }
        return iUserRepository.findByUsername(userName);
    }
}
