package com.manager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment适配器
 * @author donghuiyang
 * @create time 2016/6/6 0006.
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    List<Fragment> fragmentList = new ArrayList<Fragment>();
    public FragmentAdapter(FragmentManager fm,List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
