package com.manager.lotterypro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author donghuiyang
 * @create time 2016/5/20 0020.
 */
public class ServiceAgreementAct extends Activity implements View.OnClickListener {
    //返回按钮
    private View backBtn;
    private Button argeementBtn;

    private Intent preIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_agreement_layout);

        initView();
    }

    private void initView() {
        //topview
        ViewGroup topView = (ViewGroup)findViewById(R.id.agreement_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.register_info_common_3);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);

        argeementBtn = (Button) findViewById(R.id.agreement_btn);
        argeementBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //返回按钮

            SysApplication.backBtn(this, null);
        }else if(argeementBtn == v) {
            //同意
            Intent intent = null;
            if (IdentitySelectAct.userType == 0){
                //彩民
                intent = SysApplication.registerLotteryAct.getIntent();
                intent.putExtra("agreement", true);
                SysApplication.registerLotteryAct.setIntent(intent);
            }else if (IdentitySelectAct.userType == 1){
                //站点
                intent = SysApplication.registerBettingshopAct.getIntent();
                intent.putExtra("agreement", true);
                SysApplication.registerBettingshopAct.setIntent(intent);
            }

            SysApplication.backBtn(this, null);
        }
    }
}
