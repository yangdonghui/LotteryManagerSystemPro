package com.manager.bean;

/**
 * 耗材申报 属性
 * @author donghuiyang
 * @create time 2016/6/28 0028.
 */
public class DeclareConsumableBean extends BaseBean{

    public DeclareConsumableBean(String id,
                                 int parentType,
                                 String parentTypeInfo,
                                 int childType,
                                 String childTypeInfo,
                                 String time,
                                 String info,
                                 int num){
        this.id = id;

        this.parentType = parentType;
        this.declareParentType = parentTypeInfo;
        this.childType = childType;
        this.declareChildType = childTypeInfo;

        this.declareTime = time;
        this.declareInfo = info;

        this.num = num;
    }

    public String getId() {
        return id;
    }

    public DeclareConsumableBean setId(String id) {
        this.id = id;
        return this;
    }

    public int getParentType() {
        return parentType;
    }

    public DeclareConsumableBean setParentType(int parentType) {
        this.parentType = parentType;
        return this;
    }

    public String getDeclareParentType() {
        return declareParentType;
    }

    public DeclareConsumableBean setDeclareParentType(String declareParentType) {
        this.declareParentType = declareParentType;
        return this;
    }

    public int getChildType() {
        return childType;
    }

    public DeclareConsumableBean setChildType(int childType) {
        this.childType = childType;
        return this;
    }

    public String getDeclareChildType() {
        return declareChildType;
    }

    public DeclareConsumableBean setDeclareChildType(String declareChildType) {
        this.declareChildType = declareChildType;
        return this;
    }

    public String getDeclareTime() {
        return declareTime;
    }

    public DeclareConsumableBean setDeclareTime(String declareTime) {
        this.declareTime = declareTime;
        return this;
    }

    public String getDeclareInfo() {
        return declareInfo;
    }

    public DeclareConsumableBean setDeclareInfo(String declareInfo) {
        this.declareInfo = declareInfo;
        return this;
    }

    public int getNum() {
        return num;
    }

    public DeclareConsumableBean setNum(int num) {
        this.num = num;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public DeclareConsumableBean setPrice(int price) {
        this.price = price;
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

    //备注
    private String declareInfo = "";

    private int num;
    private int price = 0;
}
