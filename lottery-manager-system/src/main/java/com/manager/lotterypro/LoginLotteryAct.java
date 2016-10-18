package com.manager.lotterypro;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import com.manager.common.Tools;
import com.manager.helper.UserHelper;
import com.manager.mvp.presenter.UserLoginPresenter;
import com.manager.mvp.view.IUserLoginView;

/**
 * 彩民登录界面
 * Created by Administrator on 2016/4/19 0019.
 */
public class LoginLotteryAct extends Activity implements View.OnClickListener, IUserLoginView {

    //操控对象
    private UserLoginPresenter mUserLoginPresenter = new UserLoginPresenter(this);

    //根view
    private View rootView;
    //返回按钮
    private View backBtn, forgetBtn;
    //登录按钮
    private Button loginBtn;
    //注册入口按钮
    private Button registerEnterBtn;
    //编辑框控件
    private EditText editTextName, editTextPw;

    View.OnFocusChangeListener mFocusChangedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_lottery_view_layout);

        initView();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //隐藏软键盘
        Tools.hideSoftInput(this);
        mHandler = null;
    }

    @Override
    protected void onResume() {
        super.onResume();

        //清除编辑框
        clearEdit();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        rootView = (View) findViewById(R.id.login_lottery);

        backBtn = (View) findViewById(R.id.lottery_login_view_back_btn);
        backBtn.setOnClickListener(this);

        loginBtn = (Button) findViewById(R.id.login_detail_login_btn_lottery);
        loginBtn.setOnClickListener(this);

        forgetBtn = (View) findViewById(R.id.cm_forger_btn);
        forgetBtn.setOnClickListener(this);

        registerEnterBtn = (Button) findViewById(R.id.login_register_btn_lottery);
        registerEnterBtn.setOnClickListener(this);

        editTextName = (EditText) findViewById(R.id.editText_login_name_lottery);
        editTextPw = (EditText) findViewById(R.id.editText_login_pw);

    }

    private void clearEdit() {
        editTextName.setText("");
        editTextPw.setText("");
    }

    private Handler mHandler = new Handler();
    private void update() {
        //这里必须要给一个延迟，如果不加延迟则没有效果。我现在还没想明白为什么
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //将ScrollView滚动到底
                ((ScrollView) findViewById(R.id.login_lottery_scrollView)).fullScroll(View.FOCUS_DOWN);
            }
        }, 100);

    }

    @Override
    public void onClick(View v) {
        if (backBtn == v) {
            //返回按钮
            SysApplication.backBtn(this, null);

        } else if (loginBtn == v) {
            //登录按钮 登录请求
            mUserLoginPresenter.login(UserHelper.LotteryUser);

        } else if (forgetBtn == v) {
            //忘记密码
            mUserLoginPresenter.toForgetPw();

        } else if (registerEnterBtn == v) {
            //注册入口按钮
            mUserLoginPresenter.toLotteryRegister();

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //back按键
            SysApplication.backBtn(this, LoginLotteryMainAct.class);

            return true;
        }

        return false;
    }

    /**
     * 获取账号
     *
     * @return
     */
    @Override
    public String getUserName() {
        return editTextName.getText().toString();
    }

    /**
     * 获取密码
     *
     * @return
     */
    @Override
    public String getPassword() {
        return editTextPw.getText().toString();
    }

    /**
     * 显示错误信息
     */
    @Override
    public void showFailedError() {

    }

    @Override
    public void nextBtn(String msg) {

    }

}
