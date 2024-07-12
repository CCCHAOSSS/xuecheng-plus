package com.xuecheng.content.service.impl;

import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.service.CourseTeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author limei
 * @data 2024/7/11
 * @description 教师管理接口实现
 */
@Service
public class CourseTeacherServiceImpl implements CourseTeacherService {


    /**
     * 查询课程的教师信息
     * */
    @Override
    public List<CourseTeacher> getCourseTeacher(Long courseId) {
        return null;
    }
}
