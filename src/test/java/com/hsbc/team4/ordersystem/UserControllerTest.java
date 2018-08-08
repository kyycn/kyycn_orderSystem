package com.hsbc.team4.ordersystem;

import com.alibaba.fastjson.JSON;
import com.hsbc.team4.ordersystem.users.domain.User;
import com.hsbc.team4.ordersystem.users.repository.IUserRepository;
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

import java.util.HashMap;
import java.util.Map;

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
public class UserControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Autowired
    private IUserRepository iUserRepository;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();  //构造MockMvc
    }

    /**
     * saveUser
     */
    @Test
    public void saveUser(){
        User user =new User();
        user.setUsername("kevin");
        user.setPassword("123456");
        user.setEmail("2235390423@qq.com");
        user.setLocked(false);
        user.setExpired(false);
        String json= JSON.toJSONString(user);
        if(!"".equals(json)){
            try {
                mockMvc.perform(post("/user/register")
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
     * queryByUserId
     */
    @Test
    public void queryByUserId(){
        String id="10b9067db41e4790ad10830d4b87f53d1533636076984";
        try {
            mockMvc.perform(get("/user/"+id)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  updateUser
     */
    @Test
    public void updateUser() {
        String id="10b9067db41e4790ad10830d4b87f53d1533636076984";
        User user=iUserRepository.findByEntityId(id);
        user.setUsername("crx");
        String json= JSON.toJSONString(user);
        try {
            mockMvc.perform(put("/user/")
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
     * sendSMS
     */
    @Test
    public void sendSMS() {
        Map<String,String> map=new HashMap<>();
        map.put("phone","15626283540");
        map.put("msgType","SMS");
        map.put("bizType","login");
        String json= JSON.toJSONString(map);
        try {
            mockMvc.perform(post("/user/sendMessage/")
                    .content(json)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *  verifyPhone
     */
    @Test
    public void verifyPhone() {
        String phone = "15626283540";
        try {
            mockMvc.perform(get("/user/verifyPhone")
                    .param("phone",phone)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  verifyEmail
     */
    @Test
    public void verifyEmail() {
        String email = "2235390423@qq.com";
        try {
            mockMvc.perform(get("/user/verifyEmail")
                    .param("email",email)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  verifyCode
     */
    @Test
    public void verifyCode() {
        String msgId = "7d41744adf874b3db66a6287c969ae7d1533611958718";
        String code = "550636";
        try {
            mockMvc.perform(get("/user/" + msgId + "/" + code)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
