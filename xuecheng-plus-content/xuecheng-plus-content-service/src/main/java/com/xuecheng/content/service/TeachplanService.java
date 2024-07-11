package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;

import java.util.List;

/**
 * @author limei
 * @data 2024/7/4
 * @description 课程计划管理相关接口
 */
public interface TeachplanService {

    /**
     * 查询课程计划树形结构
     * */
    public List<TeachplanDto> findTeachplanTree(Long courseId);

    /**
     * 新增、修改课程计划
     * */
    public void saveTeachplan(SaveTeachplanDto saveTeachplanDto);

    /**
     * 删除课程计划
     * */
    void deleteTeachplan(Long teachplanId);

    /**
     * 移动课程计划
     * */
    void orderByTeachplan(String moveType, Long teachplanId);
}
