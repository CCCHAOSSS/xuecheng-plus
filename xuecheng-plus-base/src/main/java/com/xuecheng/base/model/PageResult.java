package com.xuecheng.base.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author limei
 * @date 2024/3/20 9:30
 * @description 分页查询的结果模型类
 */
@Data
@ToString
public class PageResult<T> implements Serializable {

    private List<T> items;

    //总记录数
    private long counts;

    // 当前页码
    private long pages;

   // 每页记录数
    private long pageSize;

    public PageResult(List<T> items, long counts, long pages, long pageSize) {
        this.items = items;
        this.counts = counts;
        this.pages = pages;
        this.pageSize = pageSize;
    }



}
