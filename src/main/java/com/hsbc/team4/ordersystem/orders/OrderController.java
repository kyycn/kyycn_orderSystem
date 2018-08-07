package com.hsbc.team4.ordersystem.orders;

import com.hsbc.team4.ordersystem.common.utils.BeanValidator;
import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hsbc.team4.ordersystem.orders.Order;

/**
 * @Description:
 * @author: Young
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem.controller
 * @Date date: 2018/8/2
 */
@RestController
@RequestMapping(value = "orders")
public class OrderController {

    private final IOrderService orderService;
    @Autowired
    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    /**
    * @Description: Users place purchase orders.
    * @Param: [order]
    * @return: java.lang.String
    * @Author: Young
    * @Date: 2018/8/3
    */
    @PostMapping("/")
    public ResponseResults confirmOrder(@RequestBody OrderDto orderDto){
        BeanValidator.validateObject(orderDto);
        Order order = Order.adaptOrder(orderDto);
        return ResponseResults.responseBySuccess("购买成功！",orderService.addEntity(order));
    }

}
