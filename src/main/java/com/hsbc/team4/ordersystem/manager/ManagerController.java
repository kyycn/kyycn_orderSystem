package com.hsbc.team4.ordersystem.manager;

import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    public ManagerController(IManagerService managerService) {
        this.managerService = managerService;
    }

    /**
     * @Description check the manager's information when login
     * @Date: 23:06 2018-08-02
     * @Param manager
     * @return com.hsbc.team4.ordersystem.common.utils.ResponseResults
     */
    @RequestMapping("/login")
    public ResponseResults checkLogin(@RequestBody Manager manager){
        if(manager.getName()!=null&&manager.getPassword()!=null&&!"".equals(manager.getName())&&!"".equals(manager.getPassword())){
            Manager managerFind = managerService.findByName(manager.getName());
            if(managerFind!=null&&manager.getPassword().equals(manager.getPassword())){
                return ResponseResults.responseBySuccessMessage("pass");
            }
        }
        return ResponseResults.responseByErrorMessage("again");
    }
}
