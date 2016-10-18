package com.manager.news;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.manager.adapter.lotterycity.LotteryTicketChildListViewAdapter;
import com.manager.bean.NoteLotterybean;
import com.manager.common.Constants;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * 中奖信息 单票 详细界面
 * @author donghuiyang
 * @create time 2016/5/17 0017.
 */
public class WinningDetailAct extends Activity implements View.OnClickListener{

    //返回按钮 历史记录按钮
    private View backBtn;

    private ListView mListView;
    private LotteryTicketChildListViewAdapter mListViewAdapter;
    private List<NoteLotterybean> mLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winning_detail_layout);

        initView();
        initListView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    /**
     * 获取控件对象 并 设置标题
     */
    private void initView() {
        ViewGroup topView = (ViewGroup) findViewById(R.id.winning_detail_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText("中奖详情");
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    private void initListView(){
        mListView = (ListView) findViewById(R.id.winning_detail_listview);
        mListViewAdapter = new LotteryTicketChildListViewAdapter(this, Constants.NoteLotteryLists2);
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
