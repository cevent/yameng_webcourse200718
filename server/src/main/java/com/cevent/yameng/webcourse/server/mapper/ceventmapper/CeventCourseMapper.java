package com.cevent.yameng.webcourse.server.mapper.ceventmapper;

import org.apache.ibatis.annotations.Param;

/**
 * Created by Cevent on 2020/7/27.
 */
public interface CeventCourseMapper {
    //1.更新课程时间：section time++
    int updateCourseTime(@Param("courseId") String courseId);
}
