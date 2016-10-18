package com.manager.lotterycity;

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
import com.manager.lotterycity.fragments.BillingRecordTabFragment1;
import com.manager.lotterycity.fragments.BillingRecordTabFragment2;
import com.manager.lotterycity.fragments.BillingRecordTabFragment3;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.view.TabCommonView;

import java.util.ArrayList;

/**
 * 即开票 历史订单 界面
 * @author donghuiyang
 * @create time 2016/5/31 0031.
 */
public class BillingHistoryOrdersAct extends FragmentActivity implements View.OnClickListener{
    //返回按钮
    private View backBtn;

    private TabCommonView tabView;

    private ViewPager vPager;
    private FragmentAdapter mAdapter;
    //所有Fragment控件数组容器
    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();

    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.billing_history_order_records_layout);

        initTopView();
        initTabView();
        initView();
        initContent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        fragmentList.clear();
        fragmentList = null;
    }

    /**
     * 设置标题
     */
    private void initTopView() {

        ViewGroup topView = (ViewGroup) findViewById(R.id.billing_history_records_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.lottery_city_title4);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    private void initTabView() {
        tabView = (TabCommonView)findViewById(R.id.billing_history_record_tabview);
        tabView.setTabType(0);
        tabView.setTexts(Constants.BillingRecordTab);

        tabView.setonClick(new TabCommonView.ICoallBack() {
            @Override
            public void onClickView(int index) {
                currentIndex = index;
                vPager.setCurrentItem(index);
            }
        });
    }

    private void initView() {
        vPager = (ViewPager) findViewById(R.id.billing_history_record_tab_content);
    }

    /**
     * 初始化 vPager 界面
     */
    private void initContent() {
        BillingRecordTabFragment1 a1 = BillingRecordTabFragment1.newInstance();
        fragmentList.add(a1);
        BillingRecordTabFragment2 a2 = BillingRecordTabFragment2.newInstance();
        fragmentList.add(a2);
        BillingRecordTabFragment3 a3 = BillingRecordTabFragment3.newInstance();
        fragmentList.add(a3);

        mAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
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
        if (backBtn == v){
            //返回按钮
            SysApplication.backBtn(this, null);
        }
    }
}
