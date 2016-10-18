package com.manager.bean;

import java.util.ArrayList;

/**
 * 开奖信息 属性
 * @author donghuiyang
 * @create time 2016/5/13 0013.
 */
public class LotteryInfoBean extends BaseBean{

    public int lotteryID;
    public int lotteryType;
    public String lotteryName;
    public String lotteryQihao;
    public String lotteryTime;
    public ArrayList<String> lotteryNumbers1;
    public ArrayList<String> lotteryNumbers2;

    public int getLotteryID() {
        return lotteryID;
    }

    public int getLotteryType() {
        return lotteryType;
    }

    public String getLotteryName() {
        return lotteryName;
    }

    public String getLotteryQihao() {
        return lotteryQihao;
    }

    public String getLotteryTime() {
        return lotteryTime;
    }

    public ArrayList<String> getLotteryNumbers1() {
        return lotteryNumbers1;
    }

    public ArrayList<String> getLotteryNumbers2() {
        return lotteryNumbers2;
    }

    public LotteryInfoBean(int id, int type, String name, String qihao, String time, ArrayList<String> numbers1, ArrayList<String> numbers2) {
        super();

        this.lotteryID = id;
        this.lotteryType = type;
        this.lotteryName = name;
        this.lotteryQihao = qihao;
        this.lotteryTime = time;

        this.lotteryNumbers1 = numbers1;
        this.lotteryNumbers2 = numbers2;
    }
}
