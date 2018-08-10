package com.hsbc.team4.ordersystem.common.base;

import com.hsbc.team4.ordersystem.products.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;


/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.common.base
 * @Description :
 * @Date : 2018/8/1
 */
@NoRepositoryBean
public interface IBaseRepository<T,ID extends Serializable> extends JpaRepository<T,ID> {
    /**
     * findByEntityId
     * @param id entity ID
     * @return T
     */
    @Query(value = "SELECT * FROM #{#entityName} t  where t.id=?1",nativeQuery=true)
    T findByEntityId(String id) ;

    /**
     * findByStatus
     * @param status
     * @param pageable
     * @return Page<T>
     */
    Page<T> findByStatus(int status, Pageable pageable);

    /**
     * updateStatusById
     * @param id
     * @param status
     * @return int
     */
    @Query(value = "update #{#entityName} t set t.status=?2 where t.id=?1",nativeQuery=true)
    @Modifying
    @Transactional
    int updateStatusById(String id, int status);




}
