package com.hsbc.team4.ordersystem;

import com.alibaba.fastjson.JSON;
import com.hsbc.team4.ordersystem.users.domain.Account;
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

import java.math.BigDecimal;

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
public class AccountControllerTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

    }

    @Test
    public void queryByAccountId(){
        String id="20150612";
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
    public void updateAccount() {
        Account account =new Account();
        account.setId("20150612");
        account.setTradePassword("1234");
        account.setUsername("kevin");
        account.setUsableBalance(new BigDecimal(10000));
        account.setRealName("陈壬询");
        String json=JSON.toJSONString(account);
        try {
            mockMvc.perform(put("/account/")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(json)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
