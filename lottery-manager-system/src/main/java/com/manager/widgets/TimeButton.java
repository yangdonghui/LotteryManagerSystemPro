package com.manager.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.manager.lotterypro.SysApplication;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 鉴于经常用到获取验证码倒计时按钮 在网上也没有找到理想的 自己写一个
 *
 * @author yung
 *         <p>
 *         2015年1月14日[佛祖保佑 永无BUG]
 *         <p>
 *         PS: 由于发现timer每次cancle()之后不能重新schedule方法,所以计时完毕只恐timer.
 *         每次开始计时的时候重新设置timer, 没想到好办法初次下策
 *         注意把该类的onCreate()onDestroy()和activity的onCreate()onDestroy()同步处理
 */
public class TimeButton extends Button implements OnClickListener {
    private long lenght = 60 * 1000;// 倒计时长度,这里给了默认60秒
    private String textafter = "秒";//后重新获取~
    private String textbefore = "获取验证码";//点击获取验证码~
    private final String TIME = "time";
    private final String CTIME = "ctime";
    private OnClickListener mOnclickListener;
    private Timer t;
    private TimerTask tt;
    private long time;
    Map<String, Long> map = new HashMap<String, Long>();

    public TimeButton(Context context) {
        super(context);
        setOnClickListener(this);

    }

    public TimeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    @SuppressLint("HandlerLeak")
    Handler han = new Handler() {
        public void handleMessage(android.os.Message msg) {
            TimeButton.this.setText(time / 1000 + textafter);
            time -= 1000;
            if (time < 0) {
                TimeButton.this.setEnabled(true);
                TimeButton.this.setText(textbefore);
                clearTimer();
            }
        }
    };

    private void initTimer() {
        time = lenght;
        t = new Timer();
        tt = new TimerTask() {

            @Override
            public void run() {
                Log.e("yung", time / 1000 + "");
                han.sendEmptyMessage(0x01);
            }
        };
    }

    private void clearTimer() {
        if (tt != null) {
            tt.cancel();
            tt = null;
        }
        if (t != null)
            t.cancel();
        t = null;
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        if (l instanceof TimeButton) {
            super.setOnClickListener(l);
        } else
            this.mOnclickListener = l;
    }

    @Override
    public void onClick(View v) {
        if (mOnclickListener != null)
            mOnclickListener.onClick(v);
        initTimer();
        this.setText(time / 1000 + textafter);
        this.setEnabled(false);
        t.schedule(tt, 0, 1000);
        // t.scheduleAtFixedRate(task, delay, period);
    }

    /**
     * 和activity的onDestroy()方法同步
     */
    public void onDestroy() {
        if (SysApplication.map == null)
            SysApplication.map = new HashMap<String, Long>();
        SysApplication.map.put(TIME, time);
        SysApplication.map.put(CTIME, System.currentTimeMillis());
        clearTimer();
        Log.e("yung", "onDestroy");
    }

    /**
     * 和activity的onCreate()方法同步
     */
    public void onCreate(Bundle bundle) {
        Log.e("yung", SysApplication.map + "");
        if (SysApplication.map == null)
            return;
        if (SysApplication.map.size() <= 0)// 这里表示没有上次未完成的计时
            return;
        long time = System.currentTimeMillis() - SysApplication.map.get(CTIME)
                - SysApplication.map.get(TIME);
        SysApplication.map.clear();
        if (time > 0)
            return;
        else {
            initTimer();
            this.time = Math.abs(time);
            t.schedule(tt, 0, 1000);
            this.setText(time + textafter);
            this.setEnabled(false);
        }
    }

    /**
     * 设置计时时候显示的文本
     */
    public TimeButton setTextAfter(String text1) {
        this.textafter = text1;
        return this;
    }

    /**
     * 设置点击之前的文本
     */
    public TimeButton setTextBefore(String text0) {
        this.textbefore = text0;
        this.setText(textbefore);
        return this;
    }

    /**
     * 设置到计时长度
     *
     * @param lenght 时间 默认毫秒
     * @return
     */
    public TimeButton setLenght(long lenght) {
        this.lenght = lenght;
        return this;
    }
    /*

*
*/
}