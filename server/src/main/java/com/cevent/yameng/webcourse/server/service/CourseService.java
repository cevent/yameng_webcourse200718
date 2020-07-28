package com.cevent.yameng.webcourse.server.service;

import com.cevent.yameng.webcourse.server.domain.Course;
import com.cevent.yameng.webcourse.server.domain.CourseExample;
import com.cevent.yameng.webcourse.server.dto.CourseDto;
import com.cevent.yameng.webcourse.server.dto.PageDto;
import com.cevent.yameng.webcourse.server.mapper.CourseMapper;
import com.cevent.yameng.webcourse.server.mapper.ceventmapper.CeventCourseMapper;
import com.cevent.yameng.webcourse.server.util.CopyUtil;
import com.cevent.yameng.webcourse.server.util.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

import java.util.Date;

@Service
public class CourseService {

    private static final Logger LOG= LoggerFactory.getLogger(CourseService.class);

    @Resource
    private CourseMapper courseMapper;
    //调用Cevent自定义mapper接口
    @Resource
    private CeventCourseMapper ceventCourseMapper;
    //注入courseCategoryService
    @Resource
    private CourseCategoryService courseCategoryService;

    /**
     * 列表查询
     */
    public void list(PageDto pageDto){

        PageHelper.startPage(pageDto.getCurrentPage(),pageDto.getInitPageNum());
        CourseExample courseExample=new CourseExample();

        courseExample.setOrderByClause("sort asc");

        List<Course> courseList=courseMapper.selectByExample(courseExample);

        PageInfo<Course> pageInfo=new PageInfo<>(courseList);
        pageDto.setSumPage(pageInfo.getTotal());

        List<CourseDto> courseDtoList=CopyUtil.copyList(courseList,CourseDto.class);
        pageDto.setList(courseDtoList);
    }

    /**
     * 新增
     */
    private void insert(Course course){
        Date now=new Date();
        course.setCreateTime(now);
        course.setUpdateTime(now);

        course.setId(UUIDUtil.getShortUUID());
        courseMapper.insert(course);
    }


    /**
     * 更新
     */
    private void update(Course course){
        course.setUpdateTime(new Date());
        courseMapper.updateByPrimaryKey(course);
    }

    /**
     * 保存：id有值更新，id=null新增
     */
    @Transactional
    public void save(CourseDto courseDto){
        Course course= CopyUtil.copy(courseDto,Course.class);
        if(StringUtils.isEmpty(courseDto.getId())){
            this.insert(course);
        }else{
            this.update(course);
        }
        //批量(数组)保存分类
        courseCategoryService.batchSave(course.getId(),courseDto.getCategoryDtos());
    }

    /**
     * 删除
     */
    public void delete(String id){
        courseMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新【课程时长】
     */
    public void updateCourseTime(String courseId){
        LOG.info("更新课程时长：{}",courseId);
        ceventCourseMapper.updateCourseTime(courseId);
    }

}
