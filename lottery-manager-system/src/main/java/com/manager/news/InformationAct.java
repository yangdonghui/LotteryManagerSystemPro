package com.manager.news;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.manager.adapter.CommonAdapter;
import com.manager.adapter.ViewHolder;
import com.manager.bean.InfoBean;
import com.manager.common.Constants;
import com.manager.lotterypro.R;

import java.util.ArrayList;

/**
 * 资讯 界面
 * @author donghuiyang
 * @create time 2016/5/13 0013.
 */
public class InformationAct extends Activity implements View.OnClickListener{

    //返回按钮
    private View backBtn;

    private ListView mListView;
    private CommonAdapter<InfoBean> mListViewAdapter;
    //生成动态数组，加入数据
    ArrayList<InfoBean> mLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_layout);

        initData();

        initView();
        initList();
    }

    @Override
    protected void onDestroy() {
        mLists = null;

        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //返回按钮
            finish();
        }
    }

    /**
     * 获取控件对象
     */
    private void initView() {
        ViewGroup topView = (ViewGroup)findViewById(R.id.information_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.news_item_str_3);

        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    private void initList() {
        mListView = (ListView) findViewById(R.id.information_listview);
        mListViewAdapter = new CommonAdapter<InfoBean>(this, mLists, R.layout.list_item_4) {
            @Override
            public void convert(ViewHolder helper, InfoBean item) {
                helper.setImageResource(R.id.list_item_4_icon_imageview, item.getIconID());
                helper.setText(R.id.list_item_4_title_textview, item.getInfoTitle());
                helper.setText(R.id.list_item_4_content_textview, item.getInfoContent());
            }
        };
        mListView.setAdapter(mListViewAdapter);

        ((ScrollView) findViewById(R.id.information_scrollView)).smoothScrollBy(0, 10);
    }

    /**
     * 初始化list数据
     */
    private void initData() {
        //添加数据
        mLists = Constants.InfosLists;
    }
}
