package com.manager.lotterycity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.manager.Interface.ICoallBack10;
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
 * 即开票预订 产品列表界面(列表模式)
 * @author donghuiyang
 * @create time 2016/4/20 0020.
 */
public class ConsumableListsAct extends Activity implements View.OnClickListener/*, View.OnLayoutChangeListener*/{

    //返回按钮
    private View backBtn;

    //历史记录
    private View historyBtn;

    //搜索栏
    private SearchView searchView;

    //滚动view
    private ScrollView mScrollView;

    //购物车提示栏
    private CartTipView cartTipView;

    //listview
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
        setContentView(R.layout.consumable_lists_layout);

        initData();

        initTopView();
        initView();
        initCartTipView();
        initListView();

        mScrollView.smoothScrollBy(0, 10);
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
        activityRootView = (View) findViewById(R.id.consumable_lists_rootview);
        //获取屏幕高度
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight / 3;

        ViewGroup topView = (ViewGroup) findViewById(R.id.consumable_lists_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.lottery_city_info16);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    /**
     * 获取控件对象
     */
    private void initView() {
        mScrollView = (ScrollView) findViewById(R.id.consumable_lists_scrollview);

        //历史记录
        historyBtn = (View) findViewById(R.id.consumable_lists_histoey_btn);
        historyBtn.setOnClickListener(this);

        //搜索栏
        searchView = (SearchView) findViewById(R.id.consumable_ists_searchview);
        searchView.setBgColor(R.color.bg_color_4);
    }

    /**
     * 初始化购物车提示栏
     */
    private void initCartTipView() {
        cartTipView = (CartTipView) findViewById(R.id.consumable_lists_cart_view_include);
        cartTipView.updateCartView(DataStorage.calculationNumInCart(), DataStorage.calculationPriceInCart());
        cartTipView.setonClick(new CartTipView.ICoallBack() {
            @Override
            public void onCartClick() {
                //进入购物车界面
                SysApplication.enterNext1(ConsumableListsAct.this, ProductCartAct.class);
            }

            @Override
            public void onOKClick() {
                //进入订单确认界面
                SysApplication.enterNext1(ConsumableListsAct.this, BillingOrderConfirmAct.class);
            }
        });
    }

    private void initData() {
        mLists = Constants.ConsumableLists;

        checkProductLists();
    }
    private void initListView() {

        mListView = (ListView) findViewById(R.id.consumable_lists_listview);
        mListViewAdapter = new ProductListViewAdapter(this, mLists);
        mListViewAdapter.setListView(mListView);
        mListView.setAdapter(mListViewAdapter);
        mListViewAdapter.setonClick(new ICoallBack10() {
            @Override
            public void onItemClick(int pos) {

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
    }

    /**
     * 更新界面
     */
    private void updateView(){
        if (cartTipView != null){
            cartTipView.updateCartView(DataStorage.calculationNumInCart(), DataStorage.calculationPriceInCart());
        }

        int index = checkProductLists();
        updateListView(index);
    }

    /**
     * 更新listview控件某项view
     * @param pos
     */
    private void updateListView(int pos){
        //修改列表
        //列表模式
        mListViewAdapter.updateItemView(pos);
    }

    /**
     * 检查产品列表与购物车中的数据
     * @return
     */
    public int checkProductLists(){
        if (DataStorage.myCartLists == null || DataStorage.myCartLists.size() <= 0) return -1;

        for (int i=0;i<Constants.ConsumableLists.size();i++){
            ProductBean data = Constants.ConsumableLists.get(i);
            if (data != null){
                for (int j=0;j<DataStorage.myCartLists.size();j++){
                    ProductBean att = DataStorage.myCartLists.get(j);
                    if (att != null){
                        if (att.getType() == data.getType() && att.getId() == data.getId()){
                            Constants.ConsumableLists.get(i).setBuyNum(att.getBuyNum());

                            //修改列表
                            return i;
                        }
                    }
                }
            }
        }

        return -1;
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //返回按钮
            SysApplication.backBtn(this, null);
        }else if (historyBtn == v){
            //历史记录
            SysApplication.enterNext1(this, BillingHistoryOrdersAct.class);
        }
    }

}
