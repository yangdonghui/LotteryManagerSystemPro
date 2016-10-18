package com.manager.lotterypro;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

/**
 *站点登录主界面
 * Created by Administrator on 2016/4/19 0019.
 */
public class LoginBettingshopMainAct extends Activity implements View.OnClickListener{

    View backView;
    Button loginBtn, registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bettingshop_main_view_layout);

        backView = (View)findViewById(R.id.bettingshop_main_view_back_btn);
        backView.setOnClickListener(this);

        loginBtn = (Button) findViewById(R.id.bettingshop_login_main_login_btn);
        registerBtn = (Button) findViewById(R.id.bettingshop_login_main_register_btn);
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == backView){
            //返回按钮
            SysApplication.backBtn(this, IdentitySelectAct.class);

        }else if(v == loginBtn){
            //进入投注站登录界面
            SysApplication.enterNext(this, LoginBettingshopAct.class);

        }else if(v == registerBtn){
            //注册入口
            SysApplication.enterNext(this, RegisterBettingshopAct.class);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //返回
            SysApplication.backBtn(this, IdentitySelectAct.class);

            return true;
        }

        return false;
    }
}
