package com.hsbc.team4.ordersystem;

import com.alibaba.fastjson.JSON;
import com.hsbc.team4.ordersystem.common.factory.UUIDFactory;
import com.hsbc.team4.ordersystem.roles.Role;
import com.hsbc.team4.ordersystem.users.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
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
@SpringBootTest
public class AccountControllerTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();  //构造MockMvc

    }

    @Test
    public void saveAccount(){
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        Account account=new Account();
        account.setId("20180803");
        account.setUsername("kevin");
        account.setPassword(bCryptPasswordEncoder.encode("123456"));
        account.setLocked(false);
        Role role=new Role();
        role.setRoleName("普通用户");
        role.setId(UUIDFactory.getUUID());
        List<Role> roles=new ArrayList<>();
        roles.add(role);
        account.setRoles(roles);
        String json= JSON.toJSONString(account);
        if(!"".equals(json)){
            try {
                mockMvc.perform(post("/account/")
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
    public void queryByAccountId(){
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
        Account account=new Account();
        account.setId("20180803");
        account.setUsername("crx");
        account.setPassword(bCryptPasswordEncoder.encode("123456"));
        account.setLocked(false);
        Role role=new Role();
        role.setRoleName("管理员");
        role.setId(UUIDFactory.getUUID());
        List<Role> roles=new ArrayList<>();
        roles.add(role);
        account.setRoles(roles);
        String json= JSON.toJSONString(account);
        try {
            mockMvc.perform(put("/account")
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
