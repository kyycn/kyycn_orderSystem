package com.hsbc.team4.ordersystem.users.controller;

import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
import com.hsbc.team4.ordersystem.users.domain.UserInfo;
import com.hsbc.team4.ordersystem.users.service.IUserInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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

    private final IUserInfoService iUserInfoService;
    private final ResponseResults responseResults;

    /**
     * UserInfoController
     * @param iUserInfoService
     * @param responseResults
     */
    @Autowired
    public UserInfoController(ResponseResults responseResults, IUserInfoService iUserInfoService) {
        this.iUserInfoService = iUserInfoService;
        this.responseResults = responseResults;
    }

    /**
     * updateUserInfo
     * @param userInfo
     * @return ResponseResults
     */
    @ApiOperation(value = "updateUserInfo",notes = "the param is a UserInfo object",httpMethod = "PUT",response = ResponseResults.class)
    @ApiImplicitParam(name = "userInfo",value = "userInfo",dataType="UserInfo")
    @PutMapping("/")
    public ResponseResults updateUserInfo(@RequestBody UserInfo userInfo){
        UserInfo userInfo1=iUserInfoService.addUserInfo(userInfo);
        if(userInfo!=null){
            return responseResults.responseBySuccess("ok",userInfo1);
        }
        return responseResults.responseByErrorMessage("overtime please refresh again");
    }

    /**
     *  queryUserInfoUsername
     * @param username
     * @return  ResponseResults
     */
    @ApiOperation(value = "queryUserInfoUsername",notes = "the param is a username",httpMethod = "GET",response = ResponseResults.class)
    @ApiImplicitParam(name = "username",value = "username",dataType="String")
    @GetMapping("/{username}")
    public ResponseResults queryUserInfoUsername(@PathVariable String username){
        return responseResults.responseBySuccess("ok",iUserInfoService.findByUsername(username));
    }

}
