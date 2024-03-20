package com.xuecheng.base.model;

import lombok.Data;
import lombok.ToString;

/**
 * @author limei
 * @date 2024/3/20 9:20
 * @description 分页查询的参数
 */
@Data
@ToString
public class PageParams {

    //当前页码
    private Long pageNo = 1L;

    // 每页记录数目 默认10
    private Long pageSize = 10L;


    public PageParams() {
    }

    public PageParams(Long pageNo, Long pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }
}
