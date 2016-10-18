package com.manager.bean;


/**
 * 用户属性  （辖区属性）
 * Created by donghuiyang on 2016/3/14 0014.
 */
public class BettingshopBean extends BaseBean{

    public BettingshopBean(String id,
                           String iconUrl,
                           String bettingshopID,
                           String nickName,
                           String realName,
                           String phone,
                           String address,
                           String saleMoney) {
        this.userId = id;
        this.iconUrl = iconUrl;
        this.bettingshopID = bettingshopID;
        this.nickName = nickName;
        this.realName = realName;
        this.userPhone = phone;
        this.address = address;
        this.saleMoney = saleMoney;
    }
    public BettingshopBean(String id,
                           String bettingshopID,
                           String phone,
                           String address){
        this.userId = id;
        this.bettingshopID = bettingshopID;
        this.userPhone = phone;
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public BettingshopBean setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getBettingshopID() {
        return bettingshopID;
    }

    public BettingshopBean setBettingshopID(String bettingshopID) {
        this.bettingshopID = bettingshopID;
        return this;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public BettingshopBean setUserPhone(String userPhone) {
        this.userPhone = userPhone;
        return this;
    }

    public String getRealName() {
        return realName;
    }

    public BettingshopBean setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public BettingshopBean setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
        return this;
    }

    public String getNickName() {
        return nickName;
    }

    public BettingshopBean setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public BettingshopBean setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getSaleMoney() {
        return saleMoney;
    }

    public BettingshopBean setSaleMoney(String saleMoney) {
        this.saleMoney = saleMoney;
        return this;
    }

    //用户id
    private String userId;
    //昵称
    private String nickName;
    //真实姓名
    private String realName;
    //投注站ID
    private String bettingshopID = "";
    //用户手机号
    private String userPhone;
    //头像链接地址
    private String iconUrl;
    //站点地址
    private String address;
    //销售额
    private String saleMoney;

}
