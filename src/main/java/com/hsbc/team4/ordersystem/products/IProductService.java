package com.hsbc.team4.ordersystem.products;

import com.hsbc.team4.ordersystem.common.base.IBaseService;
import com.hsbc.team4.ordersystem.products.Product;
import org.springframework.data.domain.Page;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.products.service
 * @Description :
 * @Date : 2018/8/2
 */
public interface IProductService extends IBaseService<Product> {

    Page<Product> findByName(int current, int pageSize, int status, String name);
    Page<Product> findByVagueType(int current,int pageSize,int status,String type);
}
