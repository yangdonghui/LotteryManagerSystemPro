package com.manager.user;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.manager.adapter.lotterycity.LotteryTicketListViewAdapter;
import com.manager.bean.SingleTicketBean;
import com.manager.common.Constants;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.view.OrderProgressView;

import java.util.ArrayList;
import java.util.List;

/**
 * 彩民 委托详情界面
 * @author donghuiyang
 * @create time 2016/6/12 0012.
 */
public class LotteryEntrustRecordDeatilAct extends Activity implements View.OnClickListener{
    //返回按钮
    private View backBtn;

    //进度
    private OrderProgressView progressView;

    //订单列表
    private ListView mListView;
    private LotteryTicketListViewAdapter mListViewAdapter;
    private List<SingleTicketBean> mLists = new ArrayList<>();

    //订单总价格
    private int ordersPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lottery_entrust_record_detail_layout);

        initData();

        initTopView();
        initProgressView();
        initListView();
    }

    /**
     * 设置标题
     */
    private void initTopView() {
        ViewGroup topView = (ViewGroup) findViewById(R.id.lottery_entrust_recodr_detail_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.entrust_str16);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    private void initProgressView() {
        progressView = (OrderProgressView) (findViewById(R.id.lottery_entrust_recodr_detail_progress_include).findViewById(R.id.order_progress_view));
        progressView.setIconText(Constants.IconTexts3);
    }

    /**
     * 初始化列表数据
     */
    private void initData() {
        mLists = Constants.EntrustLists;
        for (int i=0;i<mLists.size();i++){
            SingleTicketBean att = mLists.get(i);
            if (att != null){
                ordersPrice += att.getPrice();
            }
        }
    }

    /**
     * 初始化list列表
     */
    private void initListView() {
        mListView = (ListView) (findViewById(R.id.lottery_entrust_record_detail_list_include).findViewById(R.id.order_confirm_all_orders_expandableListView));
        mListViewAdapter = new LotteryTicketListViewAdapter(this, mLists, Constants.LotteryEntrustRecordDeatilActLayoutType);
        mListView.setAdapter(mListViewAdapter);
        //将所有项设置成默认展开
        /*int groupCount = mListView.getCount();
        for (int i=0; i<groupCount; i++) {
            mListView.expandGroup(i);
        }*/

        ((TextView) findViewById(R.id.lottery_entrust_record_detail_list_include).findViewById(R.id.order_confirm_orders_money_tv)).setText("¥" + String.valueOf(ordersPrice));

        /*//设置item点击的监听器
        mListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {



                return false;
            }
        });*/

        /*mListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i=0;i<mListViewAdapter.getGroupCount();i++){
                    if (groupPosition != i){
                        mListView.collapseGroup(i);
                    }
                }
            }
        });*/

        /*mListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });*/
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v) {
            //返回按钮
            SysApplication.backBtn(this, null);
        }
    }
}
