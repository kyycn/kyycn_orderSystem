package com.hsbc.team4.ordersystem.orders;

import com.hsbc.team4.ordersystem.common.base.IBaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
/**
 * @Description:
 * @author:Young
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem.dao
 * @Date date: 2018/8/2
 */
@Repository
public interface IOrderRepository extends IBaseRepository<Orders,String>{
    
    /**
    * @Description: Query the paging data according to the payment status of the order.
    * @Param: orderStatus: Order payment status.
    * @Author: Young
    * @Date: 2018/8/9 
    */
    Page<Orders> findByOrderStatus(int orderStatus, Pageable pageable);

    /**
     * @Description: Query the user order list.
     * @Param: [username, status]
     * @return: java.util.List<com.hsbc.team4.ordersystem.orders.Orders>
     * @Author: Young
     * @Date: 2018/8/20
     */
    Page<Orders> findByUserIdAndStatus(String userId, int status, Pageable pageable);
}
