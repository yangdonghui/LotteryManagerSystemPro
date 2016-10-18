package com.manager.bean;

import java.util.List;

/**
 * 走势图 数据
 * @author donghuiyang
 * @create time 2016/7/20 0020.
 */
public class ChartBean extends BaseBean{
    public ChartBean(String title, List<Integer> redLists, List<Integer> blueLists){
        this.title = title;
        this.redLists = redLists;
        this.blueLists = blueLists;
    }

    public String getTitle() {
        return title;
    }

    public ChartBean setTitle(String title) {
        this.title = title;
        return this;
    }

    public List<Integer> getRedLists() {
        return redLists;
    }

    public ChartBean setRedLists(List<Integer> redLists) {
        this.redLists = redLists;
        return this;
    }

    public List<Integer> getBlueLists() {
        return blueLists;
    }

    public ChartBean setBlueLists(List<Integer> blueLists) {
        this.blueLists = blueLists;
        return this;
    }

    //期号  或 标题
    private String title;
    //红球
    private List<Integer> redLists;
    //篮球
    private List<Integer> blueLists;
}
