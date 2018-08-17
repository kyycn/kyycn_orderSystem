package com.hsbc.team4.ordersystem.orders;
import com.hsbc.team4.ordersystem.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
/**
 * @Description:
 * @author: Young
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem.domain
 * @Date date: 2018/8/2
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Orders extends BaseEntity {
    @Id
    private String id;
    @NotBlank(message = "The createUsername can not be empty")
    private String createUsername;
    @NotBlank(message = "The productName can not be empty")
    private String productName;
    @NotNull(message = "The productCount can not be empty")
    private Integer productCount;
    @NotNull(message = "The orderStatus can not be empty")
    private Integer orderStatus;
    @NotNull(message = "The productPrice can not be empty")
    private Double productPrice;
    private Double totalFree;


}
