package com.cevent.yameng.webcourse.server.dto;
/*
 * typeSet:整理出所有用到的java类型，生成import语句，一种类型只需要引入一次，所以去重
 */

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CourseDto {
    /**
     * 循环list
     */
    /**
     * 课程ID
     */
    private String id;
    /**
     * 课程名
     */
    private String name;
    /**
     * 课程概述
     */
    private String summary;
    /**
     * 时长 | 单位秒
     */
    private Integer time;
    /**
     * 价格（元）
     */
    private BigDecimal price;
    /**
     * 封面
     */
    private String image;
    /**
     * 级别|ONE("1","初级"),TWO("2","中级"),THREE("3","高级")
     */
    private String level;
    /**
     * 收费|CHARGE("C","收费"),FREE("F","免费")
     */
    private String charge;
    /**
     * 状态|PUBLISH("P","发布"),DRAFT("D","草稿")
     */
    private String status;
    /**
     * 报名数
     */
    private Integer enroll;
    /**
     * 顺序
     */
    private Integer sort;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    /**
     * 增加categoryDto获取分类，需要和前端命名一致
     */
    private List<CategoryDto> categoryDtos;

    /**
     * 增加更改表的teacher
     * @return
     */
    private String teacherId;

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public List<CategoryDto> getCategoryDtos() {
        return categoryDtos;
    }

    public void setCategoryDtos(List<CategoryDto> categoryDtos) {
        this.categoryDtos = categoryDtos;
    }

    /**
     * 循环list:set/get方法
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getEnroll() {
        return enroll;
    }

    public void setEnroll(Integer enroll) {
        this.enroll = enroll;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CourseDto{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", summary='").append(summary).append('\'');
        sb.append(", time=").append(time);
        sb.append(", price=").append(price);
        sb.append(", image='").append(image).append('\'');
        sb.append(", level='").append(level).append('\'');
        sb.append(", charge='").append(charge).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", enroll=").append(enroll);
        sb.append(", sort=").append(sort);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", categoryDtos=").append(categoryDtos);
        sb.append(", teacherId='").append(teacherId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}