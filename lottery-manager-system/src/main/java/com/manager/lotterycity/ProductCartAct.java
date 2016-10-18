package com.manager.lotterycity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.manager.Interface.ICoallBack10;
import com.manager.Interface.ICoallBack2;
import com.manager.adapter.lotterycity.CartListViewAdapter;
import com.manager.bean.ProductBean;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.storage.DataStorage;
import com.manager.view.CartTipView1;
import com.manager.widgets.DeleteTipDialog;

import java.util.ArrayList;

/**
 * 即开票产品 购物车
 *
 * @author donghuiyang
 * @create time 2016/5/31 0031.
 */
public class ProductCartAct extends Activity implements View.OnClickListener/*, View.OnLayoutChangeListener */{

    //返回按钮
    private View backBtn;

    private View noDataTipView;

    //提示栏
    private CartTipView1 cartTipView;
    private TextView priceTv;
    private TextView goPayBtn;

    //Listview
    private ListView mListView;
    private CartListViewAdapter mListViewAdapter;
    //生成动态数组，加入数据
    ArrayList<ProductBean> mLists;

    /************************************************************************************************/

    //Activity最外层的Layout视图
    private View activityRootView;
    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_cart_layout);

        initData();

        initTopView();
        initCartTipView();
        initListView();
        updateNoDataTipView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //添加layout大小发生改变监听器
        //activityRootView.addOnLayoutChangeListener(this);

    }

    /*@Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        //现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起
        if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {

            //Toast.makeText(this, "监听到软键盘弹起...", Toast.LENGTH_SHORT).show();
            tipView.setVisibility(View.GONE);

        } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {

            //Toast.makeText(this, "监听到软件盘关闭...", Toast.LENGTH_SHORT).show();
            tipView.setVisibility(View.VISIBLE);

        }
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mLists = null;
    }

    /**
     * 设置标题
     */
    private void initTopView() {
        activityRootView = (View) findViewById(R.id.product_cart_rootview);
        //获取屏幕高度
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight / 3;

        ViewGroup topView = (ViewGroup) findViewById(R.id.product_cart_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.lottery_city_title3);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    private void initCartTipView() {
        cartTipView = (CartTipView1) findViewById(R.id.product_cart_cart_view);
        cartTipView.updateCartView(DataStorage.getCartListSize(), DataStorage.calculationNumInCart(), DataStorage.calculationPriceInCart());
        cartTipView.setonClick(new CartTipView1.ICoallBack() {
            @Override
            public void onOKClick() {
                //进入订单确认界面
                SysApplication.enterNext1(ProductCartAct.this, BillingOrderConfirmAct.class);
            }
        });
    }
    private void initData() {
        mLists = DataStorage.myCartLists;
    }

    /**
     * 初始化列表
     */
    private void initListView() {
        noDataTipView = (View) findViewById(R.id.product_cart_no_items_tipview);

        mListView = (ListView) findViewById(R.id.product_cart_listview);
        mListViewAdapter = new CartListViewAdapter(this, mLists);
        mListViewAdapter.setListView(mListView);
        mListView.setAdapter(mListViewAdapter);
        //删除操作回调
        mListViewAdapter.setonClick(new ICoallBack2() {

            @Override
            public void removeBtn(int position) {
                //删除提示
                showDeleteDialog(position);
            }
        });

        //修改数量回调
        mListViewAdapter.setonClick1(new ICoallBack10() {
            @Override
            public void onItemClick(int pos) {

            }

            @Override
            public void onAddNum(int value, int pos) {
                DataStorage.saveProductInCart(value, mLists.get(pos));

                updateCartTipView();
            }

            @Override
            public void onSubNum(int value, int pos) {
                DataStorage.subProductInCart(value, mLists.get(pos));

                updateCartTipView();
            }
        });
    }

    /**
     * 更新清单提示栏
     */
    private void updateCartTipView() {
        if (cartTipView != null){
            cartTipView.updateCartView(DataStorage.getCartListSize(), DataStorage.calculationNumInCart(), DataStorage.calculationPriceInCart());
        }
    }

    private void updateNoDataTipView(){
        if (mLists.size() <= 0) {
            noDataTipView.setVisibility(View.VISIBLE);
            cartTipView.setVisibility(View.GONE);
        }else{
            noDataTipView.setVisibility(View.GONE);
            cartTipView.setVisibility(View.VISIBLE);
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
                if (position != -1) {
                    //删除操作
                    DataStorage.removeProductInCart(position);

                    //无数据提示
                    updateNoDataTipView();
                    updateCartTipView();

                    //更新
                    mListViewAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void doCancel() {
                // TODO Auto-generated method stub
                dialog.closeDialog();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v) {
            //返回按钮
            SysApplication.backBtn(this, null);
        } /*else if (goPayBtn == v) {
            //确认订单 按钮
            SysApplication.enterNext1(ProductCartAct.this, BillingOrderConfirmAct.class);
        }*/
    }
}
