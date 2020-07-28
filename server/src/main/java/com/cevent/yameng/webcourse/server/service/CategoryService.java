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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;


@Service
public class CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 列表查询，二级分类不需要分页
     */
    public List<CategoryDto> all(){
        CategoryExample categoryExample=new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        List<Category> categoryList=categoryMapper.selectByExample(categoryExample);
        List<CategoryDto> categoryDtoList=CopyUtil.copyList(categoryList,CategoryDto.class);
        return categoryDtoList;
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
    @Transactional
    public void delete(String id){
        deleteChildren(id);
        categoryMapper.deleteByPrimaryKey(id);
    }

    //删除子节点
    public void deleteChildren(String id){
        Category category=categoryMapper.selectByPrimaryKey(id);
        if("00000000".equals(category.getParent())){
            //如果是一级分类，并删除二级分类
            CategoryExample categoryExample=new CategoryExample();
            categoryExample.createCriteria().andIdEqualTo(category.getId());
            categoryMapper.deleteByExample(categoryExample);
        }
    }

}
