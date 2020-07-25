package com.cevent.yameng.webcourse.server.service;

import com.cevent.yameng.webcourse.server.domain.${Domain};
import com.cevent.yameng.webcourse.server.domain.${Domain}Example;
import com.cevent.yameng.webcourse.server.dto.${Domain}Dto;
import com.cevent.yameng.webcourse.server.dto.PageDto;
import com.cevent.yameng.webcourse.server.mapper.${Domain}Mapper;
import com.cevent.yameng.webcourse.server.util.CopyUtil;
import com.cevent.yameng.webcourse.server.util.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ${Domain}Service {
    @Resource
    private ${Domain}Mapper ${domain}Mapper;

    /**
     * 列表查询
     */
    public void list(PageDto pageDto){

        PageHelper.startPage(pageDto.getCurrentPage(),pageDto.getInitPageNum());
        ${Domain}Example ${domain}Example=new ${Domain}Example();
        List<${Domain}> ${domain}List=${domain}Mapper.selectByExample(${domain}Example);

        PageInfo<${Domain}> pageInfo=new PageInfo<>(${domain}List);
        pageDto.setSumPage(pageInfo.getTotal());

        List<${Domain}Dto> ${domain}DtoList=CopyUtil.copyList(${domain}List,${Domain}Dto.class);
        pageDto.setList(${domain}DtoList);
    }

    /**
     * 新增
     */
    private void insert(${Domain} ${domain}){
        ${domain}.setId(UUIDUtil.getShortUUID());
        ${domain}Mapper.insert(${domain});
    }


    /**
     * 更新
     */
    private void update(${Domain} ${domain}){
        ${domain}Mapper.updateByPrimaryKey(${domain});
    }

    /**
     * 保存：id有值更新，id=null新增
     */
    public void save(${Domain}Dto ${domain}Dto){
        ${Domain} ${domain}= CopyUtil.copy(${domain}Dto,${Domain}.class);
        if(StringUtils.isEmpty(${domain}Dto.getId())){
            this.insert(${domain});
        }else{
            this.update(${domain});
        }
    }

    /**
     * 删除
     */
    public void delete(String id){
        ${domain}Mapper.deleteByPrimaryKey(id);
    }

}
