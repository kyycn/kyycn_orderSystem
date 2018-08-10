package com.hsbc.team4.ordersystem.orders;
import com.hsbc.team4.ordersystem.common.factory.UUIDFactory;
import com.hsbc.team4.ordersystem.common.utils.PageableTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

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
    private IOrderRepository orderRepository;
    private UUIDFactory uuidFactory;
    @Autowired
    public OrderServiceImpl(IOrderRepository orderRepository,UUIDFactory uuidFactory) {
        this.uuidFactory = uuidFactory;
        this.orderRepository = orderRepository;
    }

    @Override
    public Page<Orders> findByStatus(int current, int pageSize, int status) {
        Pageable pageable= PageableTools.addTimeSortForDescAndPage(current,pageSize);
        return orderRepository.findByStatus(status,pageable);
    }

    /**
     * @Description: Add the order.
     * @Param: [order]
     * @return: com.hsbc.team4.ordersystem.orders.Order
     * @Author: Young
     * @Date: 2018/8/3
     */
    @Override
    public Orders addEntity(Orders order) {
        order.setId(uuidFactory.getUUID());
        order.setCreateTime(System.currentTimeMillis());
        Double totalFree = order.getPrice()*order.getProductCount();
        order.setTotalFree(totalFree);
        order.setOrderStatus(1);
        return orderRepository.save(order);
    }

    @Override
    public int updateStatusById(String id, int status) {
        return orderRepository.updateStatusById(id,status);
    }

    @Override
    public Orders updateEntity(Orders order) {
        return orderRepository.saveAndFlush(order);
    }

    @Override
    public Orders findById(String id) {
        return orderRepository.findByEntityId(id);
    }

    @Override
    public Page<Orders> findByOrderStatus(int current, int pageSize, int orderStatus) {
        Pageable pageable= PageableTools.addTimeSortForDescAndPage(current,pageSize);
        return orderRepository.findByOrderStatus(orderStatus,pageable);
    }

    @Override
    public List<Orders> addOrdersList(List<Orders> orderList) {
        for (Orders order : orderList) {
            order.setId(uuidFactory.getUUID());
            order.setCreateTime(System.currentTimeMillis());
            Double totalFree = order.getPrice()*order.getProductCount();
            order.setTotalFree(totalFree);
            order.setOrderStatus(1);
            order.setStatus(1);
        }
        return orderRepository.saveAll(orderList);
    }
}
