package com.hsbc.team4.ordersystem.users.service.impl;

import com.hsbc.team4.ordersystem.common.factory.UUIDFactory;
import com.hsbc.team4.ordersystem.common.utils.PageableTools;
import com.hsbc.team4.ordersystem.smsmessage.ISendMsgService;
import com.hsbc.team4.ordersystem.smsmessage.ISenderRepository;
import com.hsbc.team4.ordersystem.smsmessage.SendMsg;
import com.hsbc.team4.ordersystem.smsmessage.Sender;
import com.hsbc.team4.ordersystem.users.domain.Account;
import com.hsbc.team4.ordersystem.users.domain.User;
import com.hsbc.team4.ordersystem.users.domain.UserInfo;
import com.hsbc.team4.ordersystem.users.repository.IAccountRepository;
import com.hsbc.team4.ordersystem.users.repository.IUserInfoRepository;
import com.hsbc.team4.ordersystem.users.repository.IUserRepository;
import com.hsbc.team4.ordersystem.users.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
@Service
@Slf4j
public class UserServiceImpl implements IUserService{

    private final IUserRepository iUserRepository;
    private final ISenderRepository iSenderRepository;
    private final UUIDFactory uuidFactory;
    private final ISendMsgService iSendMsgService;
    private final IUserInfoRepository iUserInfoRepository;
    private final IAccountRepository iAccountRepository;

    @Autowired
    public UserServiceImpl(IUserRepository iUserRepository, ISenderRepository iSenderRepository, UUIDFactory uuidFactory, ISendMsgService iSendMsgService, IUserInfoRepository iUserInfoRepository, IAccountRepository iAccountRepository) {
        this.iUserRepository = iUserRepository;
        this.iSenderRepository = iSenderRepository;
        this.uuidFactory = uuidFactory;
        this.iSendMsgService = iSendMsgService;
        this.iUserInfoRepository = iUserInfoRepository;
        this.iAccountRepository = iAccountRepository;
    }

    @Override
    public User register(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setId(uuidFactory.getUUID());
        user.setPassword(encoder.encode(user.getPassword()));
        User user1=addEntity(user);
        if(user1!=null){
            UserInfo userInfo=new UserInfo();
            userInfo.setId(uuidFactory.getUUID());
            userInfo.setUsername(user.getUsername());
            userInfo.setPhone(user.getPhone());
            userInfo.setEmail(user.getEmail());
            iUserInfoRepository.save(userInfo);
            Account account=new Account();
            account.setId(uuidFactory.getUUID());
            account.setUsername(user.getUsername());
            iAccountRepository.save(account);
        }
        return user1;
    }

    @Override
    public String login(String username, String password,HttpServletRequest request) {
      return "ok";
    }

    @Override
    public String refresh(String oldToken) {
        return null;
    }

    @Override
    public SendMsg sendMessage(Map<String, String> map) {
        SendMsg sendMsg;
        String msgType=map.get("msgType");
        String bizType=map.get("bizType");
        String phone=map.get("phone");
        if("SMS".equals(msgType)){
            sendMsg=sendSMS(phone,msgType,bizType);
        }else {
            sendMsg=sendVoice(phone,msgType,bizType);
        }
        return sendMsg;
    }

    @Override
    public SendMsg sendSMS(String phone,String msgType,String bizType) {
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        Sender sender=iSenderRepository.findByTemplateTypeAndStatus(bizType,0);
        SendMsg sendMsg=new SendMsg();
        sendMsg.setId(uuidFactory.getUUID());
        sendMsg.setMsgType(msgType);
        sendMsg.setBizType(bizType);
        sendMsg.setPhone(phone);
        sendMsg.setSender(sender.getPhone());
        sendMsg.setExpirationTime("5");
        sendMsg.setCreateTime(System.currentTimeMillis());
        String code=iSendMsgService.execute(sender,sendMsg);
        sendMsg.setCode(bCryptPasswordEncoder.encode(code));
        return iSendMsgService.addEntity(sendMsg)!=null?sendMsg:null;
    }

    @Override
    public SendMsg sendVoice(String phone, String msgType, String bizType) {
        return null;
    }

    @Override
    public String verifyCode(String msgId, String code) {
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        SendMsg sendMsg=iSendMsgService.findById(msgId);
        long time=sendMsg.getCreateTime()+Long.parseLong(sendMsg.getExpirationTime())*60*1000;
        if(System.currentTimeMillis()<=time){
            if(bCryptPasswordEncoder.matches(code,sendMsg.getCode())){
                return "you enter the code is correct";
            }
            return "you enter the code is not correct";
        }

        return "The verify was expired";
    }

    @Override
    public User findByPhone(String phone) {
        return iUserRepository.findByPhone(phone);
    }

    @Override
    public User findByEail(String email) {
        return iUserRepository.findByEmail(email);
    }

    @Override
    public User findByUsername(String userName) {
        return iUserRepository.findByUsername(userName);
    }


    @Override
    public Page<User> findByStatus(int current, int pageSize, int status) {
        Pageable pageable= PageableTools.addTimeSortForDescAndPage(current,pageSize);
        return iUserRepository.findByStatus(status,pageable);
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
