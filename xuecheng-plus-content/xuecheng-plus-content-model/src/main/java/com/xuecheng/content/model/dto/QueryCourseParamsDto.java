package com.xuecheng.content.model.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * @author limei
 * @date 2024/3/20 9:25
 * @description 数据传输对象，课程查询参数Dto
 */

@Data
@ToString
public class QueryCourseParamsDto {
    /**
     * 根据接口文档设计
     * */
    //审核状态
    private String autitStatus;

    //课程名称
    private  String courseName;

    //发布状态
    private  String publishStatus;

}
