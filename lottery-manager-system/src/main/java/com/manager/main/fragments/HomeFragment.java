package com.manager.main.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.manager.Interface.ICoallBack8;
import com.manager.bean.UserBean;
import com.manager.helper.UserHelper;
import com.manager.lotterypro.SysApplication;
import com.manager.lotterypro.R;
import com.manager.user.UserTicketAct;
import com.manager.view.BigIconView;
import com.manager.view.HomeBettingShopView;
import com.manager.view.HomeLotteryView;
import com.manager.view.HomeManagerView;
import com.manager.view.NewsColumnView;

/**
 * 首页界面
 * @author donghuiyang
 * @create time 2016/4/20 0020.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{

    private static HomeFragment mHomeFragment = null;
    private View rootView = null;

    /** Fragment当前状态是否可见 */
    protected boolean isVisible;

    //顶部view
    private ViewGroup mTopView;
    //地图入口 扫描入口
    private View mapBtn, scanBtn;
    //topview 标题
    private TextView mTopTitle = null;

    //用户
    public UserBean mUserBean = SysApplication.userBean;
    //上下文对象
    private Context mContext = null;

    //公告栏
    private NewsColumnView newsColumnView;
    private ImageView mNewsIconBtn;

    private ViewGroup childView;
    private BigIconView bigIconView;

    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static HomeFragment newInstance() {
        if (mHomeFragment == null) {
            mHomeFragment = new HomeFragment();
        }

        return mHomeFragment;
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

        mContext = getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (bigIconView != null){
            bigIconView.clearAnimation();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mHomeFragment = null;
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
            rootView = inflater.inflate(R.layout.home_fragment_layout, container, false);
            InitView();

            /*myHandler = new MyHandler(mContext,1);
            //起一个线程更新数据
            MyThread m = new MyThread();
            new Thread(m).start();*/
        }

        //修正
        updateView();

        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (rootView == null){

        }

        if (isVisibleToUser){
            //更新
        }

        super.setUserVisibleHint(isVisibleToUser);
    }

    private void InitView() {
        newsColumnView = (NewsColumnView) rootView.findViewById(R.id.home_announcement_view);
        mNewsIconBtn = (ImageView) rootView.findViewById(R.id.home_announcement_icon_imgv);
        mNewsIconBtn.setOnClickListener(this);

        childView = (ViewGroup) rootView.findViewById(R.id.home_content_child_view);
        bigIconView = (BigIconView) rootView.findViewById(R.id.home_content_child_big_icon_view);

        //顶部view
        mTopView = (ViewGroup) rootView.findViewById(R.id.home_top_view_include);

        mapBtn = (ImageView) mTopView.findViewById(R.id.home_location_imageView);
        scanBtn = (ImageView) mTopView.findViewById(R.id.home_scan_imageView);
        mapBtn.setOnClickListener(this);
        scanBtn.setOnClickListener(this);

        mTopTitle = (TextView)mTopView.findViewById(R.id.home_title_textView);
        //不同用户显示不同标题
        mTopTitle.setText(mUserBean.getUserIdentity());

        if (mUserBean.getUserType() == UserHelper.LotteryUser){
            //彩民
            HomeLotteryView view = new HomeLotteryView(mContext);
            childView.addView(view);

            view.setICoallBack(new ICoallBack8() {
                @Override
                public void onItemTouchDown(int imgID, int titleID, int styleID, int x, int y, boolean isFlip) {
                    //按下 或 移动 显示 大图标
                    bigIconView.updatePos(imgID, titleID, styleID, x, y, isFlip);
                    bigIconView.bigScaleAnimation();
                }

                @Override
                 public void onItemTouchUp() {
                    //弹起收回
                    bigIconView.smallScaleAnimation();
                }

                @Override
                public void updateView(boolean flag) {
                    bigIconView.smallScaleAnimation();
                }
            });

        }else if (mUserBean.getUserType() == UserHelper.BettingShopUser){
            //投注站
            HomeBettingShopView view = new HomeBettingShopView(mContext);
            childView.addView(view);
            view.setICoallBack(new ICoallBack8() {
                @Override
                public void onItemTouchDown(int imgID, int titleID, int styleID, int x, int y, boolean isFlip) {
                    //按下 或 移动 显示 大图标
                    bigIconView.updatePos(imgID, titleID, styleID, x, y, isFlip);
                    bigIconView.bigScaleAnimation();
                }

                @Override
                public void onItemTouchUp() {
                    //弹起收回
                    bigIconView.smallScaleAnimation();
                }

                @Override
                public void updateView(boolean flag) {
                    bigIconView.smallScaleAnimation();
                }
            });

        }else if (mUserBean.getUserType() == UserHelper.ManagerUser){
            //管理员
            HomeManagerView view = new HomeManagerView(mContext);
            childView.addView(view);
            view.setICoallBack(new ICoallBack8() {
                @Override
                public void onItemTouchDown(int imgID, int titleID, int styleID, int x, int y, boolean isFlip) {
                    //按下 或 移动 显示 大图标
                    bigIconView.updatePos(imgID, titleID, styleID, x, y, isFlip);
                    bigIconView.bigScaleAnimation();
                }

                @Override
                public void onItemTouchUp() {
                    //弹起收回
                    bigIconView.smallScaleAnimation();
                }

                @Override
                public void updateView(boolean flag) {
                    bigIconView.smallScaleAnimation();
                }
            });
        }

        /*int pageNo = (int)Math.ceil( list.size()/APP_PAGE_SIZE);
        for (int i = 0; i < pageNo; i++) {
            MyGridView appPage = new MyGridView(mContext);
            // get the "i" page data
            appPage.setAdapter(new AppAdapter(mContext, list, i));
            appPage.setNumColumns(3);
            appPage.setCacheColorHint(0x00FFFFFF);
            appPage.setSelector(new ColorDrawable(Color.TRANSPARENT));
            mScrollLayout.addView(appPage);
        }*/
    }

    /**
     * 更新view
     */
    private void updateView(){
        //更新图标点击提示view
        if (bigIconView != null && bigIconView.getVisibility() == View.VISIBLE){
            bigIconView.updateView(false);
        }
    }

    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (v == mapBtn){
            //地图入口

        }else if(v == scanBtn) {
            //扫描入口
            Intent intent = new Intent(mContext, UserTicketAct.class);
            intent.putExtra("title", R.string.home_title_1);
            startActivity(intent);
        }else if(v == mNewsIconBtn){
            if (newsColumnView.isOpening() == 1){
                //打开
                newsColumnView.runEnterAnim();
            }else if (newsColumnView.isOpening() == 0){
                //关闭
                newsColumnView.runExitAnim();
            }
        }
    }
}
