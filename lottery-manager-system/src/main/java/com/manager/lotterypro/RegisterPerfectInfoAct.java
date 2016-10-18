package com.manager.lotterypro;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.manager.bean.UserBean;
import com.manager.common.Tools;

/**
 *注册后 完善资料界面
 * Created by Administrator on 2016/4/19 0019.
 */
public class RegisterPerfectInfoAct extends Activity implements View.OnClickListener{

    //用户属性
    private UserBean userBean;

    //根view
    private View rootView;

    ViewGroup progressViewGroup;

    //返回按钮
    View backBtn;

    //下一步按钮
    private Button nextBtn;
    private View skipBtn;

    //编辑框控件
    private EditText editText1, editText2, editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_perfect_info_layout);

        initView();

        userBean = SysApplication.userBean;
    }

    @Override
    protected void onPause() {
        super.onPause();

        //隐藏软键盘
        Tools.hideSoftInput(this);

    }

    /**
     * 初始化控件
     */
    private void initView() {
        rootView = (View) findViewById(R.id.register_perfect_rootview);

        //进度栏
        progressViewGroup = (ViewGroup) findViewById(R.id.register_perfect_progress_include);
        ImageView view = (ImageView)progressViewGroup.findViewById(R.id.register_progress_view1);
        view.setImageResource(R.drawable.oval_shape_5);
        TextView tv = (TextView)progressViewGroup.findViewById(R.id.register_progress_tv1);
        tv.setTextColor(Color.WHITE);

        //topview
        ViewGroup topView = (ViewGroup)findViewById(R.id.register_perfect_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(getResources().getString(R.string.register_info2));
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setVisibility(View.GONE);

        nextBtn = (Button) findViewById(R.id.register_perfect_btn1);
        nextBtn.setOnClickListener(this);

        skipBtn = (View) findViewById(R.id.register_perfect_btn2);
        skipBtn.setOnClickListener(this);

        editText1 = (EditText) findViewById(R.id.register_perfect_edit1);
        editText2 = (EditText) findViewById(R.id.register_perfect_edit2);
        editText3 = (EditText) findViewById(R.id.register_perfect_edit3);
    }

    @Override
    public void onClick(View v) {
        if (nextBtn == v) {
            //下一步
            //mUserLoginPresenter.login();
            SysApplication.Next(this, RegisterBindingBankAct.class);

        }else if(skipBtn == v) {

            //跳过 进入完成注册界面
            SysApplication.Next(this, RegisterFinishAct.class);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }
}
