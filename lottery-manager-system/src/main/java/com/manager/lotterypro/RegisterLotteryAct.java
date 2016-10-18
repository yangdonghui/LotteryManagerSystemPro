package com.manager.lotterypro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.manager.common.AESEncryptor;
import com.manager.common.StringHelper;
import com.manager.common.Tools;
import com.manager.helper.UserHelper;
import com.manager.mvp.presenter.UserRegisterPresenter;
import com.manager.mvp.view.IUserRegisterView;
import com.manager.widgets.TimeButton;

/**
 * 彩民注册界面
 * @author donghuiyang
 * @create time 2016/4/19 0019.
 */
public class RegisterLotteryAct extends Activity implements View.OnClickListener, IUserRegisterView{
    //操控对象
    private UserRegisterPresenter mPresenter = new UserRegisterPresenter(this);

    //根view
    private View rootView;
    //返回按钮
    private View backBtn;

    //按钮
    private TimeButton verificationcodeBtn;
    private Button registerOkBtn;

    //服务协议复选框
    private CheckBox checkBox;

    //查看服务协议按钮
    private View showBtn;

    //编辑框控件
    private EditText editText1, editText2, editText3, editText4, editText5;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_lottery_layout);

        initView();

        intent = getIntent();

        SysApplication.registerLotteryAct = this;
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateCheckBox();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //隐藏软键盘
        Tools.hideSoftInput(this, rootView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        verificationcodeBtn.onDestroy();
        SysApplication.registerLotteryAct = null;
    }

    /**
     * 初始化控件
     */
    private void initView() {
        rootView = (View) findViewById(R.id.register_lottery_rootview);

        //topview
        ViewGroup topView = (ViewGroup)findViewById(R.id.register_lottery_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(getResources().getString(R.string.register));
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);


        registerOkBtn = (Button) findViewById(R.id.register_lottery_ok_btn);
        registerOkBtn.setOnClickListener(this);

        verificationcodeBtn = (TimeButton) findViewById(R.id.register_lottery_verificationcode_btn);
        verificationcodeBtn.setOnClickListener(this);

        checkBox = (CheckBox) findViewById(R.id.register_lottery_checkBox);
        /*checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    registerOkBtn.setClickable(true);
                }else{
                    registerOkBtn.setClickable(false);
                }
            }
        });*/

        showBtn = (View) findViewById(R.id.register_lottery_info_btn);
        showBtn.setOnClickListener(this);

        editText1 = (EditText) findViewById(R.id.register_lottery_edit1);
        editText2 = (EditText) findViewById(R.id.register_lottery_edit2);
        editText3 = (EditText) findViewById(R.id.register_lottery_edit3);
        editText4 = (EditText) findViewById(R.id.register_lottery_edit4);
        editText5 = (EditText) findViewById(R.id.register_lottery_edit5);

    }

    private void clearEdit() {
        editText1.setText("");
        editText2.setText("");
        editText3.setText("");
        editText4.setText("");
        editText5.setText("");
    }

    /**
     * 更新复选框状态
     */
    private void updateCheckBox() {
        boolean flag = false;

        flag = intent.getBooleanExtra("agreement", false);
        if (flag && checkBox.isChecked() != true){
            checkBox.setChecked(true);
        }
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v) {
            //返回按钮
            SysApplication.backBtn(this, null);

        }else if(showBtn == v) {
            //查看服务协议
            SysApplication.enterNext(this, ServiceAgreementAct.class);
;
        }else if(registerOkBtn == v) {
            //注册请求按钮
            //if (checkInput())
            {
                mPresenter.register();
            }
        }
    }

    /**
     * 检查输入
     * @return
     */
    private boolean checkInput() {
        if(!StringHelper.checkUserName(getUserName())){
            Toast.makeText(this, "账号必须为6-6位包含数字和字母!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!StringHelper.checkPassword(getPassword())){
            Toast.makeText(this, "密码必须为6-6位包含数字和字母!", Toast.LENGTH_SHORT).show();
            return false;
        }else if (!StringHelper.checkAgainPassword(getPassword(), getPasswordAgain())){
            Toast.makeText(this, "两次输入密码不同!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!StringHelper.isPhone(getPhone())) {
            Toast.makeText(this, "手机号格式不正确!", Toast.LENGTH_SHORT).show();
            return false;
        }else if(getVerificationCode().equals("")){
            Toast.makeText(this, "未输入手机验证码!", Toast.LENGTH_SHORT).show();
            return false;
        }else if (!getCheckBox()){
            //未阅读服务协议
            Toast.makeText(this, "未阅读服务协议!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    public int getUserType() {
        return UserHelper.LotteryUser;
    }

    @Override
    public String getUserName() {
        return editText1.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        String pwdEncoder = null;
        String password = editText2.getText().toString().trim();
        try {
            pwdEncoder = AESEncryptor.encrypt(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pwdEncoder;
    }

    @Override
    public String getPasswordAgain() {
        return editText3.getText().toString().trim();
    }

    @Override
    public String getPhone() {
        return editText4.getText().toString().trim();
    }

    @Override
    public String getVerificationCode() {
        return editText5.getText().toString().trim();
    }

    @Override
    public boolean getCheckBox() {
        return checkBox.isChecked();
    }

    @Override
    public String getRealName() {
        return null;
    }

    @Override
    public String getIdentity() {
        return null;
    }

    @Override
    public void nextBtn(String msg) {
        //进入 完善资料
        SysApplication.Next(this, RegisterPerfectInfoAct.class);
    }

}
