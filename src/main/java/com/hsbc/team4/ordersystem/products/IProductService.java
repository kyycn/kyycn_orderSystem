package com.hsbc.team4.ordersystem.products;

import com.hsbc.team4.ordersystem.common.base.IBaseService;
import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
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

    /**
     * 根据产品类型查询理财产品，分页展示
     * @param current
     * @param status
     * @param productType
     * @return Product
     */
    Page<Product> findByProductType(int current, int status, String productType);

    /**
     * 模糊查询（产品类型）
     * @param current
     * @param pageSize
     * @param status
     * @param productType
     * @return Product
     */
    Page<Product> findByProductTypeContains(int current,int pageSize,int status,String productType);

    /**
     * 模糊查询（产品名称）
     * @param current
     * @param pageSize
     * @param status
     * @param productName
     * @return Product
     */
    Page<Product> findByProductNameContains(int current,int pageSize,int status,String productName);

    /**
     * orderByProductPrice desc
     * @param current
     * @param pageSize
     * @param status
     * @return
     */
    Page<Product> orderByProductPrice(int current,int pageSize,int status);
      Product query(String id);
}
