package com.manager.wallet;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.manager.common.Constants;
import com.manager.common.Tools;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;

/**
 * 钱包 提现界面
 * @author donghuiyang
 * @create time 2016/4/20 0020.
 */
public class WalletWithdrawAct extends Activity implements View.OnClickListener{
    //返回按钮
    private View backBtn;

    //下一步 按钮
    private Button nextBtn;
    private EditText moneyEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_withdraw_layout);

        initTopView();
        initView();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //隐藏软键盘
        Tools.hideSoftInput(this);

    }

    /**
     * 设置标题
     */
    private void initTopView() {
        ViewGroup topView = (ViewGroup) findViewById(R.id.wallet_withdraw_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(Constants.PayMenuText[1]);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        nextBtn = (Button) findViewById(R.id.wallet_withdraw_next_btn);
        nextBtn.setOnClickListener(this);

        moneyEdit = (EditText) findViewById(R.id.wallet_withdraw_edit);
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //返回按钮
            SysApplication.backBtn(this, null);
        }
    }
}
