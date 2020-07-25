package com.cevent.yameng.webcourse.${module}.controller.admin;

import com.cevent.yameng.webcourse.server.dto.${Domain}Dto;
import com.cevent.yameng.webcourse.server.dto.PageDto;
import com.cevent.yameng.webcourse.server.dto.ResponseDto;
import com.cevent.yameng.webcourse.server.service.${Domain}Service;
import com.cevent.yameng.webcourse.server.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/${domain}")
public class ${Domain}Controller {

    private static final Logger LOG = LoggerFactory.getLogger(${Domain}Controller.class);
    private static final String BUSINESS_NAME="${tableNameCN}";

    @Resource
    private ${Domain}Service ${domain}Service;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        ${domain}Service.list(pageDto);
        responseDto.setResponseData(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值更新，id=null新增
     */
    @PostMapping("/sava")
    public ResponseDto save(@RequestBody ${Domain}Dto ${domain}Dto) {
        LOG.info("输出${domain}DTO对象：{}", ${domain}Dto);
        //保存校验

        ResponseDto responseDto = new ResponseDto();
        ${domain}Service.save(${domain}Dto);
        responseDto.setResponseData(${domain}Dto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        LOG.info("删除的--> id:{}", id);
        ResponseDto responseDto = new ResponseDto();
        ${domain}Service.delete(id);
        return responseDto;
    }
}
