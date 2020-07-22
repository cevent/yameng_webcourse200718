package com.cevent.yameng.webcourse.server.dto;/**
 * Created by Cevent on 2020/7/21.
 */

import sun.util.resources.ga.LocaleNames_ga;

import java.util.List;

/**
 * @author cevent
 * @description 分页实体类<泛型>
 *     使用泛型的地方都可以用Object代替，泛型可以在编译期间发现问题，避免了代码中写强制类型转换
 * @date 2020/7/21 16:39
 */
public class PageDto<T> {
    //1.当前页码：前端初始化
    protected int currentPage;
    //2.每页条数：前端初始化
    protected int initPageNum;
    //3.总条数：后台计算
    protected long sumPage;
    //4.查询结果页的集合，返回结果不确定类型，使用泛型
    protected List<T> list;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getInitPageNum() {
        return initPageNum;
    }

    public void setInitPageNum(int initPageNum) {
        this.initPageNum = initPageNum;
    }

    public long getSumPage() {
        return sumPage;
    }

    public void setSumPage(long sumPage) {
        this.sumPage = sumPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageDto{" +
                "currentPage=" + currentPage +
                ", initPageNum=" + initPageNum +
                ", sumPage=" + sumPage +
                ", list=" + list +
                '}';
    }
}
