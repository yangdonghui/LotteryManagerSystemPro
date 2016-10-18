package com.manager.lotterypro;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.manager.widgets.TimeButton;

/**
 * 密码找回界面
 * @author donghuiyang
 * @create time 2016/4/19 0019.
 */
public class ForgetPwAct extends Activity implements View.OnClickListener{

    //返回按钮
    private View backView;

    private Button okBtn;
    private TimeButton verCodeBtn;

    //编辑框
    private EditText editText1, editText2, editText3, editText4, editText5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_pw_layout);

        initView();
    }

    @Override
    protected void onDestroy() {

        verCodeBtn.onDestroy();

        super.onDestroy();
    }

    private void initView() {
        backView = (View)findViewById(R.id.forget_pw_back_btn);
        backView.setOnClickListener(this);

        editText1 = (EditText) findViewById(R.id.forget_pw_edit1);
        editText2 = (EditText) findViewById(R.id.forget_pw_edit2);
        editText3 = (EditText) findViewById(R.id.forget_pw_edit3);

        editText4 = (EditText) findViewById(R.id.forget_pw_edit4);
        editText5 = (EditText) findViewById(R.id.forget_pw_edit5);

        //确定按钮
        okBtn = (Button) findViewById(R.id.forget_pw_ok_btn);
        okBtn.setOnClickListener(this);

        //获取验证码
        verCodeBtn = (TimeButton) findViewById(R.id.forget_pw_vercode_btn);
        verCodeBtn.setOnClickListener(this);
    }

    /**
     * 更新获取验证码按钮
     */
    private void updateVerificationCodeBtn(boolean flag) {
        if (verCodeBtn != null) {
            verCodeBtn.setClickable(flag);

            if (flag){
                //获取短信验证码中 倒计时

            }else{
                //倒计时结束，重新 获取短信验证码
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (backView == v){
            //返回按钮
            SysApplication.backBtn(this, null);
        }
    }
}
