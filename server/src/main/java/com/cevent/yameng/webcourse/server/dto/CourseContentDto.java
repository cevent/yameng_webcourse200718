package com.cevent.yameng.webcourse.server.dto;
/*
* typeSet:整理出所有用到的java类型，生成import语句，一种类型只需要引入一次，所以去重
*/

public class CourseContentDto{
    /**
    * 循环list
    */
        /**
        * 课程内容ID
        */
        private String id;
        /**
        * 课程内容
        */
        private String content;

    /**
    * 循环list:set/get方法
    */
        public String getId(){
            return id;
        }

        public void setId(String id){
            this.id=id;
        }
        public String getContent(){
            return content;
        }

        public void setContent(String content){
            this.content=content;
        }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append(" [");
        stringBuilder.append("Hash = ").append(hashCode());

            stringBuilder.append(", id=").append(id);
            stringBuilder.append(", content=").append(content);

        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}