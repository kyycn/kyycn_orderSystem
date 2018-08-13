package com.hsbc.team4.ordersystem.roles;

import com.hsbc.team4.ordersystem.aop.annotations.HasLogin;
import com.hsbc.team4.ordersystem.aop.annotations.ValidateFiled;
import com.hsbc.team4.ordersystem.common.factory.UUIDFactory;
import com.hsbc.team4.ordersystem.common.utils.BeanValidator;
import com.hsbc.team4.ordersystem.common.utils.Global;
import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
import com.hsbc.team4.ordersystem.users.domain.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    private final BeanValidator beanValidator;
    private final UUIDFactory uuidFactory;
    private final Global global;
    @Autowired
    public RoleController(IRoleService iRoleService, ResponseResults responseResults, BeanValidator beanValidator, UUIDFactory uuidFactory, Global global) {
        this.iRoleService = iRoleService;
        this.responseResults=responseResults;
        this.beanValidator = beanValidator;
        this.uuidFactory = uuidFactory;
        this.global = global;
    }

    /**
     * saveRole
     * @param role
     * @return Role
     */
    @ApiOperation(value = "saveRole",notes = "the param is a Role object",httpMethod = "POST",response = ResponseResults.class)
    @ApiImplicitParam(name = "role",value = "role",dataType="Role")
    @PostMapping()
    @HasLogin(message = "you must be login")
    public ResponseResults saveRole(@RequestBody Role role){
        User user=global.getUserByToken();
        role.setId(uuidFactory.getUUID());
        role.setRoleName("ROLE_"+role.getRoleName());
        role.setCreateUsername(user.getUsername());
        role.setUpdateUsername(user.getUsername());
        Map<String,String> map= beanValidator.validateObject(role);
        if(!CollectionUtils.isEmpty(map)){
            return responseResults.responseByErrors(map);
        }
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
    @HasLogin(message = "you must be login")
    @PutMapping
    public ResponseResults updateRole(@RequestBody  Role  role){
        User user=global.getUserByToken();
        role.setUpdateUsername(user.getUsername());
        role.setUpdateTime(System.currentTimeMillis());
        Map<String,String> map=beanValidator.validateObject(role);
        if (CollectionUtils.isEmpty(map)) {
            return responseResults.responseByErrors(map);
        }
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
    @ValidateFiled(index = 0,notNull = true,message = "id is not be empty")
    @DeleteMapping()
    public ResponseResults deleteRoleById( @RequestParam(name = "id") String  id){
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

    /**
     *  getRoleList
     * @param current
     * @param pageSize
     * @param status
     * @return
     */

    @ApiOperation(value = "getRoleList",notes = "the param is a current,pageSize,status ",httpMethod = "GET",response = ResponseResults.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "the current page", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "the page show size", dataType = "Integer"),
            @ApiImplicitParam(name = "status", value = "status", required = true, dataType = "Integer")
    })
    @GetMapping("/getRoleList/{status}")
    public ResponseResults getRoleList(@RequestParam(required = false,defaultValue = "0") int current,@RequestParam(required = false,defaultValue = "10") int pageSize, @PathVariable int status){
        Page<Role> roles=iRoleService.findByStatus(current,pageSize,status);
        if(roles!=null){
            return responseResults.responseBySuccess("ok",roles);
        }
        return responseResults.responseByErrorMessage("getRoleList Failure,please try again");
    }


    @ApiOperation(value = "searchLikeRoleName",notes = "the param is a current,pageSize,searchKey,status ",httpMethod = "GET",response = ResponseResults.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "the current page", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "the page show size", dataType = "Integer"),
            @ApiImplicitParam(name = "searchKey", value = "searchKey", required = true, dataType = "String"),
            @ApiImplicitParam(name = "status", value = "status", required = true, dataType = "Integer")
    })
    @GetMapping("/searchLikeRoleName/{searchKey}/{status}")
    public ResponseResults searchLikeRoleName(@RequestParam(required = false,defaultValue = "0") int current,@RequestParam(required = false,defaultValue = "10") int pageSize,@PathVariable String searchKey,@PathVariable int status){
        Page<Role> roles=iRoleService.findRoleLikeRoleName(searchKey,status,current,pageSize);
        if(roles!=null){
            return responseResults.responseBySuccess("ok",roles);
        }
        return responseResults.responseByErrorMessage(" searchLikeRoleName Failure,please try again");
    }

}
