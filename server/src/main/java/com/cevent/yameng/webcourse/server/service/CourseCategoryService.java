package com.cevent.yameng.webcourse.server.service;

import com.cevent.yameng.webcourse.server.domain.CourseCategory;
import com.cevent.yameng.webcourse.server.domain.CourseCategoryExample;
import com.cevent.yameng.webcourse.server.dto.CategoryDto;
import com.cevent.yameng.webcourse.server.dto.CourseCategoryDto;
import com.cevent.yameng.webcourse.server.dto.PageDto;
import com.cevent.yameng.webcourse.server.mapper.CourseCategoryMapper;
import com.cevent.yameng.webcourse.server.util.CopyUtil;
import com.cevent.yameng.webcourse.server.util.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;


@Service
public class CourseCategoryService {
    @Resource
    private CourseCategoryMapper courseCategoryMapper;

    /**
     * 列表查询
     */
    public void list(PageDto pageDto){

        PageHelper.startPage(pageDto.getCurrentPage(),pageDto.getInitPageNum());
        CourseCategoryExample courseCategoryExample=new CourseCategoryExample();


        List<CourseCategory> courseCategoryList=courseCategoryMapper.selectByExample(courseCategoryExample);

        PageInfo<CourseCategory> pageInfo=new PageInfo<>(courseCategoryList);
        pageDto.setSumPage(pageInfo.getTotal());

        List<CourseCategoryDto> courseCategoryDtoList=CopyUtil.copyList(courseCategoryList,CourseCategoryDto.class);
        pageDto.setList(courseCategoryDtoList);
    }

    /**
     * 新增
     */
    private void insert(CourseCategory courseCategory){

        courseCategory.setId(UUIDUtil.getShortUUID());
        courseCategoryMapper.insert(courseCategory);
    }


    /**
     * 更新
     */
    private void update(CourseCategory courseCategory){
        courseCategoryMapper.updateByPrimaryKey(courseCategory);
    }

    /**
     * 保存：id有值更新，id=null新增
     */
    public void save(CourseCategoryDto courseCategoryDto){
        CourseCategory courseCategory= CopyUtil.copy(courseCategoryDto,CourseCategory.class);
        if(StringUtils.isEmpty(courseCategoryDto.getId())){
            this.insert(courseCategory);
        }else{
            this.update(courseCategory);
        }
    }

    /**
     * 删除
     */
    public void delete(String id){
        courseCategoryMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量保存course-category-id
     */
    @Transactional
    public void batchSave(String courseId, List<CategoryDto> categoryDtoList){
        //保存批量之前，根据courseId清空category数据
        CourseCategoryExample courseCategoryExample=new CourseCategoryExample();
        courseCategoryExample.createCriteria().andCourseIdEqualTo(courseId);
        courseCategoryMapper.deleteByExample(courseCategoryExample);
        //遍历保存
        for(int i=0;i<categoryDtoList.size();i++){
            CategoryDto categoryDto=categoryDtoList.get(i);
            CourseCategory courseCategory=new CourseCategory();
            courseCategory.setId(UUIDUtil.getShortUUID());
            courseCategory.setCourseId(courseId);
            courseCategory.setCategoryId(categoryDto.getId());
            insert(courseCategory);
        }
    }

    //查找课程下所有分类
    public List<CourseCategoryDto> listByCourseId(String courseId){
        CourseCategoryExample courseCategoryExample=new CourseCategoryExample();
        courseCategoryExample.createCriteria().andCourseIdEqualTo(courseId);
        List<CourseCategory> courseCategoryList=courseCategoryMapper.selectByExample(courseCategoryExample);
        return CopyUtil.copyList(courseCategoryList,CourseCategoryDto.class);
    }
}
