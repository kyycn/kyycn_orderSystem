package com.hsbc.team4.ordersystem.roles;

import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    private final ResponseResults responseResults;
    @Autowired
    public RoleController(IRoleService iRoleService,ResponseResults responseResults) {
        this.iRoleService = iRoleService;
        this.responseResults=responseResults;
    }

    /**
     * saveRole
     * @param role
     * @return Role
     */
    @ApiOperation(value = "saveRole",notes = "the param is a Role object",httpMethod = "POST",response = ResponseResults.class)
    @ApiImplicitParam(name = "role",value = "role",dataType="Role")
    @PostMapping("/")
    public ResponseResults saveRole(@RequestBody Role role){
        Role role1=iRoleService.addEntity(role);
        if(role1!=null){
            return responseResults.responseBySuccess("ok",role1);
        }
        return responseResults.responseByErrorMessage("save  Failure,please try again");
    }

    /**
     * updateRole
     * @param role
     * @return Role
     */
    @ApiOperation(value = "updateRole",notes = "the param is a Role object",httpMethod = "PUT",response = ResponseResults.class)
    @ApiImplicitParam(name = "role",value = "role",dataType="Role")
    @PutMapping("/")
    public ResponseResults updateRole(@RequestBody  Role  role){
        Role role1=iRoleService.updateEntity(role);
        if(role1!=null){
            return responseResults.responseBySuccess("ok",role1);
        }
        return responseResults.responseByErrorMessage("update  Failure,please try again");
    }

    /**
     * deleteRoleById
     * @param id
     * @return String
     */
    @ApiOperation(value = "deleteRoleById",notes = "the param is a id ",httpMethod = "DELETE",response = ResponseResults.class)
    @ApiImplicitParam(name = "id",value = "id",dataType="String")
    @DeleteMapping("/{id}")
    public ResponseResults deleteRoleById(@PathVariable String  id){
        int row =iRoleService.updateStatusById(id,1);
        if(row>0){
            return responseResults.responseBySuccess("ok");
        }
        return responseResults.responseByErrorMessage("delete  Failure,please try again");
    }

    /**
     * queryRoleById
     * @param id
     * @return Role
     */
    @ApiOperation(value = "queryRoleById",notes = "the param is a id ",httpMethod = "GET",response = ResponseResults.class)
    @ApiImplicitParam(name = "id",value = "id",dataType="String")
    @GetMapping("/{id}")
    public ResponseResults queryRoleById(@PathVariable String id){
        Role role=iRoleService.findById(id);
        if(role!=null){
            return responseResults.responseBySuccess("ok",role);
        }
        return responseResults.responseByErrorMessage("query Failure,please try again");
    }

    @ApiOperation(value = "getRoleList",notes = "the param is a current,pageSize,status ",httpMethod = "GET",response = ResponseResults.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "the current page", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "the page show size", dataType = "Integer"),
            @ApiImplicitParam(name = "status", value = "status", required = true, dataType = "Integer")
    })
    @GetMapping("/{status}")
    public ResponseResults getRoleList( int current, int pageSize, @PathVariable int status){
        Page<Role> roles=iRoleService.findByStatus(current,pageSize,status);
        if(roles!=null){
            return responseResults.responseBySuccess("ok",roles);
        }
        return responseResults.responseByErrorMessage("getRoleList Failure,please try again");
    }

}
