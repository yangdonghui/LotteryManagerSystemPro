package com.manager.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manager.lotterypro.R;

/**
 * 彩种介绍中 奖项规则
 * @author donghuiyang
 * @create time 2016/6/6 0006.
 */
public class AwardRulesView extends LinearLayout {

    //上下文对象
    private Context mContext = null;
    //View对象
    private View mView = null;

    private TextView infoTv;

    public AwardRulesView(Context context) {
        this(context, null);
    }

    public AwardRulesView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.lottery_introduce_tab_layout, this, true);
        infoTv = (TextView) mView.findViewById(R.id.lottery_introduce_tab_tv);
    }

    /**
     * 更新文字
     * @param tv
     */
    public void updateInfo(String tv) {
        if (infoTv != null){
            infoTv.setText(tv);
        }
    }
}
