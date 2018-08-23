package com.hsbc.team4.ordersystem.orders;

import lombok.Data;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


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
    private String id;
    @NotBlank(message = "The userId can not be empty")
    private String userId;
    @NotBlank(message = "The username can not be empty")
    private String username;
    @NotBlank(message = "The productId can not be empty")
    private String productId;
    @NotBlank(message = "The productName can not be empty")
    private String productName;
    private Integer orderStatus;
    @NotNull(message = "The productPrice can not be empty")
    private Double productPrice;
    private Double totalFree;

}
