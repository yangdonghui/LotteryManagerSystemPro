package com.manager.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * @author donghuiyang
 * @create time 2016/7/19 0019.
 */
public class CHScrollView extends HorizontalScrollView {
    private Context context;
    private HorizontalScrollView TouchView;

    public interface ICoallBack {
        public void onScrollChanged(int l, int t, int oldl, int oldt);
    }

    ICoallBack iCoallBack;
    public void setCallBack(ICoallBack iCoallBack){
        this.iCoallBack = iCoallBack;
    }

    public HorizontalScrollView getTouchView() {
        return TouchView;
    }

    public CHScrollView setTouchView(HorizontalScrollView TouchView) {
        this.TouchView = TouchView;
        return this;
    }

    public CHScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }
    public CHScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }
    public CHScrollView(Context context) {
        super(context);
        this.context = context;
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //进行触摸赋值
        /*mTouchView = this;*/
        TouchView = this;
        return super.onTouchEvent(ev);
    }
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        //当当前的CHSCrollView被触摸时，滑动其它
        if(TouchView == this && iCoallBack != null) {
            iCoallBack.onScrollChanged(l, t, oldl, oldt);
        }else{
            super.onScrollChanged(l, t, oldl, oldt);
        }
    }
}