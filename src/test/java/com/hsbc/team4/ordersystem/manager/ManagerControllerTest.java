package com.hsbc.team4.ordersystem.manager;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
* ManagerController Tester.
*
* @author Cady
* @since <pre>8 2, 2018</pre>
* @version 1.0
*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    /*all request's json*/
    private List<String> jsons;

    /**
     * @Description init the all kind of request's json
     * @Date: 23:09 2018-08-02
     * @Param
     * @return void
     */
    @Before
    public void before() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        jsons = new ArrayList<>();
        Account manager = new Account();
        manager.setName("Cady");
        manager.setPassword("123456");
        String jsonCorrect = JSON.toJSONString(manager);
        jsons.add(jsonCorrect);
        manager.setPassword("111111");
        String jsonPasswordError = JSON.toJSONString(manager);
        jsons.add(jsonPasswordError);
        manager.setPassword("123456");
        manager.setName("aaa");
        String jsonNotSuchManager = JSON.toJSONString(manager);
        jsons.add(jsonNotSuchManager);
        manager.setName(null);
        String jsonParamsMiss = JSON.toJSONString(manager);
        jsons.add(jsonParamsMiss);
    }

    @After
    public void after() throws Exception {
    }

    /**
    *
    * Method: checkLogin(@PathVariable("id") String id)
    *
    */
    @Test
    public void testCheckLogin() throws Exception {
        for(String json: jsons){
            mockMvc.perform(post("/manager/login")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(json)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print());
        }
    }


}
