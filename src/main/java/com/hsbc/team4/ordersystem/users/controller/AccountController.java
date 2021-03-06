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
 * @version :
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
     * @param account
     * @return account
     */
    @ApiOperation(value = "updateAccount",notes = "updateAccount",httpMethod = "PUT")
    @ApiImplicitParam(name = "account",value = "account",dataType="Account")
    @PutMapping("/")
    public ResponseResults updateAccount(@RequestBody Account account){
        return responseResults.responseBySuccess("ok",iAccountService.updateEntity(account));
    }

    /**
     * queryAccountById
     * @param id
     * @return ResponseResults
     */
    @ApiOperation(value = "queryAccountById",notes = "queryAccountById",httpMethod = "GET")
    @ApiImplicitParam(name = "id",value = "id",dataType="String")
    @GetMapping("/{id}")
    public ResponseResults queryAccountById(@PathVariable String id){
        return responseResults.responseBySuccess("ok",iAccountService.findById(id));
    }

}
