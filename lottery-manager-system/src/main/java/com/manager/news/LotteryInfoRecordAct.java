package com.manager.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.manager.adapter.news.LotteryInfoRecordListViewAdapter;
import com.manager.bean.LotteryInfoBean;
import com.manager.common.Constants;
import com.manager.common.Tools;
import com.manager.lotterypro.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 开奖信息 历史记录界面
 * @author donghuiyang
 * @create time 2016/5/17 0017.
 */
public class LotteryInfoRecordAct extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener{

    //返回按钮
    private View backBtn;
    //滚动区域的view控件对象
    private ScrollView scrollView;

    //listview控件
    private ListView mListView;
    private LotteryInfoRecordListViewAdapter mListViewAdapter;
    //生成动态数组，加入数据
    ArrayList<HashMap<String, Object>> mLists = new ArrayList<HashMap<String, Object>>();
    //数据列表
    private ArrayList<LotteryInfoBean> mDataLists= null;

    //彩种类型
    private int lotteryType;
    private int oldPostion = -1;
    //当前屏幕的高度
    private float screenHeight = 0, scrollviewY;
    private int h1 = 0;
    //listview 的itemview在屏幕内的位置
    int[] location = new int[2];
    //scrollview滚动到指定位置 异步消息处理
    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lotteryinfo_record_layout);

        Intent intent = getIntent();
        lotteryType = (Integer)intent.getIntExtra("data", -1);

        screenHeight = Tools.getWindowHeight(this);

        initData();
        initView();
        initList();
    }

    @Override
    protected void onDestroy() {
        //删除内存
        location = null;
        mLists.clear();
        mLists = null;
        mHandler = null;

        super.onDestroy();
    }

    /**
     * 获取控件对象 并 设置标题
     */
    private void initView() {
        ViewGroup topView = (ViewGroup) findViewById(R.id.lottery_info_record_topview);
        topView.measure(0,0);
        scrollviewY = topView.getMeasuredHeight();
        h1 = (int)(scrollviewY + Tools.getStatusBarHeight(this));

        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.news_item_str_16);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    /**
     * 初始化list数据
     */
    private void initData() {
        if (lotteryType == -1) return;

        switch (lotteryType) {
            case 0: {
                mDataLists = Constants.ShuangseqiuLotteryLists;
            }
            break;
            case 1: {
                mDataLists = Constants.DaletouLotteryLists;
            }
        }

        //添加数据
        for(int i=0;i< mDataLists.size();i++)
        {
            LotteryInfoBean att = mDataLists.get(i);
            if (att != null){
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("ItemID", att.getLotteryID());
                map.put("ItemType", att.getLotteryType());
                map.put("ItemQihao", att.getLotteryQihao());
                map.put("ItemTime", att.getLotteryTime());
                map.put("ItemNumbers1", att.getLotteryNumbers1());
                map.put("ItemNumbers2", att.getLotteryNumbers2());
                map.put("ItemName", att.getLotteryName());
                map.put("ItemExpand", false);
                mLists.add(map);
            }
        }
    }

    /**
     * 初始化listview
     */
    private void initList(){
        mListView = (ListView) findViewById(R.id.lottery_info_record_listview);
        mListViewAdapter = new LotteryInfoRecordListViewAdapter(this, mLists);
        mListViewAdapter.setListView(mListView);
        mListView.setAdapter(mListViewAdapter);

        mListView.setOnItemClickListener(this);

        //滚动位置初始置顶
        scrollView = (ScrollView)(findViewById(R.id.lottery_info_record_scrollview));
        //滚动到listview的顶部
        scrollView.smoothScrollBy(0, 10);
    }

    /**
     * 更新listview的高度
     */
    private void updateListView(boolean flag, int y, int position) {
        /*int totalHeight = 0;
        for(int i=0;i<mListViewAdapter.getCount();i++) {
            View viewItem = mListViewAdapter.getView(i, null, mListView);//这个很重要，那个展开的item的measureHeight比其他的大
            viewItem.measure(0, 0);
            totalHeight += viewItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = mListView.getLayoutParams();
        params.height = totalHeight + (mListView.getDividerHeight() * (mListView.getCount() - 1));
        mListView.setLayoutParams(params);*/

        mListViewAdapter.updateSingleRow(position);


        if (flag){
            boolean isFlag = false;

            //子view打开时，如果超出屏幕，则滚动到屏幕内
            float h = screenHeight - location[1];
            //滚动的距离
            int tmp = (int)((mListViewAdapter.h2) - h);
            if (location[1] < h1){
                //向上超出屏幕
                if (location[1] >= 0){
                    tmp = -(int)(h1 - location[1]);
                }else if (location[1] < 0){
                    tmp = -(int)(Math.abs(location[1]) + h1);
                }
                isFlag = true;
            }else if(location[1] >= h1 && location[1] <= screenHeight){
                //下面超出屏幕
                if (h < mListViewAdapter.h2){
                    tmp += h1;
                    isFlag = true;
                }
            }else if(tmp <= 0){
                return;
            }
            if (isFlag){
                final int tmp1 = tmp;
                mHandler.post(new Runnable() {
                    public void run() {
                        scrollView.smoothScrollBy(0, tmp1);
                    }
                });
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //返回按钮
            finish();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent != null && view != null){

            //获取itemview在屏幕内的坐标
            view.getLocationOnScreen(location);

            //点击item，打开或者关闭子view
            boolean isExpand = (Boolean)mLists.get(position).get("ItemExpand");
            if (oldPostion == position) {
                if (isExpand)  {
                    oldPostion = -1;
                }
                isExpand = !isExpand;
            }else{
                if (oldPostion != -1){
                    mLists.get(oldPostion).put("ItemExpand", false);
                    if (oldPostion < position){
                        location[1] -= mListViewAdapter.h2;
                        /*if (location[1] <= scrollviewY + Tools.getStatusBarHeight(this)){
                            location[1] = (int)(scrollviewY + Tools.getStatusBarHeight(this));
                        }*/
                    }
                }
                oldPostion = position;
                isExpand = true;

            }
            mLists.get(position).put("ItemExpand", isExpand);

            updateListView(isExpand, location[1], position);
        }
    }
}
