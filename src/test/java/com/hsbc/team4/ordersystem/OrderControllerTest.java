package com.hsbc.team4.ordersystem;

import com.alibaba.fastjson.JSON;
import com.hsbc.team4.ordersystem.orders.OrderDto;
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
import com.hsbc.team4.ordersystem.orders.Order;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @Description:
 * @author:Young
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem
 * @Date date: 2018/8/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();  //构造MockMvc
    }

    @Test
    public void confirmOrder(){
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId("111111111111");
        orderDto.setProductId("22222222222");
        orderDto.setProductCount(10);
        String json= JSON.toJSONString(orderDto);
        if(!"".equals(json)){
            try {
                mockMvc.perform(post("/orders/")
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
}
