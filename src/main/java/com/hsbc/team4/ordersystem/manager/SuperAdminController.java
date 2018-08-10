package com.hsbc.team4.ordersystem.manager;

import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    private IManagerService managerService;
    private IManagerAccountService managerAccountService;
    private ResponseResults responseResults;

    @Autowired
    public SuperAdminController(IManagerService managerService, IManagerAccountService managerAccountService, ResponseResults responseResults) {
        this.managerService = managerService;
        this.managerAccountService = managerAccountService;
        this.responseResults = responseResults;
    }

    @ApiOperation(value = "superLogin",notes = "root account and password",httpMethod = "POST")
    @PostMapping("/login")
    public ResponseResults superLogin(@ApiParam(required = true,name = "account",value = "managerAccount name and password")@RequestBody ManagerAccount account){
        ManagerAccount managerAccount = managerAccountService.findByName(SUPER_NAME);
        if (SUPER_NAME.equals(account.getName())&&account.getPassword().equals(managerAccount.getPassword())){
            return responseResults.responseBySuccess(managerAccount);
        }
        return responseResults.responseByError();
    }

    @ApiOperation(value = "addManager",notes = "add a manager by superAdmin",httpMethod = "POST")
    @PostMapping("/add")
    public ResponseResults addManager(@ApiParam(required = true,name = "manager",value = "manager project")@RequestBody Manager manager){
        Manager reManager = managerService.addEntity(manager);
        if (reManager == null){
            return responseResults.responseByError();
        }
        return responseResults.responseBySuccess(reManager);
    }

    @ApiOperation(value = "disableManager",notes = "set a manager's status to 0",httpMethod = "POST")
    @RequestMapping("/delete/{id}")
    public ResponseResults disableManager(@ApiParam(required = true,name = "id",value = "manager id")@PathVariable String id){
        int result = managerService.updateStatusById(id, 0);
        if (result == 0){
            return responseResults.responseByError();
        }
        return responseResults.responseBySuccess();
    }

    @ApiOperation(value = "getManager",notes = "get a manager",httpMethod = "GET")
    @GetMapping("/manager/{id}")
    public ResponseResults getManager(@ApiParam(required = true,name = "id",value = "manager id")@PathVariable String id){
        Manager manager = managerService.findById(id);
        if (manager!=null){
            return responseResults.responseBySuccess(manager);
        }
        return responseResults.responseByError();
    }

    @ApiOperation(value = "updateManager",notes = "update manager",httpMethod = "POST")
    @PostMapping("/update")
    public ResponseResults updateManager(@ApiParam(required = true, name = "manager",value = "manager message")@RequestBody Manager manager){
        Manager reManager = managerService.updateEntity(manager);
        if (manager!=null){
            return responseResults.responseBySuccess(reManager);
        }
        return responseResults.responseByError();
    }

    @ApiOperation(value = "resetPassword",notes = "reset root's password",httpMethod = "POST")
    @PostMapping("/reset")
    public ResponseResults resetPassword(@ApiParam(required = true, name = "pwd",value = "the old password and the new password")@RequestBody Map<String, Object> pwd){
        ManagerAccount rootManager = managerAccountService.findByName(SUPER_NAME);
        System.out.println(rootManager.getPassword());
        System.out.println(pwd.get("old"));
        if (rootManager!=null&&rootManager.getPassword().equals(pwd.get("old"))){
            rootManager.setPassword(pwd.get("new").toString());
            System.out.println(pwd.get("new").toString());
            managerAccountService.updateEntity(rootManager);
            return responseResults.responseBySuccess("reset password success");
        }
        return responseResults.responseByErrorMessage("fail to reset password");
    }
}
