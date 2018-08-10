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
    * @Description: 根据订单的支付状态查询分页数据。
    * @Param: orderStatus 订单支付状态。
    * @Author: Young
    * @Date: 2018/8/9 
    */
    Page<Orders> findByOrderStatus(int orderStatus, Pageable pageable);
}
