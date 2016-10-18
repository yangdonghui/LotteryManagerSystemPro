package com.manager.lotterycity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.manager.adapter.CommonAdapter;
import com.manager.adapter.ViewHolder;
import com.manager.bean.WidgetBean1;
import com.manager.common.Constants;
import com.manager.common.Tools;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;

import java.util.ArrayList;

/**
 * 彩票城 推荐号等资料 界面
 * @author donghuiyang
 * @create time 2016/7/22 0022.
 */
public class LotteryRecommendAct extends Activity implements View.OnClickListener{

    //返回按钮
    private View backBtn;

    //gridview
    private GridView mGridView;
    private CommonAdapter<WidgetBean1> mGridViewAdapter;
    //生成动态数组，加入数据
    private ArrayList<WidgetBean1> mLists;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lotterycity_recommend_layout);

        initData();

        initTopView();
        initGridView();
    }

    /**
     * 设置标题
     */
    private void initTopView() {
        ViewGroup topView = (ViewGroup) findViewById(R.id.lottery_recommend_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.lottery_city_info25);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }


    private void initData(){
        //获取列表数据
        mLists = Constants.LotteryCityLists1;
    }

    /**
     * 推荐号列表
     */
    /**
     * 初始化gridview控件
     */
    private void initGridView() {
        int size = mLists.size();
        mGridView = (GridView) findViewById(R.id.lottery_recommend_gridview);
        Tools.updateGridView(this, size, Tools.dip2px(this, 105), 0, mGridView);
        mGridViewAdapter = new CommonAdapter<WidgetBean1>(this, mLists, R.layout.grid_item_4) {
            @Override
            public void convert(ViewHolder helper, final WidgetBean1 item) {

                helper.getView(R.id.grid_item_4_btn).setTag(item.getId());
                helper.setImageResource(R.id.grid_item_4_icon_imgv, item.getIcon());
                helper.setText(R.id.grid_item_4_title_tv, item.getText());
            }
        };

        mGridView.setAdapter(mGridViewAdapter);
        //单击事件
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LotteryRecommendAct.this, LotteryRecommendDetailAct.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v) {
            //返回按钮
            SysApplication.backBtn(this, null);
        }
    }
}
