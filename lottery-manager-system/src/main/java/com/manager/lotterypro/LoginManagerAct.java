package com.manager.lotterypro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.manager.common.Tools;
import com.manager.helper.UserHelper;
import com.manager.mvp.presenter.UserLoginPresenter;
import com.manager.mvp.view.IUserLoginView;

/**
 *管理员登录界面
 * Created by Administrator on 2016/4/19 0019.
 */
public class LoginManagerAct extends Activity implements View.OnClickListener, IUserLoginView{
    //操控对象
    private UserLoginPresenter mUserLoginPresenter = new UserLoginPresenter(this);

    //根view
    private View rootView;

    //返回按钮 密码找回按钮
    View backBtn, forgetBtn;

    //登录按钮
    private Button loginBtn;

    //编辑框控件
    private EditText editTextName, editTextPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_manager_view_layout);

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
        rootView = (View) findViewById(R.id.login_manager);

        backBtn = (View)findViewById(R.id.login_manager_view_back_btn);
        backBtn.setOnClickListener(this);

        loginBtn = (Button) findViewById(R.id.login_detail_login_btn_manager);
        loginBtn.setOnClickListener(this);

        forgetBtn = (View) findViewById(R.id.gul_forger_btn);
        forgetBtn.setOnClickListener(this);

        editTextName = (EditText) findViewById(R.id.editText_login_name_manager);
        editTextPw = (EditText) findViewById(R.id.editText_login_pw_manager);
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //返回 按钮
            SysApplication.backBtn(this, IdentitySelectAct.class);
        }else if (loginBtn == v) {
            //登录按钮 登录请求
            mUserLoginPresenter.login(UserHelper.ManagerUser);

        }else if(forgetBtn == v) {
            //忘记密码
            mUserLoginPresenter.toForgetPw();

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //back按键
            SysApplication.backBtn(this, IdentitySelectAct.class);

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
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //进入 绑定银行卡
        Intent intent = new Intent(this, MainAct.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
