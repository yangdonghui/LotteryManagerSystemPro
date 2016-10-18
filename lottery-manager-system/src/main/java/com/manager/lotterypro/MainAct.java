package com.manager.lotterypro;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.manager.adapter.FragmentAdapter;
import com.manager.bean.UserBean;
import com.manager.common.Constants;
import com.manager.main.fragments.CommunityFragment;
import com.manager.main.fragments.HomeFragment;
import com.manager.main.fragments.LotteryCityFragment;
import com.manager.main.fragments.MeFragment;
import com.manager.widgets.IndexViewPager;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/19 0019.
 */
public class MainAct extends FragmentActivity implements View.OnClickListener {

    //用户数据
    private UserBean userBean = SysApplication.userBean;

    //标签项
    private ArrayList<FrameLayout> viewGroups = new ArrayList<>();
    private ArrayList<View> tabLines = new ArrayList<>();
    private ArrayList<RadioButton> radioButtons = new ArrayList<>();

    private IndexViewPager vPager;
    private FragmentAdapter mAdapter;
    //所有Fragment控件数组容器
    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();
    //当前标签页索引
    private int mCurIndex = 0;

    //tab标签文字
    private int[] tabStrs = Constants.MainTabStrs;
    private int[] tabImgs = Constants.MainTabImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initTabView();
        initContent();

        SysApplication.mMainAct = this;

        //添加到activity管理列表
        SysApplication.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        viewGroups.clear();
        viewGroups = null;
        tabLines.clear();
        tabLines = null;
        radioButtons.clear();
        radioButtons = null;

        fragmentList.clear();
        fragmentList = null;

        SysApplication.mMainAct = null;
    }

    /**
     * 初始化tab标签
     */
    private void initTabView() {
        FrameLayout homeTab = (FrameLayout) findViewById(R.id.home_tab);
        viewGroups.add(homeTab);
        radioButtons.add((RadioButton) findViewById(R.id.radioButton1));
        tabLines.add((View) homeTab.getChildAt(1));

        FrameLayout communityTab = (FrameLayout) findViewById(R.id.community_tab);
        viewGroups.add(communityTab);
        radioButtons.add((RadioButton) findViewById(R.id.radioButton2));
        tabLines.add((View) communityTab.getChildAt(1));

        FrameLayout lotteryCityTab = (FrameLayout) findViewById(R.id.lottery_tab);
        viewGroups.add(lotteryCityTab);
        radioButtons.add((RadioButton) findViewById(R.id.radioButton4));
        tabLines.add((View) lotteryCityTab.getChildAt(1));

        FrameLayout meTab = (FrameLayout) findViewById(R.id.me_tab);
        viewGroups.add(meTab);
        radioButtons.add((RadioButton) findViewById(R.id.radioButton5));
        tabLines.add((View) meTab.getChildAt(1));

        for (int i = 0; i < viewGroups.size(); i++) {
            viewGroups.get(i).setOnClickListener(this);

            radioButtons.get(i).setText(tabStrs[i]);

            Drawable drawable = getResources().getDrawable(tabImgs[i]);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());//必须设置图片大小，否则不显示
            radioButtons.get(i).setCompoundDrawables(null, drawable, null, null);
        }
    }

    /**
     * 更新tab效果
     *
     * @param
     */
    private void updateTab() {

        for (int i = 0; i < radioButtons.size(); i++) {
            if (mCurIndex == i) {
                radioButtons.get(i).setChecked(true);
                viewGroups.get(i).setBackgroundColor(Color.WHITE);
                tabLines.get(i).setBackgroundColor(getResources().getColor(R.color.view_line_0));
            } else {
                radioButtons.get(i).setChecked(false);
                viewGroups.get(i).setBackgroundColor(getResources().getColor(R.color.bg_color_0));
                tabLines.get(i).setBackgroundColor(getResources().getColor(R.color.bg_color_0));
            }
        }
    }

    private void initView() {
        vPager = (IndexViewPager) findViewById(R.id.lv_fragment_container);
        //禁止滑动
        vPager.setScanScroll(false);
        //去掉预加载，默认一次只加载一个界面
        vPager.setOffscreenPageLimit(1);
    }
    private void initContent(){
        HomeFragment a1 = HomeFragment.newInstance();
        fragmentList.add(a1);
        CommunityFragment a2 = CommunityFragment.newInstance();
        fragmentList.add(a2);
        LotteryCityFragment a4 = LotteryCityFragment.newInstance();
        fragmentList.add(a4);
        MeFragment a5 = MeFragment.newInstance();
        fragmentList.add(a5);

        mAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
        //添加到ViewPager容器
        vPager.setAdapter(mAdapter);
        vPager.setCurrentItem(mCurIndex, false);
    }

    private void changePage(int index) {

        if (index == -1 || mCurIndex == index) return;
        mCurIndex = index;

        vPager.setCurrentItem(mCurIndex, false);

        updateTab();
    }

    /**
     * tab标签点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        int index = -1;

        for (int i = 0; i < viewGroups.size(); i++) {
            if (v == viewGroups.get(i)) {
                index = i;
                break;
            }
        }


        changePage(index);
    }

    private long mExitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //showDailog("亲！真的要退出吗？");

            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次返回键退出", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
                SysApplication.exit();
            }
            return true;

        }
        return false;
    }

    /**
     * 更新“我” 的头像
     */
    public void updateIconImg(String path){
        ((MeFragment)fragmentList.get(3)).updateIcon(path);
    }
}
