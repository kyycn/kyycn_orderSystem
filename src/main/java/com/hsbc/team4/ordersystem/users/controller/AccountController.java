package com.hsbc.team4.ordersystem.users.controller;

import com.hsbc.team4.ordersystem.common.utils.BeanValidator;
import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
import com.hsbc.team4.ordersystem.users.domain.Account;
import com.hsbc.team4.ordersystem.users.service.IAccountService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author : Kevin
 * @version : 1.0
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.users
 * @Description :
 * @Date : 2018/8/3
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    private final IAccountService iAccountService;
    private final BeanValidator beanValidator;
    private final ResponseResults responseResults;

    @Autowired
    public AccountController(IAccountService iAccountService, BeanValidator beanValidator, ResponseResults responseResults) {
        this.iAccountService = iAccountService;
        this.beanValidator = beanValidator;
        this.responseResults = responseResults;
    }
    /**
     * updateAccount
     * @param account the account object
     * @return account
     */
    @ApiOperation(value = "updateAccount",notes = "the param is a account object",httpMethod = "PUT",response = ResponseResults.class)
    @ApiImplicitParam(name = "account",value = "account",dataType="Account")
    @PutMapping("/")
    public ResponseResults updateAccount(@RequestBody Account account){
        Map<String,String> map=beanValidator.validateObject(account);
        if(!CollectionUtils.isEmpty(map)){
            return responseResults.responseByErrors(map);
        }
        Account account1=iAccountService.updateAccount(account);
        if(account1!=null){
            return responseResults.responseBySuccess("ok",account);
        }
        return responseResults.responseByErrorMessage("update Failure ");
    }

    /**
     * queryAccountByUsername
     * @param username
     * @return ResponseResults
     */
    @ApiOperation(value = "queryAccountByUsername",notes = "queryAccountByUsername",httpMethod = "GET")
    @ApiImplicitParam(name = "id",value = "id",dataType="String")
    @GetMapping("/{username}")
    public ResponseResults queryAccountByUsername(@PathVariable String username){
        if (StringUtils.isEmpty(username)) {
            return responseResults.responseByErrorMessage("The username is empty");
        }
        Account account=iAccountService.findByUsername(username);
        if(account!=null){
            return responseResults.responseBySuccess("ok",account);
        }
        return responseResults.responseByErrorMessage("overtime,please refresh again");
    }

    @ApiOperation(value = "updateAccountByStatus",notes = "updateAccountByStatus",httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id",dataType="String"),
            @ApiImplicitParam(name = "status",value = "status",dataType="Integer")}
    )
    @PutMapping("/{id}/{status}")
    public ResponseResults updateAccountByStatus(@PathVariable String id,@PathVariable Integer status){
        if(StringUtils.isEmpty(status)&& StringUtils.isEmpty(id)){
            return responseResults.responseByErrorMessage("the status is isEmpty");
        }
        int row=iAccountService.updateStatusById(id,status);
        if(row>0){
            return responseResults.responseBySuccess("ok");
        }
        return responseResults.responseByErrorMessage("overtime,please update again");
    }

}
