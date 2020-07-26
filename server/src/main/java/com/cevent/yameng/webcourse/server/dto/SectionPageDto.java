package com.cevent.yameng.webcourse.server.dto;/**
 * Created by Cevent on 2020/7/26.
 */

/**
 * @author cevent
 * @description
 * @date 2020/7/26 22:24
 */
public class SectionPageDto extends PageDto{
    private String courseId;
    private String chapterId;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SectionPageDto{");
        sb.append("courseId='").append(courseId).append('\'');
        sb.append(", chapterId='").append(chapterId).append('\'');
        sb.append(", currentPage=").append(currentPage);
        sb.append(", initPageNum=").append(initPageNum);
        sb.append(", sumPage=").append(sumPage);
        sb.append(", list=").append(list);
        sb.append('}');
        return sb.toString();
    }
}
