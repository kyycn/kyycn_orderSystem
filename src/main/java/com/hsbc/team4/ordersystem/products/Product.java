package com.hsbc.team4.ordersystem.products;

import com.hsbc.team4.ordersystem.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.products.domain
 * @Description :
 * @Date : 2018/8/1
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Product extends BaseEntity{
    @Id
    private String id;
    @NotBlank(message = "The name can not be empty")
    private String name;
    @NotBlank(message = "The description can not be empty")
    private String description;
    @NotBlank(message = "The pic can not be empty")
    private String pic;
    @NotNull(message = "The price can not be empty")
    private double price;
    @NotBlank(message = "The standar can not be empty")
    private String standar;
    @NotBlank(message = "The condidtion can not be empty")
    private String condidtion;
    /**
     * adapt to product
     * @param productDto
     * @return
     */
    public static Product adaptProduct(ProductDto productDto){
        Product product=new Product();
        BeanUtils.copyProperties(productDto,product);
        return product;
    }



}
