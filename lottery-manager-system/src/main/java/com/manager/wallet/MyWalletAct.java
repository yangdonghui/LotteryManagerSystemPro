package com.manager.wallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.manager.Interface.ICoallBack;
import com.manager.adapter.CommonAdapter;
import com.manager.adapter.ViewHolder;
import com.manager.bean.UserBean;
import com.manager.bean.WidgetBean1;
import com.manager.common.Constants;
import com.manager.helper.UserHelper;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.personals.ContactsAct;
import com.manager.view.WalletMenuView;

import java.util.ArrayList;

/**
 * 我的钱包首界面
 * @author donghuiyang
 * @create time 2016/4/20 0020.
 */
public class MyWalletAct extends Activity implements View.OnClickListener{
    //用户数据
    private UserBean userBean = SysApplication.userBean;

    //返回按钮
    private View backBtn;
    //历史记录按钮
    private View recordBtn;

    private WalletMenuView menuView;
    private ArrayList<WidgetBean1> menuLists;

    private GridView mGridView;
    private CommonAdapter<WidgetBean1> mGridViewAdapter;
    private ArrayList<WidgetBean1> mLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_wallet_layout);

        initData();

        initTopView();
        initView();
        initGridView();
    }

    /**
     * 设置标题
     */
    private void initTopView() {
        ViewGroup topView = (ViewGroup) findViewById(R.id.my_wallet_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.me_item_str_0);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        menuView = (WalletMenuView) findViewById(R.id.my_wallet_menu_view);
        menuView.updateMenu(menuLists);
        menuView.setonClick(new ICoallBack() {
            @Override
            public void onItemClick(int pos) {
                Class next = null;
                switch (pos){
                    case 0:{
                        //付款 或 收款
                        if (userBean != null){
                            if (userBean.getUserType() == UserHelper.LotteryUser){
                                //彩民 付款
                                next = WalletPayingAct.class;
                            }else {
                                //站点 收款
                                next = WalletReceiptAct.class;
                            }
                        }
                    }
                    break;
                    case 2:{
                        //银行卡管理
                        next = BanksManagerAct.class;
                    }
                    break;
                    default:
                        break;
                }

                if (next != null){
                    SysApplication.enterNext1(MyWalletAct.this, next);
                }
            }
        });

        recordBtn = (View) findViewById(R.id.my_wallet_history_btn);
        recordBtn.setOnClickListener(this);
    }

    /**
     * 初始化gridview数据
     */
    private void initData() {
        if (userBean != null){
            if (userBean.getUserType() == UserHelper.LotteryUser){
                //彩民
                mLists = Constants.LotteryWalletGridViewLists;
                menuLists = Constants.WalletMenus1;
            }else{
                mLists = Constants.BettingshopWalletGridViewLists;
                menuLists = Constants.WalletMenus2;
            }
        }
    }

    /**
     * 初始化gridview控件
     */
    private void initGridView() {
        mGridView = (GridView) findViewById(R.id.my_wallet_gridView);
        mGridViewAdapter = new CommonAdapter<WidgetBean1>(this, mLists, R.layout.grid_item_1) {
            @Override
            public void convert(ViewHolder helper, WidgetBean1 item) {
                helper.setImageResource(R.id.grid_view_5_img, item.getIcon());
                helper.setText(R.id.grid_view_1_tv, item.getText());
            }
        };
        mGridView.setAdapter(mGridViewAdapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Class next = null;
                switch (position){
                    case 0:{
                        //充值
                        next = WalletPayAct.class;
                    }
                    break;
                    case 1:{
                        //提现
                        next = WalletWithdrawAct.class;
                    }
                    break;
                    case 2:{
                        //转账
                        next = ContactsAct.class;
                    }
                    break;
                    case 3:{
                        if (userBean.getUserType() == UserHelper.BettingShopUser){
                            //站点 付款
                            next = WalletPayingAct.class;
                        }
                    }
                    break;
                    case 4:{
                        //站点 缴费
                        next = BettingShopPaymentAct.class;
                    }
                    break;
                }

                if (next != null) {
                    if (next == ContactsAct.class){
                        Intent intent = new Intent(MyWalletAct.this, next);
                        intent.putExtra("pageType", 1);
                        startActivity(intent);
                    }else{
                        SysApplication.enterNext1(MyWalletAct.this, next);
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
        }else if (recordBtn == v){
            //查看交易记录
            SysApplication.enterNext1(this, WalletTransactionHistoryAct.class);
        }
    }
}
