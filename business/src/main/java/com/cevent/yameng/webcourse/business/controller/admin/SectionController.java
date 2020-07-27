package com.cevent.yameng.webcourse.business.controller.admin;

import com.cevent.yameng.webcourse.server.dto.SectionDto;
import com.cevent.yameng.webcourse.server.dto.PageDto;
import com.cevent.yameng.webcourse.server.dto.ResponseDto;
import com.cevent.yameng.webcourse.server.dto.SectionPageDto;
import com.cevent.yameng.webcourse.server.service.SectionService;
import com.cevent.yameng.webcourse.server.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/section")
public class SectionController {

    private static final Logger LOG = LoggerFactory.getLogger(SectionController.class);
    private static final String BUSINESS_NAME="小节";

    @Resource
    private SectionService sectionService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody SectionPageDto sectionPageDto) {
        ResponseDto responseDto = new ResponseDto();
        Validator.require(sectionPageDto.getChapterId(),"章节ID");
        Validator.require(sectionPageDto.getCourseId(),"课程ID");
        sectionService.list(sectionPageDto);
        responseDto.setResponseData(sectionPageDto);
        return responseDto;
    }

    /**
     * 保存，id有值更新，id=null新增
     * throws测试事务异常
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody SectionDto sectionDto) {
        LOG.info("输出sectionDTO对象：{}", sectionDto);
        //保存校验
                Validator.require(sectionDto.getTitle(),"标题");
                Validator.length(sectionDto.getTitle(),"标题",1,50);
                Validator.length(sectionDto.getVideoAdd(),"视频地址",1,200);

        ResponseDto responseDto = new ResponseDto();
        sectionService.save(sectionDto);
        responseDto.setResponseData(sectionDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        LOG.info("删除的--> id:{}", id);
        ResponseDto responseDto = new ResponseDto();
        sectionService.delete(id);
        return responseDto;
    }
}
