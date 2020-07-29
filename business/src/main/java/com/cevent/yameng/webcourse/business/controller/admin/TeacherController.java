package com.cevent.yameng.webcourse.business.controller.admin;

import com.cevent.yameng.webcourse.server.dto.TeacherDto;
import com.cevent.yameng.webcourse.server.dto.PageDto;
import com.cevent.yameng.webcourse.server.dto.ResponseDto;
import com.cevent.yameng.webcourse.server.service.TeacherService;
import com.cevent.yameng.webcourse.server.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/teacher")
public class TeacherController {

    private static final Logger LOG = LoggerFactory.getLogger(TeacherController.class);
    private static final String BUSINESS_NAME="讲师表";

    @Resource
    private TeacherService teacherService;

    /**
     * 查询所有讲师
     */
    @PostMapping("/all")
    public ResponseDto all(){
        ResponseDto responseDto=new ResponseDto();
        List<TeacherDto> teacherDtoList=teacherService.all();
        responseDto.setResponseData(teacherDtoList);
        return responseDto;
    }

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        teacherService.list(pageDto);
        responseDto.setResponseData(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值更新，id=null新增
     * freemark内置 ?c 将数值转换为字符串，防止金额2,000等现象
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody TeacherDto teacherDto) {
        LOG.info("输出teacherDTO对象：{}", teacherDto);
        //保存校验
        Validator.require(teacherDto.getName(),"讲师姓名");
        Validator.length(teacherDto.getName(),"讲师姓名",1,50);
        Validator.length(teacherDto.getNickname(),"昵称",1,50);
        Validator.length(teacherDto.getImage(),"头像",1,100);
        Validator.length(teacherDto.getPosition(),"职位",1,50);
        Validator.length(teacherDto.getMotto(),"座右铭",1,100);
        Validator.length(teacherDto.getIntroduce(),"介绍",1,500);

        ResponseDto responseDto = new ResponseDto();
        teacherService.save(teacherDto);
        responseDto.setResponseData(teacherDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        LOG.info("删除的--> id:{}", id);
        ResponseDto responseDto = new ResponseDto();
        teacherService.delete(id);
        return responseDto;
    }
}
