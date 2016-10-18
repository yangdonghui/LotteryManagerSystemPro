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
 * 彩民 委托服务
 * 彩种 玩法产品列表界面（双色球 大乐透等等）
 * @author donghuiyang
 * @create time 2016/4/20 0020.
 */
public class LotteryItemsListsAct extends Activity implements View.OnClickListener{

    //返回按钮
    private View backBtn;

    //gridview
    private GridView mGridView;
    private CommonAdapter<WidgetBean1> mGridViewAdapter;
    //生成动态数组，加入数据
    private ArrayList<WidgetBean1> mLists;

    //来源
    private int sourceType = -1;
    private int titleID = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lottery_items_lists_layout);

        sourceType = getIntent().getIntExtra("sourceType", sourceType);
        titleID = getIntent().getIntExtra("titleID", titleID);

        initData();

        initTopView();
        initGridView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mLists = null;
        mGridViewAdapter = null;
    }

    /**
     * 设置标题
     */
    private void initTopView() {

        ViewGroup topView = (ViewGroup) findViewById(R.id.lottery_items_lists_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        //彩民 委托服务 //玩法介绍
        titleView.setText(titleID);

        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    private void initData() {
        //获取列表数据
        mLists = Constants.LotteryCityLists1;
    }

    /**
     * 初始化gridview控件
     */
    private void initGridView() {
        int size = mLists.size();
        mGridView = (GridView) findViewById(R.id.lottery_items_lists_gridview);
        Tools.updateGridView(this, size, Tools.dip2px(this, 100), 0, mGridView);
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

                WidgetBean1 item = mLists.get(position);
                if (item != null){
                    if (sourceType == Constants.LotteryIntroduceActLayoutType){
                        //彩种介绍
                        if (position < 1){
                            //双色球
                            Intent intent = new Intent(LotteryItemsListsAct.this, LotteryIntroduceAct.class);
                            intent.putExtra("data", item);
                            startActivity(intent);
                        }
                    }else{
                        //委托服务--->委托投注  (只做了双色球的选号界面)
                        if (position < 1){
                            //双色球
                            Intent intent = new Intent(LotteryItemsListsAct.this, SelectNumberAct.class);
                            intent.putExtra("data", item);
                            startActivity(intent);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //返回按钮
            SysApplication.backBtn(this, null);
        }
    }
}
