package com.cevent.yameng.webcourse.server.dto;
/*
* typeSet:整理出所有用到的java类型，生成import语句，一种类型只需要引入一次，所以去重
*/

public class CategoryDto{
    /**
    * 循环list
    */
        /**
        * 分类ID
        */
        private String id;
        /**
        * 父ID
        */
        private String parent;
        /**
        * 分类名称
        */
        private String name;
        /**
        * 顺序
        */
        private Integer sort;

    /**
    * 循环list:set/get方法
    */
        public String getId(){
            return id;
        }

        public void setId(String id){
            this.id=id;
        }
        public String getParent(){
            return parent;
        }

        public void setParent(String parent){
            this.parent=parent;
        }
        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name=name;
        }
        public Integer getSort(){
            return sort;
        }

        public void setSort(Integer sort){
            this.sort=sort;
        }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append(" [");
        stringBuilder.append("Hash = ").append(hashCode());

            stringBuilder.append(", id=").append(id);
            stringBuilder.append(", parent=").append(parent);
            stringBuilder.append(", name=").append(name);
            stringBuilder.append(", sort=").append(sort);

        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}