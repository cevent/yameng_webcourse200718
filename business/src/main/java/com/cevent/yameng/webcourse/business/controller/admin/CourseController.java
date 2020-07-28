package com.cevent.yameng.webcourse.business.controller.admin;

import com.cevent.yameng.webcourse.server.dto.*;
import com.cevent.yameng.webcourse.server.service.CourseCategoryService;
import com.cevent.yameng.webcourse.server.service.CourseService;
import com.cevent.yameng.webcourse.server.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/course")
public class CourseController {

    private static final Logger LOG = LoggerFactory.getLogger(CourseController.class);
    private static final String BUSINESS_NAME="课程表";

    @Resource
    private CourseService courseService;
    //加载获取分类
    @Resource
    private CourseCategoryService courseCategoryService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody CoursePageDto coursePageDto) {
        ResponseDto responseDto = new ResponseDto();
        courseService.list(coursePageDto);
        responseDto.setResponseData(coursePageDto);
        return responseDto;
    }

    /**
     * 保存，id有值更新，id=null新增
     * freemark内置 ?c 将数值转换为字符串，防止金额2,000等现象
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody CourseDto courseDto) {
        LOG.info("输出courseDTO对象：{}", courseDto);
        //保存校验
        Validator.require(courseDto.getName(),"课程名");
        Validator.length(courseDto.getName(),"课程名",1,50);
        Validator.length(courseDto.getSummary(),"课程概述",1,2000);
        Validator.length(courseDto.getImage(),"封面",1,100);

        ResponseDto responseDto = new ResponseDto();
        courseService.save(courseDto);
        responseDto.setResponseData(courseDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        LOG.info("删除的--> id:{}", id);
        ResponseDto responseDto = new ResponseDto();
        courseService.delete(id);
        return responseDto;
    }
    /**
     * 查找课程下所有分类
     */
    @PostMapping("/list-course-category/{courseId}")
    public ResponseDto listCourseCategoryByCoursId(@PathVariable(value="courseId") String courseId){
        ResponseDto responseDto=new ResponseDto();
        List<CourseCategoryDto> courseCategoryDtoList=courseCategoryService.listByCourseId(courseId);
        responseDto.setResponseData(courseCategoryDtoList);
        return responseDto;
    }
}
