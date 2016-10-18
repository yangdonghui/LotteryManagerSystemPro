package com.manager.wallet;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.manager.adapter.user.BankManagerListViewAdapter;
import com.manager.bean.BankBean;
import com.manager.common.Constants;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;

import java.util.ArrayList;

/**
 * 银行卡管理界面
 * @author donghuiyang
 * @create time 2016/4/20 0020.
 */
public class BanksManagerAct extends Activity implements View.OnClickListener{
    //返回按钮
    private View backBtn;

    //添加银行卡按钮
    private View addBankBtn;

    //listview
    private ListView mListView;
    private BankManagerListViewAdapter mAdapter;
    private ArrayList<BankBean> mLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_banks_manager_layout);

        initData();

        initTopView();
        initView();
        initListView();

        /*LetterSpacingTextView textView = new LetterSpacingTextView(this);
        textView.setLetterSpacing(10); //参数为 float 类型。可另设其他值如 0 或者默认值 LetterSpacingTextView.LetterSpacing.NORMAL
        textView.setText("My text");
        //Add the textView in a layout, for instance:
        ((LinearLayout) findViewById(R.id.wallet_banks_manager_view)).addView(textView);*/
    }

    @Override
    protected void onDestroy() {
        mLists = null;

        super.onDestroy();
    }

    /**
     * 设置标题
     */
    private void initTopView() {
        ViewGroup topView = (ViewGroup) findViewById(R.id.wallet_banks_manager_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(Constants.PayIconTexts[3]);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    private void initView() {
        addBankBtn = (View) findViewById(R.id.wallet_banks_manager_add_btn);
        addBankBtn.setOnClickListener(this);
    }

    private void initData() {
        mLists = Constants.BankLists;
    }

    private void initListView() {
        mListView = (ListView) findViewById(R.id.wallet_banks_manager_listview);
        updateListView();
    }

    private void updateListView() {
        if (mLists != null && mLists.size() > 0){
            mAdapter = new BankManagerListViewAdapter(this, mLists);
            mListView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //返回按钮
            SysApplication.backBtn(this, null);
        }else if (addBankBtn == v){
            //添加银行卡按钮
            SysApplication.enterNext1(this, AddBankAct.class);
        }
    }
}
