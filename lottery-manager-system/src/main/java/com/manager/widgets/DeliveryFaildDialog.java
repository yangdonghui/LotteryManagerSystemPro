package com.manager.widgets;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.manager.Interface.ICoallBack5;
import com.manager.adapter.DeliveryFaildListViewAdapter;
import com.manager.lotterypro.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 付款提示对话框
 * Created by Administrator on 2016/3/24 0024.
 */
public class DeliveryFaildDialog extends Dialog {

    Context context;
    private ClickListenerInterface clickListenerInterface;

    //按钮
    private Button cancelBtn, confirmBtn;
    private ListView mListView;
    private DeliveryFaildListViewAdapter mListViewAdapter;
    private List<String> mLists = new ArrayList<>();

    private int curIndex;
    private int position;



    public interface ClickListenerInterface {
        public void doConfirm(int pos, String str);
        public void doCancel();
    }

    public DeliveryFaildDialog(Context context, int position, int theme) {
        super(context, theme);
        this.context = context;
        this.position = position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.delivery_failure_tip);

        init();
        initView();
        initList();
    }

    // 初始化 设置所有checkbox都为未选择
    public void init() {
        String[] strs = context.getResources().getStringArray(R.array.manager_delivery_failure_spingarry);
        Collections.addAll(mLists, strs);
    }

    /**
     *初始化控件
     */
    private void initView() {
        confirmBtn = (Button) findViewById(R.id.delivery_failure_tip_btn1);
        confirmBtn.setOnClickListener(clickListener);
        cancelBtn = (Button) findViewById(R.id.delivery_failure_tip_btn2);
        cancelBtn.setOnClickListener(clickListener);
    }

    private void initList() {
        mListView = (ListView) findViewById(R.id.delivery_failure_tip_listview);
        mListViewAdapter = new DeliveryFaildListViewAdapter(context, mLists);
        mListViewAdapter.setonClick(new ICoallBack5() {
            @Override
            public void onClickCheckbox(boolean flag) {

            }

            @Override
            public void doConfirm(int pos) {
                curIndex = pos;
                mListViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void doFaild(int pos) {

            }
        });
        mListView.setAdapter(mListViewAdapter);
    }

    /**
     * 关闭处理
     */
    public void closeDialog() {
        mLists.clear();
        mLists = null;
        this.dismiss();
    }

    public void setClicklistener(ClickListenerInterface clickListenerInterface) {
        this.clickListenerInterface = clickListenerInterface;
    }

    private ClickListener clickListener = new ClickListener();
    private class ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if (cancelBtn == v){
                //关闭按钮
                clickListenerInterface.doCancel();
            }else if (confirmBtn == v){
                //确定 选择失败原因提交
                clickListenerInterface.doConfirm(position, mLists.get(curIndex));
            }
        }

    }

    ;

}
