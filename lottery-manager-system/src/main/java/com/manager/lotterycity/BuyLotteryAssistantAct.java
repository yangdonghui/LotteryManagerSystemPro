package com.manager.lotterycity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.manager.adapter.CommonAdapter;
import com.manager.adapter.ViewHolder;
import com.manager.bean.WidgetBean1;
import com.manager.common.Constants;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;

import java.util.ArrayList;

/**
 * 彩票 购彩助手
 * @author donghuiyang
 * @create time 2016/7/15 0015.
 */
public class BuyLotteryAssistantAct extends Activity implements OnClickListener{

    //返回按钮
    private View backBtn;

    //listview
    private ListView mListView;
    private CommonAdapter<WidgetBean1> mListViewAdapter;
    private ArrayList<WidgetBean1> mLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_lottery_assistant_layout);

        initData();

        initTopView();
        initListView();
    }

    /**
     * 设置标题
     */
    private void initTopView() {
        ViewGroup topView = (ViewGroup) findViewById(R.id.buy_lottery_assistant_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.lottery_city_info15);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    private void initData(){
        mLists = Constants.LotteryCityLists11;
    }

    private void initListView(){
        mListView = (ListView) findViewById(R.id.buy_lottery_assistant_list_listview);
        mListViewAdapter = new CommonAdapter<WidgetBean1>(this, mLists, R.layout.list_item_25) {
            @Override
            public void convert(ViewHolder helper, final WidgetBean1 item) {
                helper.setImageResource(R.id.list_item_25_icon_imgv,item.getIcon());
                helper.setText(R.id.list_item_25_name_tv, item.getText());

                //走势图
                helper.getView(R.id.list_item_25_view1).setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //进入走势图
                        Intent intent = new Intent(BuyLotteryAssistantAct.this, LotteryChartAct.class);
                        intent.putExtra("data", item);
                        startActivity(intent);
                    }
                });
                //胆拖
                helper.getView(R.id.list_item_25_view2).setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                //遗漏分析
                helper.getView(R.id.list_item_25_view3).setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        };
        mListView.setAdapter(mListViewAdapter);
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //返回按钮
            SysApplication.backBtn(this, null);
        }
    }
}
