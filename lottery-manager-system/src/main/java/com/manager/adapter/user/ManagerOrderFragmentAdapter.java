package com.manager.adapter.user;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.manager.user.manager.fragment.ManagerConsumableOrdersTabFragment1;
import com.manager.user.manager.fragment.ManagerConsumableOrdersTabFragment2;
import com.manager.user.manager.fragment.ManagerConsumableOrdersTabFragment3;
import com.manager.user.manager.fragment.ManagerConsumableOrdersTabFragment4;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment适配器
 * @author donghuiyang
 * @create time 2016/6/6 0006.
 */
public class ManagerOrderFragmentAdapter extends FragmentPagerAdapter {

    private int tabCount = 0;

    private List<String> tagList = new ArrayList<>();

    public ManagerOrderFragmentAdapter(FragmentManager fm, int tabCount) {
        super(fm);

        this.tabCount = tabCount;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        tagList.add(makeFragmentName(container.getId(), (int)getItemId(position)));
        return super.instantiateItem(container, position);
    }

    public static String makeFragmentName(int viewId, int index) {
        return "android:switcher:" + viewId + ":" + index;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:{
                return ManagerConsumableOrdersTabFragment1.newInstance(0);
            }
            case 1:{
                return ManagerConsumableOrdersTabFragment2.newInstance();
            }
            case 2:{
                return ManagerConsumableOrdersTabFragment3.newInstance();
            }
            case 3:{
                return ManagerConsumableOrdersTabFragment4.newInstance();
            }
        }

        return null;
    }

    @Override
    public int getCount()
    {
        return tabCount;
    }
}
