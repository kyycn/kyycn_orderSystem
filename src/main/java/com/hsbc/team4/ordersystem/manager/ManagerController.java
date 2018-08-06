package com.hsbc.team4.ordersystem.manager;

import com.hsbc.team4.ordersystem.common.utils.BeanValidator;
import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
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
    @PostMapping("/login")
    public ResponseResults checkLogin(@RequestBody ManagerAccount manager){
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
}
