package com.manager.http;

/**
 * HTTP 返回码
 * @author donghuiyang
 * @create time 2016/5/19 0019.
 */
public class HttpMsg {
    //获取验证码
    public static final int SecurityCodeMsg1 = 1001;	//手机信息错误
    public static final int SecurityCodeMsg2 = 1002;	//验证码错误
    public static final int SecurityCodeMsg3 = 1003;	//网络错误
    public static final int SecurityCodeMsg4 = 1004;	//验证码短信发送失败
    public static final int SecurityCodeMsg5 = 1005;	//未知错误
    public static final int SecurityCodeMsg6 = 1006;	//验证码发送成功


    //投注站注册
    public static final int BettingshopRegisterCodeMsg1 = 1008;	    //  投注站注册成功
    public static final int BettingshopRegisterCodeMsg2= 1009;	    //  包含非法字符
    public static final int BettingshopRegisterCodeMsg3 = 10010;	//  输入信息过多
    public static final int BettingshopRegisterCodeMsg4 = 10011;	//  身份证信息错误
    public static final int BettingshopRegisterCodeMsg5 = 10012;	//  手机信息错误
    public static final int BettingshopRegisterCodeMsg6 = 10013;	//  业主ID错误
    public static final int BettingshopRegisterCodeMsg7 = 10014;	//  此业主ID已经注册
    public static final int BettingshopRegisterCodeMsg8 = 10015;	//  注册失败
    public static final int BettingshopRegisterCodeMsg9 = 10016;	//  用户名已经被使用
    public static final int BettingshopRegisterCodeMsg10 = 10017;	//  验证码错误
    public static final int BettingshopRegisterCodeMsg11 = 10018;	//  网络错误
    public static final int BettingshopRegisterCodeMsg12 = 10019;	//  两次密码输入不一致
    public static final int BettingshopRegisterCodeMsg13 = 10020;	//  验证码短信发送失败
    public static final int BettingshopRegisterCodeMsg14 = 10045;	//  未知错误

    //彩民注册
    public static final int LotteryRegisterCodeMsg1 = 100112;	//  包含非法字符
    public static final int LotteryRegisterCodeMsg2 = 100113;	//  输入信息过多
    public static final int LotteryRegisterCodeMsg3 = 100114;	//  手机信息错误
    public static final int LotteryRegisterCodeMsg4 = 100115;	//  业主ID错误
    public static final int LotteryRegisterCodeMsg5 = 100116;	//  此业主ID已经注册
    public static final int LotteryRegisterCodeMsg6 = 100117;	//  注册失败
    public static final int LotteryRegisterCodeMsg7 = 100118;	//  用户名已经被使用
    public static final int LotteryRegisterCodeMsg8 = 100119;	//  验证码错误
    public static final int LotteryRegisterCodeMsg9 = 100101;	//	网络错误
    public static final int LotteryRegisterCodeMsg10 = 100102;	//	两次密码输入不一致
    public static final int LotteryRegisterCodeMsg11 = 100103;	//	验证码短信发送失败
    public static final int LotteryRegisterCodeMsg12 = 100104;	//	未知错误
    public static final int LotteryRegisterCodeMsg13 = 100106;	//	修改密码成功
    public static final int LotteryRegisterCodeMsg14 = 100107;	//	包含非法字符

    //修改密码
    public static final int ModifyPwCodeMsg1 = 100108;	//	输入信息过多
    public static final int ModifyPwCodeMsg2 = 100109;	//	验证码错误
    public static final int ModifyPwCodeMsg3 = 100178;	//	网络错误
    public static final int ModifyPwCodeMsg4 = 100145;	//	两次密码输入不一致
    public static final int ModifyPwCodeMsg5 = 1001413; //	验证码短信发送失败
    public static final int ModifyPwCodeMsg6 = 100116;	//	未知错误

    //资料修改
    public static final int ModifyInfoCodeMsg1 = 100188;	//	资料修改成功
    public static final int ModifyInfoCodeMsg2 = 100177;	//	包含非法字符
    public static final int ModifyInfoCodeMsg3 = 100188;	//	输入信息过多
    public static final int ModifyInfoCodeMsg4 = 100155;	//	身份证信息错误(彩民输入错误)
    public static final int ModifyInfoCodeMsg5 = 100144;	//	手机信息错误
    public static final int ModifyInfoCodeMsg6 = 100166;	//	验证码错误
    public static final int ModifyInfoCodeMsg7 = 100111;	//	网络错误
    public static final int ModifyInfoCodeMsg8 = 100122;	//	Email格式错误
    public static final int ModifyInfoCodeMsg9 = 9001;	    //	未知错误

    //找回密码
    public static final int ForgetPwCodeMsg1 = 0001;	//	忘记密码修改成功
    public static final int ForgetPwCodeMsg2 = 8001;	//	包含非法字符
    public static final int ForgetPwCodeMsg3 = 7001;	//	输入信息过多
    public static final int ForgetPwCodeMsg4 = 6001;	//	验证码错误
    public static final int ForgetPwCodeMsg5 = 1601;	//	手机不符
    public static final int ForgetPwCodeMsg6 = 4001;	//	两次密码输入不一致
    public static final int ForgetPwCodeMsg7 = 3313;	//	账户不存在
    public static final int ForgetPwCodeMsg8 = 2001;	//	未知错误

}
