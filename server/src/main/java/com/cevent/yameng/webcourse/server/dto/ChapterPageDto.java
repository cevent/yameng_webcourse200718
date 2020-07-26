package com.cevent.yameng.webcourse.server.dto;/**
 * Created by Cevent on 2020/7/26.
 */

/**
 * @author cevent
 * @description
 * @date 2020/7/26 21:28
 */
public class ChapterPageDto extends PageDto{
    private String courseId;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ChapterPageDto{");
        sb.append("courseId='").append(courseId).append('\'');
        sb.append(", currentPage=").append(currentPage);
        sb.append(", initPageNum=").append(initPageNum);
        sb.append(", sumPage=").append(sumPage);
        sb.append(", list=").append(list);
        sb.append('}');
        return sb.toString();
    }
}
