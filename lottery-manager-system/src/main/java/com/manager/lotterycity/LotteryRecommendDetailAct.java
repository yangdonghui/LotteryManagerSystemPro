package com.manager.lotterycity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;

/**
 * 彩票城 单条数据 查看界面
 * @author donghuiyang
 * @create time 2016/5/16 0016.
 */
public class LotteryRecommendDetailAct extends Activity implements View.OnClickListener{

    //返回按钮
    private View backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lottery_city_detail_layout);

        initView();
    }

    /**
     * 初始化view
     */
    private void initView() {
        ViewGroup topView = (ViewGroup) findViewById(R.id.lottery_city_detail_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.lottery_city_title);
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
