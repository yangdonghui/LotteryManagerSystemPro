package com.manager.widgets;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.manager.lotterypro.R;


/**
 *
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉 简密输入框
 */
public class SecurityPasswordEditText extends LinearLayout {
    private EditText mEditText;
    private ImageView oneTextView;
    private ImageView twoTextView;
    private ImageView threeTextView;
    private ImageView fourTextView;
    private ImageView fiveTextView;
    private ImageView sixTextView;
    LayoutInflater inflater;
    ImageView[] imageViews;
    View contentView;

    private EditInterface editInterface;

    public interface EditInterface {
        public void doConfirm();
    }

    public void setEditInterface(EditInterface editInterface) {
        this.editInterface = editInterface;
    }

    public SecurityPasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflater = LayoutInflater.from(context);
        builder = new StringBuilder();
        initWidget();
    }

    private void initWidget() {
        contentView = inflater.inflate(R.layout.sdk2_simple_pwd_widget, null);
        mEditText = (EditText) contentView
                .findViewById(R.id.sdk2_pwd_edit_simple);

        oneTextView = (ImageView) contentView
                .findViewById(R.id.pay_password_view_img_0);
        twoTextView = (ImageView) contentView
                .findViewById(R.id.pay_password_view_img_1);
        threeTextView = (ImageView) contentView
                .findViewById(R.id.pay_password_view_img_2);
        fourTextView = (ImageView) contentView
                .findViewById(R.id.pay_password_view_img_3);
        fiveTextView = (ImageView) contentView
                .findViewById(R.id.pay_password_view_img_4);
        sixTextView = (ImageView) contentView
                .findViewById(R.id.pay_password_view_img_5);

        LayoutParams lParams = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);

        mEditText.addTextChangedListener(mTextWatcher);
        mEditText.setOnKeyListener(keyListener);
        imageViews = new ImageView[] { oneTextView, twoTextView, threeTextView,
                fourTextView, fiveTextView, sixTextView };
        this.addView(contentView, lParams);
    }

    TextWatcher mTextWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if (s.toString().length() == 0) {
                return;
            }

            if (builder.length() < 6) {
                builder.append(s.toString());
                setTextValue();
            }

            if (builder.length() >= 6){
                //回调
                editInterface.doConfirm();
            }
            s.delete(0, s.length());
        }

    };

    OnKeyListener keyListener = new OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {

            if (keyCode == KeyEvent.KEYCODE_DEL
                    && event.getAction() == KeyEvent.ACTION_UP) {
                delTextValue();
                return true;
            }
            return false;
        }
    };

    private void setTextValue() {

        String str = builder.toString();
        int len = str.length();

        if (len <= 6) {
            imageViews[len - 1].setVisibility(View.VISIBLE);
        }
        if (len == 6) {
            Log.i("","回调");
            Log.i("","支付密码" + str);
            if (mListener != null) {
                mListener.onNumCompleted(str);
            }
            Log.i("jone", builder.toString());
            //FunctionUtils.hideSoftInputByView(getContext(), mEditText);
        }
    }

    private void delTextValue() {
        String str = builder.toString();
        int len = str.length();
        if (len == 0) {
            return;
        }
        if (len > 0 && len <= 6) {
            builder.delete(len - 1, len);
        }
        imageViews[len - 1].setVisibility(View.INVISIBLE);
        ;
    }

    StringBuilder builder;

    public interface SecurityEditCompleListener {
        public void onNumCompleted(String num);
    }

    public SecurityEditCompleListener mListener;

    public void setSecurityEditCompleListener(
            SecurityEditCompleListener mListener) {
        this.mListener = mListener;
    }

    public void clearSecurityEdit() {
        if (builder != null) {
            if (builder.length() == 6) {
                builder.delete(0, 6);
            }
        }
        for (ImageView tv : imageViews) {
            tv.setVisibility(View.INVISIBLE);
        }

    }

    public EditText getSecurityEdit() {
        return this.mEditText;
    }
}
