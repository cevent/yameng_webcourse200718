package com.cevent.yameng.webcourse.server.mapper;

import com.cevent.yameng.webcourse.server.domain.CeventYamengWebcourse;
import com.cevent.yameng.webcourse.server.domain.CeventYamengWebcourseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CeventYamengWebcourseMapper {
    long countByExample(CeventYamengWebcourseExample example);

    int deleteByExample(CeventYamengWebcourseExample example);

    int deleteByPrimaryKey(String id);

    int insert(CeventYamengWebcourse record);

    int insertSelective(CeventYamengWebcourse record);

    List<CeventYamengWebcourse> selectByExample(CeventYamengWebcourseExample example);

    CeventYamengWebcourse selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CeventYamengWebcourse record, @Param("example") CeventYamengWebcourseExample example);

    int updateByExample(@Param("record") CeventYamengWebcourse record, @Param("example") CeventYamengWebcourseExample example);

    int updateByPrimaryKeySelective(CeventYamengWebcourse record);

    int updateByPrimaryKey(CeventYamengWebcourse record);
}