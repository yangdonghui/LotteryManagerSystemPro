package com.manager.user;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.manager.common.Tools;
import com.manager.lotterypro.R;

/**
 * 修改登录密码界面
 * @author donghuiyang
 * @create time 2016/4/19 0019.
 */
public class ModifyPasswordAct extends Activity implements View.OnClickListener{

    //返回按钮
    private View backBtn;

    private View rootview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_pw_layout);

        initView();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //隐藏软键盘
        Tools.hideSoftInput(this);

    }

    private void initView() {
        rootview = (View) findViewById(R.id.modify_pw_rootview);

        ViewGroup topView = (ViewGroup)findViewById(R.id.modify_pw_topview);

        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.setting_info_0);

        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //返回按钮
            finish();
        }
    }
}
