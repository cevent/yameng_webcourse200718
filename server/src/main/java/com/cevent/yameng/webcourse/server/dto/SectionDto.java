package com.cevent.yameng.webcourse.server.dto;
/*
* typeSet:整理出所有用到的java类型，生成import语句，一种类型只需要引入一次，所以去重
*/
        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;

public class SectionDto{
    /**
    * 循环list
    */
        /**
        * 小节ID
        * @JsonFormat注解：后端日期类型格式化，再返回到前端
        */
        private String id;
        /**
        * 标题
        * @JsonFormat注解：后端日期类型格式化，再返回到前端
        */
        private String title;
        /**
        * 课程ID
        * @JsonFormat注解：后端日期类型格式化，再返回到前端
        */
        private String courseId;
        /**
        * 章节ID
        * @JsonFormat注解：后端日期类型格式化，再返回到前端
        */
        private String chapterId;
        /**
        * 视频地址
        * @JsonFormat注解：后端日期类型格式化，再返回到前端
        */
        private String videoAdd;
        /**
        * 时长-单位秒
        * @JsonFormat注解：后端日期类型格式化，再返回到前端
        */
        private Integer time;
        /**
        * 收费-C收费-F免费
        * @JsonFormat注解：后端日期类型格式化，再返回到前端
        */
        private String charge;
        /**
        * 顺序
        * @JsonFormat注解：后端日期类型格式化，再返回到前端
        */
        private Integer sort;
        /**
        * 创建时间
        * @JsonFormat注解：后端日期类型格式化，再返回到前端
        */
            @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private Date createTime;
        /**
        * 修改时间
        * @JsonFormat注解：后端日期类型格式化，再返回到前端
        */
            @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private Date updateTime;

    /**
    * 循环list:set/get方法
    */
        public String getId(){
            return id;
        }

        public void setId(String id){
            this.id=id;
        }
        public String getTitle(){
            return title;
        }

        public void setTitle(String title){
            this.title=title;
        }
        public String getCourseId(){
            return courseId;
        }

        public void setCourseId(String courseId){
            this.courseId=courseId;
        }
        public String getChapterId(){
            return chapterId;
        }

        public void setChapterId(String chapterId){
            this.chapterId=chapterId;
        }
        public String getVideoAdd(){
            return videoAdd;
        }

        public void setVideoAdd(String videoAdd){
            this.videoAdd=videoAdd;
        }
        public Integer getTime(){
            return time;
        }

        public void setTime(Integer time){
            this.time=time;
        }
        public String getCharge(){
            return charge;
        }

        public void setCharge(String charge){
            this.charge=charge;
        }
        public Integer getSort(){
            return sort;
        }

        public void setSort(Integer sort){
            this.sort=sort;
        }
        public Date getCreateTime(){
            return createTime;
        }

        public void setCreateTime(Date createTime){
            this.createTime=createTime;
        }
        public Date getUpdateTime(){
            return updateTime;
        }

        public void setUpdateTime(Date updateTime){
            this.updateTime=updateTime;
        }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append(" [");
        stringBuilder.append("Hash = ").append(hashCode());

            stringBuilder.append(", id=").append(id);
            stringBuilder.append(", title=").append(title);
            stringBuilder.append(", courseId=").append(courseId);
            stringBuilder.append(", chapterId=").append(chapterId);
            stringBuilder.append(", videoAdd=").append(videoAdd);
            stringBuilder.append(", time=").append(time);
            stringBuilder.append(", charge=").append(charge);
            stringBuilder.append(", sort=").append(sort);
            stringBuilder.append(", createTime=").append(createTime);
            stringBuilder.append(", updateTime=").append(updateTime);

        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}