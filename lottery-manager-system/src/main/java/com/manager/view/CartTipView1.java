package com.manager.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manager.common.StringHelper;
import com.manager.lotterypro.R;


/**
 * 购物车提示栏 view
 * Created by Administrator on 2016/3/2 0002.
 */
public class CartTipView1 extends LinearLayout {

    //上下文对象
    private Context mContext = null;
    //View对象
    private View mView = null;

    //结算按钮
    private TextView confirmBtn;
    //价格
    private TextView contentTv;
    //数量标记
    private TextView numTv1;
    //购物车物品数量
    private TextView numTv2;

    private int MaxNum = 99;
    private int productNum1 = 0, productNum2 = 0;
    private float productPrice = 0;

    /**
     * 一定一个接口
     */
    public interface ICoallBack{
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

    public CartTipView1(Context context) {
        this(context, null);
    }

    public CartTipView1(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.cart_tip_1_layout, this, true);

        confirmBtn = (TextView) mView.findViewById(R.id.cart_tip_1_btn);
        contentTv = (TextView) mView.findViewById(R.id.cart_tip_1_info_tv);
        numTv1 = (TextView) mView.findViewById(R.id.cart_tip_1_info_tv2);
        numTv2 = (TextView) mView.findViewById(R.id.cart_tip_1_info_tv3);

        confirmBtn.setOnClickListener(onClickListener);
    }

    /**
     * 更新购物车view
     * @param productNum1
     * @param productNum2
     * @param productPrice
     */
    public void updateCartView(int productNum1, int productNum2, float productPrice){

        this.productNum1 = productNum1;
        this.productNum2 = productNum2;

        numTv1.setText(String.valueOf(productNum1));
        numTv2.setText(String.valueOf(productNum2));

        contentTv.setText(StringHelper.getMoneyStr1(mContext,productPrice));
/*
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
        }*/
    }

    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
             if(confirmBtn == v) {
                //提交按钮
                if (productNum1 <= 0) return;

                if (icallBack != null){
                    //进入订单详情界面
                    icallBack.onOKClick();
                }
            }
        }
    };
}
