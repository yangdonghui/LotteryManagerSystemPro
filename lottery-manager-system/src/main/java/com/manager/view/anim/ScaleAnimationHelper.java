package com.manager.view.anim;

import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;

/**
 * @author donghuiyang
 * @create time 2016/4/27 0027.
 */
public class ScaleAnimationHelper {
    View con;
    int order;
    DisplayNextView listener;

    int delayTime = 500;

    public ScaleAnimationHelper(View con, int order, int delayTime) {
        this.con = con;
        this.order = order;
        this.delayTime = delayTime;

        listener = new DisplayNextView(con, order);
    }

    ScaleAnimation myAnimation_Scale;

    //放大的类,不需要设置监听器
    public void ScaleOutAnimation(View view) {
        myAnimation_Scale = new ScaleAnimation(0.01f, 1.0f, 1f, 1f);
        myAnimation_Scale.setInterpolator(new AccelerateInterpolator());
        myAnimation_Scale.setAnimationListener(listener);
        AnimationSet aa = new AnimationSet(true);
        aa.addAnimation(myAnimation_Scale);
        aa.setDuration(delayTime);
        aa.setFillAfter(true);
        view.startAnimation(aa);
    }

    public void ScaleInAnimation(View view) {

        myAnimation_Scale = new ScaleAnimation(1.0f, 0.01f, 1.0f, 1.0f/*,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f*/);
        myAnimation_Scale.setInterpolator(new AccelerateInterpolator());
        myAnimation_Scale.setAnimationListener(listener);
        //缩小Layout的类
        AnimationSet aa = new AnimationSet(true);
        aa.addAnimation(myAnimation_Scale);
        aa.setDuration(delayTime);
        aa.setFillAfter(true);
        view.startAnimation(aa);
    }
}
