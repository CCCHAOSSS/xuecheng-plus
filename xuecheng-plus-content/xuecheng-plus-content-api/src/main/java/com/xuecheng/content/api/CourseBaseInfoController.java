package com.xuecheng.content.api;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.AddCourseDto;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.EditCourseDto;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.service.CourseBaseInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author limei
 * @date 2024/3/20 9:38
 * @description 响应、编辑课程信息接口,查询、响应结果都为json格式
 */

@Api(value = "课程信息管理接口", tags = "课程信息管理接口")
@RestController //相当于 controller和responseBody的整合
public class CourseBaseInfoController {

    @Autowired
    CourseBaseInfoService courseBaseInfoService;

    @ApiOperation(value = "课程查询接口")
    @PostMapping("/course/list")
    public PageResult<CourseBase> list(PageParams pageParams, @RequestBody(required = false) QueryCourseParamsDto queryCourseParamsDto){
        // QueryCourseParamsDto通过json格式传入，通过注解转成Dto对象
        PageResult<CourseBase> courseBasePageResult = courseBaseInfoService.queryCourseBaseList(pageParams, queryCourseParamsDto);
        return courseBasePageResult;

    }

    @ApiOperation("新增课程")
    @PostMapping("/course")
    public CourseBaseInfoDto createCourseBase(@RequestBody AddCourseDto addCourseDto){

        // 获取到用户机构所属id
        Long companyId = 1232141425L;//暂时写死

        CourseBaseInfoDto courseBase = courseBaseInfoService.createCourseBase(companyId, addCourseDto);

        return courseBase;
    }

    @ApiOperation("根据课程id查信息")
    @GetMapping("/course/{courseId}")
    public CourseBaseInfoDto getCourseBaseById(@PathVariable Long courseId){
        CourseBaseInfoDto courseBaseInfo = courseBaseInfoService.getCourseBaseInfo(courseId);
        return courseBaseInfo;
    }

    @ApiOperation("修改课程")
    @PutMapping("/course")
    public CourseBaseInfoDto modifyCourseBase(@RequestBody EditCourseDto editCourseDto){
        //获取到用户所属机构的id
        Long companyId = 1232141425L;
        CourseBaseInfoDto courseBaseInfoDto = courseBaseInfoService.updateCourseBase(companyId, editCourseDto);
        return courseBaseInfoDto;
    }

    /**
     * 删除课程
     * */
    @ApiOperation("删除课程")
    @DeleteMapping("/course/{courseId}")
    public void deleteCourseBase(@PathVariable Long courseId){
        Long companyId = 1232141425L;
        courseBaseInfoService.deleteCourseBase(courseId, companyId);
    }

}
