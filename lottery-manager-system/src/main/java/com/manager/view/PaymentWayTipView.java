package com.manager.view;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manager.Interface.ICoallBack4;
import com.manager.lotterypro.R;

/**
 * 付款方式提示栏 view
 * @author donghuiyang
 * @create time 2016/6/6 0006.
 */
public class PaymentWayTipView extends LinearLayout implements View.OnClickListener{

    //上下文对象
    private Context mContext = null;
    //View对象
    private View mView = null;

    private ImageView imgV;
    private TextView tv;
    private TextView changeTv;

    private ICoallBack4 iCoallBack;

    public void setICoallBack(ICoallBack4 iCoallBack) {
        this.iCoallBack = iCoallBack;
    }

    public PaymentWayTipView(Context context) {
        this(context, null);
    }
    public PaymentWayTipView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.wallet_pay_way_tip_layout, this, true);
        tv = (TextView) mView.findViewById(R.id.wallet_pay_way_tip_tv1);
        imgV = (ImageView) mView.findViewById(R.id.wallet_pay_way_tip_imgv);
        changeTv = (TextView) mView.findViewById(R.id.wallet_pay_way_tip_tv3);
        changeTv.setOnClickListener(this);
    }

    /**
     * 更新支付方式 信息
     * @param url
     * @param info
     */
    public void updateInfo(Uri url, String info) {
        imgV.setImageURI(url);
        tv.setText(info);
    }

    /**
     * 更新支付方式 信息
     * @param id
     * @param info
     */
    public void updateInfo(int id, String info) {
        imgV.setImageResource(id);
        tv.setText(info);
    }

    @Override
    public void onClick(View v) {
        if (changeTv == v){
            //更换 支付方式 弹出选择框
            iCoallBack.onClick();
        }
    }
}
