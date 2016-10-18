package com.manager.bean;

/**
 * 即开票产品的属性
 * @author donghuiyang
 * @create time 2016/6/1 0001.
 */
public class BillingBean extends ProductBean {

    public BillingBean(String id, String iconUrl, String title, String price, String buyNum) {
        setId(id);
        setIconUrl(iconUrl);
        setTitle(title);
        setPrice(price);
        setBuyNum(buyNum);
    }

    public BillingBean(String id, String iconUrl, String title, String price, String buyNum, String value) {
        setId(id);
        setIconUrl(iconUrl);
        setTitle(title);
        setPrice(price);
        setBuyNum(buyNum);
        setValue(value);
    }

    public BillingBean(String id, String iconUrl, String title, String price, String leftNum, String bonusNum, String winningTimes, String time, String value) {
        setId(id);
        setIconUrl(iconUrl);
        setTitle(title);
        setPrice(price);
        setValue(value);

        this.leftNum = leftNum;
        this.bonusNum = bonusNum;
        this.winningTimes = winningTimes;
        this.time = time;
    }

    @Override
    public String getLeftNum() {
        return leftNum;
    }

    @Override
    public BillingBean setLeftNum(String leftNum) {
        this.leftNum = leftNum;
        return this;
    }

    public String getBonusNum() {
        return bonusNum;
    }

    public BillingBean setBonusNum(String bonusNum) {
        this.bonusNum = bonusNum;
        return this;
    }

    public String getWinningTimes() {
        return winningTimes;
    }

    public BillingBean setWinningTimes(String winningTimes) {
        this.winningTimes = winningTimes;
        return this;
    }

    public String getTime() {
        return time;
    }

    public BillingBean setTime(String time) {
        this.time = time;
        return this;
    }

    //剩余数量
    private String leftNum;
    //最高奖金
    private String bonusNum;
    //中奖机会
    private String winningTimes;

    //发行日期
    private String time;

}
