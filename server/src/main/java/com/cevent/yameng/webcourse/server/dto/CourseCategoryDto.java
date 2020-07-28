package com.cevent.yameng.webcourse.server.dto;
/*
* typeSet:整理出所有用到的java类型，生成import语句，一种类型只需要引入一次，所以去重
*/

public class CourseCategoryDto{
    /**
    * 循环list
    */
        /**
        * 课程分类ID
        */
        private String id;
        /**
        * 课程ID|course.id
        */
        private String courseId;
        /**
        * 分类ID|category.id
        */
        private String categoryId;

    /**
    * 循环list:set/get方法
    */
        public String getId(){
            return id;
        }

        public void setId(String id){
            this.id=id;
        }
        public String getCourseId(){
            return courseId;
        }

        public void setCourseId(String courseId){
            this.courseId=courseId;
        }
        public String getCategoryId(){
            return categoryId;
        }

        public void setCategoryId(String categoryId){
            this.categoryId=categoryId;
        }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append(" [");
        stringBuilder.append("Hash = ").append(hashCode());

            stringBuilder.append(", id=").append(id);
            stringBuilder.append(", courseId=").append(courseId);
            stringBuilder.append(", categoryId=").append(categoryId);

        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}