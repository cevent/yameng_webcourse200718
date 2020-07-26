package com.cevent.yameng.webcourse.server.service;

import com.cevent.yameng.webcourse.server.domain.Section;
import com.cevent.yameng.webcourse.server.domain.SectionExample;
import com.cevent.yameng.webcourse.server.dto.SectionDto;
import com.cevent.yameng.webcourse.server.dto.PageDto;
import com.cevent.yameng.webcourse.server.enums.SectionChargeEnum;
import com.cevent.yameng.webcourse.server.mapper.SectionMapper;
import com.cevent.yameng.webcourse.server.util.CopyUtil;
import com.cevent.yameng.webcourse.server.util.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

import java.util.Date;

@Service
public class SectionService {
    @Resource
    private SectionMapper sectionMapper;

    /**
     * 列表查询
     */
    public void list(PageDto pageDto){

        PageHelper.startPage(pageDto.getCurrentPage(),pageDto.getInitPageNum());
        SectionExample sectionExample=new SectionExample();
        sectionExample.setOrderByClause("sort asc");
        List<Section> sectionList=sectionMapper.selectByExample(sectionExample);

        PageInfo<Section> pageInfo=new PageInfo<>(sectionList);
        pageDto.setSumPage(pageInfo.getTotal());

        List<SectionDto> sectionDtoList=CopyUtil.copyList(sectionList,SectionDto.class);
        pageDto.setList(sectionDtoList);
    }

    /**
     * 新增
     */
    private void insert(Section section){
        Date now=new Date();
        section.setCreateTime(now);
        section.setUpdateTime(now);
        section.setId(UUIDUtil.getShortUUID());
        //设置默认为收费
        section.setCharge(SectionChargeEnum.CHARGE.getCode());

        sectionMapper.insert(section);
    }


    /**
     * 更新
     */
    private void update(Section section){
        section.setUpdateTime(new Date());
        sectionMapper.updateByPrimaryKey(section);
    }

    /**
     * 保存：id有值更新，id=null新增
     */
    public void save(SectionDto sectionDto){
        Section section= CopyUtil.copy(sectionDto,Section.class);
        if(StringUtils.isEmpty(sectionDto.getId())){
            this.insert(section);
        }else{
            this.update(section);
        }
    }

    /**
     * 删除
     */
    public void delete(String id){
        sectionMapper.deleteByPrimaryKey(id);
    }

}
