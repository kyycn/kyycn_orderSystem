package com.hsbc.team4.ordersystem.dictionary;

import com.hsbc.team4.ordersystem.common.base.IBaseService;

import java.util.List;

/**
 * @author:Cady
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem.dictionary
 * @Description:
 * @Date date: 2018-08-09
 */
public interface IDictionaryService extends IBaseService<Dictionary> {
    List<Dictionary> findByType(int current, String type);
}
