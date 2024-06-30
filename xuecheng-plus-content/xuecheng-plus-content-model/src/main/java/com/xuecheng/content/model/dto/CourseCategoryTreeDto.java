package com.xuecheng.content.model.dto;

import com.xuecheng.content.model.po.CourseCategory;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * @author limei
 * @date 2024/3/20 20:34
 * @description 树形课程目录模型类
 */

@Data
public class CourseCategoryTreeDto extends CourseCategory implements Serializable {

    //因为是要被别人访问构成目录的，所以不用声明为private
    List<CourseCategoryTreeDto> childrenTreeNodes;

}
