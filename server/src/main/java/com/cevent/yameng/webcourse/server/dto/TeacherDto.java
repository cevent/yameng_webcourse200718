package com.cevent.yameng.webcourse.server.dto;
/*
* typeSet:整理出所有用到的java类型，生成import语句，一种类型只需要引入一次，所以去重
*/

public class TeacherDto{
    /**
    * 循环list
    */
        /**
        * 讲师ID
        */
        private String id;
        /**
        * 讲师姓名
        */
        private String name;
        /**
        * 昵称
        */
        private String nickname;
        /**
        * 头像
        */
        private String image;
        /**
        * 职位
        */
        private String position;
        /**
        * 座右铭
        */
        private String motto;
        /**
        * 介绍
        */
        private String introduce;

    /**
    * 循环list:set/get方法
    */
        public String getId(){
            return id;
        }

        public void setId(String id){
            this.id=id;
        }
        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name=name;
        }
        public String getNickname(){
            return nickname;
        }

        public void setNickname(String nickname){
            this.nickname=nickname;
        }
        public String getImage(){
            return image;
        }

        public void setImage(String image){
            this.image=image;
        }
        public String getPosition(){
            return position;
        }

        public void setPosition(String position){
            this.position=position;
        }
        public String getMotto(){
            return motto;
        }

        public void setMotto(String motto){
            this.motto=motto;
        }
        public String getIntroduce(){
            return introduce;
        }

        public void setIntroduce(String introduce){
            this.introduce=introduce;
        }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append(" [");
        stringBuilder.append("Hash = ").append(hashCode());

            stringBuilder.append(", id=").append(id);
            stringBuilder.append(", name=").append(name);
            stringBuilder.append(", nickname=").append(nickname);
            stringBuilder.append(", image=").append(image);
            stringBuilder.append(", position=").append(position);
            stringBuilder.append(", motto=").append(motto);
            stringBuilder.append(", introduce=").append(introduce);

        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}