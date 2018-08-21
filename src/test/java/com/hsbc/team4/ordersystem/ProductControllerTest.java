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
//        productDto.setId("20180802");
//        productDto.setProductName("汇丰生活信用卡");
//        productDto.setProductDescription("汇丰银行国内首发汇丰生活信用卡，其包含一张人民币银联白金卡和一张美元MasterCard白金卡\"");
//        productDto.setProductIcon("life-card.jpg");
//        productDto.setProductPrice(300);
//        productDto.setProductCondition("主卡申请人需年满18周岁，境内居民申请需月薪达人民币4,000元或以上，境外居民申请需月薪达人民币10,000元或以上。附属卡申请人须年满16周岁。 ");
//        productDto.setStandard("汇丰生活信用卡主卡年费人民币300元，内含多项专属权益。首年激活卡片后免收首年年费，刷卡满6次可免次年年费。附属卡免收年费。");
        productDto.setProductName("1");
        productDto.setProductPrice(3000);
        productDto.setProductDescription("1");
        productDto.setProductIcon("1.jpg");
        productDto.setProductType("day in search");
        productDto.setProductCondition("1");
        productDto.setStandard("1");
        String json = JSON.toJSONString(productDto);
        if (!"".equals(json)) {
            try {
                mockMvc.perform(post("/product/save")
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
    public void queryByProductId(){
        String id="a09f32895d87437ea8324de090c5e49f1533706523174";
        try {
            mockMvc.perform(get("/product/productId/"+id)
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
        productDto.setProductName("平板");
        productDto.setProductDescription("电子产品");
        productDto.setProductIcon("life-card.jpg");
        productDto.setProductPrice(300);
        String json = JSON.toJSONString(productDto);
        try {
            mockMvc.perform(put("/product/update")
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
    public void deleteByProductId() {
        String id="a09f32895d87437ea8324de090c5e49f1533706523174";
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
    @Test
    public void queryByProductType(){
        String productName="day in search";
        try {
            mockMvc.perform(get("/product/queryByProductType/0/"+productName)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON))  //接收的类型
                    .andExpect(status().isOk())   //判断接收到的状态是否是200
                    .andDo(print());  //打印内容
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void queryByProductTypeContains(){
        String productType="day";
        try{
            mockMvc.perform(get("/product/queryByProductTypeContains/0/"+productType)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON))    //接收的类型
                    .andExpect(status().isOk())     //判断接收到的状态是否为200
                    .andDo(print());    //打印内容
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void queryByProductNameContains(){
        String productName="credit";
        try{
            mockMvc.perform(get("/product/queryByProductNameContains/0/"+productName)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON))    //接收的类型
                    .andExpect(status().isOk())     //判断接收到的状态是否为200
                    .andDo(print());    //打印内容
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void orderByProductPrice(){
        try{
            mockMvc.perform(get("/product/orderByProductPrice/0")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON))    //接收的类型
                    .andExpect(status().isOk())     //判断接收到的状态是否为200
                    .andDo(print());    //打印内容

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}