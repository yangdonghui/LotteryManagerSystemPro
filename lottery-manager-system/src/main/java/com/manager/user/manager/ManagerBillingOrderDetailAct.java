package com.manager.user.manager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.manager.adapter.CommonAdapter;
import com.manager.adapter.ViewHolder;
import com.manager.bean.ItemBean;
import com.manager.bean.OrdersBean;
import com.manager.common.StringHelper;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;

import java.util.ArrayList;

/**
 * 管理员 即开票订单 管理界面
 * @author donghuiyang
 * @create time 2016/6/28 0028.
 */
public class ManagerBillingOrderDetailAct extends Activity implements View.OnClickListener{
    //返回按钮
    private View backBtn;

    //订单属性
    private OrdersBean ordersBean;

    //订单列表
    private ListView mListView;
    private CommonAdapter<ItemBean> mListViewAdapter;
    private ArrayList<ItemBean> mLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_billing_order_detail_layout);

        initTopView();
        initListView();
    }

    /**
     * 获取控件
     */
    private void initTopView() {
        ViewGroup topView = (ViewGroup) findViewById(R.id.manager_billing_order_detail_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.lottery_city_title2);

        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    /**
     * 初始化list列表
     */
    private void initListView() {
        mListView = (ListView) (findViewById(R.id.manager_billing_order_detail_lists_include).findViewById(R.id.order_confirm_all_orders_listview));
        mListViewAdapter = new CommonAdapter<ItemBean>(this, mLists, R.layout.list_item_14) {
            @Override
            public void convert(ViewHolder helper, ItemBean item) {
                helper.setText(R.id.list_item_14_tv1, item.getItemName());
                helper.setText(R.id.list_item_14_tv2, StringHelper.getPriceStr(item.getPrice()));
                helper.setText(R.id.list_item_14_tv3, StringHelper.getNumStr(item.getNum()));
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
