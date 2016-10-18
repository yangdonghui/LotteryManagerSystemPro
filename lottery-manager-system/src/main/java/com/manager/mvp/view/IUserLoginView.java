package com.manager.mvp.view;

/**
 * 登录界面的接口
 * Created by Administrator on 2016/3/14 0014.
 */
public interface IUserLoginView {
    //获取用户名
    String getUserName();
    //获取密码
    String getPassword();

    //显示登录错误
    void showFailedError();

    void nextBtn(String msg);
}
