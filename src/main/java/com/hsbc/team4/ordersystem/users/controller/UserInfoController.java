package com.hsbc.team4.ordersystem.users.controller;

import com.hsbc.team4.ordersystem.common.utils.BeanValidator;
import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
import com.hsbc.team4.ordersystem.users.domain.UserInfo;
import com.hsbc.team4.ordersystem.users.service.IUserInfoService;
import com.hsbc.team4.ordersystem.users.service.impl.ImportResourceServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    private final ImportResourceServiceImpl importResourceService;

    /**
     * UserInfoController
     * @param responseResults
     * @param iUserInfoService
     * @param beanValidator
     * @param importResourceService
     */
    @Autowired
    public UserInfoController(ResponseResults responseResults, IUserInfoService iUserInfoService, BeanValidator beanValidator, ImportResourceServiceImpl importResourceService) {
        this.iUserInfoService = iUserInfoService;
        this.responseResults = responseResults;
        this.beanValidator = beanValidator;
        this.importResourceService = importResourceService;
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

    @ApiOperation(value = "uploadHeaderImage",notes = "the param is multipartFile",httpMethod = "POST",response = ResponseResults.class)
    @PostMapping("/uploadHeaderImage/{id}")
    public ResponseResults uploadHeaderImage(@ApiParam(name = "multipartFile",value = "multipartFile") MultipartFile multipartFile, @PathVariable String id){
        UserInfo userInfo=iUserInfoService.findByUsername(id);
        if(userInfo!=null){
            Map<String,String> map=importResourceService.uploadFile(multipartFile,"headImage/");
            String path=map.get("path");
            if(path!=null){
                userInfo.setHeadURL(path);
                UserInfo userInfo1=iUserInfoService.updateUserInfo(userInfo);
                if(userInfo1!=null){
                    return responseResults.responseBySuccess("ok",userInfo);
                }
                return responseResults.responseByErrorMessage("save headImage  Failure");
            }
        }
        return responseResults.responseByErrorMessage("the username is not exist");
    }


}
