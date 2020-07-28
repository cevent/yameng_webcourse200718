package com.cevent.yameng.webcourse.business.controller.admin;

import com.cevent.yameng.webcourse.server.dto.CategoryDto;
import com.cevent.yameng.webcourse.server.dto.PageDto;
import com.cevent.yameng.webcourse.server.dto.ResponseDto;
import com.cevent.yameng.webcourse.server.service.CategoryService;
import com.cevent.yameng.webcourse.server.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);
    private static final String BUSINESS_NAME="分类表";

    @Resource
    private CategoryService categoryService;

    /**
     * 列表查询
     */
    @PostMapping("/all")
    public ResponseDto all() {
        ResponseDto responseDto = new ResponseDto();
        List<CategoryDto> categoryDtoList= categoryService.all();
        responseDto.setResponseData(categoryDtoList);
        return responseDto;
    }

    /**
     * 保存，id有值更新，id=null新增
     * freemark内置 ?c 将数值转换为字符串，防止金额2,000等现象
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody CategoryDto categoryDto) {
        LOG.info("输出categoryDTO对象：{}", categoryDto);
        //保存校验
        Validator.require(categoryDto.getParent(),"父ID");
        Validator.require(categoryDto.getName(),"分类名称");
        Validator.length(categoryDto.getName(),"分类名称",1,50);

        ResponseDto responseDto = new ResponseDto();
        categoryService.save(categoryDto);
        responseDto.setResponseData(categoryDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        LOG.info("删除的--> id:{}", id);
        ResponseDto responseDto = new ResponseDto();
        categoryService.delete(id);
        return responseDto;
    }
}
