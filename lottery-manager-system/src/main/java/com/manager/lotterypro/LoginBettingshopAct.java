package com.manager.lotterypro;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.manager.common.Tools;
import com.manager.helper.UserHelper;
import com.manager.mvp.presenter.UserLoginPresenter;
import com.manager.mvp.view.IUserLoginView;

/**
 *站点业主登录界面
 * Created by Administrator on 2016/4/19 0019.
 */
public class LoginBettingshopAct extends Activity implements View.OnClickListener, IUserLoginView{

    //操控对象
    private UserLoginPresenter mUserLoginPresenter = new UserLoginPresenter(this);

    //根view
    private View rootView;

    //返回按钮
    View backBtn, forgetBtn;
    //登录按钮
    private Button loginBtn;
    //注册入口按钮
    private Button registerEnterBtn;
    //编辑框控件
    private EditText editTextName, editTextPw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_bettingshop_view_layout);

        initView();
    }

    @Override
    protected void onResume() {

        clearEdit();

        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //隐藏软键盘
        Tools.hideSoftInput(this);

    }

    private void clearEdit() {
        editTextName.setText("");
        editTextPw.setText("");
    }

    /**
     * 初始化控件
     */
    private void initView() {
        rootView = (View) findViewById(R.id.login_bettingshop);

        backBtn = (View)findViewById(R.id.bettingshop_login_view_back_btn);
        backBtn.setOnClickListener(this);

        loginBtn = (Button) findViewById(R.id.login_detail_login_btn_betting);
        loginBtn.setOnClickListener(this);

        forgetBtn = (View) findViewById(R.id.zd_forger_btn);
        forgetBtn.setOnClickListener(this);

        registerEnterBtn = (Button) findViewById(R.id.login_register_btn_betting);
        registerEnterBtn.setOnClickListener(this);

        editTextName = (EditText) findViewById(R.id.editText_login_name_betting);
        editTextPw = (EditText) findViewById(R.id.editText_login_pw_betting);
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //返回按钮
            SysApplication.backBtn(this, null);
        }else if (loginBtn == v) {
            //登录按钮 登录请求
            mUserLoginPresenter.login(UserHelper.BettingShopUser);

        }else if(forgetBtn == v) {
            //忘记密码
            mUserLoginPresenter.toForgetPw();

        }else if (registerEnterBtn == v){
            //注册入口按钮
            mUserLoginPresenter.toBettingshopRegister();

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //返回
            SysApplication.backBtn(this, LoginBettingshopMainAct.class);

            return true;
        }

        return false;
    }

    /**
     * 获取账号
     * @return
     */
    @Override
    public String getUserName() {
        return editTextName.getText().toString();
    }

    /**
     * 获取密码
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
