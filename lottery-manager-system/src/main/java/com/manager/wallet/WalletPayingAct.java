package com.manager.wallet;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.manager.Interface.ICoallBack3;
import com.manager.Interface.ICoallBack4;
import com.manager.bean.BankBean;
import com.manager.common.Constants;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.view.CommonPopupWindow1;
import com.manager.view.PaymentWayTipView;

import java.util.ArrayList;
import java.util.List;

/**
 * 钱包 付款界面
 * @author donghuiyang
 * @create time 2016/4/20 0020.
 */
public class WalletPayingAct extends Activity implements View.OnClickListener{
    //返回按钮
    private View backBtn;

    private View rootView;

    //支付方式view
    private PaymentWayTipView payTipView;

    CommonPopupWindow1 mMenuView;
    List<BankBean> mLists = new ArrayList<>();
    List<String> data = new ArrayList<String>();

    //当前选择的支付方式的索引值
    private int curBankIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_paying_layout);

        initData();

        initTopView();
        initView();
    }

    @Override
    protected void onDestroy() {
        mLists.clear();
        mLists = null;

        data.clear();
        data = null;

        super.onDestroy();
    }

    /**
     * 设置标题
     */
    private void initTopView() {
        ViewGroup topView = (ViewGroup) findViewById(R.id.wallet_paying_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(Constants.PayIconTexts[0]);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    private void initView() {
        rootView = (View) findViewById(R.id.wallet_paying_rootview);

        payTipView = (PaymentWayTipView) findViewById(R.id.wallet_paying_pay_way_tip_view);
        payTipView.setICoallBack(new ICoallBack4() {
            @Override
            public void onClick() {
                //弹出支付列表
                popWindow(curBankIndex);
            }
        });
    }

    private void initData() {
        mLists.addAll(Constants.BankLists);
        BankBean bank = new BankBean("-1", 2, "", "零钱", null, -1, -1, -1);
        mLists.add(0, bank);

        for (int i=0;i<mLists.size();i++){
            BankBean att = mLists.get(i);
            if (att != null) {
                if (att.getNumber() != null){
                    data.add(att.getName() + att.getTypeInfo() + "(" + att.getNumber().subSequence(att.getNumber().length() - 4, att.getNumber().length()) + ")");
                }else{
                    data.add(att.getName() + att.getTypeInfo());
                }
            }
        }
    }

    private void popWindow(int curIndex) {
        String [] string = new String [data.size()] ;
        data.toArray(string);

        //实例化
        mMenuView = new CommonPopupWindow1(this, string , curIndex);
        //显示窗口
        mMenuView.showAtLocation(rootView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
        mMenuView.setonClick(new ICoallBack3() {
            @Override
            public void doCancel() {
                mMenuView.dismiss();
            }

            @Override
            public void doConfirm(int pos) {
                mMenuView.dismiss();

                curBankIndex = pos;
                payTipView.updateInfo(mLists.get(pos).getSmallIcon(), data.get(pos));
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
