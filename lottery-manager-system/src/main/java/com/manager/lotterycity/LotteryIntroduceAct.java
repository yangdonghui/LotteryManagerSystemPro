package com.manager.lotterycity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.manager.bean.LotteryInfoBean;
import com.manager.bean.UserBean;
import com.manager.bean.WidgetBean1;
import com.manager.common.Constants;
import com.manager.helper.UserHelper;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;

/**
 * 彩票介绍界面
 *
 * @author donghuiyang
 * @create time 2016/6/3 0003.
 */
public class LotteryIntroduceAct extends Activity implements View.OnClickListener {

    //用户数据
    private UserBean userBean = SysApplication.userBean;

    //返回按钮
    private View backBtn;

    //投注按钮
    private Button bettingBtn;

    //彩种介绍开奖信息子界面
    private ViewGroup lotteryViewGroup;

    private LotteryInfoBean data = Constants.AllLotteryLists.get(0);
    private WidgetBean1 widgetBean1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lottery_introduce_layout);

        Intent intent = getIntent();
        widgetBean1 = (WidgetBean1) (intent.getSerializableExtra("data"));

        initTopView();
        initView();

    }

    /**
     * 设置标题
     */
    private void initTopView() {
        ViewGroup topView = (ViewGroup) findViewById(R.id.lottery_introduce_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.lottery_city_info11);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    /**
     * 获取控件对象
     */
    private void initView() {
        //去选号按钮
        bettingBtn = (Button) findViewById(R.id.lottery_introduce_bet_btn);
        bettingBtn.setOnClickListener(this);

        if (userBean != null && userBean.getUserType() == UserHelper.BettingShopUser){
            //业主版本 隐藏
            bettingBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v) {
            //返回按钮
            SysApplication.backBtn(this, null);
        } else if (bettingBtn == v) {
            //去选号按钮
            Intent intent = new Intent(this, SelectNumberAct.class);
            intent.putExtra("data", widgetBean1);
            startActivity(intent);
        }
    }
}
