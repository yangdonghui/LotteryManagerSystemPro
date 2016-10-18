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
public class LoginLotteryMainAct extends Activity implements View.OnClickListener{

    View backView;
    Button loginBtn, registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lottery_main_view_layout);

        backView = (View)findViewById(R.id.lottery_man_view_back_btn);
        backView.setOnClickListener(this);

        loginBtn = (Button) findViewById(R.id.lottery_login_main_login_btn);
        registerBtn = (Button) findViewById(R.id.lottery_login_main_register_btn);
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == backView){
            //返回按钮
            SysApplication.backBtn(this, IdentitySelectAct.class);

        }else if(v == loginBtn){
            //进入彩民登录界面
            SysApplication.enterNext(this, LoginLotteryAct.class);

        }else if(v == registerBtn){
            //注册入口
            SysApplication.enterNext(this, RegisterLotteryAct.class);
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
