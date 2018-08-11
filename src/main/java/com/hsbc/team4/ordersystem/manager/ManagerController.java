package com.hsbc.team4.ordersystem.manager;

import com.hsbc.team4.ordersystem.common.utils.BeanValidator;
import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
import io.swagger.annotations.Api;
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
 * @Date date: 2018-08-02
 */
@RestController
@RequestMapping("/manager")
@Api(value = "manager")
public class ManagerController {

    private IManagerService managerService;
    private IManagerAccountService accountService;
    private final ResponseResults responseResults;

    @Autowired
    public ManagerController(IManagerService managerService, IManagerAccountService accountService, ResponseResults responseResults) {
        this.managerService = managerService;
        this.accountService = accountService;
        this.responseResults = responseResults;
    }


    /**
     * @Description check the manager's information when login
     * @Date: 23:06 2018-08-02
     * @Param manager
     * @return com.hsbc.team4.ordersystem.common.utils.ResponseResults
     */
    @ApiOperation(value = "checkLogin",notes = "check manager login",httpMethod = "POST")
    @PostMapping("/login")
    public ResponseResults checkLogin(@ApiParam(required = true,name = "manager",value = "manager name and password")@RequestBody ManagerAccount manager){
        Map<String,String> validateMap = new BeanValidator().validateObject(manager);
        if (validateMap.isEmpty()){
            ManagerAccount managerAccount = accountService.findByName(manager.getName());
            if(managerAccount !=null&&manager.getPassword().equals(managerAccount.getPassword())){
                return responseResults.responseBySuccess("pass",managerService.findByWordNumber(managerAccount.getName()));
            }
            return responseResults.responseByErrorMessage("error");
        }
        return responseResults.responseByErrorMessage("again");
    }

    @ApiOperation(value = "managerInfoUpdate",notes = "update own message",httpMethod = "POST")
    @PostMapping("/update")
    public ResponseResults managerInfoUpdate(@ApiParam(required = true,name = "manager",value = "update manager message")@RequestBody Manager manager){
        Manager reManager = managerService.updateEntity(manager);
        if (reManager==null){
            return responseResults.responseByErrorMessage("error");
        }
        return responseResults.responseBySuccess("success", reManager);
    }
}
