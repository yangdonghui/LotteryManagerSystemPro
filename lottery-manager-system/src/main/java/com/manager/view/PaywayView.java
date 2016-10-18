package com.manager.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manager.lotterypro.R;


/**
 * 支付方式选择  view
 * Created by Administrator on 2016/3/2 0002.
 */
public class PaywayView extends LinearLayout implements OnClickListener {

    //上下文对象
    private Context mContext = null;
    //View对象
    private View mView = null;

    private TextView tv1, tv2, tv3, tv4;
    private int typeCur = 0;

    Drawable drawable1,drawable2;

    /**
     * 一定一个接口
     */
    public interface ICoallBack{
        public void onClickView(int index, int upOrdown);
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

    public PaywayView(Context context) {
        super(context);

        mContext = context;
        initView();
    }

    public PaywayView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        initView();
    }



    /**
     * 初始化View
     */
    private void initView() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.pay_way_layout, this, true);
        if (mView != null) {
            tv1 = (TextView) mView.findViewById(R.id.pay_way_checkBox1);
            tv2 = (TextView) mView.findViewById(R.id.pay_way_checkBox2);
            tv3 = (TextView) mView.findViewById(R.id.pay_way_checkBox3);
            tv4 = (TextView) mView.findViewById(R.id.pay_way_checkBox4);

            tv1.setOnClickListener(this);
            tv2.setOnClickListener(this);
            tv3.setOnClickListener(this);
            tv4.setOnClickListener(this);
        }

        drawable1 = mContext.getResources().getDrawable(R.mipmap.pay_nav_10);
        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());//必须设置图片大小，否则不显示

        drawable2 = mContext.getResources().getDrawable(R.mipmap.pay_nav_14);
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());//必须设置图片大小，否则不显示
    }

    private void updateType(int index) {
        if (index == -1 || index == typeCur) return;

        TextView tv = null;
        switch (typeCur){
            case 0 :{
                tv1.setCompoundDrawables(drawable1, null, null, null);
            }
            break;
            case 1 :{
                tv2.setCompoundDrawables(drawable1, null, null, null);
            }
            break;
            case 2 :{
                tv3.setCompoundDrawables(drawable1, null, null, null);
            }
            break;
            case 3 :{
                tv4.setCompoundDrawables(drawable1, null, null, null);
            }
            break;
        }
        if (index == 0) {
            typeCur = index;
            tv = tv1;
        }else if (index == 1) {
            typeCur = index;
            tv = tv2;
        }else if (index == 2) {
            typeCur = index;
            tv = tv3;
        }else if (index == 3) {
            typeCur = index;
            tv = tv4;
        }

        if (tv != null){
            tv.setCompoundDrawables(drawable2, null, null, null);
        }
    }

    @Override
    public void onClick(View v) {
        int index = -1;
        if (v == tv1){
            index = 0;
        }else if (v == tv2){
            index = 1;
        }else if (v == tv3){
            index = 2;
        }else if (v == tv4){
            index = 3;
        }

        updateType(index);
    }
}
