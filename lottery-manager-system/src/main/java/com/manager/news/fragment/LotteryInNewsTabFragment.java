package com.manager.news.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.manager.adapter.news.LotteryInfoListViewAdapter;
import com.manager.bean.LotteryInfoBean;
import com.manager.bean.UserBean;
import com.manager.common.Constants;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.news.LotteryInfoDetailAct;

import java.util.ArrayList;

/**
 * 开奖信息 界面
 * Created by Administrator on 2016/3/23 0023.
 */
public class LotteryInNewsTabFragment extends Fragment {

    private UserBean userBean = SysApplication.userBean;

    //上下文对象
    private Context mContext = null;

    private ListView mListView;
    private LotteryInfoListViewAdapter mListViewAdapter;
    //生成动态数组，加入数据
    ArrayList<LotteryInfoBean> mLists;
    //list无数据的提示
    private View tipView;

    //根视图缓存
    private View rootView = null;
    //静态对象
    private static LotteryInNewsTabFragment mTabFragment = null;

    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static LotteryInNewsTabFragment newInstance() {
        if (mTabFragment == null) {
            mTabFragment = new LotteryInNewsTabFragment();
        }

        return mTabFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
        mContext = getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mLists = null;
        mTabFragment=null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        if (null != rootView) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (null != parent) {
                parent.removeView(rootView);
            }
        } else {
            rootView = inflater.inflate(R.layout.lotteryinfo_layout,null);//注意不要指定父视图

            initView();
            initList();

        }

        return rootView;
    }

    private void initView() {
        tipView = (View) rootView.findViewById(R.id.lottery_info_tab_listview_no);

        updateTipView();
    }

    private void updateTipView() {
        if (mLists != null && mLists.size() > 0){
            if (tipView.getVisibility() != View.GONE){
                tipView.setVisibility(View.GONE);
            }else {
                tipView.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * 初始化list数据
     */
    private void initData() {
        //添加数据
        mLists = Constants.AllLotteryLists;

    }
    /**
     * 初始化listview
     */
    private void initList() {
        mListView = (ListView) rootView.findViewById(R.id.lottery_info_listview);
        mListViewAdapter = new LotteryInfoListViewAdapter(mContext, mLists, 0);
        mListView.setAdapter(mListViewAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //进入详细界面
                Intent intent = new Intent(mContext, LotteryInfoDetailAct.class);
                intent.putExtra("data", mLists.get(position));
                startActivity(intent);
            }
        });
    }
}
