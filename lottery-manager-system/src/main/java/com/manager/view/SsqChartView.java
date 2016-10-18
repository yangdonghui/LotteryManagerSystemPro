package com.manager.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.manager.adapter.lotterycity.GridViewAdapter1;
import com.manager.lotterypro.R;
import com.manager.widgets.CHScrollView;
import com.manager.lotterycity.LotteryChartScrollAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 双色球走势图 view
 * Created by Administrator on 2016/3/2 0002.
 */
public class SsqChartView extends LinearLayout {

    private int MaxNum = 33;

    //上下文对象
    private Context mContext = null;
    //View对象
    private View mView = null;


    //方便测试，直接写的public
    public HorizontalScrollView mTouchView;

    //表头
    private CHScrollView headerScroll;
    private ListView mListView;
    private LotteryChartScrollAdapter adapter;
    //装入所有的HScrollView
    protected List<CHScrollView> mHScrollViews =new ArrayList<CHScrollView>();
    private GridView gridView;
    private GridViewAdapter1 gridViewAdapter;
    private List<Integer> headerLists = new ArrayList<>();

    public SsqChartView(Context context) {
        this(context, null);
    }

    public SsqChartView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;

        initView();
    }

    public void setData(int maxNum){
        MaxNum = maxNum;
        initHeaderData();
    }

    /**
     * 初始化View
     */
    private void initView() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.ssq_chart_layout, this, true);

        //添加表头数据
        gridView = (GridView) mView.findViewById(R.id.chart_item_grid);
        //表内容
        mListView = (ListView) mView.findViewById(R.id.lottery_chart_scroll_list);

        //表头
        headerScroll = (CHScrollView) mView.findViewById(R.id.chart_item_ssq_item_scroll_title);
        headerScroll.setTouchView(mTouchView);
        headerScroll.setCallBack(new CHScrollView.ICoallBack() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                SsqChartView.this.onScrollChanged(l, t, oldl, oldt);
            }
        });
        //添加表头滑动事件
        mHScrollViews.add(headerScroll);
    }

    private void initHeaderData(){
        headerLists.clear();
        for (int i=0;i<MaxNum;i++){
            headerLists.add(i+1);
        }
    }

    public void updateView(){
        List<Map<String, String>> datas = new ArrayList<Map<String,String>>();
        Map<String, String> data = null;

        setGridView();

        for(int i = 0; i < 10; i++) {
            data = new HashMap<String, String>();
            data.put("title", "Title_" + i);
            data.put("data_" + 1, "Date_" + 1 + "_" +i );
            data.put("data_" + 2, "Date_" + 2 + "_" +i );
            data.put("data_" + 3, "Date_" + 3 + "_" +i );
            data.put("data_" + 4, "Date_" + 4 + "_" +i );
            data.put("data_" + 5, "Date_" + 5 + "_" +i );
            data.put("data_" + 6, "Date_" + 6 + "_" +i );
            datas.add(data);
        }
        adapter = new LotteryChartScrollAdapter(mContext, datas, R.layout.chart_item_header
                , new String[] { "title", "data_1", "data_2", "data_3", "data_4", "data_5", "data_6"}
                , new int[] {R.id.chart_item_title_tv
                , R.id.chart_item_grid
                , R.id.chart_item_ssq_rootview}, MaxNum);
        mListView.setAdapter(adapter);
        adapter.setCallBack(new LotteryChartScrollAdapter.ICoallBack() {
            @Override
            public void dealWithScrollView(CHScrollView hScrollView) {
                addHViews(hScrollView);
            }
        });
    }

    public void addHViews(final CHScrollView hScrollView) {
        if(!mHScrollViews.isEmpty()) {
            int size = mHScrollViews.size();
            CHScrollView scrollView = mHScrollViews.get(size - 1);
            scrollView.setCallBack(new CHScrollView.ICoallBack() {
                @Override
                public void onScrollChanged(int l, int t, int oldl, int oldt) {
                    SsqChartView.this.onScrollChanged(l, t, oldl, oldt);
                }
            });
            final int scrollX = scrollView.getScrollX();
            //第一次满屏后，向下滑动，有一条数据在开始时未加入
            if(scrollX != 0) {
                mListView.post(new Runnable() {
                    @Override
                    public void run() {
                        //当listView刷新完成之后，把该条移动到最终位置
                        hScrollView.scrollTo(scrollX, 0);
                    }
                });
            }
        }
        mHScrollViews.add(hScrollView);
    }

    /**
     * 滚动 布局更新
     * @param l
     * @param t
     * @param oldl
     * @param oldt
     */
    public void onScrollChanged(int l, int t, int oldl, int oldt){
        for(CHScrollView scrollView : mHScrollViews) {
            //防止重复滑动
            if(mTouchView != scrollView)
                scrollView.smoothScrollTo(l, t);
        }
    }

    /**
     * 设置GirdView参数，绑定数据
     */
    private void setGridView() {
        int length = 50;
        int listSize = headerLists.size();
        int gridviewWidth = (int) ((listSize * (length) + (listSize-1)));
        int itemWidth = (int) (length);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);
        gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        gridView.setColumnWidth(itemWidth); // 设置列表项宽
        gridView.setHorizontalSpacing(1); // 设置列表项水平间距
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setNumColumns(listSize); // 设置列数量=列表集合数

        gridViewAdapter = new GridViewAdapter1(mContext, headerLists);
        gridView.setAdapter(gridViewAdapter);
    }
}
