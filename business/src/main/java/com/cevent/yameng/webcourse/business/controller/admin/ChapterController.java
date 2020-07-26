package com.cevent.yameng.webcourse.business.controller.admin;/**
 * Created by Cevent on 2020/7/20.
 */

import com.cevent.yameng.webcourse.server.domain.Chapter;
import com.cevent.yameng.webcourse.server.dto.ChapterDto;
import com.cevent.yameng.webcourse.server.dto.ChapterPageDto;
import com.cevent.yameng.webcourse.server.dto.PageDto;
import com.cevent.yameng.webcourse.server.dto.ResponseDto;
import com.cevent.yameng.webcourse.server.exception.ValidationException;
import com.cevent.yameng.webcourse.server.service.ChapterService;
import com.cevent.yameng.webcourse.server.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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
    private static final Logger LOG = LoggerFactory.getLogger(ChapterController.class);
    //引用AOP日志，定义这里的业务信息=章节
    private static final String BUSINESS_NAME="章节";

    @Resource
    private ChapterService chapterService;

    @RequestMapping("/chapter")
    public List<Chapter> getCaptureList() {
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

    //引入ResponseDto,requestMapping既支持post，又支持get，需要定制为特定类型post
    @PostMapping("/chapterDto")
    public ResponseDto getChapterDto(@RequestBody ChapterPageDto chapterPageDto) { //需求：接收流的方式，并且修改前端vue-data接收pageDto对象，因为被修改加入了page信息
        //获取日志信息输出：("输出：id={} , name={}",id,name) 并非("输出：id="+id"+""...)
        LOG.info("输出chapterPageDto对象: {}", chapterPageDto);
        ResponseDto responseDto = new ResponseDto();
        //判断传入的courseId有值
        Validator.require(chapterPageDto.getCourseId(),"课程ID");

        chapterService.getChaptersDtoObj(chapterPageDto);
        responseDto.setResponseData(chapterPageDto);
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
    @PostMapping("/addChapter")
    public ResponseDto addChapterDto(@RequestBody ChapterDto chapterDto) {
        LOG.info("输出chapterDTO对象：{}", chapterDto);

        //添加校验
        Validator.require(chapterDto.getName(), "章节名称");
        Validator.require(chapterDto.getCourseId(), "课程ID");
        Validator.length(chapterDto.getCourseId(), "课程ID", 3, 10);
        //抛出异常到ControllerExceptionHandler，回传响应页面@ResponseBody

        ResponseDto responseDto = new ResponseDto();
        chapterService.redirectAddOrUpdate(chapterDto);
        responseDto.setResponseData(chapterDto);
        return responseDto;
    }

    //删除章节
    @DeleteMapping("delChapter/{id}")
    public ResponseDto deleteChapter(@PathVariable String id) {
        LOG.info("删除的--> id:{}", id);
        ResponseDto responseDto = new ResponseDto();
        chapterService.deleteChapter(id);
        return responseDto;
    }
}
