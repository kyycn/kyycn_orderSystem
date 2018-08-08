package com.hsbc.team4.ordersystem.log;

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
}
