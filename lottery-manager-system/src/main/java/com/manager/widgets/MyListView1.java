package com.manager.widgets;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import com.manager.common.Tools;

/**
 * 禁止touch事件
 * Created by Administrator on 2016/2/25 0025.
 */
public class MyListView1 extends ListView {

    public MyListView1(Context context) {
        super(context);

        if (Integer.parseInt(Build.VERSION.SDK) >= 9) {
            this.setOverScrollMode(View.OVER_SCROLL_NEVER);
        }
    }

    public MyListView1(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (Integer.parseInt(Build.VERSION.SDK) >= 9) {
            this.setOverScrollMode(View.OVER_SCROLL_NEVER);
        }
    }

    public MyListView1(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        if (Tools.getAndroidSDKVersion() > 8) {
            this.setOverScrollMode(View.OVER_SCROLL_NEVER);
        }
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return false;
        /*final int actionMasked = ev.getActionMasked() & MotionEvent.ACTION_MASK;

        if (actionMasked == MotionEvent.ACTION_DOWN) {
            // 记录手指按下时的位置
            mPosition = pointToPosition((int) ev.getX(), (int) ev.getY());
            return super.dispatchTouchEvent(ev);
        }

        if (actionMasked == MotionEvent.ACTION_MOVE) {
            // 最关键的地方，忽略MOVE 事件
            // ListView onTouch获取不到MOVE事件所以不会发生滚动处理
            return true;
        }

        // 手指抬起时
        if (actionMasked == MotionEvent.ACTION_UP
                || actionMasked == MotionEvent.ACTION_CANCEL) {
            // 手指按下与抬起都在同一个视图内，交给父控件处理，这是一个点击事件
            if (pointToPosition((int) ev.getX(), (int) ev.getY()) == mPosition) {
                super.dispatchTouchEvent(ev);
            } else {
                // 如果手指已经移出按下时的Item，说明是滚动行为，清理Item pressed状态
                setPressed(false);
                invalidate();
                return true;
            }
        }

        return super.dispatchTouchEvent(ev);*/
    }
}
