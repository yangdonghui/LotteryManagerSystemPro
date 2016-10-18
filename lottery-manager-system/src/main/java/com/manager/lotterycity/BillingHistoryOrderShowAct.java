package com.manager.lotterycity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.manager.adapter.CommonAdapter;
import com.manager.adapter.ViewHolder;
import com.manager.bean.ProductBean;
import com.manager.common.Constants;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.view.OrderProgressView;

import java.util.ArrayList;

/**
 * 即开票 历史订单 详情界面
 * @author donghuiyang
 * @create time 2016/5/31 0031.
 */
public class BillingHistoryOrderShowAct extends Activity implements View.OnClickListener{
    //返回按钮
    private View backBtn;

    //进度栏
    private OrderProgressView progressView;
    //其他信息栏
    private ViewGroup orderOtherView;

    //订单列表
    private ListView mListView;
    private CommonAdapter<ProductBean> mListViewAdapter;
    ArrayList<ProductBean> mLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.billing_history_order_show_layout);

        initData();

        initTopView();
        initView();
        initListView();
    }

    @Override
    protected void onDestroy() {
        mLists = null;

        super.onDestroy();
    }

    /**
     * 设置标题
     */
    private void initTopView() {
        ViewGroup topView = (ViewGroup) findViewById(R.id.billing_history_order_show_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.pay_info_str5);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    private void initView() {
        progressView = (OrderProgressView) (findViewById(R.id.billing_history_order_show_progress_include).findViewById(R.id.order_progress_view));
        progressView.setIconText(Constants.IconTexts2);

        //其他信息栏
        orderOtherView = (ViewGroup) findViewById(R.id.billing_history_order_show_order_other_include);
    }

    /**
     * 初始化列表数据
     */
    private void initData() {
        mLists = Constants.CartLists;
    }

    /**
     * 初始化list列表
     */
    private void initListView() {
        mListView = (ListView) (findViewById(R.id.billing_history_order_show_lists_include).findViewById(R.id.order_confirm_all_orders_listview));
        mListViewAdapter = new CommonAdapter<ProductBean>(this, mLists, R.layout.list_item_14) {
            @Override
            public void convert(ViewHolder helper, ProductBean item) {
                helper.setText(R.id.list_item_14_tv1, item.getTitle());
                helper.setText(R.id.list_item_14_tv2, mContext.getResources().getString(R.string.money_str) + item.getPrice());
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
