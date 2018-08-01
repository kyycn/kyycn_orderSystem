package com.hsbc.team4.ordersystem.service;

import com.hsbc.team4.ordersystem.dao.IUserRepository;
import com.hsbc.team4.ordersystem.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.service
 * @Description :
 * @Date : 2018/8/1
 */
@Service
public class UserServiceImpl implements IUserService{

    private final IUserRepository iUserRepository;

    @Autowired
    public UserServiceImpl(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public Page<User> findByStatus(int current, int pageSize, int status) {
        return null;
    }

    @Override
    public User addEntity(User user) {
        return iUserRepository.save(user);
    }

    @Override
    public int updateStatusById(String id, int status) {
        return iUserRepository.updateStatusById(id,status);
    }

    @Override
    public User updateEntity(User user) {
        return iUserRepository.saveAndFlush(user);
    }

    @Override
    public User findById(String id) {
        return iUserRepository.findByEntityId(id);
    }


}
