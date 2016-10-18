package com.manager.lotterycity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.manager.Interface.ICoallBack10;
import com.manager.adapter.lotterycity.BillingGridViewAdapter;
import com.manager.adapter.lotterycity.ProductListViewAdapter;
import com.manager.bean.ProductBean;
import com.manager.common.Constants;
import com.manager.common.Tools;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.storage.DataStorage;
import com.manager.view.CartTipView;
import com.manager.view.SearchView;

import java.util.ArrayList;

/**
 * 即开票预订 产品列表界面（图文模式）
 * @author donghuiyang
 * @create time 2016/4/20 0020.
 */
public class BillingListsAct extends Activity implements View.OnClickListener/*, View.OnLayoutChangeListener*/{
    //当前模式
    private int curMode = 0;//默认列表模式

    //返回按钮
    private View backBtn;

    //搜索栏
    private SearchView searchView;

    //模式切换按钮
    private ImageView modeBtn;
    //历史记录
    private View historyBtn;

    //滚动view
    private ScrollView mScrollView;

    //购物车提示栏
    private CartTipView cartTipView;

    //图文模式 gridview
    private GridView mGridView;
    private BillingGridViewAdapter mGridViewAdapter;
    //列表模式 listview
    private View listviewParent;
    private ListView mListView;
    private ProductListViewAdapter mListViewAdapter;

    //生成动态数组，加入数据
    ArrayList<ProductBean> mLists;

    //Activity最外层的Layout视图
    private View activityRootView;
    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.billing_lists_layout);

        initData();

        initTopView();
        initView();
        initCartTipView();
        initGridOrListView();
        updateDataInView();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //隐藏键盘
        Tools.hideSoftInput(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.e("", "");
        //添加layout大小发生改变监听器
        //activityRootView.addOnLayoutChangeListener(this);

        //更新数据列表
        updateView();
    }

    /*@Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        //现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起
        if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {

            //Toast.makeText(this, "监听到软键盘弹起...", Toast.LENGTH_SHORT).show();
            cartTipView.setVisibility(View.GONE);

        } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {

            //Toast.makeText(this, "监听到软件盘关闭...", Toast.LENGTH_SHORT).show();
            cartTipView.setVisibility(View.VISIBLE);
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
        activityRootView = (View) findViewById(R.id.billing_lists_rootview);
        //获取屏幕高度
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight / 3;

        ViewGroup topView = (ViewGroup) findViewById(R.id.billing_lists_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.lottery_city_title1);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    /**
     * 获取控件对象
     */
    private void initView() {
        mScrollView = (ScrollView) findViewById(R.id.billing_lists_scrollview);

        //模式切换按钮
        modeBtn = (ImageView) findViewById(R.id.billing_list_mode_btn);
        modeBtn.setOnClickListener(this);

        //历史记录
        historyBtn = (View) findViewById(R.id.billing_lists_history_btn);
        historyBtn.setOnClickListener(this);

        //搜索栏
        searchView = (SearchView) findViewById(R.id.billing_lists_searchview);
        searchView.setBgColor(R.color.bg_color_4);
        searchView.setiCoallBack(new SearchView.ICoallBack() {
            @Override
            public void onClick() {
                //筛选按钮
                SysApplication.enterNext1(BillingListsAct.this, SearchProductAct.class);
            }
        });
    }

    /**
     * 初始化购物车提示栏
     */
    private void initCartTipView() {
        cartTipView = (CartTipView) findViewById(R.id.billing_lists_cart_view_include);
        cartTipView.updateCartView(DataStorage.calculationNumInCart(), DataStorage.calculationPriceInCart());
        cartTipView.setonClick(new CartTipView.ICoallBack() {
            @Override
            public void onCartClick() {
                //进入购物车界面
                SysApplication.enterNext1(BillingListsAct.this, ProductCartAct.class);
            }

            @Override
            public void onOKClick() {
                //进入购物车界面
                SysApplication.enterNext1(BillingListsAct.this, BillingOrderConfirmAct.class);
            }
        });
    }

    /**
     * 初始化列表数据
     */
    private void initData() {
        mLists = Constants.BillingLists;
        checkProductLists();
    }

    private void initGridOrListView() {
        //图文模式
        mGridView = (GridView) findViewById(R.id.billing_lists_gridview);
        mGridViewAdapter = new BillingGridViewAdapter(this, mLists);
        mGridViewAdapter.setGridView(mGridView);
        mGridView.setAdapter(mGridViewAdapter);
        mGridViewAdapter.setonClick(new ICoallBack10() {
            @Override
            public void onItemClick(int pos) {
                //进入即开票 详细界面
                SysApplication.enterNext1(BillingListsAct.this, BillingDetailAct.class);
            }

            @Override
            public void onAddNum(int value,int pos) {
                DataStorage.saveProductInCart(value,mLists.get(pos));
                cartTipView.updateCartView(DataStorage.calculationNumInCart(), DataStorage.calculationPriceInCart());
            }

            @Override
            public void onSubNum(int value,int pos) {
                DataStorage.subProductInCart(value,mLists.get(pos));
                cartTipView.updateCartView(DataStorage.calculationNumInCart(), DataStorage.calculationPriceInCart());
            }
        });

        //列表模式
        listviewParent = (View) findViewById(R.id.billing_lists_listview_parent);
        mListView = (ListView) findViewById(R.id.billing_lists_listview);
        mListViewAdapter = new ProductListViewAdapter(this, mLists);
        mListViewAdapter.setListView(mListView);
        mListView.setAdapter(mListViewAdapter);
        mListViewAdapter.setonClick(new ICoallBack10() {
            @Override
            public void onItemClick(int pos) {
                //进入即开票 详细界面
                SysApplication.enterNext1(BillingListsAct.this, BillingDetailAct.class);
            }

            @Override
            public void onAddNum(int value, int pos) {
                DataStorage.saveProductInCart(value, mLists.get(pos));
                cartTipView.updateCartView(DataStorage.calculationNumInCart(), DataStorage.calculationPriceInCart());
            }

            @Override
            public void onSubNum(int value, int pos) {
                DataStorage.subProductInCart(value, mLists.get(pos));
                cartTipView.updateCartView(DataStorage.calculationNumInCart(), DataStorage.calculationPriceInCart());
            }
        });
    }

    /**
     * 更新界面
     */
    private void updateView(){
        if (cartTipView != null){
            cartTipView.updateCartView(DataStorage.calculationNumInCart(), DataStorage.calculationPriceInCart());
        }

        int index = checkProductLists();
        updateGridOrListView(index);
    }

    private void updateDataInView(){
        if (curMode == 0){
            //列表模式下 隐藏图文列表
            if (mGridView != null && listviewParent != null){
                mGridView.setVisibility(View.GONE);
                listviewParent.setVisibility(View.VISIBLE);
            }
        }else if (curMode == 1){
            //图文模式下 隐藏列表
            if (mGridView != null && listviewParent != null){
                listviewParent.setVisibility(View.GONE);
                mGridView.setVisibility(View.VISIBLE);
            }
        }

        mScrollView.smoothScrollBy(0, 10);
    }

    /**
     * 更新gridView或者listview控件某项view
     * @param pos
     */
    private void updateGridOrListView(int pos){
        //修改列表
        if (curMode == 0 && mListViewAdapter != null){
            //列表模式
            mListViewAdapter.updateItemView(pos);
        }else if (curMode == 1 && mGridViewAdapter != null){
            //图文模式
            mGridViewAdapter.updateItemView(pos);
        }
    }

    /**
     * 检查产品列表与购物车中的数据
     * @return
     */
    public int checkProductLists(){
        if (DataStorage.myCartLists == null || DataStorage.myCartLists.size() <= 0) return -1;

        for (int i=0;i<mLists.size();i++){
            ProductBean data = mLists.get(i);
            if (data != null){
                for (int j=0;j<DataStorage.myCartLists.size();j++){
                    ProductBean att = DataStorage.myCartLists.get(j);
                    if (att != null){
                        if (att.getType() == data.getType() && att.getId() == data.getId()){
                            mLists.get(i).setBuyNum(att.getBuyNum());

                            return i;
                        }
                    }
                }
            }
        }

        return -1;
    }


    /**
     * 模式切换
     */
    private void updateMode(){
        if (curMode == 0){
            //切换 图文模式
            curMode = 1;
            modeBtn.setImageResource(R.mipmap.billing_nav_101);

        }else if (curMode == 1){
            //切换 列表模式
            curMode = 0;
            modeBtn.setImageResource(R.mipmap.billing_nav_100);
        }

        updateDataInView();
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //返回按钮
            SysApplication.backBtn(this, null);
        }else if (modeBtn == v){
            //模式切换按钮 列表模式
            updateMode();
        }else if (historyBtn == v){
            //历史记录
            SysApplication.enterNext1(this, BillingHistoryOrdersAct.class);
        }
    }

}
