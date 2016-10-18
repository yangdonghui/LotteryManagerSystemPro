package com.manager.helper;

/**
 * 用户相关常量
 * Created by Administrator on 2016/3/14 0014.
 */
public class UserHelper {
    /******************************登录状态****************************/
    //未登录
    public static final int NOT_LOGIN = 0;
    //已登录
    public static final int LOGGED_IN = 2;

    /*****************************用户类型******************************/
    public static final int NoLottery = -1;
    //彩民
    public static final int LotteryUser = 1;
    //投注站
    public static final int BettingShopUser = 2;
    //管理员
    public static final int ManagerUser = 3;
}
