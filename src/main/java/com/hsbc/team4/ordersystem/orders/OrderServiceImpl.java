package com.hsbc.team4.ordersystem.orders;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.hsbc.team4.ordersystem.orders.Order;

/**
 * @Description: OrderService implementation.
 * @author:Young
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem.service
 * @Date date: 2018/8/2
 */
@Service
public class OrderServiceImpl implements IOrderService {

    private final IOrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Page<Order> findByStatus(int current, int pageSize, int status) {
        return null;
    }

    /**
     * @Description: Add the order.
     * @Param: [order]
     * @return: com.hsbc.team4.ordersystem.orders.Order
     * @Author: Young
     * @Date: 2018/8/3
     */
    @Override
    public Order addEntity(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public int updateStatusById(String id, int status) {
        return 0;
    }

    @Override
    public Order updateEntity(Order order) {
        return null;
    }

    @Override
    public Order findById(String id) {
        return null;
    }
}
