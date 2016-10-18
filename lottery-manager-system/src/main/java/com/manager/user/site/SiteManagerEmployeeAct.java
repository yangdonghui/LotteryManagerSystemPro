package com.manager.user.site;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fortysevendeg.swipelistview.BaseSwipeListViewListener;
import com.fortysevendeg.swipelistview.SwipeListView;
import com.manager.adapter.user.EmployeeManagerListViewAdapter;
import com.manager.bean.UserBean;
import com.manager.common.Constants;
import com.manager.common.Tools;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * 站点 管理雇员界面
 * @author donghuiyang
 * @create time 2016/6/27 0027.
 */
public class SiteManagerEmployeeAct extends Activity implements View.OnClickListener{
    //返回按钮
    private View backBtn;

    //新增雇员 按钮
    private View addBtn;

    //listview
    private SwipeListView mListView;
    private EmployeeManagerListViewAdapter mListViewAdapter;
    private View noTipView;
    private List<UserBean> mLists = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.site_manager_employee_layout);

        initData();

        initTopView();
        initListView();
        initView();

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
        ViewGroup topView = (ViewGroup) findViewById(R.id.site_manager_employee_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.me_item_str_6);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    private void initView() {
        addBtn = (View) findViewById(R.id.site_manager_employee_add_btn);
        addBtn.setOnClickListener(this);

        noTipView = (View) findViewById(R.id.site_manager_employee_list_no);
        updateTipView();
    }

    private void updateTipView() {
        if (mLists != null && mLists.size() > 0){
            noTipView.setVisibility(View.GONE);
        }else {
            noTipView.setVisibility(View.VISIBLE);
        }
    }

    private void initData() {
        for (int i=0;i<Constants.EmployeeLists.size();i++){
            UserBean att = Constants.EmployeeLists.get(i);
            if (att != null){
                mLists.add(att);
            }
        }
    }

    private void initListView() {
        mListView = (SwipeListView) findViewById(R.id.site_manager_employee_listview);
        mListViewAdapter = new EmployeeManagerListViewAdapter(this, mLists, mListView);
        mListView.setAdapter(mListViewAdapter);
        mListView.setSwipeListViewListener(new TestBaseSwipeListViewListener());

        reload();
    }

    private void reload() {
        mListView.setSwipeMode(SwipeListView.SWIPE_MODE_LEFT);
        mListView.setSwipeActionLeft(SwipeListView.SWIPE_ACTION_REVEAL);
		//mSwipeListView.setSwipeActionRight(settings.getSwipeActionRight());
        mListView.setOffsetLeft(/*deviceWidth * 1 / 2*/Tools.getWindowWidth(this) - Tools.dip2px(this, 60));
        //mListView.setOffsetRight(Tools.dip2px(this, 60));
        mListView.setAnimationTime(0);
        mListView.setSwipeOpenOnLongPress(false);


    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //返回按钮
            SysApplication.backBtn(this, null);
        }else if (addBtn == v){
            //新增雇员 按钮
            SysApplication.enterNext1(this, SiteAddEmployeeAct.class);
        }
    }

    //按钮处理时间
    class TestBaseSwipeListViewListener extends BaseSwipeListViewListener {

        @Override
        public void onClickFrontView(int position) {
            super.onClickFrontView(position);
            //Toast.makeText(getApplicationContext(), mLists.get(position).getRealName(), Toast.LENGTH_SHORT).show();

            //进入详情编辑界面
            Intent intent = new Intent(SiteManagerEmployeeAct.this, SiteAddEmployeeAct.class);
            intent.putExtra("data", mLists.get(position));
            startActivity(intent);
        }

        @Override
        public void onDismiss(int[] reverseSortedPositions) {
            //删除操作
            for (int position : reverseSortedPositions) {
                mLists.remove(position);
            }
            mListViewAdapter.notifyDataSetChanged();
        }
    }
}
