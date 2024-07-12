package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.mapper.CourseTeacherMapper;
import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.service.CourseTeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    private CourseBaseMapper courseBaseMapper;


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

    /**
     * 添加、修改教师信息
     * */
    @Transactional
    @Override
    public CourseTeacher saveCourseTeacher(CourseTeacher courseTeacher) {
        //通过课程id判断是添加还是修改教师信息
        Long teacherId = courseTeacher.getId();
        if (teacherId == null){
            //说明是新增
            CourseTeacher teacher = new CourseTeacher();
            BeanUtils.copyProperties(courseTeacher,teacher);
            teacher.setCreateDate(LocalDateTime.now());
            int flag = courseTeacherMapper.insert(teacher);
            if (flag <= 0){
                XueChengPlusException.cast("新增失败");
            }
            return getCourseTeacher(courseTeacher);
        }else {
            // 不为null，说明是修改
            CourseTeacher teacher = new CourseTeacher();
            BeanUtils.copyProperties(courseTeacher,teacher);
            int flag = courseTeacherMapper.updateById(teacher);
            if (flag <= 0){
                XueChengPlusException.cast("修改失败");
            }
            return getCourseTeacher(courseTeacher);
        }
    }

    /**
     * 删除教师信息
     * */
    @Override
    public void deleteCourseTeacher(Long courseId, Long teacherId) {
        if (courseId == null || teacherId == null){
            XueChengPlusException.cast("课程id和教师id不能为空");
        }
        LambdaQueryWrapper<CourseTeacher> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CourseTeacher::getCourseId,courseId)
                .eq(CourseTeacher::getId,teacherId);
        int flag = courseTeacherMapper.delete(wrapper);
        if(flag < 0){
            XueChengPlusException.cast("删除失败");
        }

    }

    public CourseTeacher getCourseTeacher(CourseTeacher courseTeacher) {
        return courseTeacherMapper.selectById(courseTeacher.getId());
    }
}
