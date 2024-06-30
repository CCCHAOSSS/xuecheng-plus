package com.xuecheng.content.api;

import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author limei
 * @date 2024/3/20 20:38
 * @description 管理课程分类 返回json
 */

@RestController
public class CourseCategoryController {

    @GetMapping("course-category/tree-nodes")
    public CourseCategoryTreeDto queryTreeNodes(){
        return null;
    }
}
