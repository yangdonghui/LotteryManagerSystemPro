package com.manager.common;

import android.graphics.Paint;

/**
 * @author donghuiyang
 * @create time 2016/4/20 0020.
 */
public class FontTools {

    /**
     * 获取文字高度
     * @param fontSize
     * @return
     */
    public static int getFontHeight(float fontSize)
    {
        Paint paint = new Paint();
        paint.setTextSize(fontSize);
        Paint.FontMetrics fm = paint.getFontMetrics();
        return (int) Math.ceil(fm.descent - fm.top) /*+ 2*/;
    }
}
