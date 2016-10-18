package com.manager.community;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.manager.common.Tools;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * 发帖界面
 * @author donghuiyang
 * @create time 2016/4/20 0020.
 */
public class SendPostAct extends Activity implements View.OnClickListener{

    //发表类型
    private int sendType = -1;

    //上传图片url
    private List<String> upLoadImgs = new ArrayList<>();

    //返回按钮
    private View backBtn;
    //ok按钮
    private View sendBtn;

    private GridView mGridView;
    private MyGridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_post_layout);

        sendType = getIntent().getIntExtra("sendType", sendType);

        if (sendType == 2 || sendType == 3){
            //拍照 或 手机相册
            initData();
        }

        initView();
        initGridView();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //隐藏软键盘
        Tools.hideSoftInput(this);

    }

    @Override
    protected void onDestroy() {
        upLoadImgs.clear();
        upLoadImgs = null;

        super.onDestroy();
    }

    /**
     * 获取控件对象
     */
    private void initView() {
        backBtn = (View) findViewById(R.id.send_post_cancel_btn);
        backBtn.setOnClickListener(this);

        sendBtn = (View) findViewById(R.id.send_post_ok_btn);
        sendBtn.setOnClickListener(this);
    }

    /**
     * 初始化upload列表
     */
    private void initData(){
        upLoadImgs.add("1");
        upLoadImgs.add("2");
    }
    private void initGridView(){
        int size = upLoadImgs.size();

        mGridView = (GridView) findViewById(R.id.send_post_upload_gridView);
        Tools.updateGridView(this, size, Tools.dip2px(this, 75), 0, mGridView);
        adapter = new MyGridAdapter();
        mGridView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //取消按钮
            SysApplication.backBtn(this, null);

        }else if (sendBtn == v) {
            //发送 请求按钮
        }
    }

    protected class MyGridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return upLoadImgs.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            View view = View.inflate(SendPostAct.this, R.layout.grid_item_6, null);
            ImageView imgv = (ImageView) view.findViewById(R.id.grid_view_6_img);
            if (position >= upLoadImgs.size()){
                if (position == getCount()-2){
                    //添加手机图片
                    imgv.setImageResource(R.mipmap.send_post_upload_add_btn);
                }else if (position == getCount() - 1){
                    //拍照
                    imgv.setImageResource(R.mipmap.send_post_upload_btn);
                }
            }

            return view;
        }
    }
}
