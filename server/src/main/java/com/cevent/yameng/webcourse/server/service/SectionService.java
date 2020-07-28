package com.cevent.yameng.webcourse.server.service;

import com.cevent.yameng.webcourse.server.domain.Section;
import com.cevent.yameng.webcourse.server.domain.SectionExample;
import com.cevent.yameng.webcourse.server.dto.SectionDto;
import com.cevent.yameng.webcourse.server.dto.PageDto;
import com.cevent.yameng.webcourse.server.dto.SectionPageDto;
import com.cevent.yameng.webcourse.server.enums.SectionChargeEnum;
import com.cevent.yameng.webcourse.server.mapper.SectionMapper;
import com.cevent.yameng.webcourse.server.util.CopyUtil;
import com.cevent.yameng.webcourse.server.util.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.omg.SendingContext.RunTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

import java.util.Date;

@Service
public class SectionService {
    @Resource
    private SectionMapper sectionMapper;

    //注入courseService
    @Resource
    private CourseService courseService;

    /**
     * 列表查询
     */
    public void list(SectionPageDto sectionPageDto){

        PageHelper.startPage(sectionPageDto.getCurrentPage(),sectionPageDto.getInitPageNum());
        SectionExample sectionExample=new SectionExample();
        //查询course和chapterID
        SectionExample.Criteria criteria=sectionExample.createCriteria();
        if(!StringUtils.isEmpty(sectionPageDto.getCourseId())){
            criteria.andCourseIdEqualTo(sectionPageDto.getCourseId());
        }
        if(!StringUtils.isEmpty(sectionPageDto.getChapterId())){
            criteria.andChapterIdEqualTo(sectionPageDto.getChapterId());
        }
        sectionExample.setOrderByClause("sort asc");
        List<Section> sectionList=sectionMapper.selectByExample(sectionExample);

        PageInfo<Section> pageInfo=new PageInfo<>(sectionList);
        sectionPageDto.setSumPage(pageInfo.getTotal());

        List<SectionDto> sectionDtoList=CopyUtil.copyList(sectionList,SectionDto.class);
        sectionPageDto.setList(sectionDtoList);
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
        //section.setCharge(SectionChargeEnum.CHARGE.getCode());

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
     * @Transactional(rollbackFor = Exception.class)
     */
    @Transactional
    public void save(SectionDto sectionDto) {
        Section section= CopyUtil.copy(sectionDto,Section.class);
        if(StringUtils.isEmpty(sectionDto.getId())){
            this.insert(section);
        }else{
            this.update(section);
        }
//        if(true){
//            //测试事务RuntimeException起作用
//            //throw new RuntimeException("事务测试runtime");
//            //如果启用Exception，那么需要方法声明@Trans(rollbackFor)
//            //throw new Exception("事务测试exception");
//        }

        //保存时，修改/新增小节，都更新课程时长
        courseService.updateCourseTime(sectionDto.getCourseId());
    }

    /**
     * 删除
     */
    public void delete(String id){
        sectionMapper.deleteByPrimaryKey(id);
    }

}
