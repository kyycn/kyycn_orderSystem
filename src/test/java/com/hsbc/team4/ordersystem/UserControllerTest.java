package com.hsbc.team4.ordersystem;

import com.alibaba.fastjson.JSON;
import com.hsbc.team4.ordersystem.common.factory.UUIDFactory;
import com.hsbc.team4.ordersystem.roles.Role;
import com.hsbc.team4.ordersystem.users.controller.UserController;
import com.hsbc.team4.ordersystem.users.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem
 * @Description :
 * @Date : 2018/8/3
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UUIDFactory uuidFactory;

//    @Before
//    public void setUp() throws Exception {
//        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();  //构造MockMvc
//    }

    @Test
    public void saveUser(){
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        User user =new User();
        user.setId("20180803");
        user.setUsername("kevin");
        user.setPassword(bCryptPasswordEncoder.encode("123456"));
        user.setLocked(false);
        Role role=new Role();
        role.setRoleName("普通用户");
        role.setId(uuidFactory.getUUID());
        List<Role> roles=new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        String json= JSON.toJSONString(user);
        if(!"".equals(json)){
            try {
                mockMvc.perform(post("/user/")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andDo(print());

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(json);
        }

    }

    @Test
    public void queryByUserId(){
        String id="20180803";
        try {
            mockMvc.perform(get("/account/"+id)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getAccountList(){
        try {
            mockMvc.perform(get("/account/0")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateUser() {
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        User user =new User();
        user.setId("20180803");
        user.setUsername("crx");
        user.setPassword(bCryptPasswordEncoder.encode("123456"));
        user.setLocked(false);
        Role role=new Role();
        role.setRoleName("管理员");
        role.setId(uuidFactory.getUUID());
        List<Role> roles=new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        String json= JSON.toJSONString(user);
        try {
            mockMvc.perform(put("/user")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(json)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void deleteByUserId() {
        String id="20180803";
        try {
            mockMvc.perform(delete("/account/"+id)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
