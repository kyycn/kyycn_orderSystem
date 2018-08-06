package com.hsbc.team4.ordersystem.users.controller;

import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
import com.hsbc.team4.ordersystem.smsmessage.ISendMsgService;
import com.hsbc.team4.ordersystem.smsmessage.SendMsg;
import com.hsbc.team4.ordersystem.users.domain.User;
import com.hsbc.team4.ordersystem.users.service.IUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.products.controller
 * @Description :
 * @Date : 2018/8/1
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    private final IUserService iUserService;
    private final ISendMsgService iSendMsgService;
    private final ResponseResults responseResults;

    @Autowired
    public UserController(IUserService iUserService, ISendMsgService iSendMsgService, ResponseResults responseResults) {
        this.iUserService = iUserService;
        this.iSendMsgService = iSendMsgService;
        this.responseResults = responseResults;
    }


    @ApiOperation(value = "verify phone",notes = " pass a phone param",httpMethod = "GET")
    @ApiImplicitParam(name = "phone",value = "phone",dataType="String")
    @GetMapping("/verifyPhone/{phone}")
    public ResponseResults verifyPhone(@PathVariable String phone){
        User user=iUserService.findByPhone(phone);
        if(user!=null){
            return responseResults.responseBySuccess("The phone had been register");
        }
        return responseResults.responseBySuccess("ok");
    }

    @ApiOperation(value = "verify email",notes = " pass a email param",httpMethod = "GET")
    @ApiImplicitParam(name = "email",value = "email",dataType="String")
    @GetMapping("/verifyEmail/{email}")
    public ResponseResults verifyEmail(@PathVariable String email){
        User user=iUserService.findByEail(email);
        if(user!=null){
            return responseResults.responseBySuccess("The email had been register");
        }
        return responseResults.responseBySuccess("ok");
    }

    @ApiOperation(value = "send email",notes = " pass a email param",httpMethod = "GET")
    @ApiImplicitParam(name = "phone",value = "phone",dataType="String")
    @GetMapping("/sendEmail/{email}")
    public ResponseResults sendEmail(@PathVariable String email){
        return responseResults.responseBySuccess("ok");
    }

    /**
     * sendMessage
     * @param map
     * @return
     */
    @ApiOperation(value = "sends verify message",notes = " pass a map",httpMethod = "POST")
    @ApiImplicitParam(name = "phone",value = "phone",dataType="String")
    @PostMapping("/sendMessage")
    public ResponseResults sendMessage(@RequestBody Map<String,String> map){
        SendMsg sendMsg=iUserService.sendMessage(map);
        if(sendMsg!=null){
            return responseResults.responseBySuccess("ok",sendMsg);
        }
        return responseResults.responseByErrorMessage("overtime  Please re-send");
    }

    /**
     * submit verify code
     * @param verifyCode  verifyCode
     * @return String
     */
    @ApiOperation(value = "submit verify message",notes = " pass a msgId and verifyCode",httpMethod = "POST")
    @ApiImplicitParam(name = "phone",value = "phone",dataType="String")
    @GetMapping("/{msgId}/{verifyCode}")
    public ResponseResults submitVerifyCode(@PathVariable String msgId,@PathVariable String verifyCode){
        return responseResults.responseBySuccess(iUserService.verifyCode(msgId,verifyCode));
    }

    /**
     * register
     * @param user
     * @return String
     */
    @ApiOperation(value = "register",notes = "pass username and password",httpMethod = "POST")
    @ApiImplicitParam(name = "user",value = "The user message",dataType="User")
    @PostMapping("/register")
    public ResponseResults register(@RequestBody User user){
        return  responseResults.responseBySuccess("保存成功",iUserService.register(user));
    }

    @ApiOperation(value = "login",notes = "pass username and password",httpMethod = "POST")
    @ApiImplicitParam(name = "user",value = "The user message",dataType="String")
    @PostMapping("/login")
    public ResponseResults login(@ApiParam(required = true,name = "user",value = "login user") @RequestBody User user, HttpServletRequest request){
        try {
            Map<String,Object> resultMap=new HashMap<>();
            final String token = iUserService.login(user.getUsername(),user.getPassword(),request);
            resultMap.put("token",token);
            if(token!=null){
                User user1=iUserService.findByUsername(user.getUsername());
                user1.setLastLoginTime(System.currentTimeMillis());
                User user2=iUserService.updateEntity(user1);
                resultMap.put("user",user2);
                return responseResults.responseBySuccess("succeed",resultMap);
            }
            return responseResults.responseByErrorMessage("The password is not correct，please enter again");
        }catch (Exception e){
            log.warn(e.getMessage());
            return responseResults.responseByErrorMessage("The username or password is not correct,please enter again");
        }
    }


    @ApiOperation(value = "refresh",notes = "pass the oldToken",httpMethod = "POST")
    @ApiImplicitParam(name = "oldToken",value = "The oldToken message",dataType="String")
    @PostMapping("/")
    public String refresh(String oldToken){
        return "ok";
    }



}
