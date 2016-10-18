package com.manager.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.manager.lotterypro.R;

/**
 * 故障申报 子项view
 * @author donghuiyang
 * @create time 2016/5/11 0011.
 */
public class FaultDetailItemsView extends LinearLayout{
    private Context context;
    //View对象
    private View mView = null;

    public FaultDetailItemsView(Context context) {
        this(context, null);
    }

    public FaultDetailItemsView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        mView = LayoutInflater.from(context).inflate(R.layout.fault_detail_items_layout, this, true);

    }
}
