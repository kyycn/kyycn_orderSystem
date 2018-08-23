package com.hsbc.team4.ordersystem.orders;
import com.hsbc.team4.ordersystem.common.base.IBaseService;
import org.springframework.data.domain.Page;
import java.util.List;

/**
 * @Description: OrderService interface.
 * @author:Young
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem.service
 * @Date date: 2018/8/2
 */
public interface IOrderService extends IBaseService<Orders>{
     /**
      * @Description: Get order data according to the payment status page of the order.
      * @Param: current: The current page.
      * @Param: pageSize: Data displayed per page.
      * @Param: orderStatus: Order payment status.
      * @return: org.springframework.data.domain.Page<com.hsbc.team4.ordersystem.orders.Orders>
      * @Author: Young
      * @Date: 2018/8/8
      */
     Page<Orders> findByOrderStatus(int current, int pageSize, int orderStatus);
    /**
    * @Description:  Bulk add orders.
    * @Param:  orderList: List of orders to add.
    * @return: List of orders added successfully.
    * @Author: Young
    * @Date: 2018/8/8 
    */
     List<Orders> addOrdersList(List<Orders> orderList);

    /**
     * @Description: Query the user order list.
     * @Param: [userId, status]
     * @return: java.util.List<com.hsbc.team4.ordersystem.orders.Orders>
     * @Author: Young
     * @Date: 2018/8/20
     */
    Page<Orders> findByUserIdAndStatus(int current, int pageSize, String userId, int status);
}
