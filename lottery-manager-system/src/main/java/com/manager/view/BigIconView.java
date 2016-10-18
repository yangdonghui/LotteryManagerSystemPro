package com.manager.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.manager.common.ImageTools;
import com.manager.common.Tools;
import com.manager.lotterypro.R;

/**
 * 首页图标放大view
 * @author donghuiyang
 * @create time 2016/6/6 0006.
 */
public class BigIconView extends RelativeLayout {

    //上下文对象
    private Context mContext = null;
    //View对象
    private View mView = null;

    private ImageView imgV1, imgV2;
    private TextView tv1;

    //显示隐藏标记
    private boolean isShow = false;
    private boolean isFlip = false;

    private int width, height;
    private int top = 30;

    //水平翻转后图片
    private Bitmap srcBitmap,newBitmap;

    public BigIconView(Context context) {
        this(context, null);
    }

    public BigIconView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.home_icon_big_layout, this, true);
        imgV1 = (ImageView) mView.findViewById(R.id.home_icon_big_imgv1);
        imgV2 = (ImageView) mView.findViewById(R.id.home_icon_big_imgv2);
        tv1 = (TextView) mView.findViewById(R.id.home_icon_big_tv);

        srcBitmap = ImageTools.drawableToBitmap(imgV1.getDrawable());
        newBitmap = ImageTools.convertScaleX(srcBitmap, srcBitmap.getWidth(), srcBitmap.getHeight());

        this.measure(0, 0);
        width = getMeasuredWidth();
        height = getMeasuredHeight();

        this.setVisibility(GONE);
    }

    public void bigScaleAnimation(){
        if (!isShow){
            updateView(true);

            ScaleAnimation animation = null;
            if (!isFlip){
                animation =new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                        Animation.RELATIVE_TO_SELF, 0.8f, Animation.RELATIVE_TO_SELF, 1.0f);
            }else{
                animation =new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                        Animation.RELATIVE_TO_SELF, 0.2f, Animation.RELATIVE_TO_SELF, 1.0f);
            }

            AnimationSet aa = new AnimationSet(true);
            aa.addAnimation(animation);
            aa.setFillAfter(true);//动画终止时停留在最后一帧，不然会回到没有执行前的状态
            aa.setDuration(100);//动画持续时间
            this.startAnimation(aa);

            isShow = true;
        }
    }
    public void smallScaleAnimation(){
        if (isShow){

            ScaleAnimation animation = null;
            if (!isFlip) {
                animation =new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.8f, Animation.RELATIVE_TO_SELF, 1.0f);
            }else{
                animation =new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.2f, Animation.RELATIVE_TO_SELF, 1.0f);
            }

            AnimationSet aa = new AnimationSet(true);
            aa.addAnimation(animation);
            aa.setFillAfter(true);//动画终止时停留在最后一帧，不然会回到没有执行前的状态
            aa.setDuration(100);//动画持续时间
            this.startAnimation(aa);
            aa.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    //隐藏
                    updateView(false);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            isShow = false;
        }
    }

    /**
     * 更新view 显示 或 隐藏
     * @param flag
     */
    public void updateView(boolean flag){
        isShow = flag;

        if (flag){
            setVisibility(VISIBLE);
        }else{
            setVisibility(GONE);
        }
    }

    /**
     * 更新坐标
     * @param x
     * @param y
     */
    public void updatePos(int imgID, int titleID, int styleID, int x, int y, boolean isFlip){
        this.isFlip = isFlip;

        if (isFlip){
            imgV1.setImageBitmap(newBitmap);
        }else{
            imgV1.setImageBitmap(srcBitmap);
        }

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams)this.getLayoutParams();
        if (isFlip){
            //图标右侧
            lp.leftMargin = x - (int)(width*0.2) + 10;
        }else{
            //图标左侧
            lp.leftMargin = x - (int)(width*0.8) + 40;
        }

        lp.topMargin = y - (int)(height) + Tools.dip2px(mContext, top) - 40;
        this.setLayoutParams(lp);

        imgV2.setImageResource(imgID);
        tv1.setText(titleID);
        tv1.setTextAppearance(mContext, styleID);
    }

    public boolean isShow() {
        return isShow;
    }
}
