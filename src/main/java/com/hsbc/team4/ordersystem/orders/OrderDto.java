package com.hsbc.team4.ordersystem.orders;

import lombok.Data;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.hsbc.team4.ordersystem.orders.Order;

/**
 * @Description:
 * @author:Young
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem.dto
 * @Date date: 2018/8/3
 */
@Data
public class OrderDto {
    @Id
    private String orderId;
    @NotBlank(message = "The products can not be empty")
    private String productId;
    @NotNull(message = "The productCount can not be empty")
    private Integer productCount;

}
