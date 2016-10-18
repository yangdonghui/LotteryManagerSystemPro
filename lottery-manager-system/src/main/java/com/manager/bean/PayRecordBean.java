package com.manager.bean;

/**
 * @author donghuiyang
 * @create time 2016/6/17 0017.
 */
public class PayRecordBean extends BaseBean {

    public PayRecordBean(String id,
                         int type,
                          String title,
                          int state,
                          String stateStr,
                          String price,
                         String time){
        this.id = id;
        this.type = type;
        this.title = title;
        this.state = state;
        this.stateInfo = stateStr;
        this.price = price;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public PayRecordBean setId(String id) {
        this.id = id;
        return this;
    }

    public int getType() {
        return type;
    }

    public PayRecordBean setType(int type) {
        this.type = type;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public PayRecordBean setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getState() {
        return state;
    }

    public PayRecordBean setState(int state) {
        this.state = state;
        return this;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public PayRecordBean setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public PayRecordBean setPrice(String price) {
        this.price = price;
        return this;
    }

    public String getTime() {
        return time;
    }

    public PayRecordBean setTime(String time) {
        this.time = time;
        return this;
    }

    private String id;
    private int type;
    private String title;
    private int state;
    private String stateInfo;
    private String price;
    private String time;
}
