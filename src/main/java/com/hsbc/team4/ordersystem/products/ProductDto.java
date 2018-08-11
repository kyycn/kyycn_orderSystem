package com.hsbc.team4.ordersystem.products;

import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.products.dto
 * @Description :
 * @Date : 2018/8/2
 */
@Data
public class ProductDto {
    @Id
    private String id;
    @NotBlank(message = "The productName can not be empty")
    private String productName;
    @NotNull(message = "The productPrice can not be empty")
    private double productPrice;
    @NotBlank(message = "The productDescription can not be empty")
    private String productDescription;
    @NotBlank(message = "the productIcon cannot be empty")
    private String productIcon;
    @NotBlank(message = "The productType can not be empty")
    private String productType;
    @NotBlank(message = "The condidtion can not be empty")
    private String condidtion;
    @NotBlank(message = "The standar can not be empty")
    private String standar;
    @NotBlank(message = "The Type can not be empty")
    private String Type;
}
