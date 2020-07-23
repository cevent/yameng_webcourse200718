package com.cevent.yameng.webcourse.business.controller.admin;/**
 * Created by Cevent on 2020/7/20.
 */

import com.cevent.yameng.webcourse.server.domain.Chapter;
import com.cevent.yameng.webcourse.server.dto.ChapterDto;
import com.cevent.yameng.webcourse.server.dto.PageDto;
import com.cevent.yameng.webcourse.server.dto.ResponseDto;
import com.cevent.yameng.webcourse.server.service.ChapterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author cevent
 * @description 添加admin/web区分的路径接口隔离，便于做权限拦截
 * @date 2020/7/20 22:38
 */
@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {

    //调取日志
    private static final Logger LOG= LoggerFactory.getLogger(ChapterController.class);

    @Resource
    private ChapterService chapterService;

    @RequestMapping("/chapter")
    public List<Chapter> getCaptureList(){
        return chapterService.getChapters();
    }

    //获取dto实体类属性，调用分页
    //public PageDto getChapterDto(PageDto pageDto){ //接收表单的方式
//    @RequestMapping("/chapterDto")
//    public PageDto getChapterDto(@RequestBody PageDto pageDto){ //需求：接收流的方式，并且修改前端vue-data接收pageDto对象，因为被修改加入了page信息
//        //获取日志信息输出：("输出：id={} , name={}",id,name) 并非("输出：id="+id"+""...)
//        LOG.info("输出pageDto对象: {}",pageDto);
//
//        chapterService.getChaptersDtoObj(pageDto);
//        return pageDto;
//    }

    //引入ResponseDto
    @RequestMapping("/chapterDto")
    public ResponseDto getChapterDto(@RequestBody PageDto pageDto){ //需求：接收流的方式，并且修改前端vue-data接收pageDto对象，因为被修改加入了page信息
        //获取日志信息输出：("输出：id={} , name={}",id,name) 并非("输出：id="+id"+""...)
        LOG.info("输出pageDto对象: {}",pageDto);
        ResponseDto responseDto=new ResponseDto();
        chapterService.getChaptersDtoObj(pageDto);
        responseDto.setResponseData(pageDto);
        return responseDto;
    }

//    //新增章节controller，调用server-service的addChapter
//    @RequestMapping("/addChapter")
//    public ChapterDto addChapterDto(@RequestBody ChapterDto chapterDto){
//        LOG.info("输出chapterDTO对象：{}",chapterDto);
//        chapterService.addChapter(chapterDto);
//        return chapterDto;
//    }

    //引入ResponseDto，所有请求都返回该类型
    //新增章节controller，调用server-service的addChapter
    @RequestMapping("/addChapter")
    public ResponseDto addChapterDto(@RequestBody ChapterDto chapterDto){
        LOG.info("输出chapterDTO对象：{}",chapterDto);
        ResponseDto responseDto=new ResponseDto();
        chapterService.addChapter(chapterDto);
        responseDto.setResponseData(chapterDto);
        return responseDto;
    }
}
