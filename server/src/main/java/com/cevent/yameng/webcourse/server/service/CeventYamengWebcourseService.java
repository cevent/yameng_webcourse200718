package com.cevent.yameng.webcourse.server.service;/**
 * Created by Cevent on 2020/7/19.
 */

import com.cevent.yameng.webcourse.server.domain.CeventYamengWebcourse;
import com.cevent.yameng.webcourse.server.domain.CeventYamengWebcourseExample;
import com.cevent.yameng.webcourse.server.mapper.CeventYamengWebcourseMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author cevent
 * @description Service层交给spring管理
 * @date 2020/7/19 14:34
 */
@Service
public class CeventYamengWebcourseService {
    //引入mapper
    @Resource
    private CeventYamengWebcourseMapper webcourseMapper;
    //查询列表，采用example方法
    public List<CeventYamengWebcourse> test_list() {
        return webcourseMapper.selectByExample(null);
    }

    //查询排序
    public List<CeventYamengWebcourse> order_list(){
        CeventYamengWebcourseExample example=new CeventYamengWebcourseExample();
        example.setOrderByClause("id desc");
        return webcourseMapper.selectByExample(example);
    }

    //根据id查询
    public List<CeventYamengWebcourse> getByid(){
        CeventYamengWebcourseExample example=new CeventYamengWebcourseExample();
        example.createCriteria().andIdEqualTo("3");
        return webcourseMapper.selectByExample(example);
    }
}
