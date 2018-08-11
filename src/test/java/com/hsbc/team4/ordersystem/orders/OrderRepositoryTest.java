package com.hsbc.team4.ordersystem.orders;

import com.hsbc.team4.ordersystem.common.adapt.BeanAdapter;
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
    @Autowired
    private BeanAdapter beanAdapter;

    @Test
    public void addEntity(){
        Orders order = new Orders();
        order.setId("1111");
        order.setOrderStatus(1);
        order.setProductCount(10);
        order.setUserId("100000");
        order.setProductId("111123");
        order.setPrice(12.1);
        order.setTotalFree(order.getPrice()*order.getProductCount());
        Orders order1 = orderRepository.save(order);
        OrderDto dto = (OrderDto) beanAdapter.daoAdapter(order1, new OrderDto());
        System.out.println(dto);
        Assert.assertNotEquals(null,order1);
    }
}
