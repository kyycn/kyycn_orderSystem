package com.hsbc.team4.ordersystem.users;

import com.hsbc.team4.ordersystem.common.base.IBaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Kevin
 * @version :
 * @Project :ordersystem
 * @Package : com.hsbc.team4.ordersystem.products.repository
 * @Description :
 * @Date : 2018/8/1
 */
@Repository
public interface IUserRepository extends JpaRepository<User,String> {
}
