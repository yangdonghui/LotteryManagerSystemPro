package com.manager.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manager.lotterypro.R;


/**
 * 中间标题 两边 线 view
 * Created by Administrator on 2016/3/2 0002.
 */
public class LineTextLineView extends LinearLayout {

    //上下文对象
    private Context mContext = null;
    //View对象
    private View mView = null;

    private TextView titleTv;
    private String titleText;


    public LineTextLineView(Context context) {
        this(context, null);
    }

    public LineTextLineView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineTextLineView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);

        mContext = context;

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LineTextLineView, defStyle, 0);
        titleText = a.getString(R.styleable.LineTextLineView_title_text);
        if (titleText == null){
            titleText = mContext.getResources().getString(R.string.lottery_city_info9);
        }

        initView();
    }


    /**
     * 初始化View
     */
    private void initView() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.line_tv_line_layout, this, true);
        titleTv = (TextView) mView.findViewById(R.id.line_tv_line_title_tv);
        titleTv.setText(titleText);
    }
}
