package com.hsbc.team4.ordersystem.products;

import com.hsbc.team4.ordersystem.common.base.IBaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.products.repository
 * @Description :
 * @Date : 2018/8/2
 */
public interface IProductRepository extends IBaseRepository<Product,String> {

    /**
     * 根据产品类型查询理财产品
     * @param status
     * @param productType
     * @param pageable
     * @return Product
     */
    @Query(value = "select * from Product p where p.status=? and p.productType=?",nativeQuery =true)
    Page<Product> findByProductType(int status,String productType,Pageable pageable);

    /**
     * 模糊查询（根据产品类型）
     * @param status
     * @param productType
     * @param pageable
     * @return Product
     */
    @Query(value = "select * from Product p "
            +"where p.status=?1 and p.productType like CONCAT('%',?2,'%')",nativeQuery = true)
    Page<Product> findByProductTypeContains(int status,String productType, Pageable pageable);

    /**
     * 模糊查询(根据产品名称)
     * @param status
     * @param productName
     * @param pageable
     * @return
     */
    @Query(value = "select * from Product p "+
            "where p.status=?1 and p.productName like CONCAT('%',?2,'%')",nativeQuery = true)
    Page<Product> findByProductNameContains(int status,String productName,Pageable pageable);

    /**
     * 理财产品按照产品价格降序排序
     * @param status
     * @param pageable
     * @return
     */
    @Query(value = "select * from Product p where p.status=? order by p.productPrice desc",nativeQuery = true)
    Page<Product> orderByProductPrice(int status,Pageable pageable);

    @Query(value = "SELECT * FROM Product p where  p.productType=?1 and p.productName like %?2% or p.productNumber  like %?2%",nativeQuery=true)
    Page<Product> queryLike(String productType,String str, Pageable pageable);
}
