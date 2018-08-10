package com.hsbc.team4.ordersystem.dictionary;

import com.hsbc.team4.ordersystem.common.base.IBaseRepository;
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
    List<Dictionary> findByType(String type);
}
