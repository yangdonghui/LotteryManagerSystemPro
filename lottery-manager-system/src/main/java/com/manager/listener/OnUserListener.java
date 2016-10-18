package com.manager.listener;

import com.manager.bean.UserBean;

/**
 * 用户登录模块的相关回调接口
 * Created by Administrator on 2016/3/14 0014.
 */
public interface OnUserListener {
    //成功回调
    void onSuccess(String msg, UserBean user);

    //登录失败回调
    void onFailed(String msg);
}
