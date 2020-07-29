package com.cevent.yameng.webcourse.server.service;

import com.cevent.yameng.webcourse.server.domain.Teacher;
import com.cevent.yameng.webcourse.server.domain.TeacherExample;
import com.cevent.yameng.webcourse.server.dto.TeacherDto;
import com.cevent.yameng.webcourse.server.dto.PageDto;
import com.cevent.yameng.webcourse.server.mapper.TeacherMapper;
import com.cevent.yameng.webcourse.server.util.CopyUtil;
import com.cevent.yameng.webcourse.server.util.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;


@Service
public class TeacherService {
    @Resource
    private TeacherMapper teacherMapper;

    /**
     * 查询所有讲师
     */
    public List<TeacherDto> all(){
        TeacherExample teacherExample=new TeacherExample();
        List<Teacher> teacherList=teacherMapper.selectByExample(teacherExample);
        return CopyUtil.copyList(teacherList,TeacherDto.class);
    }

    /**
     * 列表查询
     */
    public void list(PageDto pageDto){

        PageHelper.startPage(pageDto.getCurrentPage(),pageDto.getInitPageNum());
        TeacherExample teacherExample=new TeacherExample();


        List<Teacher> teacherList=teacherMapper.selectByExample(teacherExample);

        PageInfo<Teacher> pageInfo=new PageInfo<>(teacherList);
        pageDto.setSumPage(pageInfo.getTotal());

        List<TeacherDto> teacherDtoList=CopyUtil.copyList(teacherList,TeacherDto.class);
        pageDto.setList(teacherDtoList);
    }

    /**
     * 新增
     */
    private void insert(Teacher teacher){

        teacher.setId(UUIDUtil.getShortUUID());
        teacherMapper.insert(teacher);
    }


    /**
     * 更新
     */
    private void update(Teacher teacher){
        teacherMapper.updateByPrimaryKey(teacher);
    }

    /**
     * 保存：id有值更新，id=null新增
     */
    public void save(TeacherDto teacherDto){
        Teacher teacher= CopyUtil.copy(teacherDto,Teacher.class);
        if(StringUtils.isEmpty(teacherDto.getId())){
            this.insert(teacher);
        }else{
            this.update(teacher);
        }
    }

    /**
     * 删除
     */
    public void delete(String id){
        teacherMapper.deleteByPrimaryKey(id);
    }

}
