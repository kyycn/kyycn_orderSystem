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
    @NotBlank(message = "The issueDate can not be empty")
    private String issueDate;
    @NotBlank(message = "The yieldRate can not be empty")
    private String yieldRate;
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
