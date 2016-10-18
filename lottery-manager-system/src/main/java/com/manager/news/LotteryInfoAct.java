package com.manager.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.manager.adapter.news.LotteryInfoListViewAdapter;
import com.manager.bean.LotteryInfoBean;
import com.manager.common.Constants;
import com.manager.lotterypro.R;

import java.util.ArrayList;

/**
 * 开奖信息主界面
 * @author donghuiyang
 * @create time 2016/5/16 0016.
 */
public class LotteryInfoAct extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener{

    //返回按钮
    private View backBtn;

    private ListView mListView;
    private LotteryInfoListViewAdapter mListViewAdapter;
    //生成动态数组，加入数据
    ArrayList<LotteryInfoBean> mLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lotteryinfo_layout);

        initData();

        initView();
        initList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mLists = null;
    }

    /**
     * 获取控件对象 并设置标题
     */
    private void initView() {
        /*ViewGroup topView = (ViewGroup) findViewById(R.id.lottery_info_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.news_item_str_4);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);*/
    }

    /**
     * 初始化list数据
     */
    private void initData() {
        //添加数据
        mLists = Constants.AllLotteryLists;

    }
    /**
     * 初始化listview
     */
    private void initList() {
        mListView = (ListView) findViewById(R.id.lottery_info_listview);
        mListViewAdapter = new LotteryInfoListViewAdapter(this, mLists, 0);
        mListView.setAdapter(mListViewAdapter);

        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            finish();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent != null && view != null){
            //进入详细界面
            Intent intent = new Intent(this, LotteryInfoDetailAct.class);
            intent.putExtra("data", mLists.get(position));
            startActivity(intent);
        }
    }
}
