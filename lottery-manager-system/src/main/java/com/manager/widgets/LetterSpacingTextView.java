package com.manager.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ScaleXSpan;
import android.util.AttributeSet;
import android.widget.TextView;

import com.manager.lotterypro.R;

/**
 * 示例：设置 TextView 的字间距
 * @author Pedro Barros (pedrobarros.dev at gmail.com)
 * @since May 7, 2013
 */
public class LetterSpacingTextView extends TextView {

    private float letterSpacing = LetterSpacing.NORMAL;
    private CharSequence originalText = "";


    public LetterSpacingTextView(Context context) {
        this(context, null);
    }

    public LetterSpacingTextView(Context context, AttributeSet attrs){
        this(context, attrs, 0);
    }

    public LetterSpacingTextView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LetterSpacingTextView, defStyle, 0);
        letterSpacing = a.getFloat(R.styleable.LetterSpacingTextView_letterSpacing, 0);

    }

    public float getLetterSpacing() {
        return letterSpacing;
    }

    public void setLetterSpacing(float letterSpacing) {
        this.letterSpacing = letterSpacing;
        applyLetterSpacing();
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        originalText = text;
        applyLetterSpacing();
    }

    @Override
    public CharSequence getText() {
        return originalText;
    }

    private void applyLetterSpacing() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < originalText.length(); i++) {
            builder.append(originalText.charAt(i));
            if(i+1 < originalText.length()) {
                builder.append("\u00A0");
            }
        }
        SpannableString finalText = new SpannableString(builder.toString());
        if(builder.toString().length() > 1) {
            for(int i = 1; i < builder.toString().length(); i+=2) {
                finalText.setSpan(new ScaleXSpan((letterSpacing+1)/10), i, i+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        super.setText(finalText, BufferType.SPANNABLE);
    }

    public class LetterSpacing {
        public final static float NORMAL = 0;
    }
}
