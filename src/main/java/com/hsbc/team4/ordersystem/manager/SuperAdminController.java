package com.hsbc.team4.ordersystem.manager;

import com.hsbc.team4.ordersystem.common.utils.BeanValidator;
import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
import com.hsbc.team4.ordersystem.common.utils.ValidatorTools;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author:Cady
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem.manager
 * @Description:
 * @Date date: 2018-08-07
 */
@RestController
@RequestMapping("/super")
@Api(value = "super")
public class SuperAdminController {

    private static final String SUPER_NAME = "root";
    private final IManagerService managerService;
    private final IManagerAccountService managerAccountService;
    private final ResponseResults responseResults;
    private final BeanValidator beanValidator;

    @Autowired
    public SuperAdminController(IManagerService managerService, IManagerAccountService managerAccountService, ResponseResults responseResults, BeanValidator beanValidator) {
        this.managerService = managerService;
        this.managerAccountService = managerAccountService;
        this.responseResults = responseResults;
        this.beanValidator = beanValidator;
    }

    @ApiOperation(value = "superLogin",notes = "root account and password",httpMethod = "POST")
    @PostMapping("/login")
    public ResponseResults superLogin(@ApiParam(required = true,name = "account",value = "managerAccount name and password")@RequestBody ManagerAccount account){
        if (beanValidator.validateObject(account).isEmpty()){
            ManagerAccount managerAccount = managerAccountService.findByName(SUPER_NAME);
            if (SUPER_NAME.equals(account.getName())&&account.getPassword().equals(managerAccount.getPassword())){

                return responseResults.responseBySuccess("pass", managerAccount);
            }
        }
        return responseResults.responseByErrorMessage("error");
    }

    @ApiOperation(value = "addManager",notes = "add a manager by superAdmin",httpMethod = "POST")
    @PostMapping("/add")
    public ResponseResults addManager(@ApiParam(required = true,name = "manager",value = "manager project")@RequestBody Manager manager){
        if (!beanValidator.validateObject(manager).isEmpty()){
            return responseResults.responseByErrorMessage("information error");
        }
        Manager reManager = managerService.addEntity(manager);
        if (reManager == null){
            return responseResults.responseByError();
        }
        return responseResults.responseBySuccess("add success", reManager);
    }

    @ApiOperation(value = "disableManager",notes = "set a manager's status to 0",httpMethod = "POST")
    @RequestMapping("/delete/{id}/{status}")
    public ResponseResults updateManagerByStatus(@ApiParam(required = true,name = "id",value = "manager id")@PathVariable String id,
                                                 @ApiParam(required = true,name = "id",value = "manager id")@PathVariable Integer status){

        if (StringUtils.isNotBlank(id)){
            int result = managerService.updateStatusById(id, status);
            if (result > 0){
                return responseResults.responseBySuccessMessage("update success");
            }
            return responseResults.responseByErrorMessage("update fail");
        }
        return responseResults.responseByErrorMessage("id can not be blank");
    }

    @ApiOperation(value = "getManager",notes = "get a manager",httpMethod = "GET")
    @GetMapping("/manager/{id}")
    public ResponseResults getManager(@ApiParam(required = true,name = "id",value = "manager id")@PathVariable String id){

        if (StringUtils.isNotBlank(id)){
            Manager manager = managerService.findById(id);
            if (manager!=null){
                return responseResults.responseBySuccess(manager);
            }
            return responseResults.responseByErrorMessage("fail to get the manager");
        }
        return responseResults.responseByErrorMessage("id can not be brank");
    }

    @ApiOperation(value = "updateManager",notes = "update manager",httpMethod = "POST")
    @PostMapping("/update")
    public ResponseResults updateManager(@ApiParam(required = true, name = "manager",value = "manager message")@RequestBody Manager manager){

        if (!beanValidator.validateObject(manager).isEmpty()){
            return responseResults.responseByErrorMessage("information error");
        }
        Manager reManager = managerService.updateEntity(manager);
        if (manager!=null){
            return responseResults.responseBySuccess("update success", reManager);
        }
        return responseResults.responseByError();
    }

    @ApiOperation(value = "resetPassword",notes = "reset root's password",httpMethod = "POST")
    @PostMapping("/reset")
    public ResponseResults resetPassword(@ApiParam(required = true, name = "pwd",value = "the old password and the new password")@RequestBody Map<String, String> pwd){

        if (StringUtils.isNotBlank(pwd.get("old")) && StringUtils.isNotBlank(pwd.get("new"))){
            ManagerAccount rootManager = managerAccountService.findByName(SUPER_NAME);
            if (rootManager!=null && rootManager.getPassword().equals(pwd.get("old")) && ValidatorTools.isPassword(pwd.get("new"))){
                rootManager.setPassword(pwd.get("new"));
                managerAccountService.updateEntity(rootManager);
                return responseResults.responseBySuccess("reset password success");
            }
            return responseResults.responseByErrorMessage("fail to reset password");
        }
        return responseResults.responseByErrorMessage("property error");
    }
}
