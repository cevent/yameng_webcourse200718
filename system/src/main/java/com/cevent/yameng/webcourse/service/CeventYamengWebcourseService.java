package com.cevent.yameng.webcourse.service;/**
 * Created by Cevent on 2020/7/19.
 */

import com.cevent.yameng.webcourse.domain.CeventYamengWebcourse;
import com.cevent.yameng.webcourse.mapper.CeventYamengWebcourseMapper;
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
    //查询列表
    public List<CeventYamengWebcourse> test_list() {
        return webcourseMapper.test_list();
    }
}
