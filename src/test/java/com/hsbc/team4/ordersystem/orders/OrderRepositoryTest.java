package com.hsbc.team4.ordersystem;

import com.hsbc.team4.ordersystem.orders.IOrderRepository;
import com.hsbc.team4.ordersystem.orders.Orders;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @author:Young
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem
 * @Date date: 2018/8/8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTest {
    @Autowired
    private IOrderRepository orderRepository;

    @Test
    public void addEntity(){
        Orders order = new Orders();
        order.setId("1111");
        order.setOrderStatus(1);
        order.setProductCount(10);
        order.setUserId("100000");
        order.setProductId("111123");
        Orders order1 = orderRepository.save(order);
        Assert.assertNotEquals(null,order1);
    }
}
