package com.manager.mvp.view;

/**
 * Created by Administrator on 2016/3/14 0014.
 */
public interface IUserRegisterView {
    //获取用户类型
    int getUserType();
    //获取用户名
    String getUserName();
    //获取密码
    String getPassword();
    //获取确认密码
    String getPasswordAgain();

    //获取手机号
    String getPhone();
    //获取验证码
    String getVerificationCode();

    //复选按钮
    boolean getCheckBox();

    //获取真实姓名
    String getRealName();
    //获取身份证号
    String getIdentity();

    void nextBtn(String msg);
}
