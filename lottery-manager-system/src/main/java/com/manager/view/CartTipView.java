package com.manager.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manager.common.StringHelper;
import com.manager.lotterypro.R;


/**
 * 购物车提示栏 view
 * Created by Administrator on 2016/3/2 0002.
 */
public class CartTipView extends LinearLayout {

    //上下文对象
    private Context mContext = null;
    //View对象
    private View mView = null;

    //购物车按钮
    private ImageView cartBtn;
    //结算按钮
    private TextView confirmBtn;
    //价格
    private TextView contentTv;
    //数量标记
    private ViewGroup numViewGroup;
    //购物车物品数量
    private TextView numTv;

    private int MaxNum = 99;
    private int productNum = 0;
    private float productPrice = 0;

    /**
     * 一定一个接口
     */
    public interface ICoallBack{
        //购物车按钮
        public void onCartClick();
        //结算按钮
        public void onOKClick();
    }
    /**
     * 初始化接口变量
     */
    ICoallBack icallBack = null;
    /**
     * 自定义控件的自定义事件
     * @param iBack 接口类型
     */
    public void setonClick(ICoallBack iBack)
    {
        icallBack = iBack;
    }

    public CartTipView(Context context) {
        this(context, null);
    }

    public CartTipView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.cart_tip_layout, this, true);

        cartBtn = (ImageView) mView.findViewById(R.id.cart_tip_btn1);
        confirmBtn = (TextView) mView.findViewById(R.id.cart_tip_btn2);
        contentTv = (TextView) mView.findViewById(R.id.cart_tip_info_tv);
        numViewGroup = (ViewGroup) mView.findViewById(R.id.cart_tip_num_viewgroup);
        numTv = (TextView) mView.findViewById(R.id.cart_tip_num_tv);

        cartBtn.setOnClickListener(onClickListener);
        confirmBtn.setOnClickListener(onClickListener);
    }

    /**
     * 购物车产品数量
     * @param productNum
     * @return
     */
    public CartTipView setProductNum(int productNum) {
        this.productNum = productNum;
        return this;
    }

    public CartTipView setProductPrice(float productPrice) {
        this.productPrice = productPrice;
        return this;
    }

    /**
     * 更新购物车view
     * @param productNum
     */
    public void updateCartView(int productNum, float productPrice){
        setProductNum(productNum);
        setProductPrice(productPrice);

        //更新数量
        if (productNum > 0){
            if (numViewGroup.getVisibility() == GONE){
                numViewGroup.setVisibility(VISIBLE);
            }

            if (productNum > MaxNum){
                numTv.setText("..");
            }else {
                numTv.setText(String.valueOf(productNum));
            }
            //购物车价格
            contentTv.setText(StringHelper.getMoneyStr(mContext, productPrice));

        }else if (productNum <= 0){
            if (numViewGroup.getVisibility() == VISIBLE){
                numViewGroup.setVisibility(GONE);
            }

            //购物车为空
            contentTv.setText(R.string.cart_hint_str1);
        }
    }

    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (cartBtn == v){
                //购物车界面
                if (productNum <= 0) return;

                icallBack.onCartClick();

            }else if(confirmBtn == v) {
                //提交按钮
                if (productNum <= 0) return;

                if (icallBack != null){
                    //进入订单详情界面
                    icallBack.onOKClick();
                }
            }
        }
    };
}
