package com.cevent.yameng.webcourse.server.dto;/**
 * Created by Cevent on 2020/7/26.
 */

/**
 * @author cevent
 * @description
 * @date 2020/7/26 22:36
 */
public class CoursePageDto extends PageDto{
    private String status;
    private String categotyId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategotyId() {
        return categotyId;
    }

    public void setCategotyId(String categotyId) {
        this.categotyId = categotyId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CoursePageDto{");
        sb.append("status='").append(status).append('\'');
        sb.append(", categotyId='").append(categotyId).append('\'');
        sb.append(", currentPage=").append(currentPage);
        sb.append(", initPageNum=").append(initPageNum);
        sb.append(", sumPage=").append(sumPage);
        sb.append(", list=").append(list);
        sb.append('}');
        return sb.toString();
    }
}
