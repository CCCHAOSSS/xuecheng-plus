package com.xuecheng.content.service;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.AddCourseDto;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.EditCourseDto;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;

/**
 * @author limei
 * @date 2024/3/20 15:02
 * @description 课程信息的接口
 */
public interface CourseBaseInfoService {

    //分页查询
    public PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto);

    /**
     * 新增课程
     * @param companyId 机构id
     * @param addCourseDto 课程信息
     * @return 课程详细信息
     * */
    public CourseBaseInfoDto createCourseBase(Long companyId  ,AddCourseDto addCourseDto);

    public CourseBaseInfoDto getCourseBaseInfo(Long courseId);

    public CourseBaseInfoDto updateCourseBase(Long companyId, EditCourseDto editCourseDto);

    void deleteCourseBase(Long courseId, Long companyId);
}
