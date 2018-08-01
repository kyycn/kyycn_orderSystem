package com.hsbc.team4.ordersystem.common.base;

import org.springframework.data.domain.Page;

/**
 *
 * @author chenRenXun
 * @date 2018/4/18 0018
 */

public interface IBaseService<T> {
    /**
     * 分页获取数据
     * @param current 当前页
     * @param pageSize 每页显示的数据
     * @param status 状态
     * @return Page<T>
     */
    Page<T> findByStatus(int current, int pageSize, int status);

    /**
     * 添加实体类
     * @param t t
     * @return 实体对象
     *
     */
    T addEntity(T t);

    /**
     * 通过Id修改状态
     * @param id
     * @param status
     * @return
     */
    int updateStatusById(String id,int status);
    /**
     * 编辑实体类
     * @param t
     * @return T
     */
    T updateEntity(T t);

    /**
     * 通过Id查询
     * @param id
     * @return T
     */
    T findById(String id);

}
