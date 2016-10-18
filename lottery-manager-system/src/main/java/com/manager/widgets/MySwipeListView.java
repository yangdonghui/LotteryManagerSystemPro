package com.manager.widgets;

import android.content.Context;
import android.util.AttributeSet;

import com.fortysevendeg.swipelistview.SwipeListView;

/**
 * Created by Administrator on 2016/3/21 0021.
 */
public class MySwipeListView extends SwipeListView {

    public MySwipeListView(Context context, AttributeSet attrs) {
        // TODO Auto-generated method stub
        super(context, attrs);
    }

    public MySwipeListView(Context context, AttributeSet attrs, int defStyle) {
        // TODO Auto-generated method stub
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
