package com.hsbc.team4.ordersystem.log;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.log
 * @Description :
 * @Date : 2018/8/6
 */
@Repository
public interface ILogRepository extends JpaRepository<Log,String>{
    /**
     * findByOperateType
     * @param operateType
     * @param pageable
     * @return
     */
    Page<Log> findByOperateTypeContains(String operateType, Pageable pageable);

    /**
     *  pageable
     * @param pageable
     * @return Page<Log>
     */
    @Override
    Page<Log> findAll(Pageable pageable);

}
