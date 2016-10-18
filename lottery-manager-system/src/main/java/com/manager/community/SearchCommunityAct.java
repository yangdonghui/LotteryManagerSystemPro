package com.manager.community;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.GridView;
import android.widget.TextView;

import com.manager.adapter.community.CommunityListViewAdapter;
import com.manager.adapter.community.SearchGridViewAdapter;
import com.manager.common.Constants;
import com.manager.common.Tools;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.view.SearchView;
import com.manager.widgets.MyListView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 创建社区 界面
 * @author donghuiyang
 * @create time 2016/4/20 0020.
 */
public class SearchCommunityAct extends Activity implements View.OnClickListener{

    //返回按钮
    private View backBtn;

    //根view
    private View rootview;

    //搜索编辑框
    private SearchView searchView;

    //热门搜索
    private GridView myGridView;
    private SearchGridViewAdapter myGridViewAdapte;
    //生成动态数组，加入数据
    ArrayList<String> mGridViewLists = new ArrayList<String>();
    //搜索结果列表
    private MyListView myListView;
    private CommunityListViewAdapter myListViewAdapter;
    ArrayList<HashMap<String, Object>> mListViewLists = new ArrayList<HashMap<String, Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_community_layout);

        initData();

        initView();
        initGridView();
        initListView();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //隐藏软键盘
        Tools.hideSoftInput(this);
    }

    @Override
    protected void onDestroy() {

        mGridViewLists.clear();
        mGridViewLists = null;

        mListViewLists.clear();
        mListViewLists = null;

        myGridViewAdapte = null;

        super.onDestroy();
    }

    /**
     * 获取控件对象
     */
    private void initView() {
        rootview = (View) findViewById(R.id.search_community_rootview);

        //搜索栏
        searchView = (SearchView) findViewById(R.id.search_community_searchview);
        searchView.setEditorActionListener(onEditorActionListener);

        ViewGroup topView = (ViewGroup)findViewById(R.id.search_community_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);

        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    /**
     * 获取gridview控件
     */
    private void initGridView() {
        myGridView = (GridView) findViewById(R.id.search_community_gridview);
        myGridViewAdapte = new SearchGridViewAdapter(this, mGridViewLists);
        myGridView.setAdapter(myGridViewAdapte);

    }

    private void initListView() {
        myListView = (MyListView) findViewById(R.id.search_community_listview);
        myListViewAdapter = new CommunityListViewAdapter(this, mListViewLists);
        myListView.setAdapter(myListViewAdapter);
    }

    private void initData() {
        mGridViewLists.addAll(Constants.searchLists);
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //返回按钮
            SysApplication.backBtn(this, null);
        }
    }


    private TextView.OnEditorActionListener onEditorActionListener = new TextView.OnEditorActionListener() {

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				/*判断是否是“SEARCH”键*/
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                /*隐藏软键盘*/
                Tools.hideSoftInput(SearchCommunityAct.this, rootview);
                return true;
            }
            return false;
        }
    };
}
