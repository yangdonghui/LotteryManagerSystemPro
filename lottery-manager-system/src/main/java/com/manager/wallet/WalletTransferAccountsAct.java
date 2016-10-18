package com.manager.wallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.manager.bean.FriendBean;
import com.manager.common.Constants;
import com.manager.common.Tools;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;

/**
 * 钱包 转账界面
 * @author donghuiyang
 * @create time 2016/4/20 0020.
 */
public class WalletTransferAccountsAct extends Activity implements View.OnClickListener{
    //返回按钮
    private View backBtn;

    private Button nextBtn;
    private EditText moneyEdit, infoEdit;

    //好友数据
    private FriendBean friendData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_transferaccount_layout);

        Intent intent = getIntent();
        friendData = (FriendBean)intent.getSerializableExtra("data");

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
        ViewGroup topView = (ViewGroup) findViewById(R.id.wallet_transferaccount_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(Constants.PayMenuText[2]);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        nextBtn = (Button) findViewById(R.id.wallet_transferaccount_ok_btn);
        nextBtn.setOnClickListener(this);

        moneyEdit = (EditText) findViewById(R.id.wallet_transferaccount_edit1);
        infoEdit = (EditText) findViewById(R.id.wallet_transferaccount_edit2);
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //返回按钮
            SysApplication.backBtn(this, null);
        }
    }
}
