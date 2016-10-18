package com.manager.bean;

import java.util.ArrayList;

/**
 * 用户选择彩种 号码 的数据结构
 * @author donghuiyang
 * @create time 2016/6/14 0014.
 */
public class UserSelectNumberBean{

    public UserSelectNumberBean(String id, int type, String name, String time, ArrayList numbersList) {
        this.id = id;
        this.lotteryType = type;
        this.lotteryName = name;
        this.time = time;


        setNumbersList(numbersList);
    }

    public String getId() {
        return id;
    }

    public UserSelectNumberBean setId(String id) {
        this.id = id;
        return this;
    }

    public int getLotteryType() {
        return lotteryType;
    }

    public UserSelectNumberBean setLotteryType(int lotteryType) {
        this.lotteryType = lotteryType;
        return this;
    }

    public String getLotteryName() {
        return lotteryName;
    }

    public UserSelectNumberBean setLotteryName(String lotteryName) {
        this.lotteryName = lotteryName;
        return this;
    }

    public String getTime() {
        return time;
    }

    public UserSelectNumberBean setTime(String time) {
        this.time = time;
        return this;
    }

    public ArrayList<NoteLotterybean> getNumbersList() {
        return numbersList;
    }

    public UserSelectNumberBean setNumbersList(ArrayList<NoteLotterybean> numbersList) {
        if (this.numbersList == null){
            this.numbersList = new ArrayList<>();
        }

        this.numbersList.clear();;
        this.numbersList.addAll(numbersList);
        return this;
    }

    private String id;
    private int lotteryType;
    private String lotteryName;
    private String time;

    private ArrayList<NoteLotterybean> numbersList;

    public boolean isChecked = true;
}
