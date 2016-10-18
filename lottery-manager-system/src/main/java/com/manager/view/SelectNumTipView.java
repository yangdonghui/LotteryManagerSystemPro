package com.manager.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manager.common.Tools;
import com.manager.lotterypro.R;

/**
 * 选号 信息提示
 * @author donghuiyang
 * @create time 2016/6/6 0006.
 */
public class SelectNumTipView extends LinearLayout {

    //上下文对象
    private Context mContext = null;
    //View对象
    private View mView = null;

    private TextView tv1, tv2, tv3;

    private boolean isShow = false;

    private int count, money;


    public SelectNumTipView(Context context) {
        this(context, null);
    }

    public SelectNumTipView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.select_num_tip_layout, this, true);
        tv1 = (TextView) mView.findViewById(R.id.select_num_tip_tv1);
        tv2 = (TextView) mView.findViewById(R.id.select_num_tip_tv2);
        tv3 = (TextView) mView.findViewById(R.id.select_num_tip_tv3);
    }

    /**
     * 更新内容
     * @param count
     * @param money
     */
    public void updateTv(int count, int money) {
        this.count = count;
        this.money = money;

        tv2.setText(String.valueOf(count) + "注");
        tv3.setText(String.valueOf(money) + "元");

        if (count == 1){
            tv1.setText("单注");
        }else if (count > 1){
            tv1.setText("复式");
        }

        invalidate();
    }

    /**
     * 更新 选号提示框的动画
     * @param direction
     */
    public void updateSelectTipViewAnim(int direction){
        if (direction == 1 && !isShow){
            Animation animation = new TranslateAnimation(0,0,0, Tools.dip2px(mContext, 30));//平移动画
            animation.setFillAfter(true);//动画终止时停留在最后一帧，不然会回到没有执行前的状态
            animation.setDuration(300);//动画持续时间0.2秒
            this.startAnimation(animation);//是用selectTipView来显示动画的

            isShow = true;

        }else if (direction == -1 && isShow){
            Animation animation = new TranslateAnimation(0,0,Tools.dip2px(mContext, 30),0);//平移动画
            animation.setFillAfter(true);//动画终止时停留在最后一帧，不然会回到没有执行前的状态
            animation.setDuration(300);//动画持续时间0.2秒
            this.startAnimation(animation);//是用selectTipView来显示动画的

            isShow = false;
        }
    }

    /**
     * 是否显示
     * @return
     */
    public boolean isShow() {
        return isShow;
    }
}
