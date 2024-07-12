package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.mapper.CourseTeacherMapper;
import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.service.CourseTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author limei
 * @data 2024/7/11
 * @description 教师管理接口实现
 */
@Service
public class CourseTeacherServiceImpl implements CourseTeacherService {


    @Autowired
    private CourseTeacherMapper courseTeacherMapper;


    /**
     * 查询课程的教师信息
     * */
    @Override
    public List<CourseTeacher> getCourseTeacherList(Long courseId) {
        if (courseId == null){
            XueChengPlusException.cast("课程id不存在");
        }
        LambdaQueryWrapper<CourseTeacher> wrapper = new LambdaQueryWrapper();
        wrapper.eq(CourseTeacher::getCourseId,courseId);
        List<CourseTeacher> courseTeachers = courseTeacherMapper.selectList(wrapper);
        return courseTeachers;
    }
}
