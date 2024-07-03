package com.xuecheng.content.api;

import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.service.CourseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author limei
 * @date 2024/3/20 20:38
 * @description 管理课程分类 返回json
 */

@RestController
public class CourseCategoryController {

    @Autowired
    CourseCategoryService courseCategoryService;

    /**
     * 查询课程分类
     * */
    @GetMapping("course-category/tree-nodes")
    public List<CourseCategoryTreeDto> queryTreeNodes(){
        //放入根节点即可
        return courseCategoryService.queryTreeNodes("1");
    }
}
