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
    @NotBlank(message = "The userId can not be empty")
    private String userId;
    @NotBlank(message = "The productId can not be empty")
    private String productId;
    @NotNull(message = "The productCount can not be empty")
    private Integer productCount;
    @NotNull(message = "The orderStatus can not be empty")
    private Integer orderStatus;
    @NotNull(message = "The price can not be empty")
    private Double price;
    private Double totalFree;


}
