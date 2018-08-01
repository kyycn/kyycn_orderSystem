package com.hsbc.team4.ordersystem.domain;

import com.hsbc.team4.ordersystem.common.base.BaseEntity;
import com.hsbc.team4.ordersystem.dto.ProductDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.util.BeanDefinitionUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.domain
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
    @NotBlank(message = "The type can not be empty")
    private String type;
    @NotBlank(message = "The color can not be empty")
    private String color;
    @NotNull(message = "The price can not be empty")
    private double price;

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
