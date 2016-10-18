package com.manager.personals;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.manager.adapter.CommonAdapter;
import com.manager.adapter.ViewHolder;
import com.manager.bean.FriendBean;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 好友详细信息
 * @author donghuiyang
 * @create time 2016/5/26 0026.
 */
public class FriendInfoAct extends Activity implements View.OnClickListener{
    //返回按钮
    private View backBtn;

    //发消息按钮
    private Button sendBtn;

    //头像
    private ImageView headImagV;
    //昵称 账户 地区
    private TextView nickTv, nameTv, addressTv;
    //性别标识图
    private ImageView sexImgV;

    private View gridParent;
    private GridView myGridView;
    private CommonAdapter<Integer> mGridViewAdapter;
    //生成动态数组，加入数据
    ArrayList<HashMap<String, Object>> mLists = new ArrayList<HashMap<String, Object>>();

    //好友数据
    private FriendBean friendData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_info_layout);

        friendData = (FriendBean)getIntent().getSerializableExtra("data");

        initView();
        initGridView();

        updateViewData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mGridViewAdapter = null;

        mLists.clear();
        mLists = null;
    }

    /**
     * 设置标题
     */
    private void initView() {
        //topview
        ViewGroup topView = (ViewGroup)findViewById(R.id.friend_info_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.contacts_info_12);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);

        headImagV = (ImageView) findViewById(R.id.friend_info_icon_imgv);
        sexImgV = (ImageView) findViewById(R.id.friend_info_sex_imgv);
        nickTv = (TextView) findViewById(R.id.friend_info_realname_tv);
        nameTv = (TextView) findViewById(R.id.friend_info_name_tv);
        addressTv = (TextView) findViewById(R.id.friend_info_address_tv);

        sendBtn = (Button) findViewById(R.id.friend_info_send_btn);
        sendBtn.setOnClickListener(this);

        //查看具体社区列表
        gridParent = (View) findViewById(R.id.friend_info_community_list);
        gridParent.setOnClickListener(this);
    }

    private void updateViewData() {
        if (friendData == null) return;

        //昵称
        nickTv.setText(friendData.getName());

        //头像
        ImageLoader.getInstance().displayImage(friendData.getIconUrl(), headImagV);

        //账号
    }

    private List<Integer> mDatas = new ArrayList<Integer>(Arrays.asList(
            R.drawable.demo_debug, R.drawable.lena_debug, R.drawable.demo_debug));
    /**
     * 获取gridview控件并初始化
     */
    private void initGridView() {
        myGridView = (GridView) findViewById(R.id.friend_info_community_gridView);
        mGridViewAdapter = new CommonAdapter<Integer>(this, mDatas, R.layout.horizontal_scroll_index_gallery_item) {
            @Override
            public void convert(ViewHolder helper, Integer item) {
                helper.setImageResource(R.id.id_index_gallery_item_image, item.intValue());
                helper.getView(R.id.id_index_gallery_item_text).setVisibility(View.GONE);
            }
        };//好友社区列表
        myGridView.setAdapter(mGridViewAdapter);
    }

    /**
     * 初始化数据列表
     */
    private void initData() {

    }

    @Override
    public void onClick(View v) {
        if (backBtn == v) {
            //返回按钮
            SysApplication.backBtn(this, null);

        }else if(sendBtn == v) {
            //聊天 入口
            Intent intent = new Intent(this, ChattingAct.class);
            intent.putExtra("data", friendData);
            startActivity(intent);
        }else if (gridParent == v) {
            //查看好友的社区列表
            if (mLists != null && mLists.size() > 0){
                //点击查看社区列表

            }
        }
    }
}
