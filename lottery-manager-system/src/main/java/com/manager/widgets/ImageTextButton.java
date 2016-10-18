package com.manager.widgets;

/**
 * Created by Administrator on 2016/2/26 0026.
 */
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manager.lotterypro.R;


public class ImageTextButton extends LinearLayout {
    private ImageView iv;
    private TextView tv;

    public ImageTextButton(Context context) {
        super(context);
    }

    public ImageTextButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.image_text_buttton, this,
                true);
        iv = (ImageView) findViewById(R.id.iv);
        tv = (TextView) findViewById(R.id.tv);
    }

    public void setDefaultImageResource(int resId) {
        iv.setImageResource(resId);
    }

    public void setDefaultTextViewText(String text) {
        tv.setText(text);
    }

    /**
     * @param resId
     */
    public void setImageResource(int resId) {
        iv.setImageResource(resId);
    }

    /**
     * @param text
     */
    public void setTextViewText(String text) {
        tv.setText(text);
    }

    /**
     * @param color
     */
    public void setTextColor(int color) {
        tv.setTextColor(color);
    }

}