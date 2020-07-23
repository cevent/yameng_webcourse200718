package com.cevent.yameng.webcourse.server.service;/**
 * Created by Cevent on 2020/7/20.
 */

import com.cevent.yameng.webcourse.server.domain.Chapter;
import com.cevent.yameng.webcourse.server.domain.ChapterExample;
import com.cevent.yameng.webcourse.server.dto.ChapterDto;
import com.cevent.yameng.webcourse.server.dto.PageDto;
import com.cevent.yameng.webcourse.server.mapper.ChapterMapper;
import com.cevent.yameng.webcourse.server.util.CopyUtil;
import com.cevent.yameng.webcourse.server.util.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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
    public void getChaptersDtoObj(PageDto pageDto){
        //引入github-pagehelper：pageNum（第几页,index=1）pageSize（每页条数）
        //pageHelper会查程序如下执行的第一条语句，即chapterMapper.selectByExample(chapterExample)
        //PageHelper.startPage(2,2);

        //A-->引入PageDto
        PageHelper.startPage(pageDto.getCurrentPage(),pageDto.getInitPageNum());

        ChapterExample chapterExample=new ChapterExample();
//        //criteria创建条件
//        chapterExample.createCriteria().andIdEqualTo("4");
//        //clause排序拼接子句
//        chapterExample.setOrderByClause("id desc");

        //查询醋结果集，page执行语句
        List<Chapter> chaptersObj=chapterMapper.selectByExample(chapterExample);

        //B-->引用pageInfo<泛型>
        PageInfo<Chapter> pageInfo=new PageInfo<>(chaptersObj);
        pageDto.setSumPage(pageInfo.getTotal());

        //循环遍历结果集:原生for循环：fori
        List<ChapterDto> chapterDtos=new ArrayList<ChapterDto>();
        for (int i = 0;i < chaptersObj.size(); i++) {
            //BeanUtils是spring提供的工具类，用于实体类的复制
            Chapter chapter=chaptersObj.get(i);
            ChapterDto chapterDto=new ChapterDto();
            BeanUtils.copyProperties(chapter,chapterDto);
            chapterDtos.add(chapterDto);
        }
        pageDto.setList(chapterDtos);
    }

    //新增章节方法
    private void addChapter(Chapter chapter){
        //这里前端只传入name和courseid，需要uuid自动生成id
        chapter.setId(UUIDUtil.getShortUUID());
        chapterMapper.insert(chapter);
    }


    //更新章节方法
    private void updateChapter(Chapter chapter){
        //更具主键更新
        chapterMapper.updateByPrimaryKey(chapter);
    }
    //向外暴露只跑路Dto，不暴露原实体类(只供servie内部用)
    //判断是否有值，完成新增/修改跳转
    public void redirectAddOrUpdate(ChapterDto chapterDto){
        Chapter chapter= CopyUtil.copy(chapterDto,Chapter.class);
        if(StringUtils.isEmpty(chapterDto.getId())){
            this.addChapter(chapter);
        }else{
            this.updateChapter(chapter);
        }
    }

    //删除章节的方法
    public void deleteChapter(String id){
        chapterMapper.deleteByPrimaryKey(id);
    }

}
