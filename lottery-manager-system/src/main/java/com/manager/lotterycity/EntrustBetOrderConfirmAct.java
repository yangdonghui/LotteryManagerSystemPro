package com.manager.lotterycity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;


import com.manager.adapter.lotterycity.LotteryTicketListViewAdapter;
import com.manager.bean.FriendBean;
import com.manager.bean.SingleTicketBean;
import com.manager.common.Constants;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;

import java.util.List;

/**
 * 委托投注 订单确认界面
 * @author donghuiyang
 * @create time 2016/6/7 0007.
 */
public class EntrustBetOrderConfirmAct extends Activity implements View.OnClickListener{
    //选择好友的数据
    private FriendBean friendBean;

    //返回按钮
    private View backBtn;

    //被委托人信息
    private View userInfoView;
    //姓名
    private TextView beEntrustNameTv;
    //phone
    private TextView beEntrustPhoneTv;
    //地址
    private TextView beEntrustAddressTv;

    //订单列表
    private ListView mListView;
    private LotteryTicketListViewAdapter mListViewAdapter;
    private List<SingleTicketBean> mLists;

    //订单总价格
    private int ordersPrice = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrust_bet_order_confirm_layout);

        initData();

        initTopView();
        initView();
        initListView();

        ((ScrollView)findViewById(R.id.entrust_order_confirm_scrollview)).smoothScrollBy(0, 10);

        SysApplication.entrustBetOrderConfirmAct = this;
    }

    @Override
    protected void onResume() {
        super.onResume();

        friendBean = (FriendBean)getIntent().getSerializableExtra("userInfo");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mLists = null;

        SysApplication.entrustBetOrderConfirmAct = null;
    }

    /**
     * 设置标题
     */
    private void initTopView() {
        ViewGroup topView = (ViewGroup) findViewById(R.id.entrust_bet_order_comfirm_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.pay_info_str5);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    /**
     * 初始化控件
     */
    private void initView(){
        userInfoView = (View) findViewById(R.id.billing_order_confirm_principal_info_include_parent);
        userInfoView.setOnClickListener(this);

        beEntrustNameTv = (TextView) findViewById(R.id.principal_info_name_tv);
        beEntrustPhoneTv = (TextView) findViewById(R.id.principal_info_phone_tv);
        beEntrustAddressTv = (TextView) findViewById(R.id.principal_info_address_tv);

        //更新user view
        updateUserView();
    }

    /**
     * 初始化列表数据
     */
    private void initData() {
        friendBean = (FriendBean) getIntent().getSerializableExtra("userInfo");

        mLists = Constants.EntrustLists;
        for (int i=0;i<mLists.size();i++){
            SingleTicketBean att = mLists.get(i);
            if (att != null){
                ordersPrice += att.getPrice();
            }
        }
    }

    /**
     * 更新被委托人信息
     * @param friendBean
     */
    public void updateFriendBean(FriendBean friendBean){
        this.friendBean = friendBean;

        updateUserView();
    }
    private void updateUserView(){
        if (friendBean != null){
            beEntrustNameTv.setText(friendBean.getName());
        }
    }

    /**
     * 初始化list列表
     */
    private void initListView() {
        mListView = (ListView) (findViewById(R.id.entrust_bet_order_confirm_lists_include).findViewById(R.id.order_confirm_all_orders_listview));
        mListViewAdapter = new LotteryTicketListViewAdapter(this, mLists, Constants.EntrustBetOrderConfirmActLayoutType);
        mListView.setAdapter(mListViewAdapter);

        ((TextView)findViewById(R.id.entrust_bet_order_confirm_lists_include).findViewById(R.id.order_confirm_orders_money_tv)).setText("¥" + String.valueOf(ordersPrice));
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //返回按钮
            SysApplication.backBtn(this, null);
        }else if (userInfoView == v){
            //修改委托人
            Intent intent = new Intent(this, EntrustBetAct.class);
            intent.putExtra("sourceType", Constants.EntrustBetOrderConfirmActLayoutType);
            startActivity(intent);
        }
    }
}
