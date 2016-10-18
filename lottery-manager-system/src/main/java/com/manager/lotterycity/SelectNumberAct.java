package com.manager.lotterycity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.manager.Interface.ICoallBack11;
import com.manager.Interface.ICoallBack2;
import com.manager.Interface.ICoallBack22;
import com.manager.adapter.lotterycity.SelectNumberListViewAdapter;
import com.manager.adapter.lotterycity.SsqPostzoneBallsAdapter;
import com.manager.bean.NoteLotterybean;
import com.manager.bean.NumberBean;
import com.manager.bean.WidgetBean1;
import com.manager.common.Tools;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.storage.DataStorage;
import com.manager.view.SelectNumTipView;
import com.manager.widgets.DeleteTipDialog;
import com.manager.widgets.ScrollDisabledListView;

import java.util.ArrayList;
import java.util.Collections;

import sun.lottery.conf.util.Double;


/**
 * 选号投注界面
 * @author donghuiyang
 * @create time 2016/6/3 0003.
 */
public class SelectNumberAct extends Activity implements View.OnClickListener{
    //数据
    private WidgetBean1 widgetBean1;
    private int lotteryType = 0;
    private String lotteryName = "双色球";

    //返回按钮
    private View backBtn;

    private View rootView;

    //选号提示框
    private SelectNumTipView selectTipView;

    //委托投注按钮
    private Button entrustBtn, saveBtn;
    //增添号码 按钮
    private View addBtn;

    //工具栏按钮
    private View toolBtn1, toolBtn2, toolBtn3, toolBtn4;

    private GridView redGridView;
    private SsqPostzoneBallsAdapter redAdapter;
    private String[] redBallNums;

    private GridView blueGridView;
    private SsqPostzoneBallsAdapter blueAdapter;
    private String[] blueBallNums;

    private ArrayList blueBallNumList = new ArrayList();
    private ArrayList blueBallNumTmpList = new ArrayList();

    private ArrayList redBallNumList = new ArrayList();
    private ArrayList redBallNumTmpList = new ArrayList();

    private int redSelectNumeMin = 0, blueSelectNumMin = 0;

    //滚动view
    private ScrollView scrollView;

    //选号数据列表
    private ScrollDisabledListView mListView;
    private SelectNumberListViewAdapter mListViewAdapter;
    private ArrayList<NoteLotterybean> mLists = new ArrayList<>();
    //选号列表 无数据提示
    private View selectListTipView;
    private View allRemoveBtn;
    private View bottomBtnView;

    //listview 的itemview在屏幕内的位置
    int[] location = new int[2];
    //当前屏幕的高度
    private int screenHeight = 0, scrollviewY;
    private int h1 = 0;
    //scrollview滚动到指定位置 异步消息处理
    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_number_layout);

        Intent intent = getIntent();
        widgetBean1 = (WidgetBean1) (intent.getSerializableExtra("data"));
        lotteryType = Integer.valueOf(widgetBean1.getId());
        lotteryName = widgetBean1.getText();
        redSelectNumeMin = widgetBean1.getNum1();
        blueSelectNumMin = widgetBean1.getNum2();

        redBallNums = new String[widgetBean1.getNum3()];
        blueBallNums = new String[widgetBean1.getNum4()];

        screenHeight = Tools.getWindowHeight(this);

        initTopView();
        initView();
        initInfoView();
        initListView();

        loadRedBallNums();
        initRedView();

        loadBlueBallNums();
        initBlueView();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //隐藏软键盘
        Tools.hideSoftInput(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        redBallNumList.clear();
        redBallNumList = null;

        blueBallNumList.clear();
        blueBallNumList = null;

        redBallNums = null;
        blueBallNums = null;

        if (mLists != null) {
            mLists.clear();
            mLists = null;
        }

        redAdapter = null;
        blueAdapter = null;
        mListViewAdapter = null;
    }

    /**
     * 设置标题
     */
    private void initTopView() {
        ViewGroup topView = (ViewGroup) findViewById(R.id.select_number_topview);
        topView.measure(0,0);
        scrollviewY = topView.getMeasuredHeight();
        h1 = (int)(scrollviewY + Tools.getStatusBarHeight(this));

        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(lotteryName + getResources().getString(R.string.betting_str10));
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    /**
     * 获取控件对象
     */
    private void initView() {
        rootView = (View) findViewById(R.id.select_number_rootview);
        scrollView = (ScrollView) findViewById(R.id.select_num_scrollview);
        selectTipView = (SelectNumTipView) findViewById(R.id.select_num_tip_include);

        //委托投注按钮
        entrustBtn = (Button) findViewById(R.id.select_number_bottom_btn2);
        entrustBtn.setOnClickListener(this);

        //保存选号按钮
        saveBtn = (Button) findViewById(R.id.select_number_bottom_btn1);
        saveBtn.setOnClickListener(this);

        //工具栏按钮
        toolBtn1 = (View)findViewById(R.id.select_number_tool_include).findViewById(R.id.select_number_tool_btn1);
        toolBtn1.setOnClickListener(this);
        toolBtn2 = (View)findViewById(R.id.select_number_tool_include).findViewById(R.id.select_number_tool_btn2);
        toolBtn2.setOnClickListener(this);
        toolBtn3 = (View)findViewById(R.id.select_number_tool_include).findViewById(R.id.select_number_tool_btn3);
        toolBtn3.setOnClickListener(this);
        toolBtn4 = (View)findViewById(R.id.select_number_tool_include).findViewById(R.id.select_number_tool_btn4);
        toolBtn4.setOnClickListener(this);

        //增添新号
        addBtn = (View) findViewById(R.id.select_number_add_btn);
        addBtn.setOnClickListener(this);

        //底部保存和委托按钮
        bottomBtnView = (View) findViewById(R.id.select_number_bottom_view);
        //全部删除按钮
        allRemoveBtn = (View) findViewById(R.id.select_number_remove_all_btn);
        allRemoveBtn.setOnClickListener(this);
        selectListTipView = (View) findViewById(R.id.select_number_tv3);
        selectListTipView();
    }

    private void initInfoView() {
        ((TextView) findViewById(R.id.select_number_tv1)).setText(widgetBean1.getText());
        ((TextView) findViewById(R.id.select_number_tv4)).setText(widgetBean1.getInfo1());
        ((TextView) findViewById(R.id.select_number_tv5)).setText(widgetBean1.getInfo2());
    }

    /**
     * 初始化选号列表控件
     */
    private void initListView() {
        mListView = (ScrollDisabledListView) findViewById(R.id.select_number_listview);
        mListViewAdapter = new SelectNumberListViewAdapter(this, mLists);
        mListView.setAdapter(mListViewAdapter);

        mListViewAdapter.setonClick(new ICoallBack2() {
            @Override
            public void removeBtn(int position) {
                //删除提示
                showDeleteDialog(position);
            }
        });
    }

    /**
     * 删除某项已选的号码组
     */
    private void removeSelectItem(int position) {
        if (position >= 0){
            if (mLists != null && mLists.size() > position) {
                mLists.remove(position);
                mListViewAdapter.notifyDataSetChanged();
            }
        }

        selectListTipView();
    }

    /**
     * 全部删除 选号列表
     */
    private void removeAllSelectItems() {
        if (mLists != null && mLists.size() > 0){
            mLists.clear();
            mListViewAdapter.notifyDataSetChanged();

            selectListTipView();
        }
    }

    /**
     * 更新选号列表区域 提示view
     */
    private void selectListTipView() {
        if (mLists == null || mLists.size() <= 0){
            //选号列表为空
            if (selectListTipView.getVisibility() != View.VISIBLE){
                selectListTipView.setVisibility(View.VISIBLE);
            }

            if (allRemoveBtn.getVisibility() != View.GONE){
                allRemoveBtn.setVisibility(View.GONE);
            }

            if (bottomBtnView.getVisibility() != View.GONE){
                bottomBtnView.setVisibility(View.GONE);
            }
        }else if (mLists != null && mLists.size() > 0){
            if (selectListTipView.getVisibility() != View.GONE){
                selectListTipView.setVisibility(View.GONE);
            }

            if (allRemoveBtn.getVisibility() != View.VISIBLE){
                allRemoveBtn.setVisibility(View.VISIBLE);
            }

            if (bottomBtnView.getVisibility() != View.VISIBLE){
                bottomBtnView.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * 动态加载红球
     */
    private void loadRedBallNums() {
        for (int i = 0; i < redBallNums.length; i++) {
            if (i >= 9) {
                redBallNums[i] = String.valueOf(i + 1);
            } else {
                redBallNums[i] = "0" + (i + 1);
            }
        }
    }

    /**
     * 取消所选的红球，redBallNumList移除此球号码
     *
     * @param ballNum
     */
    private void cutRedNumList(String ballNum) {
        for (int i = 0; i < redBallNumList.size(); i++) {
            if (redBallNumList.get(i).equals(ballNum)) {
                redBallNumList.remove(i);
            }
        }
    }

    /**
     * 添加红球选区
     */
    private void initRedView() {
        redGridView = (GridView) findViewById(R.id.Ssq_redBallLayout);
        redAdapter = new SsqPostzoneBallsAdapter(this, R.layout.white_number, redBallNums, redBallNumList, 1);
        redAdapter.setGridView(redGridView);
        redGridView.setAdapter(redAdapter);

        redAdapter.setonClick(new ICoallBack11() {
            @Override
            public void addRedView(String value) {
                //添加红球
                // 将选择的号码加入一个集合，以便于好管理
                if (!redBallNumList.contains(value)) {
                    redBallNumList.add(value);
                    //redAdapter.notifyDataSetChanged();

                    dealWithSelectRedList(value, false);
                }
            }

            @Override
            public void removeRedView(String value) {
                //删除选中的红球
                cutRedNumList(value);
                //redAdapter.notifyDataSetChanged();

                dealWithSelectRedList(value, true);
            }
        });
    }

    /**
     * 动态加载蓝球
     */
    private void loadBlueBallNums() {
        for (int i = 0; i < blueBallNums.length; i++) {
            if (i >= 9) {
                blueBallNums[i] = String.valueOf(i + 1);
            } else {
                blueBallNums[i] = "0" + (i + 1);
            }
        }
    }
    /**
     * 取消所选的蓝球，blueBallNumList移除此球号码
     *
     * @param ballNum
     */
    private void cutBlueNumList(String ballNum) {
        for (int i = 0; i < blueBallNumList.size(); i++) {
            if (blueBallNumList.get(i).equals(ballNum)) {
                blueBallNumList.remove(i);
            }
        }
    }
    /**
     * 添加蓝球选区
     */
    private void initBlueView() {
        blueGridView = (GridView) findViewById(R.id.Ssq_blueBallLayout);
        blueAdapter = new SsqPostzoneBallsAdapter(this, R.layout.white_number, blueBallNums, blueBallNumList, 2);
        blueAdapter.setGridView(blueGridView);
        blueGridView.setAdapter(blueAdapter);

        blueAdapter.setonClick(new ICoallBack22() {
            @Override
            public void addBlueView(String value) {
                //添加蓝球
                if (!blueBallNumList.contains(value)) {
                    blueBallNumList.add(value);
                    //blueAdapter.notifyDataSetChanged();

                    //选号提示处理
                    dealWithSelectBlueList(value, false);
                }
            }

            @Override
            public void removeBlueView(String value) {
                //删除选中的蓝球
                cutBlueNumList(value);
                //blueAdapter.notifyDataSetChanged();

                //选号提示处理
                dealWithSelectBlueList(value, true);
            }
        });


    }

    /**
     * 选号计算 金额
     * @param value
     * @param isRemove
     */
    private void dealWithSelectBlueList(String value, boolean isRemove) {
        if (isRemove){
            blueBallNumTmpList.remove(Integer.valueOf(value));
        }else{
            blueBallNumTmpList.add(Integer.valueOf(value));
        }
        if (redBallNumTmpList.size() >= redSelectNumeMin  && blueBallNumTmpList.size() >= blueSelectNumMin){
            //选号信息提示框显示
            int[] a = Double.DoubleMethod(redBallNumTmpList, blueBallNumTmpList);
            Log.e("", "");

            if (a != null){
                selectTipView.updateTv(a[0], a[1]);
                selectTipView.updateSelectTipViewAnim(1);
                a = null;
            }


        }else{
            //选号信息提示框隐藏
            selectTipView.updateSelectTipViewAnim(-1);
        }
    }
    private void dealWithSelectRedList(String value, boolean isRemove) {
        if (isRemove){
            redBallNumTmpList.remove(Integer.valueOf(value));
        }else{
            redBallNumTmpList.add(Integer.valueOf(value));
        }
        if (redBallNumList.size() >= redSelectNumeMin  && blueBallNumList.size() >= blueSelectNumMin){
            //选号信息提示框显示
            int[] a = Double.DoubleMethod(redBallNumTmpList, blueBallNumTmpList);
            Log.e("", "");

            if (a != null){
                selectTipView.updateTv(a[0], a[1]);
                selectTipView.updateSelectTipViewAnim(1);

                a = null;
            }

        }else{
            //选号信息提示框隐藏
            selectTipView.updateSelectTipViewAnim(-1);
        }
    }

    /**
     * 清空所选所有与号码
     */
    private void clearSelectedDatas() {
        if (redBallNumList != null && redBallNumList.size() > 0){
            Tools.clearLists(redBallNumList);
            Tools.clearLists(redBallNumTmpList);
            redAdapter.clearSelectNumbers();
        }
        if (blueBallNumList != null && blueBallNumList.size() > 0){
            Tools.clearLists(blueBallNumList);
            Tools.clearLists(blueBallNumTmpList);
            blueAdapter.clearSelectNumbers();

            //隐藏提示框
            selectTipView.updateSelectTipViewAnim(-1);
        }
    }

    private void addSelectNumbers() {

        if (redBallNumList.size() < redSelectNumeMin){
            Tools.showToast(this, getResources().getString(R.string.select_tip_str1));
            return;
        }else if (blueBallNumList.size() < blueSelectNumMin){
            Tools.showToast(this, getResources().getString(R.string.select_tip_str2));
            return;
        }

        if (redBallNumList != null && redBallNumList.size() >= redSelectNumeMin
                && blueBallNumList != null && blueBallNumList.size() >= blueSelectNumMin){

            //添加到数据
            int doubleSingle = 0;
            int size = mLists.size();
            if (redBallNumList.size() > redSelectNumeMin || blueBallNumList.size() > blueSelectNumMin){
                //复式
                doubleSingle = 1;
            }

            Collections.sort(redBallNumList);
            Collections.sort(blueBallNumList);

            NoteLotterybean att = new NoteLotterybean(String.valueOf(size+1), doubleSingle, lotteryType, lotteryName, 1, 2, "");
            att.setNumbers(new NumberBean("", redBallNumList, blueBallNumList));

            mLists.add(0, att);
            mListViewAdapter.notifyDataSetChanged();
            selectListTipView();
            clearSelectedDatas();

            View view2 = mListViewAdapter.getView(0, null, mListView);
            view2.measure(0,0);
            int curListH = view2.getMeasuredHeight();

            //获取itemview在屏幕内的坐标
            mListView.getLocationOnScreen(location);
            updateScrollView(location[1], curListH);

            Log.e("","");
        }
    }

    /**
     * 更新scrollview的位置
     * @param y
     */
    private void updateScrollView(int y, int dis) {
        int tmp = 0;
        if (y + dis >= screenHeight){
            tmp = y + Tools.px2dip(this, dis) - screenHeight;
        }

        if (tmp > 0){
            final int tmp1 = tmp;
            mHandler.post(new Runnable() {
                public void run() {
                    scrollView.smoothScrollBy(0, tmp1);
                }
            });
        }
    }

    /**
     * 保存选号 到 “我的彩票”
     */
    private void saveSelectNumbers() {
        DataStorage.saveSelectNumbers(lotteryType, lotteryName, "", mLists);

        removeAllSelectItems();
        selectListTipView();

        Toast.makeText(this, "已保存，可以 <我-我的彩票> 中查看",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //返回按钮
            SysApplication.backBtn(this, null);
        }else if (entrustBtn == v){
            //委托投注按钮
            Intent intent = new Intent(this, EntrustBetAct.class);
            startActivity(intent);
        }else if (saveBtn == v){
            //保存选号按钮
            saveSelectNumbers();
        }else if(addBtn == v) {
            //增添新号
            addSelectNumbers();
        }else if (allRemoveBtn == v){
            //全部删除按钮
            showDeleteDialog(-1);
        }else{
            if (toolBtn4 == v){
                //清空选号
                clearSelectedDatas();

            }else if (toolBtn3 == v){
                //随机号码
                randNumbers();
                Log.e("","");

            }else if (toolBtn2 == v){
                //历史排除
            }else if (toolBtn1 == v){
                //拖胆选号
            }
        }
    }

    /**
     * 随机号码
     */
    private void randNumbers() {
        //清空之前选号
        clearSelectedDatas();

        Double.CompareDouble(redBallNumTmpList, blueBallNumTmpList);

        if (mLists != null && mLists.size() > 0){
            int count = 0;
            ArrayList<Integer> newList1, newList2;
            while (mLists.size() != count){
                if (redBallNumTmpList != null && redBallNumTmpList.size() > 0 && blueBallNumTmpList != null && blueBallNumTmpList.size() > 0){
                    for (int i=0;i<mLists.size();i++){
                        if (mLists.get(i).getDoubleSingleType() == 1){
                            //复式不做比较
                            continue;
                        }
                        //比较是否已存在
                        newList1 = Tools.convertArrayList(mLists.get(i).getNumbers().getNumbersList1());
                        newList2 = Tools.convertArrayList(mLists.get(i).getNumbers().getNumbersList2());

                        if (redBallNumTmpList.equals(newList1) && blueBallNumTmpList.equals(newList2)){
                            //相同 重新随机号码
                            redBallNumTmpList.clear();
                            blueBallNumTmpList.clear();
                            Double.CompareDouble(redBallNumTmpList, blueBallNumTmpList);
                            break;
                        }else if (!newList1.equals(redBallNumTmpList) || !newList2.equals(blueBallNumTmpList)){
                            //不等 则退出循环
                            count ++;
                        }
                    }
                }
            }
        }

        if (redBallNumTmpList != null && redBallNumTmpList.size() > 0 && blueBallNumTmpList != null && blueBallNumTmpList.size() > 0){
            redBallNumList.addAll(Tools.convertIntegerArrayList(redBallNumTmpList));
            blueBallNumList.addAll(Tools.convertIntegerArrayList(blueBallNumTmpList));

            redAdapter.updateAllSelectNumberView(redBallNumTmpList);
            blueAdapter.updateAllSelectNumberView(blueBallNumTmpList);

            //选号信息提示框显示
            int[] a = Double.DoubleMethod(redBallNumTmpList, blueBallNumTmpList);
            Log.e("", "");

            if (a != null){
                selectTipView.updateTv(a[0], a[1]);
                selectTipView.updateSelectTipViewAnim(1);

                a = null;
            }
        }
    }

    /**
     * 删除提示窗口
     * @param position
     */
    private void showDeleteDialog(final int position) {
        final DeleteTipDialog dialog = new DeleteTipDialog(this, R.style.CustomDialog);
        dialog.show();

        dialog.setClicklistener(new DeleteTipDialog.ClickListenerInterface() {
            @Override
            public void doConfirm() {
                // TODO Auto-generated method stub
                dialog.closeDialog();

                //删除操作
                if (position != -1){
                    //删除选号
                    removeSelectItem(position);
                }else{
                    //全部删除按钮
                    removeAllSelectItems();
                }
            }

            @Override
            public void doCancel() {
                // TODO Auto-generated method stub
                dialog.closeDialog();
            }
        });
    }
}
