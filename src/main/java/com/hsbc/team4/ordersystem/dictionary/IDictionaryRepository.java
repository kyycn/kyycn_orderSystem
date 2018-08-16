package com.hsbc.team4.ordersystem.dictionary;

import com.hsbc.team4.ordersystem.common.base.IBaseRepository;
import com.hsbc.team4.ordersystem.products.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author:Cady
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem.dictionary
 * @Description:
 * @Date date: 2018-08-09
 */
@Repository
public interface IDictionaryRepository extends IBaseRepository<Dictionary, String> {

    @Query(value = "select * from Dictionary d where d.type=?",nativeQuery =true)
    Page<Dictionary> findByType(String type, Pageable pageable);;

}
