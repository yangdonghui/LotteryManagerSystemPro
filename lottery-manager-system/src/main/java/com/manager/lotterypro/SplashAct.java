package com.manager.lotterypro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * @author donghuiyang
 * @create time 2016/5/11 0011.
 */
public class SplashAct extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent();
        if (SysApplication.isLogin()){
            //已登录 跳转到首页
            intent.setClass(this, MainAct.class);
        }else{
            //未注册 跳转到选择身份界面
            intent.setClass(this, IdentitySelectAct.class);
        }

        startActivity(intent);
        finish();
    }
}
