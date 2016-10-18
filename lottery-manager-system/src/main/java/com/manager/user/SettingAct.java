package com.manager.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;

/**
 * 设置界面
 * @author donghuiyang
 * @create time 2016/4/19 0019.
 */
public class SettingAct extends Activity implements View.OnClickListener{

    //返回按钮
    private View backBtn;

    //退出登录
    private View exitBtn;

    private View childView0, childView1, childView2, childView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);

        initView();
    }

    /**
     * 获取控件对象 并 初始化
     */
    private void initView() {
        ViewGroup topView = (ViewGroup)findViewById(R.id.setting_topview);

        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.setting_title);

        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);

        exitBtn = (TextView) findViewById(R.id.settting_exit_btn);
        exitBtn.setOnClickListener(this);

        childView0 = (View) findViewById(R.id.setting_view_0);
        childView0.setOnClickListener(this);
        childView1 = (View) findViewById(R.id.setting_view_1);
        childView1.setOnClickListener(this);
        childView2 = (View) findViewById(R.id.setting_view_2);
        childView2.setOnClickListener(this);
        childView3 = (View) findViewById(R.id.setting_view_3);
        childView3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //返回按钮
            finish();
        }else if (exitBtn == v){
            //退出 到 登录界面
            SysApplication.exitLogin(this);

        }else if(childView0 == v) {
            //密码修改入口
            Intent intent = new Intent(this, ModifyPasswordAct.class);
            startActivity(intent);
        }else if(childView1 == v) {
            //资料修改入口
            Intent intent = new Intent(this, ModifyInfoAct.class);
            startActivity(intent);
        }else if(childView2 == v) {
            //手机号入口
            Intent intent = new Intent(this, ModifyPhoneAct.class);
            startActivity(intent);
        }
    }
}
