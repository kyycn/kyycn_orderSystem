package com.hsbc.team4.ordersystem.users.service.impl;

import com.hsbc.team4.ordersystem.common.factory.UUIDFactory;
import com.hsbc.team4.ordersystem.common.utils.PageableTools;
import com.hsbc.team4.ordersystem.common.utils.ValidatorTools;
import com.hsbc.team4.ordersystem.jwt.JwtTokenGenerator;
import com.hsbc.team4.ordersystem.roles.IRoleRepository;
import com.hsbc.team4.ordersystem.roles.Role;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
public class UserServiceImpl implements IUserService,UserDetailsService {

    private final IUserRepository iUserRepository;
    private final ISenderRepository iSenderRepository;
    private final UUIDFactory uuidFactory;
    private final ISendMsgService iSendMsgService;
    private final IUserInfoRepository iUserInfoRepository;
    private final IAccountRepository iAccountRepository;
    private final IRoleRepository iRoleRepository;
    private final JwtTokenGenerator jwtTokenGenerator;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImpl(IUserRepository iUserRepository, ISenderRepository iSenderRepository, UUIDFactory uuidFactory, ISendMsgService iSendMsgService, IUserInfoRepository iUserInfoRepository, IAccountRepository iAccountRepository, IRoleRepository iRoleRepository, JwtTokenGenerator jwtTokenGenerator) {
        this.iUserRepository = iUserRepository;
        this.iSenderRepository = iSenderRepository;
        this.uuidFactory = uuidFactory;
        this.iSendMsgService = iSendMsgService;
        this.iUserInfoRepository = iUserInfoRepository;
        this.iAccountRepository = iAccountRepository;
        this.iRoleRepository = iRoleRepository;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @Override
    public User register(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setId(uuidFactory.getUUID());
        user.setPassword(encoder.encode(user.getPassword()));
        Role role=new Role();
        role.setId(uuidFactory.getUUID());
        role.setRoleName("user");
        List<Role> roles=new ArrayList<>();
        roles.add(role);
        List<Role> roleList=iRoleRepository.saveAll(roles);
        user.setRoles(roleList);
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
            account.setRealName(user.getUsername());
            account.setTradePassword(user.getPassword());
            account.setUsableBalance(new BigDecimal(0.00));
            account.setFreezeBalance(new BigDecimal(0.00));
            iAccountRepository.save(account);
        }
        return user1;
    }

    @Override
    public String login(String username, String password,HttpServletRequest request) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //将用户名和密码封装到一个UsernamePasswordAuthenticationToken对象中
        UserDetails userDetails = loadUserByUsername(username);
        if(!passwordEncoder.matches(password.trim(),userDetails.getPassword())){
            return null;
        }
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),password,userDetails.getAuthorities());
        // Perform the security
        upToken.setDetails(new WebAuthenticationDetails(request));
        //对封装到UsernamePasswordAuthenticationToken中的用户名和密码进行校验
        Authentication authentication = authenticationManager.authenticate(upToken);
        if (!authentication.isAuthenticated())
        {
            log.error("用户名或密码错误");
            throw new BadCredentialsException("用户名或密码错误");
        }
        HttpSession session = request.getSession();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
        //  load token
        return jwtTokenGenerator.generateToken(userDetails);
    }

    @Override
    public String refresh(String oldToken) {
        return null;
    }

    @Override
    public SendMsg sendMessage(Map<String, String> map) {
        SendMsg sendMsg;
        String phone=map.get("phone");
        String msgType=map.get("msgType");
        String bizType=map.get("bizType");
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
    public String checkVerifyCode(Map<String,String> map) {
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        SendMsg sendMsg=iSendMsgService.findById(map.get("msgId"));
        long time=sendMsg.getCreateTime()+Long.parseLong(sendMsg.getExpirationTime())*60*1000;
        if(System.currentTimeMillis()<=time){
            if(bCryptPasswordEncoder.matches(map.get("verifyCode"),sendMsg.getCode())){
                return "you enter the verifyCode is correct";
            }
            return "you enter the code is verifyCode correct";
        }

        return "The verifyCode was expired";
    }

    @Override
    public User findByPhone(String phone) {
        return iUserRepository.findByPhone(phone);
    }

    @Override
    public User findByEmail(String email) {
        return iUserRepository.findByEmail(email);
    }

    @Override
    public User findByUsername(String userName) {
        return iUserRepository.findByUsername(userName);
    }

    @Override
    public String updatePassword(Map<String,String> map) {
        String id=map.get("id");
        String oldPassword=map.get("oldPassword");
        String newPassword=map.get("newPassword");
        User user=iUserRepository.findByIdAndPassword(id,oldPassword);
        if(user!=null){
            int row = iUserRepository.updatePassword(id, newPassword);
            if(row>0){
                return "ok";
            }
        }
        return "The original password is not correct";
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



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user;
        if(ValidatorTools.isUsername(s)){
            user=this.findByUsername(s);
        }else if(ValidatorTools.isEmail(s)){
            user=this.findByEmail(s);
        }else if (ValidatorTools.isMobile(s)){
            user=this.findByPhone(s);
        }else {
            throw new UsernameNotFoundException("The user format is not correct");
        }
        if(user==null){
            throw new UsernameNotFoundException("The user is not found");
        }
        log.info("the user info",user);
        String token= jwtTokenGenerator.generateToken(user);
        System.out.println(token);
        return new User(user);
    }

}
