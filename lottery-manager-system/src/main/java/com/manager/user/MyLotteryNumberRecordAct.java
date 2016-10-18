package com.manager.user;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.manager.adapter.user.LotteryNumberRecordListViewAdapter;
import com.manager.bean.UserBean;
import com.manager.bean.UserSelectNumberBean;
import com.manager.common.Tools;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.storage.DataStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的彩票 选号列表界面
 * @author donghuiyang
 * @create time 2016/6/14 0014.
 */
public class MyLotteryNumberRecordAct extends Activity implements View.OnClickListener, View.OnLayoutChangeListener{
    //用户数据
    private UserBean userBean = SysApplication.userBean;

    //返回按钮
    private View backBtn;
    //委托按钮
    private Button entrustBtn;

    private View tipView;

    //list控件
    private ListView mListView;
    private LotteryNumberRecordListViewAdapter mListViewAdapter;
    //生成动态数组，加入数据
    private ArrayList<UserSelectNumberBean> mLists = DataStorage.myLotteryNumberLists;//Constants.SelectNumberLists;

    //选中的数据
    private List<UserSelectNumberBean> mSelectLists = new ArrayList<>();

    //Activity最外层的Layout视图
    private View activityRootView;
    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_lottery_numbers_record_layout);

        initTopView();
        initListView();
        initView();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //隐藏键盘
        Tools.hideSoftInput(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mLists = null;
        mSelectLists.clear();
        mSelectLists = null;
    }

    @Override
    protected void onResume() {
        super.onResume();

        //添加layout大小发生改变监听器
        activityRootView.addOnLayoutChangeListener(this);
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        //现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起
        if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {
            //Toast.makeText(this, "监听到软键盘弹起...", Toast.LENGTH_SHORT).show();


        } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {
            //Toast.makeText(this, "监听到软件盘关闭...", Toast.LENGTH_SHORT).show();

            /*隐藏软键盘*/
            EditText v1 = (EditText)this.getCurrentFocus();
            if (v1 != null){
                v1.setFocusable(false);
                v1.setCursorVisible(false);
                if (v1.getText().toString().equals("")){
                    v1.setText("1");
                }

                //Tools.hideSoftInput(this);
            }
        }
    }

    /**
     * 设置标题
     */
    private void initTopView() {
        ViewGroup topView = (ViewGroup) findViewById(R.id.my_lottery_number_record_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.me_item_str_2);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    private void initView() {
        activityRootView = (View) findViewById(R.id.my_lottery_number_record_rootview);

        entrustBtn = (Button) findViewById(R.id.my_lottery_number_record_btn);
        entrustBtn.setOnClickListener(this);

        tipView = (View) findViewById(R.id.my_lottery_number_record_listview_no);
        updateTipView();
    }

    /**
     * 更新选择按钮
     */
    private void updateEntrustBtn(){

    }

    private void updateTipView() {
        if (mLists != null && mLists.size() > 0){
            tipView.setVisibility(View.GONE);
        }else{
            tipView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 初始化数据列表
     */
    private void initListView() {
        mListView = (ListView) findViewById(R.id.my_lottery_number_record_listview);
        mListViewAdapter = new LotteryNumberRecordListViewAdapter(this, mLists);
        mListViewAdapter.setListView(mListView);
        mListView.setAdapter(mListViewAdapter);
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v) {
            //返回按钮
            SysApplication.backBtn(this, null);
        }
    }
}
