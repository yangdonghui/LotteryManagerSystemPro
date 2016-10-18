package com.manager.wallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.manager.adapter.CommonAdapter;
import com.manager.adapter.ViewHolder;
import com.manager.bean.PayRecordBean;
import com.manager.common.Constants;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;

import java.util.ArrayList;

/**
 * 钱包交易历史界面
 * @author donghuiyang
 * @create time 2016/4/20 0020.
 */
public class WalletTransactionHistoryAct extends Activity implements View.OnClickListener{
    //筛选类型
    private int filterType = 0;

    //返回按钮
    private View backBtn;
    //筛选按钮
    private View filterBtn;

    //listview
    private ListView mListView;
    private CommonAdapter<PayRecordBean> mAdapter;
    private ArrayList<PayRecordBean> mLists = new ArrayList<>();
    //list无数据的提示
    private View tipView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_transferaccount_record_layout);

        initData();

        initTopView();
        initView();
        initListView();

        SysApplication.walletTransactionHistoryAct = this;

    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        int type = intent.getIntExtra("data", -1);
        if (type != -1 && type != filterType){
            filterType = type;
            updateData();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mLists.clear();
        mLists = null;

        SysApplication.walletTransactionHistoryAct = null;
    }

    /**
     * 设置标题
     */
    private void initTopView() {
        ViewGroup topView = (ViewGroup) findViewById(R.id.wallet_transferaccount_record_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.wallet_str_10);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    private void initView() {
        filterBtn = (View) findViewById(R.id.wallet_transferaccount_record_btn);
        filterBtn.setOnClickListener(this);

        tipView = (View) findViewById(R.id.wallet_transferaccount_record_list_no);
        updateTipView();
    }

    private void updateTipView() {
        if (tipView == null) return;

        if (mLists != null && mLists.size() > 0){
            tipView.setVisibility(View.GONE);
        }else{
            tipView.setVisibility(View.VISIBLE);
        }
    }

    private void initData() {
        mLists.clear();

        for (int i=0;i<Constants.PayRecordLists.size();i++){
            PayRecordBean att = Constants.PayRecordLists.get(i);
            if (att != null && (filterType == 0 || filterType == att.getType())){
                mLists.add(att);
            }
        }

        updateTipView();
    }

    private void initListView() {
        mListView = (ListView) findViewById(R.id.wallet_transferaccount_record_listview);
        updateListView();
    }

    private void updateData(){
        if (filterType == -1) return;

        initData();
        mAdapter.notifyDataSetChanged();
    }

    private void updateListView() {
        if (mLists != null && mLists.size() > 0){
            mAdapter = new CommonAdapter<PayRecordBean>(this, mLists, R.layout.list_item_18) {
                @Override
                public void convert(ViewHolder helper, PayRecordBean item) {
                    helper.setText(R.id.list_item_18_tv1, item.getTitle());
                    helper.setText(R.id.list_item_18_tv2, item.getPrice());
                    helper.setText(R.id.list_item_18_tv4, item.getTime());
                    helper.setText(R.id.list_item_18_tv3, item.getStateInfo());

                    int state = item.getState();
                    if (state == 1){
                        //退款状态
                        ((TextView)helper.getView(R.id.list_item_18_tv3)).setTextAppearance(WalletTransactionHistoryAct.this,R.style.text_size_12_text_color_9);
                    }else{
                        ((TextView)helper.getView(R.id.list_item_18_tv3)).setTextAppearance(WalletTransactionHistoryAct.this,R.style.text_size_12_text_color_3);
                    }
                }
            };
            mListView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //返回按钮
            SysApplication.backBtn(this, null);
        }else if (filterBtn == v){
            //筛选按钮
            SysApplication.enterNext1(this, TransferAccountFilterAct.class);
        }
    }
}
