package com.manager.bean;

import java.util.List;

/**
 * 站点申报 订单 属性
 * @author donghuiyang
 * @create time 2016/6/3 0003.
 */
public class DeclareOrdersBean extends BaseBean {
    //id号
    private String id;
    //订单类型
    private int type;
    //订单编号
    private String number;
    //订单内容
    private String content;
    //订单生成时间
    private String time;

    //订单状态
    private int state;
    private String stateStr;

    //订单总价格
    private String price;
    //物品列表
    private List<DeclareConsumableBean> listConsumableData;
    //物品列表
    private List<DeclareFaultBean> listFaultData;

    //配送时间
    private String sendTime;
    //失败原因
    private String faildStr;

    public DeclareOrdersBean(int type,
                             String id,
                             String number,
                             String time,
                             int state,
                             String stateStr,
                             String price,
                             List<DeclareFaultBean> listData1,
                             List<DeclareConsumableBean> listData2){
        this.type = type;
        this.id = id;
        this.number = number;
        this.time = time;
        this.state = state;
        this.stateStr = stateStr;
        this.price = price;

        //故障
        this.listFaultData = listData1;
        //耗材
        this.listConsumableData = listData2;
    }

    public String getId() {
        return id;
    }

    public DeclareOrdersBean setId(String id) {
        this.id = id;
        return this;
    }

    public int getType() {
        return type;
    }

    public DeclareOrdersBean setType(int type) {
        this.type = type;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public DeclareOrdersBean setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getContent() {
        return content;
    }

    public DeclareOrdersBean setContent(String content) {
        this.content = content;
        return this;
    }

    public String getTime() {
        return time;
    }

    public DeclareOrdersBean setTime(String time) {
        this.time = time;
        return this;
    }

    public int getState() {
        return state;
    }

    public DeclareOrdersBean setState(int state) {
        this.state = state;
        return this;
    }

    public String getstateStr() {
        return stateStr;
    }

    public DeclareOrdersBean setstateStr(String stateStr) {
        this.stateStr = stateStr;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public DeclareOrdersBean setPrice(String price) {
        this.price = price;
        return this;
    }

    public String getFaildStr() {
        return faildStr;
    }

    public DeclareOrdersBean setFaildStr(String faildStr) {
        this.faildStr = faildStr;
        return this;
    }

    public String getSendTime() {
        return sendTime;
    }

    public DeclareOrdersBean setSendTime(String sendTime) {
        this.sendTime = sendTime;
        return this;
    }

    public String getStateStr() {
        return stateStr;
    }

    public DeclareOrdersBean setStateStr(String stateStr) {
        this.stateStr = stateStr;
        return this;
    }

    public List<DeclareConsumableBean> getListConsumableData() {
        return listConsumableData;
    }

    public DeclareOrdersBean setListConsumableData(List<DeclareConsumableBean> listConsumableData) {
        this.listConsumableData = listConsumableData;
        return this;
    }

    public List<DeclareFaultBean> getListFaultData() {
        return listFaultData;
    }

    public DeclareOrdersBean setListFaultData(List<DeclareFaultBean> listFaultData) {
        this.listFaultData = listFaultData;
        return this;
    }
}
