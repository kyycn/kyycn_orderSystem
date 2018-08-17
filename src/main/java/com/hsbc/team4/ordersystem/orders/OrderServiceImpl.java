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

    /**
     * @Description: find Order By Status.
     * @Param: [current, pageSize, status]
     * @return: org.springframework.data.domain.Page<com.hsbc.team4.ordersystem.orders.Orders>
     * @Author: Young
     * @Date: 2018/8/12
     */
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
        Double totalFree = order.getProductPrice()*order.getProductCount();
        order.setTotalFree(totalFree);
        order.setOrderStatus(0);
        return orderRepository.save(order);
    }

    /**
     * @Description: Update the order status by id.
     * @Param: [id, status]
     * @return: int
     * @Author: Young
     * @Date: 2018/8/12
     */
    @Override
    public int updateStatusById(String id, int status) {
        return orderRepository.updateStatusById(id,status);
    }

    /**
     * @Description: Update the order.
     * @Param: [order]
     * @return: com.hsbc.team4.ordersystem.orders.Orders
     * @Author: Young
     * @Date: 2018/8/12
     */
    @Override
    public Orders updateEntity(Orders order) {
        return orderRepository.saveAndFlush(order);
    }

    /**
     * @Description: Find the order by id.
     * @Param: [id]
     * @return: com.hsbc.team4.ordersystem.orders.Orders
     * @Author: Young
     * @Date: 2018/8/12
     */
    @Override
    public Orders findById(String id) {
        return orderRepository.findByEntityId(id);
    }

    /**
     * @Description: Get order list by order status.
     * @Param: [current, pageSize, orderStatus]
     * @return: org.springframework.data.domain.Page<com.hsbc.team4.ordersystem.orders.Orders>
     * @Author: Young
     * @Date: 2018/8/12
     */
    @Override
    public Page<Orders> findByOrderStatus(int current, int pageSize, int orderStatus) {
        Pageable pageable= PageableTools.addTimeSortForDescAndPage(current,pageSize);
        return orderRepository.findByOrderStatus(orderStatus,pageable);
    }

    /**
     * @Description: Bulk add orders.
     * @Param: [orderList]
     * @return: java.util.List<com.hsbc.team4.ordersystem.orders.Orders>
     * @Author: Young
     * @Date: 2018/8/12
     */
    @Override
    public List<Orders> addOrdersList(List<Orders> orderList) {
        for (Orders order : orderList) {
            order.setId(uuidFactory.getUUID());
            Double totalFree = order.getProductPrice()*order.getProductCount();
            order.setTotalFree(totalFree);
            order.setOrderStatus(0);
        }
        return orderRepository.saveAll(orderList);
    }
}
