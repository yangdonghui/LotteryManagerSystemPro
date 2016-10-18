package com.manager.user.site;

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
import com.manager.user.fragment.DeclareRecordTabFragment1;
import com.manager.user.fragment.DeclareRecordTabFragment2;
import com.manager.user.fragment.DeclareRecordTabFragment3;
import com.manager.view.TabCommonView;

import java.util.ArrayList;

/**
 * 站点 申报记录界面
 * @author donghuiyang
 * @create time 2016/4/19 0019.
 */
public class SiteDeclareRecordAct extends FragmentActivity implements View.OnClickListener {

    //title
    private TextView titleView = null;
    //返回按钮
    private View backBtn = null;


    private TabCommonView tabView;
    private ViewPager vPager;
    private FragmentAdapter mAdapter;
    //所有Fragment控件数组容器
    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.site_declare_record_layout);

        //普通视图初始化
        initTopView();
        initView();
        //tab标签初始化
        initTabView();
        initContent();
    }

    @Override
    protected void onDestroy() {

        fragmentList.clear();
        fragmentList = null;

        super.onDestroy();
    }

    private void initTopView() {
        ViewGroup topView = (ViewGroup)findViewById(R.id.site_declare_record_topview);
        titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        titleView.setText(R.string.site_declare_info9);

        backBtn.setOnClickListener(this);
    }

    /**
     * tab标签初始化
     */
    private void initTabView() {
        tabView = (TabCommonView)findViewById(R.id.site_declare_record_tabview);

        tabView.setTexts(Constants.SiteRecordTab);

        tabView.setonClick(new TabCommonView.ICoallBack() {
            @Override
            public void onClickView(int index) {
                currentIndex = index;
                vPager.setCurrentItem(index);
            }
        });
    }

    private void initView() {
        vPager = (ViewPager) findViewById(R.id.site_declare_record_tab_content);
        vPager.setOffscreenPageLimit(0);
    }

    private void initContent() {
        DeclareRecordTabFragment1 a1 = DeclareRecordTabFragment1.newInstance();
        fragmentList.add(a1);
        DeclareRecordTabFragment2 a2 = DeclareRecordTabFragment2.newInstance();
        fragmentList.add(a2);
        DeclareRecordTabFragment3 a3 = DeclareRecordTabFragment3.newInstance();
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
    /**
     * 按钮事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        int index = -1;

        if (v == backBtn) {
            //删除当前activity
            finish();
        }
    }
}
