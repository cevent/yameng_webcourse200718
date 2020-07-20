package com.cevent.yameng.webcourse.server.service;/**
 * Created by Cevent on 2020/7/20.
 */

import com.cevent.yameng.webcourse.server.domain.Chapter;
import com.cevent.yameng.webcourse.server.domain.ChapterExample;
import com.cevent.yameng.webcourse.server.dto.ChapterDto;
import com.cevent.yameng.webcourse.server.mapper.ChapterMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.text.resources.iw.FormatData_iw_IL;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cevent
 * @description
 * @date 2020/7/20 22:34
 */
@Service
public class ChapterService {
    @Resource
    private ChapterMapper chapterMapper;

    public List<Chapter> getChapters(){
        ChapterExample chapterExample=new ChapterExample();
        chapterExample.createCriteria().andIdEqualTo("1");
        chapterExample.setOrderByClause("id desc");
        return chapterMapper.selectByExample(chapterExample);
    }

    //使用dto：先创建dto获取的对象
    public  List<ChapterDto> getChaptersDtoObj(){
        ChapterExample chapterExample=new ChapterExample();
//        //criteria创建条件
//        chapterExample.createCriteria().andIdEqualTo("4");
//        //clause排序拼接子句
//        chapterExample.setOrderByClause("id desc");
        //查询醋结果集
        List<Chapter> chaptersObj=chapterMapper.selectByExample(chapterExample);
        //循环遍历结果集:原生for循环：fori
        List<ChapterDto> chapterDtos=new ArrayList<ChapterDto>();
        for (int i = 0;i < chaptersObj.size(); i++) {
            //BeanUtils是spring提供的工具类，用于实体类的复制
            Chapter chapter=chaptersObj.get(i);
            ChapterDto chapterDto=new ChapterDto();
            BeanUtils.copyProperties(chapter,chapterDto);
            chapterDtos.add(chapterDto);
        }
        return chapterDtos;
    }
}
