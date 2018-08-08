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
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();  //构造MockMvc

    }

    @Test
    public void saveProduct(){
        ProductDto productDto=new ProductDto();
        productDto.setId("20180803");
        productDto.setProductName("credit card purchase");
        productDto.setProductPrice(3000);
        productDto.setProductDescription("a good product");
        productDto.setProductIcon("gold.jpg");
        productDto.setProductType("week in search");
        String json= JSON.toJSONString(productDto);
        if(!"".equals(json)){
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

    @Test
    public void queryByProductId(){
        String id="20180801";
        try {
            mockMvc.perform(get("/product/productId/"+id)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON))  //接收的类型
                    .andExpect(status().isOk())   //判断接收到的状态是否是200
                    .andDo(print());  //打印内容
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getProductList(){
        try {
            mockMvc.perform(get("/product/0")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON))  //接收的类型
                    .andExpect(status().isOk())   //判断接收到的状态是否是200
                    .andDo(print());  //打印内容
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateProduct() {
        ProductDto productDto=new ProductDto();
        productDto.setId("20180803");
        productDto.setProductName("credit card purchase");
        productDto.setProductPrice(3000);
        productDto.setProductDescription("a good product");
        productDto.setProductIcon("credit.jpg");
        productDto.setProductType("month in search");
        String json=JSON.toJSONString(productDto);
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

    @Test
    public void deleteByUserId() {
        String id="20180801";
        try {
            mockMvc.perform(delete("/product/"+id)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON))  //接收的类型
                    .andExpect(status().isOk())   //判断接收到的状态是否是200
                    .andDo(print());  //打印内容

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void getProductListByType(){
        String name="day in search";
        try {
            mockMvc.perform(get("/product/queryType/0/"+name)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON))  //接收的类型
                    .andExpect(status().isOk())   //判断接收到的状态是否是200
                    .andDo(print());  //打印内容
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
