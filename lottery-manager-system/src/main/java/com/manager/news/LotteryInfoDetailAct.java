package com.manager.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manager.bean.LotteryInfoBean;
import com.manager.lotterypro.R;

import java.util.ArrayList;

/**
 * 开奖信息 单期 详细界面
 * @author donghuiyang
 * @create time 2016/5/17 0017.
 */
public class LotteryInfoDetailAct extends Activity implements View.OnClickListener{

    //返回按钮 历史记录按钮
    private View backBtn, recordBtn;
    private ViewGroup numbersViewGroup;

    LotteryInfoBean data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lotteryinfo_detail_layout);

        //获取数据
        Intent intent = getIntent();
        data = (LotteryInfoBean)intent.getSerializableExtra("data");

        initView();
        initNumberView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        data = null;
    }

    /**
     * 获取控件对象 并 设置标题
     */
    private void initView() {
        ViewGroup topView = (ViewGroup) findViewById(R.id.lottery_info_detail_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(data.getLotteryName());
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
        recordBtn = (View) findViewById(R.id.lottery_info_detail_record_btn);
        recordBtn.setOnClickListener(this);

        numbersViewGroup = (ViewGroup) findViewById(R.id.lottery_info_detail_viewgroup);
    }

    /**
     * 初始化号码view
     */
    private void initNumberView() {
        int num1 = ((ArrayList<String>) data.getLotteryNumbers1()).size();
        int num2 = ((ArrayList<String>) data.getLotteryNumbers2()).size();
        for (int i = 0; i < num1; i++) {
            View v1 = LayoutInflater.from(this).inflate(R.layout.red_number, null);
            TextView tv1 = (TextView) v1.findViewById(R.id.number_red);

            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.setMargins(3, 0, 0, 0);
            v1.setLayoutParams(layout);
            numbersViewGroup.addView(v1);
            tv1.setText(((ArrayList<String>) data.getLotteryNumbers1()).get(i));
        }

        for (int i = 0; i < num2; i++) {
            View v2 = LayoutInflater.from(this).inflate(R.layout.blue_number, null);
            TextView tv2 = (TextView) v2.findViewById(R.id.number_blue);

            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            if (i == 0){
                layout.setMargins(30, 0, 0, 0);
            }else{
                layout.setMargins(3, 0, 0, 0);
            }
            v2.setLayoutParams(layout);
            numbersViewGroup.addView(v2);
            tv2.setText(((ArrayList<String>) data.getLotteryNumbers2()).get(i));
        }
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //返回按钮
            finish();
        }else if (recordBtn == v){
            //历史记录按钮
            Intent intent = new Intent(this, LotteryInfoRecordAct.class);
            intent.putExtra("data", (Integer)data.getLotteryType());
            startActivity(intent);
        }
    }
}
