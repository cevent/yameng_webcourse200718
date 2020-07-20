package com.cevent.yameng.webcourse.business.controller.admin;/**
 * Created by Cevent on 2020/7/20.
 */

import com.cevent.yameng.webcourse.server.domain.Chapter;
import com.cevent.yameng.webcourse.server.dto.ChapterDto;
import com.cevent.yameng.webcourse.server.service.ChapterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author cevent
 * @description
 * @date 2020/7/20 22:38
 */
@RestController
@RequestMapping("/admin")
public class ChapterController {

    @Resource
    private ChapterService chapterService;

    @RequestMapping("/chapter")
    public List<Chapter> getCaptureList(){
        return chapterService.getChapters();
    }

    //获取dto实体类属性
    @RequestMapping("/chapterDto")
    public List<ChapterDto> getChapterDto(){
        return chapterService.getChaptersDtoObj();
    }
}
