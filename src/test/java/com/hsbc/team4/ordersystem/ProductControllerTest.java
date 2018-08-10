package com.hsbc.team4.ordersystem;

import com.alibaba.fastjson.JSON;
import com.hsbc.team4.ordersystem.products.ProductDto;
import io.swagger.annotations.ApiOperation;
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
        String id="a09f32895d87437ea8324de090c5e49f1533706523174";
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
        productDto.setId("a09f32895d87437ea8324de090c5e49f1533706523174");
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
    public void deleteByProductId() {
        String id="a09f32895d87437ea8324de090c5e49f1533706523174";
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
