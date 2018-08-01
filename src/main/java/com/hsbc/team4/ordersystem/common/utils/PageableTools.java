package com.hsbc.team4.ordersystem.common.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * 分页工具类
 * @author Kevin
 */
public class PageableTools {

    /**
     * 获取基础分页对象
     * @param page  获取第几页
     * @param size  每页条数
     * @return
     */
    public static Pageable basicPage(Integer page, Integer size) {
        page = (page==null || page<0)?0:page;
        size = (size==null || size<=0)?10:size;
        return new PageRequest(page, size);
    }

    /**
     * 获取基础分页对象，每页条数默认15条
     * @param page 获取第几页
     * @return
     */
    public static Pageable basicPage(Integer page) {
        return basicPage(page, 0);
    }

    /**
     * 获取按添加时间降序排序和分页的对象
     *
     * @param page
     * @param size
     * @return
     */
    public static Pageable addTimeSortForDescAndPage(Integer page, Integer size) {
        Sort sort = SortTools.addTimeSortForDesc();
        page = (page == null || page < 0) ? 0 : page;
        size = (size == null || size <= 0) ? 10 : size;
        return new PageRequest(page, size, sort);
    }

    /**
     * 获取按删除时间降序排序和分页的对象
     *
     * @param page
     * @param size
     * @return
     */
    public static Pageable deletedTimeSortForDescAndPage(Integer page, Integer size) {
        Sort sort = SortTools.deletedTimeSortForDesc();
        page = (page == null || page < 0) ? 0 : page;
        size = (size == null || size <= 0) ? 10 : size;
        return new PageRequest(page, size, sort);
    }


}
