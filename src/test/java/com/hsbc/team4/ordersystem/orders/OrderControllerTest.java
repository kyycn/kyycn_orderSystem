package com.hsbc.team4.ordersystem.orders;

import com.alibaba.fastjson.JSON;
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
import java.util.ArrayList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Description:
 * @author:Young
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem.orders
 * @Date date: 2018/8/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void addOrder() throws Exception {
        OrderDto orderDto = new OrderDto();
        orderDto.setId("111111111111");
        orderDto.setProductId("22222222222");
        orderDto.setProductCount(10);
        orderDto.setUserId("000000000");
        orderDto.setOrderStatus(1);
        orderDto.setPrice(12.0);
        ArrayList<OrderDto> list = new ArrayList<>();
        list.add(orderDto);
        String json= JSON.toJSONString(list);
        if(!"".equals(json)){
            try {
                 mockMvc.perform(post("/orders/")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))  //接收的类型
                        .andExpect(status().isOk())   //判断接收到的状态是否是200
                        .andDo(print());//打印内容
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void deleteOrderById() throws Exception {
        mockMvc.perform(delete("/orders/66b699b651cd4d4e9edc280c12f79daa1533803355112")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(print());
    }

    @Test
    public void getOrderById() throws Exception {
        mockMvc.perform(get("/orders/66b699b651cd4d4e9edc280c12f79daa1533803355112")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void getOrderList() throws Exception {
        mockMvc.perform(get("/orders/status/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void getOrderListByOrderStatus() throws Exception {
        mockMvc.perform(get("/orders/orderStatus/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

}