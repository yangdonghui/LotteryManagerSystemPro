package com.manager.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.manager.adapter.FragmentAdapter;
import com.manager.bean.UserBean;
import com.manager.common.Constants;
import com.manager.helper.UserHelper;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.user.fragment.EntrustRecordTabFragment2;
import com.manager.user.fragment.EntrustRecordTabFragment3;
import com.manager.user.fragment.EntrustRecordTabFragment4;
import com.manager.view.TabCommonView;

import java.util.ArrayList;

/**
 * 我的委托服务记录界面
 * @author donghuiyang
 * @create time 2016/6/7 0007.
 */
public class MyEntrustRecordAct extends FragmentActivity implements View.OnClickListener{

    private UserBean userBean = SysApplication.userBean;

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
        setContentView(R.layout.my_entrust_record_layout);

        initTopView();
        initTabView();
        initView();
        initContent();
    }

    /**
     * 设置标题
     */
    private void initTopView() {
        ViewGroup topView = (ViewGroup) findViewById(R.id.my_entrust_record_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        //委托服务
        titleView.setText(R.string.entrust_str_0);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    private void initTabView() {
        tabView = (TabCommonView)findViewById(R.id.my_entrust_record_tabview);
        if (userBean.getUserType() == UserHelper.LotteryUser || userBean.getUserType() == UserHelper.ManagerUser){
            tabView.setTexts(Constants.LotteryEntrustTab);
        }else if(userBean.getUserType() == UserHelper.BettingShopUser){
            tabView.setTexts(Constants.BettingshopEntrustTab);
        }
        tabView.setTabType(2);
        tabView.updateView();
        tabView.setonClick(new TabCommonView.ICoallBack() {
            @Override
            public void onClickView(int index) {
                currentIndex = index;
                vPager.setCurrentItem(index);
            }
        });
    }

    private void initView() {
        vPager = (ViewPager) findViewById(R.id.my_entrust_record_tab_content);
    }

    private void initContent() {
        EntrustRecordTabFragment2 a2 = EntrustRecordTabFragment2.newInstance();
        fragmentList.add(a2);
        EntrustRecordTabFragment3 a3 = EntrustRecordTabFragment3.newInstance();
        fragmentList.add(a3);
        EntrustRecordTabFragment4 a4 = EntrustRecordTabFragment4.newInstance();
        fragmentList.add(a4);

        mAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
        //添加到ViewPager容器
        vPager.setAdapter(mAdapter);
        vPager.setCurrentItem(currentIndex);
        vPager.setOffscreenPageLimit(1);

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
                Log.e("","");
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
