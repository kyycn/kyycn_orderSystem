package com.hsbc.team4.ordersystem.users;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.users
 * @Description :
 * @Date : 2018/8/3
 */
@RestController
@RequestMapping("account")
public class AccountController {
    private final IAccountService iAccountService;


    @Autowired
    public AccountController(IAccountService iAccountService) {
        this.iAccountService = iAccountService;
    }

    /**
     * sendMessage
     * @param phone phone
     * @return String
     */
    @ApiOperation(value = "sends verify message",notes = " pass a phone argument",httpMethod = "GET")
    @ApiImplicitParam(name = "phone",value = "phone",dataType="String")
    @GetMapping("/{phone}")
    public String sendMessage(@PathVariable String phone){
        return "ok";
    }

    /**
     * submit verify code
     * @param verifyCode  verifyCode
     * @return String
     */
    @ApiOperation(value = "sends verify message",notes = " pass a phone argument",httpMethod = "POST")
    @ApiImplicitParam(name = "phone",value = "phone",dataType="String")
    @PostMapping("/{verifyCode}")
    public String submitVerifyCode(@PathVariable String verifyCode){
        return "ok";
    }

    /**
     * register
     * @param account
     * @return String
     */
    @ApiOperation(value = "register",notes = "pass username and password",httpMethod = "POST")
    @ApiImplicitParam(name = "account",value = "The account message",dataType="Account")
    @PostMapping("/register")
    public String register(@RequestBody Account account){
        return "ok";
    }

    @ApiOperation(value = "login",notes = "pass username and password",httpMethod = "POST")
    @ApiImplicitParam(name = "account",value = "The account message",dataType="String")
    @PostMapping("/login")
    public String login(@RequestBody Account account){
        return "ok";
    }

    @ApiOperation(value = "refresh",notes = "pass the oldToken",httpMethod = "POST")
    @ApiImplicitParam(name = "oldToken",value = "The oldToken message",dataType="String")
    @PostMapping("/")
    public String refresh(String oldToken){
        return "ok";
    }





}
