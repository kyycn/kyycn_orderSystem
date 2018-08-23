package com.hsbc.team4.ordersystem.orders;
import com.hsbc.team4.ordersystem.aop.annotations.HasLogin;
import com.hsbc.team4.ordersystem.common.adapt.BeanAdapter;
import com.hsbc.team4.ordersystem.common.utils.BeanValidator;
import com.hsbc.team4.ordersystem.common.utils.Global;
import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
import com.hsbc.team4.ordersystem.users.domain.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private final Global global ;

    @Autowired
    public OrderController(IOrderService orderService, BeanValidator beanValidator, BeanAdapter beanAdapter, ResponseResults responseResults,Global global) {
        this.orderService = orderService;
        this.beanValidator = beanValidator;
        this.beanAdapter = beanAdapter;
        this.responseResults = responseResults;
        this.global = global;
    }

    /**
    * @Description: Users place purchase orders.
    * @Param: [order]
    * @return: java.lang.String
    * @Author: Young
    * @Date: 2018/8/3
    */

    @PostMapping("/")
    @HasLogin(message = "you must be login")
    public ResponseResults addOrder(@RequestBody ArrayList<OrderDto> orderDtoList){
        Map<String, String> map = beanValidator.validateObject(orderDtoList);
        if (!CollectionUtils.isEmpty(map)){
            return responseResults.responseByErrors(map);
        }
        User user = global.getUserByToken();
        ArrayList<Orders> list = new ArrayList<>();
        for (OrderDto orderDto : orderDtoList) {
            Orders order = (Orders) beanAdapter.dtoAdapter(orderDto, new Orders());
            order.setCreateUsername(user.getUsername());
            order.setUpdateUsername(user.getUsername());
            order.setOrderStatus(1);
            list.add(order);
        }
        List<Orders> orders = orderService.addOrdersList(list);
        if (!CollectionUtils.isEmpty(orders)){
            return responseResults.responseBySuccess("ok！",orders);
        }else {
            return responseResults.responseByErrorMessage("Add failed,please try again!");
        }
    }

    /**
     * @Description: updateOrder.
     * @Param: [orderDto]
     * @return: com.hsbc.team4.ordersystem.common.utils.ResponseResults
     * @Author: Young
     * @Date: 2018/8/12
     */
    @ApiOperation(value = "updateOrder",notes = "the param is a orderDto",httpMethod = "PUT",response = ResponseResults.class)
    @PutMapping("/")
    @HasLogin(message = "you must be login")
    public  ResponseResults updateOrder(@RequestBody OrderDto orderDto){
        Map<String, String> map = beanValidator.validateObject(orderDto);
        if (!CollectionUtils.isEmpty(map)){
            return responseResults.responseByErrors(map);
        }
        Orders order = (Orders) beanAdapter.dtoAdapter(orderDto, new Orders());
        User user = global.getUserByToken();
        order.setUpdateUsername(user.getUsername());
        Orders orders = orderService.updateEntity(order);
        if (orders!=null){
            return responseResults.responseBySuccess("ok!",orders);
        }else {
            return responseResults.responseByErrorMessage("update  failed,please try again!");
        }
    }

    /**
     * @Description: deleteOrderById.
     * @Param: [id]
     * @return: com.hsbc.team4.ordersystem.common.utils.ResponseResults
     * @Author: Young
     * @Date: 2018/8/12
     */
    @ApiOperation(value = "deleteOrderById",notes = "the param is a id",httpMethod = "DELETE",response = ResponseResults.class)
    @DeleteMapping("/")
    public ResponseResults deleteOrderById(@RequestParam(name = "id") String id){
        int result = orderService.updateStatusById(id, 1);
        if (result>0){
            return responseResults.responseBySuccessMessage("ok!");
        }else {
            return responseResults.responseByErrorMessage("delete failed,please try again!");
        }
    }

    /**
     * @Description: getOrderById.
     * @Param: [id]
     * @return: com.hsbc.team4.ordersystem.common.utils.ResponseResults
     * @Author: Young
     * @Date: 2018/8/12
     */
    @ApiOperation(value = "getOrderById",notes = "the param is a id",httpMethod = "GET",response = ResponseResults.class)
    @GetMapping("/")
    public ResponseResults getOrderById(@RequestParam(name = "id") String id){
        Orders order = orderService.findById(id);
        if (order!=null){
            return responseResults.responseBySuccess("ok!",order);
        }else {
            return responseResults.responseByErrorMessage("query Failure,please try again！");
        }
    }

    /**
     * @Description: getOrderList.
     * @Param: [current, pageSize, status]
     * @return: com.hsbc.team4.ordersystem.common.utils.ResponseResults
     * @Author: Young
     * @Date: 2018/8/12
     */
    @ApiOperation(value = "getOrderList",notes = "the param is the status",httpMethod = "GET",response = ResponseResults.class)
    @GetMapping("/status")
    public ResponseResults getOrderList(@RequestParam(name = "current",defaultValue = "0") int current,
                                        @RequestParam(name = "pageSize",defaultValue = "10") int pageSize,
                                        @RequestParam(name = "status") int status){
        Page<Orders> orders = orderService.findByStatus(current, pageSize, status);
        if (orders!=null){
            return responseResults.responseBySuccess("ok!",orders);
        } else {
            return responseResults.responseByErrorMessage("getOrderList Failure,please try again.");
        }
    }

    /**
     * @Description: getOrderListByOrderStatus.
     * @Param: [current, pageSize, orderStatus]
     * @return: com.hsbc.team4.ordersystem.common.utils.ResponseResults
     * @Author: Young
     * @Date: 2018/8/12
     */
    @ApiOperation(value = "getOrderListByOrderStatus",notes = "the param is the orderStatus",httpMethod = "GET",response = ResponseResults.class)
    @GetMapping("/orderStatus")
    public ResponseResults getOrderListByOrderStatus(@RequestParam(name = "current",defaultValue = "0") int current,
                                                     @RequestParam(name = "pageSize",defaultValue = "10") int pageSize,
                                                     @RequestParam(name = "orderStatus") int orderStatus){
        Page<Orders> orders = orderService.findByOrderStatus(current, pageSize, orderStatus);
        if (orders!=null){
            return responseResults.responseBySuccess("ok!",orders);
        } else {
            return responseResults.responseByErrorMessage("getOrderList Failure,please try again.");
        }
    }

    @ApiOperation(value = "listUserOrders",notes = "the param is the userId",httpMethod = "GET",response = ResponseResults.class)
    @GetMapping("/listUserOrders")
    public ResponseResults listUserOrders(@RequestParam(name = "current",defaultValue = "0") int current,
                                                     @RequestParam(name = "pageSize",defaultValue = "10") int pageSize,
                                                     @RequestParam(name = "userId") String userId){
        Page<Orders> orders = orderService.findByUserIdAndStatus(current, pageSize, userId,0);
        if (orders!=null){
            return responseResults.responseBySuccess("ok!",orders);
        } else {
            return responseResults.responseByErrorMessage("getOrderList Failure,please try again.");
        }
    }
}
