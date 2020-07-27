package com.cevent.yameng.webcourse.server.service;

import com.cevent.yameng.webcourse.server.domain.Category;
import com.cevent.yameng.webcourse.server.domain.CategoryExample;
import com.cevent.yameng.webcourse.server.dto.CategoryDto;
import com.cevent.yameng.webcourse.server.dto.PageDto;
import com.cevent.yameng.webcourse.server.mapper.CategoryMapper;
import com.cevent.yameng.webcourse.server.util.CopyUtil;
import com.cevent.yameng.webcourse.server.util.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;


@Service
public class CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 列表查询
     */
    public void list(PageDto pageDto){

        PageHelper.startPage(pageDto.getCurrentPage(),pageDto.getInitPageNum());
        CategoryExample categoryExample=new CategoryExample();

        categoryExample.setOrderByClause("sort asc");

        List<Category> categoryList=categoryMapper.selectByExample(categoryExample);

        PageInfo<Category> pageInfo=new PageInfo<>(categoryList);
        pageDto.setSumPage(pageInfo.getTotal());

        List<CategoryDto> categoryDtoList=CopyUtil.copyList(categoryList,CategoryDto.class);
        pageDto.setList(categoryDtoList);
    }

    /**
     * 新增
     */
    private void insert(Category category){
        category.setId(UUIDUtil.getShortUUID());
        categoryMapper.insert(category);
    }


    /**
     * 更新
     */
    private void update(Category category){
        categoryMapper.updateByPrimaryKey(category);
    }

    /**
     * 保存：id有值更新，id=null新增
     */
    public void save(CategoryDto categoryDto){
        Category category= CopyUtil.copy(categoryDto,Category.class);
        if(StringUtils.isEmpty(categoryDto.getId())){
            this.insert(category);
        }else{
            this.update(category);
        }
    }

    /**
     * 删除
     */
    public void delete(String id){
        categoryMapper.deleteByPrimaryKey(id);
    }

}
