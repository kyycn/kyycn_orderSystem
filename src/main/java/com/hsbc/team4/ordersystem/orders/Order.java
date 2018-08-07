package com.hsbc.team4.ordersystem.orders;

import com.hsbc.team4.ordersystem.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description:
 * @author:Young
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem.domain
 * @Date date: 2018/8/2
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Order extends BaseEntity{
    @Id
    private String orderId;
    @NotBlank(message = "The userId can not be empty")
    private String userId;
    @NotBlank(message = "The productId can not be empty")
    private String productId;
    @NotNull(message = "The productCount can not be empty")
    private Integer productCount;
    @NotNull(message = "The orderStatus can not be empty")
    private Boolean orderStatus;



        /**
        * @Description: adapt to OrderDto.
        * @Param: [orderDto]
        * @return: com.hsbc.team4.ordersystem.orders.Order
        * @Author: Young
        * @Date: 2018/8/3
        */
    public static Order adaptOrder(OrderDto orderDto){
        Order order = new Order();
        BeanUtils.copyProperties(orderDto,order);
        return order;
    }
}
