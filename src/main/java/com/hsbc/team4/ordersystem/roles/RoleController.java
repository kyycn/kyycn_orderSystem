package com.hsbc.team4.ordersystem.roles;

import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
import com.hsbc.team4.ordersystem.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.roles
 * @Description :
 * @Date : 2018/8/2
 */
@RestController
@RequestMapping(path = "/role")
public class RoleController {
    private final IRoleService iRoleService;

    @Autowired
    public RoleController(IRoleService iRoleService) {
        this.iRoleService = iRoleService;
    }

    /**
     * saveRole
     * @param role
     * @return Role
     */
    @PostMapping("/")
    public ResponseResults saveRole(@RequestBody Role role){
        return ResponseResults.responseBySuccess("ok",iRoleService.addEntity(role));
    }

    /**
     * updateRole
     * @param role
     * @return Role
     */
    @PutMapping("/")
    public ResponseResults updateRole(@RequestBody  Role  role){
        return ResponseResults.responseBySuccess("ok",iRoleService.updateEntity(role));
    }

    /**
     * deleteRoleById
     * @param id
     * @return String
     */
    @DeleteMapping("/{id}")
    public ResponseResults deleteRoleById(@PathVariable String  id){
        return ResponseResults.responseBySuccess("ok",iRoleService.updateStatusById(id,1));
    }

    /**
     * queryRoleById
     * @param id
     * @return Role
     */
    @GetMapping("/{id}")
    public ResponseResults queryUserById(@PathVariable String id){
        return ResponseResults.responseBySuccess("ok",iRoleService.findById(id));
    }

    @GetMapping("/{status}")
    public ResponseResults getRoleList(@RequestParam(value = "current",defaultValue = "0") int current,
                                       @RequestParam(value = "current",defaultValue = "10") int pageSize,
                                       @PathVariable int status){
        return ResponseResults.responseBySuccess("ok",iRoleService.findByStatus(current,pageSize,status));
    }

}
