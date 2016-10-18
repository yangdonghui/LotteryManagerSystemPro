package com.manager.personals;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.TextView;

import com.manager.adapter.personals.SideBarListViewAdapter;
import com.manager.bean.FriendBean;
import com.manager.bean.WidgetBean1;
import com.manager.common.Tools;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.view.SearchView;
import com.manager.widgets.MyListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加好友界面
 *
 * @author donghuiyang
 * @create time 2016/4/20 0020.
 */
public class AddFriendAct extends Activity implements View.OnClickListener {
    //返回按钮
    private View backBtn;

    //根view
    private View rootView;

    //搜索编辑框
    private SearchView searchView;

    //listview
    private MyListView mListView;
    private SideBarListViewAdapter mListAdapter;
    //生成动态数组，加入数据
    private ArrayList<FriendBean> mLists = new ArrayList<FriendBean>();

    private static List<WidgetBean1> initLists = new ArrayList<WidgetBean1>(){
        {
            add(new WidgetBean1("0", R.mipmap.contact_icon2, "手机联系人"));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_friend_layout);

        initData();

        initView();
        initList();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //隐藏软键盘
        Tools.hideSoftInput(this);

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
    private void initView() {
        rootView  = (ViewGroup) findViewById(R.id.add_friend_rootview);

        searchView = (SearchView) findViewById(R.id.add_friend_search_view);
        searchView.setEditorActionListener(onEditorActionListener);

        //topview
        ViewGroup topView = (ViewGroup) findViewById(R.id.add_friend_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.contacts_info_2);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
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
        //添加默认item
        for (WidgetBean1 att:initLists){
            FriendBean p = new FriendBean(att.getText());
            p.setType(-2);
            p.setInfo("添加通讯录中的朋友");
            p.setDefaultIcon(att.getIcon());
            mLists.add(p);
        }
    }

    private void initList() {
        mListView = (MyListView) findViewById(R.id.add_friend_listview);
        mListAdapter = new SideBarListViewAdapter(this, mLists, null, -1);
        mListView.setAdapter(mListAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //进入手机联系人界面

            }
        });
    }

    private TextView.OnEditorActionListener onEditorActionListener = new TextView.OnEditorActionListener() {

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				/*判断是否是“SEARCH”键*/
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                /*隐藏软键盘*/
                Tools.hideSoftInput(AddFriendAct.this, rootView);
                return true;
            }
            return false;
        }
    };
}
