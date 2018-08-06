package com.hsbc.team4.ordersystem.users.controller;

import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
import com.hsbc.team4.ordersystem.users.domain.UserInfo;
import com.hsbc.team4.ordersystem.users.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.users.controller
 * @Description :
 * @Date : 2018/8/5
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private final IUserInfoService iUserInfoService;
    private final ResponseResults responseResults;

    /**
     * UserInfoController
     * @param iUserInfoService
     * @param responseResults
     */
    public UserInfoController(IUserInfoService iUserInfoService, ResponseResults responseResults) {
        this.iUserInfoService = iUserInfoService;
        this.responseResults = responseResults;
    }

    /**
     * updateUserInfo
     * @param userInfo
     * @return account
     */
    @PutMapping("/")
    public ResponseResults updateRole(@RequestBody UserInfo userInfo){
        return responseResults.responseBySuccess("ok",iUserInfoService.addUserInfo(userInfo));
    }

    /**
     * queryAccountById
     * @param id
     * @return Role
     */
    @GetMapping("/{id}")
    public ResponseResults queryAccountById(@PathVariable String id){
        return responseResults.responseBySuccess("ok",iUserInfoService.findById(id));
    }

}
