package com.hsbc.team4.ordersystem.users.controller;

import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
import com.hsbc.team4.ordersystem.users.domain.Account;
import com.hsbc.team4.ordersystem.users.service.IAccountService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private final ResponseResults responseResults;

    @Autowired
    public AccountController(IAccountService iAccountService, ResponseResults responseResults) {
        this.iAccountService = iAccountService;
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
    @ApiOperation(value = "queryAccountById",notes = "queryAccountById",httpMethod = "GET")
    @ApiImplicitParam(name = "id",value = "id",dataType="String")
    @GetMapping("/{username}")
    public ResponseResults queryAccountByUsername(@PathVariable String username){
        Account account=iAccountService.findByUsername(username);
        if(account!=null){
            return responseResults.responseBySuccess("ok",account);
        }
        return responseResults.responseByErrorMessage("overtime,please refresh again");
    }

}
