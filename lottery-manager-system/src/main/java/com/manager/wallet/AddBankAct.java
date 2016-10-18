package com.manager.wallet;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;

/**
 * 添加银行卡界面
 * @author donghuiyang
 * @create time 2016/4/20 0020.
 */
public class AddBankAct extends Activity  implements View.OnClickListener{
    //返回按钮
    private View backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_add_bank_layout);

        initTopView();
    }

    /**
     * 设置标题
     */
    private void initTopView() {
        ViewGroup topView = (ViewGroup) findViewById(R.id.wallet_add_bank_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.register_info3);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //返回按钮
            SysApplication.backBtn(this, null);
        }
    }
}
