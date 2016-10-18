package com.manager.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;

import com.manager.common.FontTools;
import com.manager.common.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * 垂直滚动的TextView Widget
 *
 * @author donghuiyang
 * @create time 2016/4/21 0021.
 */
public class VerticalScrollTextView extends TextView {
    Context context;
    private Paint mPaint;
    private float mX;
    private Paint mPathPaint;
    public int index = 0;
    private List<Sentence> list;
    public float mTouchHistoryY;
    private int mY;
    private float middleY;// y轴中间
    private static final int DY = 40; // 每一行的间隔

    public VerticalScrollTextView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public VerticalScrollTextView(Context context, AttributeSet attr) {
        super(context, attr);
        this.context = context;
        init();
    }

    public VerticalScrollTextView(Context context, AttributeSet attr, int i) {
        super(context, attr, i);
        this.context = context;
        init();
    }

    private void init() {
        setFocusable(true);
        if (list == null) {
            list = new ArrayList<Sentence>();
            Sentence sen = new Sentence(0, "暂时没有通知公告");
            list.add(0, sen);
        }

        float textsize = 26 * Tools.getScale(context);

        // 非高亮部分
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(textsize);
        mPaint.setColor(Color.BLACK);
        mPaint.setTypeface(Typeface.SERIF);

        // 高亮部分 当前歌词
        mPathPaint = new Paint();
        mPathPaint.setAntiAlias(true);
        mPathPaint.setColor(0xff333333);
        mPathPaint.setTextSize(textsize);
        mPathPaint.setTypeface(Typeface.SANS_SERIF);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //canvas.drawColor(0xEFeffff);
        Paint p = mPaint;
        Paint p2 = mPathPaint;
        p.setTextAlign(Paint.Align.LEFT);
        if (index == -1)
            return;
        p2.setTextAlign(Paint.Align.LEFT);
        // 先画当前行，之后再画他的前面和后面，这样就保持当前行在中间的位置
        canvas.drawText(list.get(index).getName(), mX, middleY, p2);
        float tempY = middleY;
        // 画出本句之前的句子
        for (int i = index - 1; i >= 0; i--) {
            tempY = tempY - DY;
            if (tempY < 0) {
                break;
            }
            canvas.drawText(list.get(i).getName(), mX, tempY, p);
        }
        tempY = middleY;
        // 画出本句之后的句子
        for (int i = index + 1; i < list.size(); i++) {
            // 往下推移
            tempY = tempY + DY;
            if (tempY > mY) {
                break;
            }
            canvas.drawText(list.get(i).getName(), mX, tempY, p);
        }
    }

    protected void onSizeChanged(int w, int h, int ow, int oh) {
        super.onSizeChanged(w, h, ow, oh);
        mX = w * 0.0f;
        mY = h;

        int h1 = (int) Math.abs(mPathPaint.getFontMetrics().ascent);
        middleY = h * 0.5f - (int) Math.abs(mPathPaint.getFontMetrics().descent) + (int) FontTools.getFontHeight(mPathPaint.getTextSize()) / 2 - 2;//FontTools.getFontHeight(mPathPaint.getTextSize())/2

    }

    public long updateIndex(int index) {
        if (index == -1)
            return -1;
        this.index = index;
        return index;
    }

    public List<Sentence> getList() {
        return list;
    }

    public void setList(List<Sentence> list) {
        this.list = list;
    }

    public void updateUI() {
        new Thread(new updateThread()).start();
    }

    class updateThread implements Runnable {
        long time = 2000; // 开始 的时间，不能为零，否则前面几句歌词没有显示出来
        int i = 0;

        public void run() {
            while (true) {
                long sleeptime = updateIndex(i);
                time += sleeptime;
                mHandler.post(mUpdateResults);
                if (sleeptime == -1)
                    return;
                try {
                    Thread.sleep(time);
                    i++;
                    if (i == getList().size())
                        i = 0;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    Handler mHandler = new Handler();
    Runnable mUpdateResults = new Runnable() {
        public void run() {
            invalidate(); // 更新视图
        }
    };
}