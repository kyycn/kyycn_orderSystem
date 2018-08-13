package com.hsbc.team4.ordersystem.users.controller;

import com.hsbc.team4.ordersystem.common.utils.BeanValidator;
import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
import com.hsbc.team4.ordersystem.users.domain.UserInfo;
import com.hsbc.team4.ordersystem.users.service.IUserInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    private final BeanValidator beanValidator;

    /**
     * UserInfoController
     * @param responseResults
     * @param iUserInfoService
     * @param beanValidator
     */
    @Autowired
    public UserInfoController(ResponseResults responseResults, IUserInfoService iUserInfoService, BeanValidator beanValidator) {
        this.iUserInfoService = iUserInfoService;
        this.responseResults = responseResults;
        this.beanValidator = beanValidator;
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
        Map<String,String> errors=beanValidator.validateObject(userInfo);
        if(!CollectionUtils.isEmpty(errors)){
            return responseResults.responseByErrors("errors",errors);
        }
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
        if(!StringUtils.isEmpty(username)){
            return responseResults.responseBySuccess("ok",iUserInfoService.findByUsername(username));
        }
        return responseResults.responseByErrorMessage(" username error,the username is not be empty");
    }

}
