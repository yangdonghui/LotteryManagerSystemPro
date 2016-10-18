package com.manager.view.anim;

import android.view.View;
import android.view.animation.Animation;

import com.manager.view.NewsColumnView;

/**
 * @author donghuiyang
 * @create time 2016/4/27 0027.
 */
public class DisplayNextView implements Animation.AnimationListener {

    Object obj;

    // 动画监听器的构造函数
    View ac;
    int order;

    public DisplayNextView(View ac, int order) {
        this.ac = ac;
        this.order = order;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        doSomethingOnEnd(order);
    }

    public void onAnimationRepeat(Animation animation) {
    }

    private final class SwapViews implements Runnable {
        public void run() {
            switch (order) {
                case 2:
                    ((NewsColumnView) ac).updateView(2);
                break;
                case 3:
                    ((NewsColumnView) ac).updateView(3);
                    break;

                case 4:
                    //显示首页大图标处理

                    break;
                case 5:
                    //隐藏首页大图标处理、

                    break;
            }
        }
    }

    public void doSomethingOnEnd(int _order) {
        switch (_order) {

            case 2:
                ((NewsColumnView) ac).post(new SwapViews());
                break;
            case 3:
                ((NewsColumnView) ac).post(new SwapViews());
                break;
        }
    }
}

