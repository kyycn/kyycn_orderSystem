package com.hsbc.team4.ordersystem;

import com.alibaba.fastjson.JSON;
import com.hsbc.team4.ordersystem.roles.Role;
import com.hsbc.team4.ordersystem.users.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
public class RoleControllerTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

    }

    /**
     * saveRole
     */
    @Test
    public void saveRole(){
        Role role=new Role();
        role.setId("20150612");
        role.setRoleName("超级管理员");
        role.setCreateTime(System.currentTimeMillis());
        String json= JSON.toJSONString(role);
        if(!"".equals(json)){
            try {
                mockMvc.perform(post("/role/")
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

    /**
     * queryRoleId
     */
    @Test
    public void queryByRoleId(){
        String id="20150612";
        try {
            mockMvc.perform(get("/role/"+id)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * getRoleList
     */
    @Test
    public void getRoleList(){
        try {
            mockMvc.perform(get("/role/0")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * updateRole
     */
    @Test
    public void updateRole() {
        Role role=new Role();
        role.setId("20150612");
        role.setRoleName("普通管理员");
        String json=JSON.toJSONString(role);
        try {
            mockMvc.perform(put("/role/")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(json)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * deleteByRoleId
     */
    @Test
    public void deleteByRoleId() {
        String id="20150612";
        try {
            mockMvc.perform(delete("/role/"+id)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
