package com.hsbc.team4.ordersystem;

import com.alibaba.fastjson.JSON;
import com.hsbc.team4.ordersystem.common.factory.UUIDFactory;
import com.hsbc.team4.ordersystem.smsmessage.Sender;
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
 * @Date : 2018/8/5
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SenderControllerTest {
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private UUIDFactory uuidFactory;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

    }

    /**
     * saveSender
     */
    @Test
    public void saveSender(){
        Sender sender=new Sender();
        sender.setId("20180718");
        sender.setBaseUrl("https://api.miaodiyun.com/20150822/industrySMS/sendSMS");
        sender.setAccountId("e5a5cf58d15b4dd5857f7049ac398392");
        sender.setAuthToken("a0f9d9d9801d4787b377fb34503ab9b1");
        sender.setDateType("json");
        sender.setPhone("13612246284");
        sender.setTemplateId("505418437");
        sender.setTemplateType("login");
        sender.setCreateTime(System.currentTimeMillis());
        sender.setUpdateTime(System.currentTimeMillis());
        String json= JSON.toJSONString(sender);
        if(!"".equals(json)){
            try {
                mockMvc.perform(post("/sender/")
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
     * querySenderId
     */
    @Test
    public void querySenderId(){
        String id="20180718";
        try {
            mockMvc.perform(get("/sender/"+id)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * getSenderList
     */
    @Test
    public void getSenderList(){
        try {
            mockMvc.perform(get("/sender/0")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * updateSender
     */
    @Test
    public void updateSender() {
        Sender sender=new Sender();
        sender.setBaseUrl("https://api.miaodiyun.com/20150822/industrySMS/sendSMS");
        sender.setAccountId("74e7fa7a07ea405b8cfbb790e030cd1f");
        sender.setAuthToken("0e88935*******ed5");
        sender.setDateType("json");
        sender.setPhone("15626283540");
        sender.setTemplateId("501257098");
        String json= JSON.toJSONString(sender);
        try {
            mockMvc.perform(put("/sender/")
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
     * deleteBySenderId
     */
    @Test
    public void deleteBySenderId() {
        String id="20150612";
        try {
            mockMvc.perform(delete("/sender/"+id)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
