package com.hsbc.team4.ordersystem;

import com.alibaba.fastjson.JSON;
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
 * @Date : 2018/8/1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();  //构造MockMvc

    }

    @Test
    public void saveUser(){
        User user=new User();
        user.setId("20150612");
        user.setUsername("Mr chen");
        user.setPassword("123456");
        String json= JSON.toJSONString(user);
        if(!"".equals(json)){
            try {
                mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))  //接收的类型
                        .andExpect(status().isOk())   //判断接收到的状态是否是200
                        .andDo(print());  //打印内容

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(json);
        }

    }

    @Test
    public void queryByUserId(){
        String id="20150612";
        try {
            mockMvc.perform(get("/user/"+id)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON))  //接收的类型
                    .andExpect(status().isOk())   //判断接收到的状态是否是200
                    .andDo(print());  //打印内容
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getUserList(){
        try {
            mockMvc.perform(get("/user/0")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON))  //接收的类型
                    .andExpect(status().isOk())   //判断接收到的状态是否是200
                    .andDo(print());  //打印内容
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateUser() {
        User user=new User();
        user.setId("20150612");
        user.setUsername("奇点");
        user.setPassword("123456");
        String json=JSON.toJSONString(user);
        try {
            mockMvc.perform(put("/user")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(json)
                    .accept(MediaType.APPLICATION_JSON))  //接收的类型
                    .andExpect(status().isOk())   //判断接收到的状态是否是200
                    .andDo(print());  //打印内容
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void deleteByUserId() {
        String id="20150611";
        try {
            mockMvc.perform(delete("/user/"+id)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON))  //接收的类型
                    .andExpect(status().isOk())   //判断接收到的状态是否是200
                    .andDo(print());  //打印内容

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
