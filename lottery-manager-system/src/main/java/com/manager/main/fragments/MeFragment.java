package com.manager.main.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.manager.adapter.CommonAdapter;
import com.manager.adapter.ViewHolder;
import com.manager.bean.UserBean;
import com.manager.bean.WidgetBean1;
import com.manager.common.Constants;
import com.manager.helper.UserHelper;
import com.manager.lotterypro.SysApplication;
import com.manager.lotterypro.R;
import com.manager.user.ModifyHeadSelectPhotoAct;
import com.manager.user.MyLotteryNumberRecordAct;
import com.manager.user.SettingAct;
import com.manager.user.manager.ManagerAreaListsAct;
import com.manager.user.site.SiteManagerEmployeeAct;
import com.manager.wallet.MyWalletAct;

import java.util.ArrayList;

/**
 * 我的界面
 * @author donghuiyang
 * @create time 2016/4/20 0020.
 */
public class MeFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener{

    //用户
    public UserBean mUserBean = SysApplication.userBean;
    //上下文对象
    private Context mContext = null;

    private TextView userNameTextView;
    private GridView gridView;
    private CommonAdapter<WidgetBean1> gridViewAdapter;

    //头像
    private ImageView iconImg;
    private View rootView = null;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String path = (String)msg.obj;
            switch (msg.what) {
                case 0:
                    updateIcon(path);
                    break;

                default:
                    break;
            }
        }
    };

    private static MeFragment mMeFragment = null;
    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static MeFragment newInstance() {
        if (mMeFragment == null) {
            mMeFragment = new MeFragment();
        }

        return mMeFragment;
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

        Intent intent = getActivity().getIntent();
        String path = intent.getStringExtra("path");
        if (path != null){
            Message msg = new Message();
            msg.what = 0;
            msg.obj = path;
            mHandler.handleMessage(msg);
        }

        Log.e("", "");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mMeFragment = null;
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
            rootView = inflater.inflate(R.layout.me_fragment_layout, container, false);

            initView();
        }

        return rootView;
    }

    /**
     * 初始化view
     */
    private void initView() {
        if (mUserBean == null) return;

        ArrayList<WidgetBean1> lists = null;

        //头像
        iconImg = (ImageView) rootView.findViewById(R.id.me_headicon_img);
        iconImg.setOnClickListener(this);
        //用户id
        userNameTextView = (TextView) rootView.findViewById(R.id.me__username_tv);

        //功能列表
        gridView = (GridView) rootView.findViewById(R.id.me_gridview);
        switch (mUserBean.getUserType()){
            case UserHelper.LotteryUser:{
                lists = Constants.MeGridViewLists1;

                userNameTextView.setText(mUserBean.getUserIdentity() + " : " + mUserBean.getUserName());
            }
            break;
            case UserHelper.BettingShopUser:{
                lists = Constants.MeGridViewLists2;

                userNameTextView.setText(mUserBean.getUserIdentity() + " : " + mUserBean.getUserName());
            }
            break;
            case UserHelper.ManagerUser:{
                lists = Constants.MeGridViewLists3;

                userNameTextView.setText(mUserBean.getUserIdentity() + " : " + mUserBean.getUserName());
            }
            break;
        }

        gridViewAdapter = new CommonAdapter<WidgetBean1>(mContext, lists, R.layout.grid_item_5) {
            @Override
            public void convert(ViewHolder helper, WidgetBean1 item) {
                helper.setImageResource(R.id.grid_view_5_img, item.getIcon());
                helper.setText(R.id.grid_view_5_tv, item.getText());
            }
        };

        gridView.setAdapter(gridViewAdapter);
        //单击事件
        gridView.setOnItemClickListener(this);
    }

    /**
     * 更新头像
     * @param path
     */
    public void updateIcon(String path){
        //mUserBean.setIconUrl(path);

        Bitmap bmp = BitmapFactory.decodeFile(path);
        if (bmp != null){
            iconImg.setImageBitmap(bmp);
        }
    }

    /**
     * gridview 单项点击事件
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mUserBean == null) return;

        Class next = null;
        int type = mUserBean.getUserType();
        switch (position){
            case 0:{
                //我的钱包
                next = MyWalletAct.class;
            }
            break;

            case 1:{
                if (mUserBean.getUserType() == UserHelper.LotteryUser){
                    //彩民 我的彩票
                    next = MyLotteryNumberRecordAct.class;
                }else if (type == UserHelper.BettingShopUser){
                    //站点 雇员管理
                    SysApplication.enterNext1(mContext, SiteManagerEmployeeAct.class);
                }else if (type == UserHelper.ManagerUser){
                    //自辖站列表
                    next = ManagerAreaListsAct.class;
                }
            }
            break;

            case 2:{
                //设置
                next = SettingAct.class;
            }
            break;

            default:
                break;
        }

        if (next != null){
            SysApplication.enterNext1(mContext, next);
        }
    }

    @Override
    public void onClick(View v) {
        if(iconImg == v){
            //修改头像
            SysApplication.enterNext1(mContext, ModifyHeadSelectPhotoAct.class);
        }
    }
}
