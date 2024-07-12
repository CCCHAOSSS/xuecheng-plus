package com.xuecheng.content.api;

import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.service.CourseTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author limei
 * @data 2024/7/11
 * @description 教师管理
 */

@Slf4j
@RestController
@Api(value = "教师管理相关接口", tags = "教师管理相关接口")
public class CourseTeacherController {
    @Autowired
    private CourseTeacherService courseTeacherService;

    /**
     * 查询教师信息
     * */
    @ApiOperation("教师信息查询接口")
    @GetMapping("/courseTeacher/list/{courseId}")
    public List<CourseTeacher> getCourseTeacherList(@PathVariable Long courseId){
        return courseTeacherService.getCourseTeacherList(courseId);
    }

    /**
     * 添加、修改教师信息
     * */
    @ApiOperation("添加、修改教师信息")
    @PostMapping("/courseTeacher")
    public CourseTeacher saveCourseTeacher(@RequestBody CourseTeacher courseTeacher){
        return courseTeacherService.saveCourseTeacher(courseTeacher);
    }

    /**
     * 删除教师
     * */
    @ApiOperation("删除教师")
    @DeleteMapping("/courseTeacher/course/{courseId}/{teacherId}")
    public void deleteCourseTeacher(@PathVariable Long courseId, @PathVariable Long teacherId){
        courseTeacherService.deleteCourseTeacher(courseId,teacherId);
    }
}
