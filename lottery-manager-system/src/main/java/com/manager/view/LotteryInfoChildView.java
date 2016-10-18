package com.manager.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.manager.lotterypro.R;

/**
 * 开奖信息 显示详细数据 子界面
 * @author donghuiyang
 * @create time 2016/5/6 0006.
 */
public class LotteryInfoChildView extends LinearLayout{
    //上下文对象
    private Context context = null;
    //View对象
    private View mView = null;

    public LotteryInfoChildView(Context context){
        this(context, null);
    }

    public LotteryInfoChildView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        initView();

    }

    /**
     * 初始化View
     */
    private void initView() {
        mView = LayoutInflater.from(context).inflate(R.layout.lottery_info_child_view_layout, this, true);
    }
}
