package com.manager.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * @author donghuiyang
 * @create time 2016/6/29 0029.
 */
public class MyScrollView2 extends ScrollView {

    private ScrollViewListener scrollViewListener = null;

    public MyScrollView2(Context context) {
        super(context);
    }

    public MyScrollView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }
}
