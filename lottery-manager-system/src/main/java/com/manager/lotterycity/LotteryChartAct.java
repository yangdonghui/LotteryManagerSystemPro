package com.manager.lotterycity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.manager.Interface.FragmentListener;
import com.manager.bean.WidgetBean1;
import com.manager.common.Constants;
import com.manager.lotterycity.chart.fragment.BlueChartFragment;
import com.manager.lotterycity.chart.fragment.RedChartFragment;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.view.TabCommonView;
import com.manager.widgets.IndexViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 彩票 走势图
 * @author donghuiyang
 * @create time 2016/7/15 0015.
 */
public class LotteryChartAct extends FragmentActivity implements View.OnClickListener, FragmentListener {

    private int currentIndex = 0;

    WidgetBean1 item;

    //返回按钮
    private View backBtn;

    private TabCommonView tabView;
    private IndexViewPager vPager;

    private ChartFragmentAdapter pageAdapter;
    private List<Fragment> fragmentList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lottery_chart_layout);

        //彩种的数据
        item = (WidgetBean1)getIntent().getSerializableExtra("data");

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
        ViewGroup topView = (ViewGroup) findViewById(R.id.lottery_chart_toview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(item.getText() + getResources().getString(R.string.lottery_city_info20));
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    /**
     * tab标签初始化
     */
    private void initTabView() {
        tabView = (TabCommonView)findViewById(R.id.lottery_chart_tabview);
        tabView.setTabType(1);
        tabView.setTexts(Constants.ChartTab1);
        tabView.updateTab(currentIndex, R.style.text_size_14_text_bg_color_19);
        tabView.updateImageViewCursor(R.drawable.rect_radius_32_1);
        tabView.setonClick(new TabCommonView.ICoallBack() {
            @Override
            public void onClickView(int index) {
                currentIndex = index;

                tabView.updateView();
                updateContent();
            }
        });
    }

    private void initView() {
        vPager = (IndexViewPager) findViewById(R.id.lottery_chart_content);
        vPager.setScanScroll(false);
        vPager.setOffscreenPageLimit(0);
    }

    private void initContent(){
        RedChartFragment fragment1 = RedChartFragment.newInstance();
        fragmentList.add(fragment1);

        BlueChartFragment fragment2 = BlueChartFragment.newInstance();
        fragmentList.add(fragment2);

        pageAdapter = new ChartFragmentAdapter(getSupportFragmentManager(), fragmentList);
        vPager.setAdapter(pageAdapter);

        updateContent();
    }

    private void updateContent(){
        vPager.setCurrentItem(currentIndex, false);

        if (currentIndex == 0){
            //红球


        }else if (currentIndex == 1){
            //蓝球
        }

    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //返回按钮
            SysApplication.backBtn(this, null);
        }
    }

    @Override
    public void onFragmentClickListener(int item, String value, boolean isRemove) {
        pageAdapter.update(item, value, isRemove);
    }

    public class ChartFragmentAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentList = new ArrayList<>();
        FragmentManager fm;

        public ChartFragmentAdapter(FragmentManager fm,List<Fragment> fragmentList) {
            super(fm);

            this.fm = fm;
            this.fragmentList = fragmentList;
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return (Fragment)RedChartFragment.newInstance();
                case 1:
                    return (Fragment)BlueChartFragment.newInstance();
                default:
                    return null;

            }
        }

        public void update(int item, String value, boolean isRemove){
            Fragment fragment = fragmentList.get(item);
            if(fragment != null){
                switch (item) {
                    case 0:
                        ((RedChartFragment) fragment).update(value, isRemove);
                        break;
                    case 1:
                        ((BlueChartFragment) fragment).update(value, isRemove);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
