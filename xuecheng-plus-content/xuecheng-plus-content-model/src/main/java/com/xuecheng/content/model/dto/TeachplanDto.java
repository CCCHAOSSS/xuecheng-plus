package com.xuecheng.content.model.dto;

import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.model.po.TeachplanMedia;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author limei
 * @data 2024/7/4
 * @description 课程计划信息的模型类
 */
@Data
@ToString
public class TeachplanDto extends Teachplan {
    // 与media关联的信息
    private TeachplanMedia teachplanMedia;

    //小章节list
    private List<TeachplanDto> teachPlanTreeNodes;
}
