package com.manager.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.manager.lotterypro.R;


/**
 * 发帖 输入栏 view
 * Created by Administrator on 2016/3/2 0002.
 */
public class InputView extends LinearLayout {

    //上下文对象
    private Context mContext = null;
    //View对象
    private View mView = null;

    //输入框
    private EditText mEditTextContent;
    //发送按钮
    private View sendBtn;

    public interface ICoallBack {
        public void onSend(String text);
    }

    private ICoallBack iCoallBack;
    public void setonClick(ICoallBack iCoallBack){
        this.iCoallBack = iCoallBack;
    }

    public InputView(Context context) {
        super(context);

        mContext = context;
        initView();
    }

    public InputView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.input_view_layout, this, true);

        mEditTextContent = (EditText) mView.findViewById(R.id.input_view_editText);
        sendBtn = (View) mView.findViewById(R.id.input_view_send_btn);
        sendBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送消息按钮
                iCoallBack.onSend(mEditTextContent.getText().toString());
            }
        });
    }

    /**
     * 设置编辑框内容
     * @param content
     */
    public void setEditTextContent(String content){
        this.mEditTextContent.setText(content);
    }
}
