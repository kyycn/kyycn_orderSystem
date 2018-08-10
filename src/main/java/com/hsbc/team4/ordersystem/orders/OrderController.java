package com.hsbc.team4.ordersystem.orders;
import com.hsbc.team4.ordersystem.common.adapt.BeanAdapter;
import com.hsbc.team4.ordersystem.common.utils.BeanValidator;
import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: Young
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem.controller
 * @Date date: 2018/8/2
 */
@RestController
@RequestMapping("orders")
public class OrderController {

    private final IOrderService orderService;
    private final BeanValidator beanValidator;
    private final BeanAdapter beanAdapter;
    private final ResponseResults responseResults;

    @Autowired
    public OrderController(IOrderService orderService, BeanValidator beanValidator, BeanAdapter beanAdapter, ResponseResults responseResults) {
        this.orderService = orderService;
        this.beanValidator = beanValidator;
        this.beanAdapter = beanAdapter;
        this.responseResults = responseResults;
    }

    /**
    * @Description: Users place purchase orders.
    * @Param: [order]
    * @return: java.lang.String
    * @Author: Young
    * @Date: 2018/8/3
    */
    @PostMapping("/")
    public ResponseResults addOrder(@RequestBody ArrayList<OrderDto> orderDtoList){
        beanValidator.validateObject(orderDtoList);
        ArrayList<Orders> list = new ArrayList<>();
        for (OrderDto orderDto : orderDtoList) {
            Orders order = (Orders) beanAdapter.dtoAdapter(orderDto, new Orders());
            list.add(order);
        }
        List<Orders> orders = orderService.addOrdersList(list);
        if (!CollectionUtils.isEmpty(orders)){
            return responseResults.responseBySuccess("添加订单成功！",orders);
        }else {
            return responseResults.responseByErrorMessage("添加失败!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseResults deleteOrderById(@PathVariable String id){
        int result = orderService.updateStatusById(id, 1);
        if (result>0){
            return responseResults.responseBySuccessMessage("删除成功!");
        }else {
            return responseResults.responseByErrorMessage("删除失败！");
        }
    }

    @GetMapping("/{id}")
    public ResponseResults getOrderById(@PathVariable String id){
        Orders order = orderService.findById(id);
        if (order!=null){
            return responseResults.responseBySuccess(order);
        }else {
            return responseResults.responseByErrorMessage("订单不存在！");
        }
    }

    @GetMapping("/status/{status}")
    public ResponseResults getOrderList(@RequestParam(name = "current",defaultValue = "0") int current,
                                        @RequestParam(name = "pageSize",defaultValue = "10") int pageSize,
                                        @PathVariable int status){
       return responseResults.responseBySuccess(orderService.findByStatus(current,pageSize,status));
    }

    @GetMapping("/orderStatus/{orderStatus}")
    public ResponseResults getOrderListByOrderStatus(@RequestParam(name = "current",defaultValue = "0") int current,
                                        @RequestParam(name = "pageSize",defaultValue = "10") int pageSize,
                                        @PathVariable int orderStatus){
        return responseResults.responseBySuccess(orderService.findByOrderStatus(current,pageSize,orderStatus));
    }
}
