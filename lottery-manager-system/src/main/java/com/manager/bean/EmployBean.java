package com.manager.bean;


/**
 * 用户属性  （站点雇员）
 * Created by donghuiyang on 2016/3/14 0014.
 */
public class EmployBean extends BaseBean{

    public EmployBean(){

    }

    public EmployBean(int mUserType, String mUserName, String mUserPw, String mUserPhone){
        this.userName = mUserName;
        this.userPw = mUserPw;
        this.userPhone = mUserPhone;

    }

    public String getUserIdentity() {
        return userIdentity;
    }

    public EmployBean setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public EmployBean setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUserPw() {
        return userPw;
    }

    public EmployBean setUserPw(String userPw) {
        this.userPw = userPw;
        return this;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public EmployBean setUserPhone(String userPhone) {
        this.userPhone = userPhone;
        return this;
    }

    public String getBettingshopID() {
        return bettingshopID;
    }

    public EmployBean setBettingshopID(String bettingshopID) {
        this.bettingshopID = bettingshopID;
        return this;
    }

    public String getRealName() {
        return realName;
    }

    public EmployBean setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public EmployBean setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public EmployBean setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
        return this;
    }

    public int getSex() {
        return sex;
    }

    public EmployBean setSex(int sex) {
        this.sex = sex;
        return this;
    }

    public int getAccountState() {
        return accountState;
    }

    public EmployBean setAccountState(int accountState) {
        this.accountState = accountState;
        return this;
    }

    //用户id
    private String userId;
    //用户类型
    private int userType = -1;

    private String userIdentity = "";
    //用户名
    private String userName = "";
    //用户密码
    private String userPw;
    //用户手机号
    private String userPhone;
    //投注站ID
    private String bettingshopID = "";
    //真实姓名
    private String realName;
    //身份证号码
    private String cardNumber;

    //头像链接地址
    private String iconUrl;
    //性别
    private int sex;
    //账号状态
    private int accountState;//站点雇员 账号状态 0：启用 1：停用

}
