package com.manager.bean;

import java.util.List;

/**
 * @author donghuiyang
 * @create time 2016/6/3 0003.
 */
public class OrdersBean extends BaseBean {
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
    private List<ItemBean> listData;

    //配送时间
    private String sendTime;
    //失败原因
    private String faildStr;

    public OrdersBean(String id,
                      String number,
                      String time,
                      int state,
                      String stateStr,
                      String price,
                      List<ItemBean> listData){
        this.id = id;
        this.number = number;
        this.time = time;
        this.state = state;
        this.stateStr = stateStr;
        this.price = price;
        this.listData = listData;
    }

    public OrdersBean(String id,
                      int type,
                      String number,
                      String sendTime,
                      int state,
                      String stateStr,
                      String price,
                      List<ItemBean> listData,
                      String faildStr){
        this.id = id;
        this.type = type;
        this.number = number;

        this.state = state;
        this.stateStr = stateStr;
        this.price = price;
        this.listData = listData;

        this.sendTime = sendTime;
        this.faildStr = faildStr;
    }

    public OrdersBean(String id,
                      String number,
                      String content,
                      String time,
                      String stateStr,
                      String price){
        this.id = id;
        this.number = number;
        this.content = content;
        this.time = time;
        this.stateStr = stateStr;
        this.price = price;
    }

    public OrdersBean(String id,
                      String number,
                      String content,
                      String time,
                      int state,
                      String stateStr,
                      String price,
                      List<ItemBean> listData){
        this.id = id;
        this.number = number;
        this.content = content;
        this.time = time;
        this.state = state;
        this.stateStr = stateStr;
        this.price = price;

        this.listData = listData;
    }

    public String getId() {
        return id;
    }

    public OrdersBean setId(String id) {
        this.id = id;
        return this;
    }

    public int getType() {
        return type;
    }

    public OrdersBean setType(int type) {
        this.type = type;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public OrdersBean setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getContent() {
        return content;
    }

    public OrdersBean setContent(String content) {
        this.content = content;
        return this;
    }

    public String getTime() {
        return time;
    }

    public OrdersBean setTime(String time) {
        this.time = time;
        return this;
    }

    public int getState() {
        return state;
    }

    public OrdersBean setState(int state) {
        this.state = state;
        return this;
    }

    public String getstateStr() {
        return stateStr;
    }

    public OrdersBean setstateStr(String stateStr) {
        this.stateStr = stateStr;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public OrdersBean setPrice(String price) {
        this.price = price;
        return this;
    }

    public List<ItemBean> getListData() {
        return listData;
    }

    public OrdersBean setListData(List<ItemBean> listData) {
        this.listData = listData;
        return this;
    }

    public String getFaildStr() {
        return faildStr;
    }

    public OrdersBean setFaildStr(String faildStr) {
        this.faildStr = faildStr;
        return this;
    }

    public String getSendTime() {
        return sendTime;
    }

    public OrdersBean setSendTime(String sendTime) {
        this.sendTime = sendTime;
        return this;
    }
}
