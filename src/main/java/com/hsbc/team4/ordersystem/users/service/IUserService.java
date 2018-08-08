package com.hsbc.team4.ordersystem.users.service;

import com.hsbc.team4.ordersystem.common.base.IBaseService;
import com.hsbc.team4.ordersystem.smsmessage.SendMsg;
import com.hsbc.team4.ordersystem.users.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.products.service
 * @Description :
 * @Date : 2018/8/1
 */
public interface IUserService extends IBaseService<User>{
    /**
     * register
     * @param user user object
     * @return string
     */
    User register(User user);

    /**
     * login
     * @param username username
     * @param password password
     * @return String
     */
    String login(String username,String password,HttpServletRequest request);

    /**
     * oldToken
     * @param oldToken oldToken
     * @return new newToken
     */
    String refresh(String oldToken);

    /**
     *  sendMessage
     * @param map
     * @return  String
     */
    SendMsg sendMessage(Map<String,String> map);

    /**
     *  sendSMS
     * @param phone
     * @param msgType
     * @param bizType
     * @return
     */
    SendMsg sendSMS(String phone, String msgType, String bizType);

    /**
     *  sendVoice
     * @param phone
     * @param msgType
     * @param bizType
     * @return
     */
    SendMsg sendVoice(String phone,String msgType,String bizType);

    /**
     * ckeckVerifyCode
     * @param map
     * @return String
     */
    String checkVerifyCode(Map<String,String> map);

    /**
     *  findByPhone
     * @param phone
     * @return
     */
    User findByPhone(String phone);

    /**
     *  findByEail
     * @param email
     * @return User
     */
    User findByEmail(String email);

    /**
     *  findByUsername
     * @param userName
     * @return User
     */
    User findByUsername(String userName);

    /**
     * updatePassword
     * massage
     * @param map
     * @return  massage
     */
    String updatePassword(Map<String,String> map);

}
