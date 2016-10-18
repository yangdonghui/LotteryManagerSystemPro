package com.manager.lotterycity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.manager.Interface.ICoallBack9;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.view.CartTipView;
import com.manager.view.ChangeBuyNumView;

/**
 * 即开票 详情界面
 *
 * @author donghuiyang
 * @create time 2016/4/20 0020.
 */
public class BillingDetailAct extends Activity implements View.OnClickListener, View.OnLayoutChangeListener {
    //返回按钮
    private View backBtn;

    //购买按钮
    private Button buyBtn;

    //购物车提示栏
    private CartTipView cartTipView;

    //修改数量view
    ChangeBuyNumView changeNumBtn;
    /*//修改数量view
    View subNumBtn;
    View addNumBtn;
    EditText numTv;*/

    //订单列表
    private ListView mListView;

    //Activity最外层的Layout视图
    private View activityRootView;
    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.billing_detail_layout);

        initTopView();
        initCartTipView();
        initAddOrSubView();
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
            cartTipView.setVisibility(View.GONE);

        } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {

            //Toast.makeText(this, "监听到软件盘关闭...", Toast.LENGTH_SHORT).show();
            cartTipView.setVisibility(View.VISIBLE);

        }
    }

    /**
     * 设置标题
     */
    private void initTopView() {
        activityRootView = (View) findViewById(R.id.billing_detail_rootview);
        //获取屏幕高度
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight / 3;

        ViewGroup topView = (ViewGroup) findViewById(R.id.billing_detail_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.lottery_city_title2);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    private void initAddOrSubView() {
        changeNumBtn = (ChangeBuyNumView) (findViewById(R.id.billing_detail_changeview));
        changeNumBtn.setonClick(new ICoallBack9() {
            @Override
            public void modifyNumClick(int value, int btnState) {
                if (value <= 0) {
                    //从清单中清除
                    Log.e("", "listItemView.changeNumBtn======remove");

                    /*DataStorage.removeProductInCart(mLists.get(pos));
                    cartTipView.updateCartView(DataStorage.myCartLists.size(), DataStorage.calculationPriceInCart());*/

                } else {
                    //更新购物车清单（某个商品的数量）
                    Log.e("", "listItemView.changeNumBtn======add");

                    /*DataStorage.saveProductInCart(mLists.get(pos));
                    cartTipView.updateCartView(DataStorage.myCartLists.size(), DataStorage.calculationPriceInCart());*/
                }
            }
        });

        //加入购物车按钮
        /*buyBtn = (Button) findViewById(R.id.billing_detail_buy_btn);
        buyBtn.setOnClickListener(this);*/
    }

    /**
     * 初始化购物车提示栏
     */
    private void initCartTipView() {
        cartTipView = (CartTipView) findViewById(R.id.billing_detail_cart_view_include);
        cartTipView.setonClick(new CartTipView.ICoallBack() {
            @Override
            public void onCartClick() {
                SysApplication.enterNext1(BillingDetailAct.this, ProductCartAct.class);
            }

            @Override
            public void onOKClick() {
                SysApplication.enterNext1(BillingDetailAct.this, BillingOrderConfirmAct.class);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v) {
            //返回按钮
            SysApplication.backBtn(this, null);
        }/*else if (buyBtn == v) {
            //加入购物车  请求服务器

        }*/
    }
}
