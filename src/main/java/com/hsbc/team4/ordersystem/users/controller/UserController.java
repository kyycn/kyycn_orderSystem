package com.hsbc.team4.ordersystem.users.controller;

import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
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
 * @version : 1.0
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.products.controller
 * @Description : The is a UserController
 * @Date : 2018/8/1
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    private final IUserService iUserService;
    private final ResponseResults responseResults;

    @Autowired
    public UserController(IUserService iUserService,ResponseResults responseResults) {
        this.iUserService = iUserService;
        this.responseResults = responseResults;
    }

    /**
     *  phone
     * @param phone  phone
     * @return ResponseResults
     */
    @ApiOperation(value = "verify phone",notes = " pass a phone param",httpMethod = "GET",response = ResponseResults.class)
    @ApiImplicitParam(name = "phone",value = "phone",dataType="String")
    @GetMapping("/verifyPhone")
    public ResponseResults verifyPhone(@RequestParam String phone){
        User user=iUserService.findByPhone(phone);
        if(user!=null){
            return responseResults.responseBySuccess("The phone had been register");
        }
        return responseResults.responseBySuccess("ok");
    }

    /**
     * sendMessage
     * @param map The map is must including phone,msgType and bizType
     * @return ResponseResults
     */
    @ApiOperation(value = "sends verify message",notes = " pass a map",httpMethod = "POST",response = ResponseResults.class)
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
     *  verifyEmail
     * @param email  email
     * @return ResponseResults
     */
    @ApiOperation(value = "verify email",notes = " pass a email param",httpMethod = "GET",response = ResponseResults.class)
    @ApiImplicitParam(name = "email",value = "email",dataType="String")
    @GetMapping("/verifyEmail")
    public ResponseResults verifyEmail(@RequestParam String email){
        User user=iUserService.findByEmail(email);
        if(user!=null){
            return responseResults.responseBySuccess("The email had been register");
        }
        return responseResults.responseBySuccess("ok");
    }

    /**
     *  sendEmail
     * @param email  email
     * @return ResponseResults
     */
    @ApiOperation(value = "send email",notes = " pass a email param",httpMethod = "GET",response = ResponseResults.class)
    @ApiImplicitParam(name = "phone",value = "phone",dataType="String")
    @GetMapping("/sendEmail/{email}")
    public ResponseResults sendEmail(@PathVariable String email){
        return responseResults.responseBySuccess("ok");
    }

    /**
     *  checkVerifyCode
     * @param map The map must be including msgId And verifyCode
     * @return  ResponseResults
     */
    @ApiOperation(value = "submit verify message",notes = " pass a msgId and verifyCode",httpMethod = "POST",response = ResponseResults.class)
    @ApiImplicitParam(name = "map",value = "map",dataType="Map")
    @GetMapping("/checkVerifyCode")
    public ResponseResults checkVerifyCode(@RequestBody Map<String,String> map){
        return responseResults.responseBySuccess(iUserService.checkVerifyCode(map));
    }

    /**
     *  ResponseResults
     * @param user
     * @return ResponseResults
     */
    @ApiOperation(value = "register",notes = "pass username and password",httpMethod = "POST",response = ResponseResults.class)
    @ApiImplicitParam(name = "user",value = "The user message",dataType="User")
    @PostMapping("/register")
    public ResponseResults register(@RequestBody User user){
        return  responseResults.responseBySuccess("保存成功",iUserService.register(user));
    }

    /**
     * login
     * @param user
     * @param request
     * @return ResponseResults
     */
    @ApiOperation(value = "login",notes = "pass username and password",httpMethod = "POST",response = ResponseResults.class)
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


    @ApiOperation(value = "refresh",notes = "pass the oldToken",httpMethod = "POST",response = ResponseResults.class)
    @ApiImplicitParam(name = "oldToken",value = "The oldToken message",dataType="String")
    @PostMapping("/")
    public String refresh(String oldToken){
        return "ok";
    }

    @ApiOperation(value = "updatePassword",notes = "pass the oldPassword And newPassword",httpMethod = "POST",response = ResponseResults.class)
    @ApiImplicitParam(name = "map",value = "map must be include id,oldPassword,newPassword",dataType="Map")
    @PostMapping("/updatePassword")
    public ResponseResults updatePassword(@RequestBody Map<String,String> map){
        return responseResults.responseBySuccess(iUserService.updatePassword(map));
    }

    /**
     *  queryUserById
     * @param id the user id
     * @return ResponseResults
     */
    @ApiOperation(value = "queryUserById",notes = "the param is a UserId",httpMethod = "GET",response = ResponseResults.class)
    @ApiImplicitParam(name = "id",value = "the user id",dataType="String")
    @GetMapping("/{id}")
    public ResponseResults queryUserById(@PathVariable String id){
        User user=iUserService.findById(id);
        if(user!=null){
            return responseResults.responseBySuccess("ok",user);
        }
        return responseResults.responseBySuccess();
    }

    @ApiOperation(value = "updateUser",notes = "the param is a User object",httpMethod = "PUT",response = ResponseResults.class)
    @ApiImplicitParam(name = "user",value = "the user object",dataType="User")
    @PutMapping("/")
    public ResponseResults updateUser(@RequestBody User user){
        User user1=iUserService.updateEntity(user);
        if(user1!=null){
            return responseResults.responseBySuccess("ok",user);
        }
        return responseResults.responseByErrorMessage("update Failure");
    }








}
