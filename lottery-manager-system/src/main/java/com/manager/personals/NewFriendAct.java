package com.manager.personals;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.manager.adapter.personals.AddFriendListViewAdapter;
import com.manager.bean.FriendBean;
import com.manager.common.Constants;
import com.manager.common.Tools;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.view.SearchView;
import com.manager.widgets.MyListView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 新的朋友界面
 *
 * @author donghuiyang
 * @create time 2016/4/20 0020.
 */
public class NewFriendAct extends Activity implements View.OnClickListener {
    //返回按钮
    private View backBtn;

    //根view
    private View rootView;

    //搜索编辑框
    private SearchView searchView;

    private View searchViewGroup, historyViewGroup;
    //listview
    private MyListView mSearchListView, mHistoryListView;
    private AddFriendListViewAdapter mSearchListAdapter, mHistoryListAdapter;
    //生成动态数组，加入数据
    private ArrayList<HashMap<String, Object>> mSearchLists = new ArrayList<HashMap<String, Object>>();
    //生成动态数组，加入数据
    private ArrayList<HashMap<String, Object>> mHistoryLists = new ArrayList<HashMap<String, Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_friend_layout);

        initData();

        initView();
        initList();

        updateSearchViewGroup();
        updateHistoryViewGroup();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //隐藏软键盘
        Tools.hideSoftInput(this);

    }

    @Override
    protected void onDestroy() {

        mSearchLists.clear();
        mSearchLists = null;

        mHistoryLists.clear();
        mHistoryLists = null;

        super.onDestroy();
    }

    /**
     * 设置标题
     */
    private void initView() {
        rootView  = (ViewGroup) findViewById(R.id.new_friend_rootview);

        searchView = (SearchView) findViewById(R.id.new_friend_search_view);
        searchView.setEditorActionListener(onEditorActionListener);

        //topview
        ViewGroup topView = (ViewGroup) findViewById(R.id.new_friend_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.comunity_string2);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);

        //列表区
        searchViewGroup = (View) findViewById(R.id.new_friend_search_viewgroup);
        historyViewGroup = (View) findViewById(R.id.new_friend_history_viewgroup);


        //默认弹出软键盘
        /*Timer timer = new Timer();
        timer.schedule(new TimerTask()
                       {
                           public void run()
                           {
                               Tools.showSoftInput(AddFriendAct.this, rootview);
                           }
                       },
                100);*/
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v) {
            //返回按钮
            SysApplication.backBtn(this, null);
        }
    }

    /**
     * 初始化数据
     */
    private void initData() {
        for (int i=0; i< Constants.FriendHistoryLists.size(); i++) {
            FriendBean att = Constants.FriendHistoryLists.get(i);
            if (att != null){
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("ItemName", att.getName());
                map.put("ItemIcon", att.getIconUrl());
                map.put("ItemInfo", att.getInfo());
                map.put("ItemState", att.getState());
                map.put("ItemStateStr", att.getStateStr());
                map.put("ItemID", att.getId());
                mHistoryLists.add(map);
            }
        }
    }

    private void initList() {
        //搜索列表
        mSearchListView = (MyListView) findViewById(R.id.new_friend_search_listview);
        mSearchListAdapter = new AddFriendListViewAdapter(this, mSearchLists);
        mSearchListView.setAdapter(mSearchListAdapter);

        //历史列表
        mHistoryListView = (MyListView) findViewById(R.id.new_friend_history_listview);
        mHistoryListAdapter = new AddFriendListViewAdapter(this, mHistoryLists);
        mHistoryListView.setAdapter(mHistoryListAdapter);
    }

    /**
     * 更新列表是否显示
     */
    private void updateSearchViewGroup() {
        if (mSearchLists == null || mSearchLists.size() <= 0){
            //无数据
            searchViewGroup.setVisibility(View.GONE);
        }else if(mSearchLists != null && mSearchLists.size() > 0){
            searchViewGroup.setVisibility(View.VISIBLE);
        }
    }
    private void updateHistoryViewGroup() {
        if (mHistoryLists == null || mHistoryLists.size() <= 0){
            //无数据
            historyViewGroup.setVisibility(View.GONE);
        }else if(mHistoryLists != null && mHistoryLists.size() > 0){
            historyViewGroup.setVisibility(View.VISIBLE);
        }
    }

    private TextView.OnEditorActionListener onEditorActionListener = new TextView.OnEditorActionListener() {

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				/*判断是否是“SEARCH”键*/
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                /*隐藏软键盘*/
                Tools.hideSoftInput(NewFriendAct.this, rootView);
                return true;
            }
            return false;
        }
    };
}
