package com.hsbc.team4.ordersystem.products;

import com.hsbc.team4.ordersystem.common.base.IBaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.products.repository
 * @Description :
 * @Date : 2018/8/2
 */
public interface IProductRepository extends IBaseRepository<Product,String> {

}
