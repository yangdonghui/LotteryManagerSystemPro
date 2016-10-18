package com.manager.main.fragments;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.manager.adapter.CommonAdapter;
import com.manager.adapter.ViewHolder;
import com.manager.bean.UserBean;
import com.manager.bean.WidgetBean1;
import com.manager.common.Constants;
import com.manager.helper.UserHelper;
import com.manager.lotterycity.BillingListsAct;

import com.manager.lotterycity.BuyLotteryAssistantAct;
import com.manager.lotterycity.ConsumableListsAct;
import com.manager.lotterycity.LotteryItemsListsAct;
import com.manager.lotterycity.LotteryRecommendAct;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.user.site.SiteDeclareAct;

import java.util.ArrayList;
import java.util.List;

/**
 * 彩票城界面
 * @author donghuiyang
 * @create time 2016/4/20 0020.
 */
public class LotteryCityFragment  extends Fragment{

    //用户
    public UserBean mUserBean = SysApplication.getUserBean();
    //上下文对象
    private Context mContext = null;

    private GridView mGridView;
    private CommonAdapter<WidgetBean1> mGridViewAdapter;
    private List<WidgetBean1> mLists = new ArrayList<>();

    private View rootView = null;

    private static LotteryCityFragment mLotteryCityFragment = null;
    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static LotteryCityFragment newInstance() {
        if (mLotteryCityFragment == null) {
            mLotteryCityFragment = new LotteryCityFragment();
        }

        return mLotteryCityFragment;
    }

    /**
     * 使用静态工厂方法，将外部传入的参数可以通过Fragment.setArgument保存在它自己身上，
     * 这样我们可以在Fragment.onCreate(...)调用的时候将这些参数取出来
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("", "aaaaaaaaaaaaa");

        initData();
        mContext = getActivity();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLists.clear();
        mLists = null;
        mLotteryCityFragment = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (null != rootView) {
            //缓存view
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (null != parent) {
                parent.removeView(rootView);
            }
        } else {
            rootView = inflater.inflate(R.layout.lottery_city_fragment_layout1, container, false);

            initTopView();
            iniGridView();
        }

        return rootView;
    }

    /**
     * 初始化view
     */
    private void initTopView() {
        ViewGroup topView = (ViewGroup) rootView.findViewById(R.id.lottery_city_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.lottery_city_title);
        View backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setVisibility(View.GONE);
    }

    private void initData(){
        int count = 0;
        for (WidgetBean1 att:Constants.LotteryCityGridViewLists){
            mLists.add(att);
            count ++;

            if (mUserBean.getUserType() != UserHelper.BettingShopUser && count >= 3){
                //彩民版本 只包含前三项功能
                break;
            }
        }
    }

    /**
     * 初始化控件
     */
    private void iniGridView() {
        mGridView = (GridView) rootView.findViewById(R.id.lottery_city_gridView);
        mGridViewAdapter = new CommonAdapter<WidgetBean1>(mContext, mLists, R.layout.grid_item_5) {
            @Override
            public void convert(ViewHolder helper, WidgetBean1 item) {
                helper.setImageResource(R.id.grid_view_5_img, item.getIcon());
                helper.setText(R.id.grid_view_5_tv, item.getText());
            }
        };
        mGridView.setAdapter(mGridViewAdapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Class next = null;
                switch (position) {
                    case 0:{
                        //玩法介绍
                        Intent intent = new Intent(mContext, LotteryItemsListsAct.class);
                        intent.putExtra("sourceType", Constants.LotteryIntroduceActLayoutType);
                        intent.putExtra("titleID", R.string.lottery_city_info11);
                        startActivity(intent);
                    }
                    break;
                    case 1: {
                        //购彩助手
                        next = BuyLotteryAssistantAct.class;
                    }
                    break;

                    case 2:{
                        //推荐号
                        next = LotteryRecommendAct.class;
                    }
                    break;
                    case 3:{
                        //故障申报
                        next = SiteDeclareAct.class;
                    }
                    break;
                    case 4:{
                        //耗材
                        next = ConsumableListsAct.class;
                    }
                    break;
                    case 5:{
                        //即开票
                        next = BillingListsAct.class;
                    }
                    break;
                }

                if (next != null){
                    Intent intent = new Intent(mContext, next);
                    startActivity(intent);
                }
            }
        });
    }
}
