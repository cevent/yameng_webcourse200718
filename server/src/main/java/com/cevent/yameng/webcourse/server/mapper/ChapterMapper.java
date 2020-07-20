package com.cevent.yameng.webcourse.server.mapper;

import com.cevent.yameng.webcourse.server.domain.Chapter;
import com.cevent.yameng.webcourse.server.domain.ChapterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChapterMapper {
    long countByExample(ChapterExample example);

    int deleteByExample(ChapterExample example);

    int deleteByPrimaryKey(String id);

    int insert(Chapter record);

    int insertSelective(Chapter record);

    List<Chapter> selectByExample(ChapterExample example);

    Chapter selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Chapter record, @Param("example") ChapterExample example);

    int updateByExample(@Param("record") Chapter record, @Param("example") ChapterExample example);

    int updateByPrimaryKeySelective(Chapter record);

    int updateByPrimaryKey(Chapter record);
}