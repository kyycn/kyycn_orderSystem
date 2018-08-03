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
    @NotBlank(message = "The name can not be empty")
    private String name;
    @NotBlank(message = "The type can not be empty")
    private String description;
    @NotBlank(message = "The color can not be empty")
    private String pic;
    @NotNull(message = "The price can not be empty")
    private double price;
    @NotBlank(message = "The color can not be empty")
    private String standar;
    @NotBlank(message = "The color can not be empty")
    private String condidtion;
}
