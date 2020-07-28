package com.cevent.yameng.webcourse.server.mapper.ceventmapper;

import com.cevent.yameng.webcourse.server.dto.SortDto;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Cevent on 2020/7/27.
 */
public interface CeventCourseMapper {
    //1.更新课程时间：section time++
    int updateCourseTime(@Param("courseId") String courseId);
    //2.更新排序
    int updateSort(SortDto sortDto);
    //3.移动排序，向前+1
    int moveSortForward(SortDto sortDto);
    //4.移动排序，向后-1
    int moveSortBackward(SortDto sortDto);
}
