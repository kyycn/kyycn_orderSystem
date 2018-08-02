package com.hsbc.team4.ordersystem.users;

import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.products.controller
 * @Description :
 * @Date : 2018/8/1
 */
@RestController
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * saveUser
     * @param user
     * @return User
     */
    @PostMapping("/user")
    public ResponseResults saveUser(@RequestBody User user){
        return ResponseResults.responseBySuccess("ok",userService.addEntity(user));
    }

    /**
     * updateUser
     * @param user
     * @return User
     */
    @PutMapping("/user")
    public ResponseResults updateUser(@RequestBody  User  user){
        return ResponseResults.responseBySuccess("ok",userService.updateEntity(user));
    }

    /**
     * deleteUserById
     * @param id
     * @return String
     */
    @DeleteMapping("user/{id}")
    public ResponseResults deleteUserById(@PathVariable String  id){
        return ResponseResults.responseBySuccess("ok",userService.updateStatusById(id,1));
    }

    /**
     * queryUserById
     * @param id
     * @return User
     */
    @GetMapping("user/{id}")
    public ResponseResults queryUserById(@PathVariable String id){
        return ResponseResults.responseBySuccess("ok",userService.findById(id));
    }

    @GetMapping("user/{status}")
    public ResponseResults getUserList(@RequestParam(value = "current",defaultValue = "0") int current,
                                       @RequestParam(value = "current",defaultValue = "10") int pageSize,
                                       @PathVariable int status){
        return ResponseResults.responseBySuccess("ok",userService.findByStatus(current,pageSize,status));
    }


}
