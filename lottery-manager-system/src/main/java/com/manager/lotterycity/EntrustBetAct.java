package com.manager.lotterycity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.manager.adapter.CommonAdapter;
import com.manager.adapter.ViewHolder;
import com.manager.bean.FriendBean;
import com.manager.common.Constants;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;

import java.util.List;

/**
 * 委托投注 选择被委托人 界面
 * @author donghuiyang
 * @create time 2016/6/7 0007.
 */
public class EntrustBetAct extends Activity implements View.OnClickListener{

    //返回按钮
    private View backBtn;

    private View tipView;

    //数据列表
    private ListView mListView;
    private CommonAdapter<FriendBean> mAdapter;
    private List<FriendBean> mLists;

    //来源
    private int sourceType = Constants.LayoutDefault;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrust_bet_layout);

        initData();

        initTopView();
        initView();
        initListView();
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
        ViewGroup topView = (ViewGroup) findViewById(R.id.entrust_bet_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.betting_str11);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    private void initView() {
        tipView = (View) findViewById(R.id.entrust_bet_list_tipview);

        updateTipView();
    }
    private void updateTipView() {
        if (mLists == null || mLists.size() <= 0){
            if (tipView.getVisibility() != View.VISIBLE){
                tipView.setVisibility(View.VISIBLE);
            }
        }else if (mLists != null && mLists.size() > 0){
            if (tipView.getVisibility() != View.GONE){
                tipView.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 初始化数据
     */
    private void initData() {
        //来源
        Intent intent = getIntent();
        sourceType = intent.getIntExtra("sourceType", sourceType);

        mLists = Constants.FriendHistoryLists;
    }

    /**
     * 初始化数据列表
     */
    protected void initListView() {
        mListView = (ListView) findViewById(R.id.entrust_bet_listview);
        mAdapter = new CommonAdapter<FriendBean>(this, mLists, R.layout.item) {
            @Override
            public void convert(ViewHolder helper, FriendBean item) {
                ViewGroup.LayoutParams lp = helper.getView(R.id.item_rootview).getLayoutParams();
                lp.height = 100;
                helper.getView(R.id.item_rootview).setLayoutParams(lp);

                helper.setImageByUrl(R.id.contact_item_icon, item.getIconUrl());
                helper.setText(R.id.itemTv, item.getName());

                helper.getView(R.id.contact_item_info_Tv).setVisibility(View.GONE);
            }
        };
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //委托投注 订单确认
                if (sourceType == Constants.EntrustBetOrderConfirmActLayoutType){

                    //只关闭 并回到订单详情界面
                    SysApplication.entrustBetOrderConfirmAct.updateFriendBean(mLists.get(position));

                }else{
                    Intent intent = new Intent(EntrustBetAct.this, EntrustBetOrderConfirmAct.class);
                    intent.putExtra("userInfo", mLists.get(position));
                    startActivity(intent);
                }

                EntrustBetAct.this.finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //返回按钮
            SysApplication.backBtn(this, null);
        }
    }
}
