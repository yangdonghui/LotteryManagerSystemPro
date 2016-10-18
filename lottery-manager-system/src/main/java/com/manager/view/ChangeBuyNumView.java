package com.manager.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manager.Interface.ICoallBack9;
import com.manager.lotterypro.R;

/**
 * 设置 购买数量 view
 * @author donghuiyang
 * @create time 2016/6/6 0006.
 */
public class ChangeBuyNumView extends LinearLayout implements View.OnClickListener{

    private int MinNum = 0;
    private int MaxNum = 99;

    //上下文对象
    private Context mContext = null;
    //View对象
    private View mView = null;

    private int num = 0;
    private String showContentStrl;
    //相关控件
    private TextView numTv;
    private ImageView subImagev, addImagev;

    //加减号回调
    private ICoallBack9 iCoallBack;
    public void setonClick(ICoallBack9 iCoallBack){
        this.iCoallBack = iCoallBack;
    }

    public ChangeBuyNumView(Context context) {
        this(context, null);
    }
    public ChangeBuyNumView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChangeBuyNumView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);

        TypedArray att = context.obtainStyledAttributes(attrs, R.styleable.ChangeBuyNumView, defStyle, 0);
        num = att.getInt(R.styleable.ChangeBuyNumView_initNum, 0);
        MinNum = num;
        mContext = context;
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.buy_change_num_layout, this, true);

        numTv = (TextView) mView.findViewById(R.id.buy_change_num_tv);
        subImagev = (ImageView) mView.findViewById(R.id.buy_change_sub_btn);
        subImagev.setOnClickListener(this);
        addImagev = (ImageView) mView.findViewById(R.id.buy_change_add_btn);
        addImagev.setOnClickListener(this);

        updateView(-1);
    }

    public void updateView(int btnState) {
        if (num <= 0){
            //隐藏减号
            subImagev.setVisibility(INVISIBLE);
            numTv.setVisibility(INVISIBLE);
        }else if (num > 0){
            subImagev.setVisibility(VISIBLE);
            numTv.setVisibility(VISIBLE);
        }


        if (num > MaxNum){
            showContentStrl = String.valueOf(MaxNum)+"..";
        }else{
            showContentStrl = String.valueOf(num);
        }

        numTv.setText(showContentStrl);

        if (iCoallBack != null && btnState != -1){
            //回调
            iCoallBack.modifyNumClick(num, btnState);
        }
    }

    public int getNum() {
        return num;
    }

    public ChangeBuyNumView setNum(int num) {
        this.num = num;

        //更新view
        updateView(-1);

        return this;
    }

    @Override
    public void onClick(View v) {
        if (subImagev.getVisibility() == VISIBLE && subImagev == v){
            //减号
            num --;
            if (num <= MinNum){
                num = MinNum;
            }
            updateView(0);
        }else if (addImagev.getVisibility() == VISIBLE && addImagev == v){
            //加号
            num ++;
            updateView(1);
        }
    }
}
