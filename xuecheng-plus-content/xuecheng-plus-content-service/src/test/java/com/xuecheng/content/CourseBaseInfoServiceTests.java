package com.xuecheng.content;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.service.CourseBaseInfoService;
import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author limei
 * @date 2024/3/20 15:12
 * @description
 */

@SpringBootTest
public class CourseBaseInfoServiceTests {

    @Autowired      //测试啥就注入啥
    CourseBaseInfoService courseBaseInfoService;


    /**
     * 单元测试：测试CourseBaseInfoServiceImpl
     * */
    @Test
    public void testCourseBaseInfoService(){

        //查询条件
        QueryCourseParamsDto courseParamsDto = new QueryCourseParamsDto();
        courseParamsDto.setCourseName("Spring");  //查询的课程名称（假定为java
        courseParamsDto.setPublishStatus("002001");

        //分页参数对象
        PageParams pageParams = new PageParams();
        pageParams.setPageNo(1L);
        pageParams.setPageSize(10L);

        PageResult<CourseBase> courseBasePageResult = courseBaseInfoService.queryCourseBaseList(pageParams, courseParamsDto);

//        System.out.println(courseBasePageResult);
    }
}