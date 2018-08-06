package com.hsbc.team4.ordersystem.users.service.impl;

import com.hsbc.team4.ordersystem.users.domain.UserInfo;
import com.hsbc.team4.ordersystem.users.repository.IUserInfoRepository;
import com.hsbc.team4.ordersystem.users.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.users
 * @Description :
 * @Date : 2018/8/5
 */
@Service
public class IUserInfoServiceImpl implements IUserInfoService{

    private final IUserInfoRepository iUserInfoRepository;
    @Autowired
    public IUserInfoServiceImpl(IUserInfoRepository iUserInfoRepository) {
        this.iUserInfoRepository = iUserInfoRepository;
    }
    /**
     *  findById
     * @param id
     * @return UserInfo
     */
    @Override
    public UserInfo findById(String id) {
        return iUserInfoRepository.findById(id).get();
    }

    @Override
    public UserInfo findByUsername(String username) {
        return iUserInfoRepository.findByUsername(username);
    }

    @Override
    public UserInfo addUserInfo(UserInfo userInfo) {
        return iUserInfoRepository.save(userInfo);
    }

    @Override
    public UserInfo updateUserInfo(UserInfo userInfo) {
        return iUserInfoRepository.saveAndFlush(userInfo);
    }
}
