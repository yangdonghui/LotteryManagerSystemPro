package com.manager.lotterycity.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.manager.adapter.CommonAdapter;
import com.manager.adapter.ViewHolder;
import com.manager.bean.OrdersBean;
import com.manager.bean.UserBean;
import com.manager.common.Constants;
import com.manager.lotterycity.BillingHistoryOrderShowAct;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;

import java.util.ArrayList;

/**
 * 即开票历史记录 全部订单记录 界面
 * Created by Administrator on 2016/3/23 0023.
 */
public class BillingRecordTabFragment1 extends Fragment {

    private UserBean userBean = SysApplication.userBean;

    //上下文对象
    private Context mContext = null;

    //list控件
    private ListView mListView;
    private CommonAdapter<OrdersBean> mListViewAdapter;
    //生成动态数组，加入数据
    ArrayList<OrdersBean> mLists;
    //list无数据的提示
    private View tipView;

    //根视图缓存
    private View rootView = null;
    //静态对象
    private static BillingRecordTabFragment1 mTabFragment = null;

    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static BillingRecordTabFragment1 newInstance() {
        if (mTabFragment == null) {
            mTabFragment = new BillingRecordTabFragment1();
        }

        return mTabFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
        mContext = getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mLists = null;
        mTabFragment=null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        if (null != rootView) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (null != parent) {
                parent.removeView(rootView);
            }
        } else {
            rootView = inflater.inflate(R.layout.billing_history_record_tab,null);//注意不要指定父视图

            initView();
            initListView();

        }

        return rootView;
    }

    private void initView() {
        tipView = (View) rootView.findViewById(R.id.billing_history_record_tab_listview_no);

        updateTipView();
    }

    private void updateTipView() {
        if (mLists != null && mLists.size() > 0){
            if (tipView.getVisibility() != View.GONE){
                tipView.setVisibility(View.GONE);
            }else {
                tipView.setVisibility(View.VISIBLE);
            }
        }
    }

    private void initData() {
        mLists = Constants.BillingRecordLists;
    }

    private void initListView() {
        mListView = (ListView) rootView.findViewById(R.id.billing_history_records_listview);
        mListViewAdapter = new CommonAdapter<OrdersBean>(mContext, mLists, R.layout.list_item_12) {
            @Override
            public void convert(ViewHolder helper, OrdersBean item) {
                helper.setText(R.id.list_item_12_tv1, item.getNumber());
                helper.setText(R.id.list_item_12_tv2, item.getstateStr());
                helper.setText(R.id.list_item_12_tv3, item.getContent());
                helper.setText(R.id.list_item_12_tv4, item.getPrice());
                helper.setText(R.id.list_item_12_tv5, item.getTime());
            }
        };
        mListView.setAdapter(mListViewAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //查看历史记录中订单详情
                SysApplication.enterNext1(mContext, BillingHistoryOrderShowAct.class);
            }
        });

    }
}
