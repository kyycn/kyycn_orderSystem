package com.hsbc.team4.ordersystem.manager;

import com.alibaba.fastjson.JSON;
import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
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

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
* SuperAdminController Tester.
*
* @author <Authors name>
* @since <pre>???? 7, 2018</pre>
* @version 1.0
*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SuperAdminControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    @Autowired
    private IManagerService managerService;

    @Before
    public void before() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @After
    public void after() throws Exception {
    }

    /**
    *
    * Method: superLogin(@RequestBody ManagerAccount account)
    *
    */
    @Test
    public void testSuperLogin() throws Exception {
        ManagerAccount managerAccount = new ManagerAccount();
        managerAccount.setName("root");
        managerAccount.setPassword("rootpwd");
        String json = JSON.toJSONString(managerAccount);
        mockMvc.perform(post("/super/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    /**
    *
    * Method: addManager(@RequestBody Manager manager)
    *
    */
    @Test
    public void testAddManager() throws Exception {
        Manager manager = new Manager();
        manager.setName("superadd");
        manager.setWorkNumber("85245");
        manager.setDepartment("dev");
        String json = JSON.toJSONString(manager);
        mockMvc.perform(post("/super/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    /**
    *
    * Method: disableManager(@PathVariable String id)
    *
    */
    @Test
    public void testDisableManager() throws Exception {
        mockMvc.perform(post("/super/delete/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    /**
    *
    * Method: getManager(@PathVariable String id)
    *
    */
    @Test
    public void testGetManager() throws Exception {
        mockMvc.perform(get("/super/manager/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    /**
    *
    * Method: updateManager(@RequestBody Manager manager)
    *
    */
    @Test
    public void testUpdateManager() throws Exception {
        Manager manager = managerService.findById("1");
        manager.setStatus(1);
        String json = JSON.toJSONString(manager);
        mockMvc.perform(post("/super/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testResetPassword() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("old", "rootpwd");
        map.put("new", "1234567");
        String json = JSON.toJSONString(map);
        mockMvc.perform(post("/super/reset")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
