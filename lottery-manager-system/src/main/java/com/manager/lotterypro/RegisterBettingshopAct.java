package com.manager.lotterypro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.manager.common.StringHelper;
import com.manager.common.Tools;
import com.manager.helper.UserHelper;
import com.manager.mvp.presenter.UserRegisterPresenter;
import com.manager.mvp.view.IUserRegisterView;
import com.manager.widgets.TimeButton;

/**
 * 投注站 注册界面
 * @author donghuiyang
 * @create time 2016/4/19 0019.
 */
public class RegisterBettingshopAct extends Activity implements View.OnClickListener, IUserRegisterView{
    //操控对象
    private UserRegisterPresenter mPresenter = new UserRegisterPresenter(this);

    //根view
    private View rootView;

    ViewGroup progressViewGroup;

    //返回按钮
    private View backBtn;

    //按钮
    private TimeButton verificationcodeBtn;
    private Button registerOkBtn;

    //服务协议复选框
    private CheckBox checkBox;

    //查看服务协议按钮
    private View showBtn;

    //扫描身份证号
    private ImageButton scanBtn;

    //编辑框控件
    private EditText editText1, editText2, editText3, editText4, editText5, editText6, editText7;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_bettingshop_layout);

        initView();
        initProgressView();

        intent = getIntent();

        SysApplication.registerBettingshopAct = this;
    }

    @Override
    protected void onResume() {

        //clearEdit();

        updateCheckBox();

        super.onResume();
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
        SysApplication.registerBettingshopAct = null;

    }

    /**
     * 初始化控件
     */
    private void initView() {
        rootView = (View) findViewById(R.id.register_bettingshop_rootview);

        //topview
        ViewGroup topView = (ViewGroup)findViewById(R.id.register_bettingshop_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(getResources().getString(R.string.register_info_5));
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);


        registerOkBtn = (Button) findViewById(R.id.register_bettingshop_ok_btn);
        registerOkBtn.setOnClickListener(this);

        verificationcodeBtn = (TimeButton) findViewById(R.id.register_bettingshop_verificationcode_btn);
        verificationcodeBtn.setOnClickListener(this);

        //身份证扫描
        scanBtn = (ImageButton) findViewById(R.id.register_bettingshop_identity_scan_btn);
        scanBtn.setOnClickListener(this);

        checkBox = (CheckBox) findViewById(R.id.register_bettingshop_checkBox);
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

        showBtn = (View) findViewById(R.id.register_bettingshop_info_btn);
        showBtn.setOnClickListener(this);

        editText1 = (EditText) findViewById(R.id.register_bettingshop_edit1);
        editText2 = (EditText) findViewById(R.id.register_bettingshop_edit2);
        editText3 = (EditText) findViewById(R.id.register_bettingshop_edit3);
        editText4 = (EditText) findViewById(R.id.register_bettingshop_edit4);
        editText5 = (EditText) findViewById(R.id.register_bettingshop_edit5);
        editText6 = (EditText) findViewById(R.id.register_bettingshop_edit6);
        editText7 = (EditText) findViewById(R.id.register_bettingshop_edit7);

    }

    private void initProgressView() {
        //进度栏
        progressViewGroup = (ViewGroup) findViewById(R.id.register_bettingshop_progress_include);

        LinearLayout layout = (LinearLayout) progressViewGroup.findViewById(R.id.register_progress_framelayout111);
        FrameLayout frameLayout3 = (FrameLayout) progressViewGroup.findViewById(R.id.register_progress_framelayout3);
        if (IdentitySelectAct.userType == 2){
            //站点注册 2步骤
            frameLayout3.setVisibility(View.GONE);
            progressViewGroup.findViewById(R.id.register_progress_line2).setVisibility(View.GONE);
            {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)layout.getLayoutParams();
                params.leftMargin = 215;
                params.rightMargin = 215;
                layout.setLayoutParams(params);
                layout.requestLayout();
            }
        }
    }

    private void clearEdit() {
        editText1.setText("");
        editText2.setText("");
        editText3.setText("");
        editText4.setText("");
        editText5.setText("");
        editText6.setText("");
        editText7.setText("");
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
            SysApplication.enterNext1(this, ServiceAgreementAct.class);

        }else if(registerOkBtn == v) {
            //注册请求按钮
            /*if (checkInput())*/{
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
        }else if (getRealName().equals("")){
            Toast.makeText(this, "请填写真实姓名!", Toast.LENGTH_SHORT).show();
            return false;
        }else if (!StringHelper.isSardID(getIdentity())){
            Toast.makeText(this, "身份证号格式不正确!", Toast.LENGTH_SHORT).show();
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
        return UserHelper.BettingShopUser;
    }

    @Override
    public String getUserName() {
        return editText1.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return editText2.getText().toString().trim();
    }

    @Override
    public String getPasswordAgain() {
        return editText3.getText().toString().trim();
    }

    @Override
    public String getPhone() {
        return editText6.getText().toString().trim();
    }

    @Override
    public String getVerificationCode() {
        return editText7.getText().toString().trim();
    }

    @Override
    public boolean getCheckBox() {
        return checkBox.isChecked();
    }

    @Override
    public String getRealName() {
        return editText4.getText().toString().trim();
    }

    @Override
    public String getIdentity() {
        return editText5.getText().toString().trim();
    }

    @Override
    public void nextBtn(String msg) {

        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //进入 绑定银行卡
        SysApplication.Next(this, RegisterBindingBankAct.class);
    }

}
