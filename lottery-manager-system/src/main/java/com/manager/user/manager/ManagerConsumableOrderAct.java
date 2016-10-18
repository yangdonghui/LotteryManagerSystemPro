package com.manager.user.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.manager.adapter.FragmentAdapter;
import com.manager.common.Constants;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.user.manager.fragment.ManagerConsumableOrdersTabFragment1;
import com.manager.user.manager.fragment.ManagerConsumableOrdersTabFragment2;
import com.manager.user.manager.fragment.ManagerConsumableOrdersTabFragment3;
import com.manager.user.manager.fragment.ManagerConsumableOrdersTabFragment4;
import com.manager.view.TabCommonView;

import java.util.ArrayList;

/**
 * 管理员 管理 站点申报订单（耗材配送）
 * @author donghuiyang
 * @create time 2016/6/22 0022.
 */
public class ManagerConsumableOrderAct extends FragmentActivity implements View.OnClickListener {

    //返回按钮
    private View backBtn;

    private TabCommonView tabView;

    private ViewPager vPager;
    private FragmentAdapter mAdapter;
    //所有Fragment控件数组容器
    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();

    private int currentIndex = 0, tabCount = 0;

    //页面数据类型
    private int pageType = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_orders_layout);

        //获取管理类型
        Intent intent = getIntent();
        pageType = intent.getIntExtra("data", pageType);

        initTopView();
        initView();
        initTabView();
        initContent();
    }

    /**
     * 设置标题
     */
    private void initTopView() {
        ViewGroup topView = (ViewGroup) findViewById(R.id.manager_orders_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        //耗材配送
        titleView.setText(R.string.manager_str_07);

        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    private void initTabView() {
        tabView = (TabCommonView)findViewById(R.id.manager_orders_tabview);
        tabView.setTexts(Constants.ManagerConsumableTab);
        tabCount = Constants.ManagerConsumableTab.length;

        tabView.setonClick(new TabCommonView.ICoallBack() {
            @Override
            public void onClickView(int index) {
                currentIndex = index;
                vPager.setCurrentItem(index);
            }
        });
    }

    private void initView() {
        vPager = (ViewPager) findViewById(R.id.manager_orders_tab_content);
        vPager.setOffscreenPageLimit(0);
    }

    private void initContent() {
        ManagerConsumableOrdersTabFragment1 a1 = ManagerConsumableOrdersTabFragment1.newInstance(pageType);
        fragmentList.add(a1);
        ManagerConsumableOrdersTabFragment2 a2 = ManagerConsumableOrdersTabFragment2.newInstance();
        fragmentList.add(a2);
        ManagerConsumableOrdersTabFragment3 a3 = ManagerConsumableOrdersTabFragment3.newInstance();
        fragmentList.add(a3);
        ManagerConsumableOrdersTabFragment4 a4 = ManagerConsumableOrdersTabFragment4.newInstance();
        fragmentList.add(a4);

        mAdapter = new FragmentAdapter(getSupportFragmentManager(),fragmentList);
        //添加到ViewPager容器
        vPager.setAdapter(mAdapter);
        vPager.setCurrentItem(currentIndex);

        vPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tabView.updateCursor(positionOffset, position);
            }

            @Override
            public void onPageSelected(int position) {
                currentIndex = position;
                tabView.updateTab(currentIndex);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.e("", "");

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
