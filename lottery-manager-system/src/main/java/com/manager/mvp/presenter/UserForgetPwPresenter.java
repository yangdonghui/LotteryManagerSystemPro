package com.manager.mvp.presenter;

import com.manager.bean.UserBean;
import com.manager.biz.UserBiz;
import com.manager.listener.OnUserListener;
import com.manager.mvp.view.IUserLoginView;

/**
 * 找回密码管理类
 * Created by Administrator on 2016/3/14 0014.
 */
public class UserForgetPwPresenter {
    private UserBiz userBiz;
    private IUserLoginView userLoginView;

    public UserForgetPwPresenter(IUserLoginView userLoginView)
    {
        this.userLoginView = userLoginView;
        this.userBiz = new UserBiz();
    }

    /**
     * 登录
     */
    public void modifyPw() {
        userBiz.modifyPw(userLoginView.getPassword(), new OnUserListener() {
            @Override
            public void onSuccess(String msg,final UserBean user) {
            }

            @Override
            public void onFailed(String msg) {
            }
        });
    }
}
