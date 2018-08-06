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

    private IAccountRepository accountRepository;

    @Autowired
    public ManagerController(IManagerService managerService, IAccountRepository accountRepository) {
        this.managerService = managerService;
        this.accountRepository = accountRepository;
    }




    /**
     * @Description check the manager's information when login
     * @Date: 23:06 2018-08-02
     * @Param manager
     * @return com.hsbc.team4.ordersystem.common.utils.ResponseResults
     */
    @PostMapping("/login")
    public ResponseResults checkLogin(@RequestBody Account manager){
        Map<String,String> validateMap = BeanValidator.validateObject(manager);
        if (validateMap.isEmpty()){
            Account account = accountRepository.findByName(manager.getName());
            if(account!=null&&manager.getPassword().equals(account.getPassword())){
                return ResponseResults.responseBySuccess("pass",managerService.findByName(account.getName()));
            }
            return ResponseResults.responseByErrorMessage("error");
        }
        return ResponseResults.responseByErrorMessage("again");
    }
}
