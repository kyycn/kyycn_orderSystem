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
      * @Description: 根据订单的支付状态分页获取订单数据。
      * @Param: current 当前页。
      * @Param: pageSize 每页显示的数据。
      * @Param: orderStatus 订单支付状态。
      * @return: org.springframework.data.domain.Page<com.hsbc.team4.ordersystem.orders.Orders>
      * @Author: Young
      * @Date: 2018/8/8
      */
     Page<Orders> findByOrderStatus(int current, int pageSize, int orderStatus);
    /**
    * @Description:  批量添加订单。
    * @Param:  orderList 订单列表。
    * @return:  添加成功的订单列表。
    * @Author: Young
    * @Date: 2018/8/8 
    */
     List<Orders> addOrdersList(List<Orders> orderList);
}
