package com.manager.user;

import android.content.Context;
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
import com.manager.news.fragment.LotteryInNewsTabFragment;
import com.manager.news.fragment.NotifyNewsTabFragment;
import com.manager.news.fragment.PromotionNewsTabFragment;
import com.manager.news.fragment.WinningInNewsTabFragment;
import com.manager.view.TabCommonView;

import java.util.ArrayList;

/**
 * 彩民开中奖信息 界面
 * @author donghuiyang
 * @create time 2016/4/20 0020.
 */
public class LotteryAndWinningAct extends FragmentActivity {
    //用户
    public UserBean mUserBean = SysApplication.userBean;
    //上下文对象
    private Context mContext = null;

    //返回按钮
    private View backBtn;

    TextView titleView;

    private TabCommonView tabView;

    private ViewPager vPager;
    private FragmentAdapter mAdapter;
    //所有Fragment控件数组容器
    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();

    private int currentIndex = 0;

    /**
     * 使用静态工厂方法，将外部传入的参数可以通过Fragment.setArgument保存在它自己身上，
     * 这样我们可以在Fragment.onCreate(...)调用的时候将这些参数取出来
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lottery_winning_info_layout);

        initTopView();
        initTabView();

        initView();
        initContent();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        fragmentList = null;
    }

    /**
     * 设置标题
     */
    private void initTopView(){
        ViewGroup topView = (ViewGroup) findViewById(R.id.news_fragment_topview);
        titleView = (TextView) topView.findViewById(R.id.title_tv_topview);

        if (mUserBean != null && mUserBean.getUserType() == UserHelper.LotteryUser){
            //彩民
            titleView.setText(R.string.news_item_str_4);
        }else{
            //业主
            titleView.setText(R.string.news_title);
        }

        backBtn = (View) findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //返回按钮
                SysApplication.backBtn(LotteryAndWinningAct.this, null);
            }
        });
    }

    /**
     * 初始tabview
     */
    private void initTabView() {
        //彩民版本 2个标签
        tabView = (TabCommonView)findViewById(R.id.news_fragment_tabview);
        tabView.setTabType(2);
        if (mUserBean != null && mUserBean.getUserType() == UserHelper.LotteryUser) {
            //彩民版本
            tabView.setTexts(Constants.NewsTab);
        }else{
            //业主版本
            tabView.setTexts(Constants.NewsTab1);
        }
        tabView.updateView();
        tabView.setonClick(new TabCommonView.ICoallBack() {
            @Override
            public void onClickView(int index) {
                tabView.updateView();

                currentIndex = index;
                vPager.setCurrentItem(index);
            }
        });


    }

    private void initView() {
        vPager = (ViewPager) findViewById(R.id.news_fragment_tab_content);
    }

    private void initContent() {
        //开奖界面
        if (mUserBean != null){
            if (mUserBean.getUserType() == UserHelper.LotteryUser){
                LotteryInNewsTabFragment a1 = LotteryInNewsTabFragment.newInstance();
                fragmentList.add(a1);
                //中奖界面
                WinningInNewsTabFragment a2 = WinningInNewsTabFragment.newInstance();
                fragmentList.add(a2);
            }else{
                LotteryInNewsTabFragment a1 = LotteryInNewsTabFragment.newInstance();
                fragmentList.add(a1);
                //促销信息
                PromotionNewsTabFragment a2 = PromotionNewsTabFragment.newInstance();
                fragmentList.add(a2);
                //通知
                NotifyNewsTabFragment a3 = NotifyNewsTabFragment.newInstance();
                fragmentList.add(a3);
            }
        }

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
                tabView.setCurrentIndex(currentIndex);
                tabView.updateView();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.e("", "");
            }
        });
    }
}
