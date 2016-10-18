package com.manager.mvp.presenter;

import android.os.Handler;

import com.manager.bean.UserBean;
import com.manager.biz.UserBiz;
import com.manager.listener.OnUserListener;
import com.manager.mvp.view.IUserRegisterView;


/**
 * Created by Administrator on 2016/3/14 0014.
 */
public class UserRegisterPresenter {
    private UserBiz userBiz;
    private IUserRegisterView userRegisterView;
    private Handler mHandler = new Handler();

    public UserRegisterPresenter(IUserRegisterView userRegisterView)
    {
        this.userRegisterView = userRegisterView;
        this.userBiz = new UserBiz();
    }

    /**
     * 注册
     */
    public void register() {
        userBiz.register(userRegisterView.getUserType(),
                userRegisterView.getUserName(),
                userRegisterView.getPassword(),
                userRegisterView.getPhone(),
                userRegisterView.getVerificationCode(),
                new OnUserListener() {
            @Override
            public void onSuccess(final String msg,final UserBean user) {

                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userRegisterView.nextBtn(msg);
                    }
                });
            }

            @Override
            public void onFailed(String msg) {
                //Toast.makeText((Context)userRegisterView,msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void nextBtn() {

    }
}
