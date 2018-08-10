package com.hsbc.team4.ordersystem.users.repository;

import com.hsbc.team4.ordersystem.common.base.IBaseRepository;
import com.hsbc.team4.ordersystem.products.Product;
import com.hsbc.team4.ordersystem.users.domain.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author:yang
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem.users.repository
 * @Description:
 * @Date date: 2018/8/9
 */
@Repository
public interface IFavouriteRepository extends JpaRepository<Favourite,String> {

    @Query(value="select * from Product where id in (select proId from favourite where userId=?1)", nativeQuery = true)
     public List<Product> findByUserId(String userId);

    public Favourite findByUserIdAndProId(String userId,String proId);
}
