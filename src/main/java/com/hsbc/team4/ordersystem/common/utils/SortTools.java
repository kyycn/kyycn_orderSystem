package com.hsbc.team4.ordersystem.common.utils;

import org.springframework.data.domain.Sort;

/**
 * @author:Kevin
 * @version:
 * @Project: permission_manage
 * @Package: com.hsbc.dev.teamo4.sms.common.utils.data
 * @Description: BeanValidator
 * @Date date: 2018/7/28
 */
public class SortTools {
    /**
     * 获取基础的排序对象
     *
     * @param orderType
     * @param orderField
     * @return
     */
    public static Sort basicSort(String orderType, String orderField) {
        return new Sort(Sort.Direction.fromString(orderType), orderField);
    }

    /**
     * 按添加时间降序排序
     *
     * @return
     */
    public static Sort addTimeSortForDesc() {
        return basicSort("DESC", "createTime");
    }


      /**
     * 按删除时间降序排序
     *
     * @return
     */
    public static Sort deletedTimeSortForDesc() {
        return basicSort("DESC", "deletedTime");
    }

}
