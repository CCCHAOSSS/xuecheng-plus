package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.mapper.TeachplanMapper;
import com.xuecheng.content.mapper.TeachplanMediaMapper;
import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.model.po.TeachplanMedia;
import com.xuecheng.content.service.TeachplanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author limei
 * @data 2024/7/4
 * @description 课程计划查询实现类
 */

@Service
public class TeachplanServiceImpl implements TeachplanService {

    @Autowired
    TeachplanMapper teachplanMapper;

    @Autowired
    TeachplanMediaMapper teachplanMediaMapper;
    @Override
    public List<TeachplanDto> findTeachplanTree(Long courseId) {
        List<TeachplanDto> teachplanDtos = teachplanMapper.selectTreeNodes(courseId);
        return teachplanDtos;
    }



    private int getTeachplanCount(Long parentId,Long courseId){
        LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper = queryWrapper.eq(Teachplan::getCourseId, courseId).eq(Teachplan::getParentid, parentId);
        Integer count = teachplanMapper.selectCount(queryWrapper);
        return count+1;
    }

    /**
     * 新增、修改课程计划
     * */
    @Override
    public void saveTeachplan(SaveTeachplanDto saveTeachplanDto) {
        //通过课程计划id判断新增还是修改
        Long teachplanId = saveTeachplanDto.getId();
        if(teachplanId == null){
            //新增
            Teachplan teachplan = new Teachplan();
            BeanUtils.copyProperties(saveTeachplanDto,teachplan);
            //确定排序字段,找到它的同级节点个数，排序字段就是个数加1
            Long parentId = saveTeachplanDto.getParentid();
            Long courseId = saveTeachplanDto.getCourseId();
            int teachplanCount = getTeachplanCount(parentId,courseId);
            teachplan.setOrderby(teachplanCount);
            teachplanMapper.insert(teachplan);
        }else {
            //修改
            Teachplan teachplan = teachplanMapper.selectById(teachplanId);
            //将参数复制到对象中
            BeanUtils.copyProperties(saveTeachplanDto,teachplan);
            teachplanMapper.updateById(teachplan);
        }

    }

    /**
     * 删除课程计划
     * */
    @Override
    public void deleteTeachplan(Long teachplanId) {
        if (teachplanId == null){
            XueChengPlusException.cast("课程计划id为空");
        }
        Teachplan teachplan = teachplanMapper.selectById(teachplanId);
        Integer grade = teachplan.getGrade();

        //根据课程计划层级进行不同的删除操作
        if (grade == 1){
            // 当前层级为章，查询是否有小节
            LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
            // select * from teachplan where parentid = {当前章计划id}
            queryWrapper.eq(Teachplan::getParentid,teachplanId);
            Integer counts = teachplanMapper.selectCount(queryWrapper);
            if (counts > 0){
                XueChengPlusException.cast("该章节存在子信息，无法删除");
            }
            teachplanMapper.deleteById(teachplanId);
        }else {
            //当前层级为节
            teachplanMapper.deleteById(teachplanId);
            //将其它关联的视频信息也删除
            LambdaQueryWrapper<TeachplanMedia> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(TeachplanMedia::getTeachplanId,teachplanId);
            teachplanMediaMapper.delete(queryWrapper);
        }

    }

    /**
     * 课程计划移动
     * */
    @Override
    public void orderByTeachplan(String moveType, Long teachplanId) {

        Teachplan teachplan = teachplanMapper.selectById(teachplanId);
        if (teachplan == null){
            XueChengPlusException.cast("课程id不存在");
        }
        Integer grade = teachplan.getGrade();
        Integer orderby = teachplan.getOrderby();

        //如果是小节，则比较对应章节下的小节
        Long parentid = teachplan.getParentid();
        //如果是章节，则比较同一课程下的章节
        Long courseId = teachplan.getCourseId();

        if ("moveup".equals(moveType)){
            if (grade == 1){
                //grade=1,是章节，要找到在这之前的章节
                LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(Teachplan::getGrade, grade)
                        .eq(Teachplan::getCourseId, courseId)
                        .lt(Teachplan::getOrderby, orderby)
                        .orderByDesc(Teachplan::getOrderby)
                        .last("limit 1");
                Teachplan tmp = teachplanMapper.selectOne(queryWrapper);
                exchangeOrderby(teachplan,tmp);
            }else if (grade == 2){
                //grade=2,是小节，要找到在这之前的小节
                LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(Teachplan::getParentid, parentid)
                        .lt(Teachplan::getOrderby, orderby)
                        .orderByDesc(Teachplan::getOrderby)
                        .last("limit 1");
                Teachplan tmp = teachplanMapper.selectOne(queryWrapper);
                exchangeOrderby(teachplan,tmp);
            }

        }else if ("movedown".equals(moveType)){
            if (grade == 1){
                //grade=1,是章节，要找到在这之后的章节
                LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(Teachplan::getGrade, grade)
                        .eq(Teachplan::getCourseId, courseId)
                        .gt(Teachplan::getOrderby, orderby)
                        .orderByAsc(Teachplan::getOrderby)
                        .last("limit 1");
                Teachplan tmp = teachplanMapper.selectOne(queryWrapper);
                exchangeOrderby(teachplan,tmp);
            }else if (grade == 2){
                //grade=2,是小节，要找到在这之后的小节
                LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(Teachplan::getParentid, parentid)
                        .gt(Teachplan::getOrderby, orderby)
                        .orderByAsc(Teachplan::getOrderby)
                        .last("limit 1");
                Teachplan tmp = teachplanMapper.selectOne(queryWrapper);
                exchangeOrderby(teachplan,tmp);
            }
        }
    }

    /**
     * 交换两个顺序
     * */
    private void exchangeOrderby(Teachplan teachplan, Teachplan tmp){
        if (tmp == null){
            XueChengPlusException.cast("已经到头，无法移动");
        } else {
            Integer orderby = teachplan.getOrderby();
            Integer tmpOrderby = tmp.getOrderby();
            teachplan.setOrderby(tmpOrderby);
            tmp.setOrderby(orderby);
            teachplanMapper.updateById(tmp);
            teachplanMapper.updateById(teachplan);
        }

    }
}
