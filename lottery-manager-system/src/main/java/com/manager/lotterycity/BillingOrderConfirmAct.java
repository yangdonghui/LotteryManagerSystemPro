package com.manager.lotterycity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.manager.adapter.CommonAdapter;
import com.manager.adapter.ViewHolder;
import com.manager.bean.ProductBean;
import com.manager.common.Constants;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.user.UserAddressManagerAct;
import com.manager.view.OrderConfirmAddressView;

import java.util.ArrayList;

/**
 * 即开票 订单确认 详情界面
 * @author donghuiyang
 * @create time 2016/5/31 0031.
 */
public class BillingOrderConfirmAct extends Activity implements View.OnClickListener{
    //返回按钮
    private View backBtn;

    //地址栏
    private OrderConfirmAddressView addressView;

    //订单列表
    private ListView mListView;
    private CommonAdapter<ProductBean> mListViewAdapter;
    ArrayList<ProductBean> mLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_order_confirm_layout);

        initData();

        initTopView();
        initView();
        initListView();

        ((ScrollView)findViewById(R.id.product_order_confirm_scrollview)).smoothScrollBy(0,10);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mLists = null;
    }

    /**
     * 设置标题
     */
    private void initTopView() {
        ViewGroup topView = (ViewGroup) findViewById(R.id.product_order_comfirm_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.pay_info_str5);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    private void initView() {
        addressView = (OrderConfirmAddressView) findViewById(R.id.order_confirm_address_view_include);
        addressView.setonClick(new OrderConfirmAddressView.ICoallBack() {
            @Override
            public void onClick() {
                //进入地址管理界面
                SysApplication.enterNext1(BillingOrderConfirmAct.this, UserAddressManagerAct.class);
            }
        });
    }

    private void initData() {
        mLists = Constants.CartLists;
    }

    /**
     * 初始化list列表
     */
    private void initListView() {
        mListView = (ListView) (findViewById(R.id.product_order_confirm_lists_include).findViewById(R.id.order_confirm_all_orders_listview));
        mListViewAdapter = new CommonAdapter<ProductBean>(this, mLists, R.layout.list_item_14) {
            @Override
            public void convert(ViewHolder helper, ProductBean item) {
                helper.setText(R.id.list_item_14_tv1, item.getTitle());
                helper.setText(R.id.list_item_14_tv2, getResources().getString(R.string.money_str) + item.getPrice());
                if (item.getValue() != null || item.getValue().equals("")){
                    helper.getView(R.id.list_item_14_tv11).setVisibility(View.VISIBLE);
                    helper.setText(R.id.list_item_14_tv11, getResources().getString(R.string.bettingshop_info3) + item.getValue());
                }
            }
        };
        mListView.setAdapter(mListViewAdapter);
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //返回按钮
            SysApplication.backBtn(this, null);
        }
    }
}
