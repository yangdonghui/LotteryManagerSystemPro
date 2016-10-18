package com.manager.mvp.presenter;

import android.app.Activity;
import android.os.Handler;

import com.manager.bean.UserBean;
import com.manager.biz.UserBiz;
import com.manager.listener.OnUserListener;
import com.manager.lotterypro.ForgetPwAct;
import com.manager.lotterypro.RegisterBettingshopAct;
import com.manager.lotterypro.RegisterLotteryAct;
import com.manager.lotterypro.SysApplication;
import com.manager.mvp.view.IUserLoginView;

/**
 * Created by Administrator on 2016/3/14 0014.
 */
public class UserLoginPresenter {
    private UserBiz userBiz;
    private IUserLoginView userLoginView;

    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView)
    {
        this.userLoginView = userLoginView;
        this.userBiz = new UserBiz();
    }

    /**
     * 登录
     */
    public void login(final int type) {

        userBiz.login(type,userLoginView.getUserName(), userLoginView.getPassword(), new OnUserListener() {
            @Override
            public void onSuccess(final String msg,final UserBean user) {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.nextBtn(msg);
                    }
                });
            }

            @Override
            public void onFailed(String msg) {
            }
        });
    }

    /**
     * 进入注册界面
     */
    public void toLotteryRegister() {
        //注册入口
        SysApplication.enterNext((Activity) userLoginView, RegisterLotteryAct.class);
    }
    public void toBettingshopRegister() {
        //注册入口
        SysApplication.enterNext((Activity)userLoginView, RegisterBettingshopAct.class);
    }

    /**
     * 进入找回密码界面
     */
    public void toForgetPw() {
        SysApplication.enterNext((Activity)userLoginView, ForgetPwAct.class);
    }
}
