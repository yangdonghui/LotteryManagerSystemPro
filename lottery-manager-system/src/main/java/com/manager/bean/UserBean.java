package com.manager.bean;


import com.manager.helper.UserHelper;

/**
 * 用户属性  （彩民 、站点、站点雇员、中心管理员等）
 * Created by donghuiyang on 2016/3/14 0014.
 */
public class UserBean extends BaseBean{

    public UserBean(){

    }

    public UserBean(int mUserType, String mUserName, String mUserPw, String mUserPhone){
        this.userName = mUserName;
        this.userPw = mUserPw;
        this.userPhone = mUserPhone;

        setUserType(mUserType);
    }

    public UserBean(String id, String iconUrl, String realName, String userName, int sex, String cardNumber, String userPhone) {
        this.userId = id;
        this.iconUrl = iconUrl;
        this.realName = realName;
        this.userName = userName;
        this.sex = sex;
        this.cardNumber = cardNumber;
        this.userPhone = userPhone;
    }

    /**
     * 赋值
     * @param mUserType:用户类型
     * @param mUserName:用户名
     * @param mUserPw:密码
     * @param mUserPhone:手机号码
     */
    public void setAllData(int mUserType, String mUserName, String mUserPw, String mUserPhone, String isLogin) {

        this.userName = mUserName;
        this.userPw = mUserPw;
        this.userPhone = mUserPhone;
        this.isLogin = Integer.parseInt(isLogin);

        setUserType(mUserType);
    }

    public int getUserType() {
        return userType;
    }

    public UserBean setUserType(int userType) {
        this.userType = userType;
        if (this.userType == UserHelper.LotteryUser) {
            setUserIdentity("迷彩");
        }else if (this.userType == UserHelper.BettingShopUser){
            setUserIdentity("业主");
        }else if (this.userType == UserHelper.ManagerUser){
            setUserIdentity("管理员");
        }
        return this;
    }

    public String getUserIdentity() {
        return userIdentity;
    }

    public UserBean setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UserBean setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUserPw() {
        return userPw;
    }

    public UserBean setUserPw(String userPw) {
        this.userPw = userPw;
        return this;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public UserBean setUserPhone(String userPhone) {
        this.userPhone = userPhone;
        return this;
    }

    public String getBettingshopID() {
        return bettingshopID;
    }

    public UserBean setBettingshopID(String bettingshopID) {
        this.bettingshopID = bettingshopID;
        return this;
    }

    public String getRealName() {
        return realName;
    }

    public UserBean setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public UserBean setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public UserBean setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
        return this;
    }

    public int getSex() {
        return sex;
    }

    public UserBean setSex(int sex) {
        this.sex = sex;
        return this;
    }

    public int getAccountState() {
        return accountState;
    }

    public UserBean setAccountState(int accountState) {
        this.accountState = accountState;
        return this;
    }

    public int getIsLogin() {
        return isLogin;
    }

    public UserBean setIsLogin(int isLogin) {
        this.isLogin = isLogin;
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

    //是否在线
    private int isLogin = 0;

}
