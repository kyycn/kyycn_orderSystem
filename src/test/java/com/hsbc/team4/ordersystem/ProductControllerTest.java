package com.hsbc.team4.ordersystem;

import com.alibaba.fastjson.JSON;
import com.hsbc.team4.ordersystem.products.ProductDto;
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

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem
 * @Description :
 * @Date : 2018/8/2
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();  //构造MockMvc

    }
/**
 * @Author:yang
 * @Description:
 * @Param: 
 * @return:
 * @Date: 2018/8/3
 */
    @Test
    public void saveProduct() {
        ProductDto productDto = new ProductDto();
        productDto.setId("20180802");
        productDto.setName("项链");
        productDto.setDescription("首饰");
        productDto.setPic("黑色");
        productDto.setPrice(100);
        String json = JSON.toJSONString(productDto);
        if (!"".equals(json)) {
            try {
                mockMvc.perform(post("/product/")
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
/**
 * @Author:yang
 * @Description:test product info query
 * @Param: 
 * @return:
 * @Date: 2018/8/3
 */
    @Test
    public void query() {
        String id = "20180802";
        try {
            mockMvc.perform(get("/product/query?id=" + id)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON))  //接收的类型
                    //.andExpect(status().isOk())   //判断接收到的状态是否是200
                    .andDo(print());  //打印内容
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testFormLoginSuccess() throws Exception {

        // 测试登录成功
        mockMvc
                .perform(formLogin("/login").user("user").password("123"))
                .andExpect(authenticated());
    }

    @Test
    public void testFormLoginFail() throws Exception {
        // 测试登录失败
        mockMvc
                .perform(formLogin("/login").user("user").password("1234"))
                .andExpect(unauthenticated());
    }

    @Test
    public void testLogoutFail() throws Exception {
        // 测试退出登录
        mockMvc.perform(logout("/logout")).andExpect(unauthenticated());
    }

    @Test
    public void getProductList() {
        try {
            mockMvc.perform(get("/product/0")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON))  //接收的类型
                    //    .andExpect(status().isOk())   //判断接收到的状态是否是200
                    .andDo(print());  //打印内容
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Author:yang
     * @Description:
     * @Param:
     * @return:
     * @Date: 2018/8/3
     */
    @Test
    public void updateProduct() {
        ProductDto productDto = new ProductDto();
        productDto.setId("20180802");
        productDto.setName("平板");
        productDto.setDescription("电子产品");
        productDto.setPic("白色");
        productDto.setPrice(100);
        String json = JSON.toJSONString(productDto);
        try {
            mockMvc.perform(put("/product/")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(json)
                    .accept(MediaType.APPLICATION_JSON))  //接收的类型
                    .andExpect(status().isOk())   //判断接收到的状态是否是200
                    .andDo(print());  //打印内容
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @Author:yang
     * @Description:
     * @Param:
     * @return:
     * @Date: 2018/8/3
     */
    @Test
    public void deleteByUserId() {
        String id = "20180802";
        try {
            mockMvc.perform(delete("/product/" + id)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON))  //接收的类型
                    .andExpect(status().isOk())   //判断接收到的状态是否是200
                    .andDo(print());  //打印内容

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
