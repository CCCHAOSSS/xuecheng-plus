package com.xuecheng.content.service;

import com.xuecheng.content.model.po.CourseTeacher;

import java.util.List;

/**
 * @author limei
 * @data 2024/7/11
 * @description 教师管理接口
 */
public interface CourseTeacherService {
    List<CourseTeacher> getCourseTeacher(Long courseId);
}
