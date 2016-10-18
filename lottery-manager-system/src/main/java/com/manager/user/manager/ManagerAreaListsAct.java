package com.manager.user.manager;

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
import com.manager.bean.BettingshopBean;
import com.manager.common.Constants;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理员 辖区列表界面
 * @author donghuiyang
 * @create time 2016/6/28 0028.
 */
public class ManagerAreaListsAct extends Activity implements View.OnClickListener{
    //返回按钮
    private View backBtn;

    //listview相关
    private ListView mListView;
    private CommonAdapter<BettingshopBean> mListViewAdapter;
    private List<BettingshopBean> mLists = new ArrayList<>();
    private View noListsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_area_lists_layout);

        initData();

        initTopView();
        initListView();
        initView();
        updateTipView();
    }

    @Override
    protected void onDestroy() {

        mLists.clear();
        mLists = null;

        super.onDestroy();
    }

    /**
     * 设置标题
     */
    private void initTopView() {
        ViewGroup topView = (ViewGroup) findViewById(R.id.manager_area_lists_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.me_item_str_7);

        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    private void initView() {
        noListsView = (View) findViewById(R.id.manager_area_lists_listview_no);
    }

    private void updateTipView() {
        if (mLists == null || mLists.size() <= 0){
            noListsView.setVisibility(View.VISIBLE);
        }else {
            noListsView.setVisibility(View.GONE);
        }
    }

    private void initData() {
        for (int i=0;i< Constants.ManagerAreaLists.size();i++){
            BettingshopBean att = Constants.ManagerAreaLists.get(i);
            if (att != null){
                mLists.add(att);
            }
        }
    }

    private void initListView() {
        mListView = (ListView) findViewById(R.id.manager_area_lists_listview);
        mListViewAdapter = new CommonAdapter<BettingshopBean>(this, mLists, R.layout.list_item_23) {
            @Override
            public void convert(ViewHolder helper, BettingshopBean item) {
                helper.setText(R.id.list_item_23_name_tv, item.getRealName());
                helper.setText(R.id.list_item_23_address_tv, item.getAddress());
                helper.setText(R.id.list_item_23_money_tv, item.getSaleMoney());
            }
        };
        mListView.setAdapter(mListViewAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ManagerAreaListsAct.this, AreaDetailAct.class);
                intent.putExtra("data", mLists.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v) {
            //返回按钮
            SysApplication.backBtn(this, null);
        }
    }
}
