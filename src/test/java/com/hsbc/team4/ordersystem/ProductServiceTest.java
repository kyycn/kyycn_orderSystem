package com.hsbc.team4.ordersystem;

import com.hsbc.team4.ordersystem.products.IProductService;
import com.hsbc.team4.ordersystem.products.Product;
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

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author:yang
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem
 * @Description:
 * @Date date: 2018/8/8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Autowired
    IProductService productService;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();  //构造MockMvc

    }

    @Test
    public void testFindById(){
        Product product=productService.query("20180802");

    }
}
