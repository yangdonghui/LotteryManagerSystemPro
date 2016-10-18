package com.manager.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manager.lotterypro.R;


/**
 * 订单确认界面 用户信息 view
 * Created by Administrator on 2016/3/2 0002.
 */
public class OrderConfirmAddressView extends LinearLayout {

    //上下文对象
    private Context mContext = null;
    //View对象
    private View mView = null;

    private TextView name;
    //电话
    private TextView phone;
    //地址
    private TextView address;

    /**
     * 一定一个接口
     */
    public interface ICoallBack{
        //进入地址管理界面 按钮
        public void onClick();
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

    public OrderConfirmAddressView(Context context) {
        super(context);

        mContext = context;
        initView();
    }

    public OrderConfirmAddressView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.order_confirm_userinfo_layout, this, true);
        mView.setClickable(true);

        name = (TextView) mView.findViewById(R.id.order_confirm_userinfo_tv1);
        phone = (TextView) mView.findViewById(R.id.order_confirm_userinfo_tv2);
        address = (TextView) mView.findViewById(R.id.order_confirm_userinfo_tv3);

        mView.setOnClickListener(onClickListener);
    }

    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mView == v){
                //进入地址管理界面
                icallBack.onClick();
            }
        }
    };
}
