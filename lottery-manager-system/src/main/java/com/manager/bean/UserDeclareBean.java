package com.manager.bean;

/**
 * 申报内容属性
 * Created by Administrator on 2016/3/23 0023.
 */
public class UserDeclareBean extends BaseBean{


    public String getDeclareNumber() {
        return declareNumber;
    }

    public UserDeclareBean setDeclareNumber(String declareNumber) {
        this.declareNumber = declareNumber;
        return this;
    }

    public String getDeclareParentType() {
        return declareParentType;
    }

    public UserDeclareBean setDeclareParentType(String declareParentType) {
        this.declareParentType = declareParentType;
        return this;
    }

    public String getDeclareTime() {
        return declareTime;
    }

    public UserDeclareBean setDeclareTime(String declareTime) {
        this.declareTime = declareTime;
        return this;
    }

    public String getDeclareChildType() {
        return declareChildType;
    }

    public UserDeclareBean setDeclareChildType(String declareChildType) {
        this.declareChildType = declareChildType;
        return this;
    }

    public String getDeclareInfo() {
        return declareInfo;
    }

    public UserDeclareBean setDeclareInfo(String declareInfo) {
        this.declareInfo = declareInfo;
        return this;
    }

    public String getDeclareState() {
        return declareState;
    }

    public UserDeclareBean setDeclareState(String declareState) {
        this.declareState = declareState;
        return this;
    }

    public int getID() {
        return mID;
    }


    public UserDeclareBean(int id,
                           String arg0,
                           String arg1,
                           String arg2,
                           String arg3,
                           String arg4,
                           String arg5) {

        super();

        this.mID = id;
        this.declareNumber = arg0;
        this.declareParentType = arg1;
        this.declareTime = arg2;
        this.declareChildType = arg3;
        this.declareInfo = arg4;
        this.declareState = arg5;

    }


    private int mID;
    //申请状态
    private String declareState = "";
    //申请编号
    private String declareNumber = "";
    //申报分类
    private String declareParentType = "";
    //期望日期
    private String declareTime = "";
    //类型
    private String declareChildType = "";
    //备注
    private String declareInfo = "";
}
