package com.xuecheng.content;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author limei
 * @date 2024/3/20 13:35
 * @description TODO
 */

@SpringBootTest
public class CouresBaseMapperTests {

    @Autowired
    CourseBaseMapper courseBaseMapper;


    /**
     * 单元测试：测试mybatis-plus的generator生成的mapper代码能用
    * */
    @Test
    public void testCourseBaseMapper(){
//        CourseBase courseBase = courseBaseMapper.selectById(18);
//        Assertions.assertNotNull(courseBase);   //断言

        //详细进行分页查询的单元测试
        // 查询条件  -lambda
        QueryCourseParamsDto courseParamsDto = new QueryCourseParamsDto();
        courseParamsDto.setCourseName("java");  //查询的课程名称（假定为java

        //分页参数对象
        PageParams pageParams = new PageParams();
        pageParams.setPageNo(1L);
        pageParams.setPageSize(10L);

        //拼装查询条件
        LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper<>();
        /*
          根据名称模糊查询
        //第一个参数是一个布尔值，表示是否添加这个条件。第二个参数是字段名，第三个参数是需要模糊查询的值（对象）。
        // CourseBase::getName 表示使用 CourseBase 类的 getName 方法作为查询条件的字段
        * */
        queryWrapper.like(StringUtils.isNotEmpty(courseParamsDto.getCourseName()), CourseBase::getName, courseParamsDto.getCourseName());

        //根据课程审核状态查询
        queryWrapper.eq(StringUtils.isNotEmpty(courseParamsDto.getAuditStatus()), CourseBase::getAuditStatus, courseParamsDto.getAuditStatus());

        //TODO: 根据课程发布状态查询
        queryWrapper.eq(StringUtils.isNotEmpty(courseParamsDto.getPublishStatus()), CourseBase::getStatus, courseParamsDto.getPublishStatus());

         //创建分页参数对象*/
        Page<CourseBase> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        Page<CourseBase> pageResult = courseBaseMapper.selectPage(page, queryWrapper);
        List<CourseBase> items = pageResult.getRecords();
        long counts = pageResult.getTotal();
        PageResult<CourseBase> courseBasePageResult = new PageResult<CourseBase>(items, counts, pageParams.getPageNo(), pageParams.getPageSize());
        System.out.println("结果===》" + courseBasePageResult);
    }
}
