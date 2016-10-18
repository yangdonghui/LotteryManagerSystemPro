package com.manager.bean;

import java.util.ArrayList;

/**
 * 故障报修 属性
 * @author donghuiyang
 * @create time 2016/6/28 0028.
 */
public class DeclareFaultBean extends BaseBean{

    public DeclareFaultBean(String id,
                            int parentType,
                            String parentTypeInfo,
                            int childType,
                            String childTypeInfo,
                            String time1,
                            String time2,
                            String info,
                            BettingshopBean bettingshopBean){
        this.id = id;
        this.parentType = parentType;
        this.declareParentType = parentTypeInfo;
        this.childType = childType;
        this.declareChildType = childTypeInfo;
        this.declareTime = time1;
        this.finishTime = time2;
        this.declareInfo = info;

        this.bettingshopBean = bettingshopBean;
    }

    public String getId() {
        return id;
    }

    public DeclareFaultBean setId(String id) {
        this.id = id;
        return this;
    }

    public int getParentType() {
        return parentType;
    }

    public DeclareFaultBean setParentType(int parentType) {
        this.parentType = parentType;
        return this;
    }

    public String getDeclareParentType() {
        return declareParentType;
    }

    public DeclareFaultBean setDeclareParentType(String declareParentType) {
        this.declareParentType = declareParentType;
        return this;
    }

    public int getChildType() {
        return childType;
    }

    public DeclareFaultBean setChildType(int childType) {
        this.childType = childType;
        return this;
    }

    public String getDeclareChildType() {
        return declareChildType;
    }

    public DeclareFaultBean setDeclareChildType(String declareChildType) {
        this.declareChildType = declareChildType;
        return this;
    }

    public String getDeclareTime() {
        return declareTime;
    }

    public DeclareFaultBean setDeclareTime(String declareTime) {
        this.declareTime = declareTime;
        return this;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public DeclareFaultBean setFinishTime(String finishTime) {
        this.finishTime = finishTime;
        return this;
    }

    public String getDeclareInfo() {
        return declareInfo;
    }

    public DeclareFaultBean setDeclareInfo(String declareInfo) {
        this.declareInfo = declareInfo;
        return this;
    }

    public ArrayList<String> getIconLists() {
        return iconLists;
    }

    public DeclareFaultBean setIconLists(ArrayList<String> iconLists) {
        this.iconLists = iconLists;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public DeclareFaultBean setPrice(int price) {
        this.price = price;
        return this;
    }

    public BettingshopBean getBettingshopBean() {
        return bettingshopBean;
    }

    public DeclareFaultBean setBettingshopBean(BettingshopBean bettingshopBean) {
        this.bettingshopBean = bettingshopBean;
        return this;
    }

    private String id;

    private int parentType;
    //申报分类
    private String declareParentType = "";
    private int childType;
    //类型
    private String declareChildType = "";

    //期望日期
    private String declareTime = "";
    //完成时间
    private String finishTime;

    //备注
    private String declareInfo = "";

    //图片
    private ArrayList<String> iconLists;

    private int price = 0;

    //投注站信息
    private BettingshopBean bettingshopBean;

}
