package com.manager.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manager.common.Tools;
import com.manager.lotterypro.R;


/**
 * 站点申报 故障报修选项 view
 * Created by Administrator on 2016/3/2 0002.
 */
public class SearchView extends LinearLayout {

    //上下文对象
    private Context mContext = null;
    //View对象
    private View mView = null;

    private View rootView;
    private EditText editText;
    private String hintText;
    private TextView btn;

    //类型
    private int searchIntype;//1: 社区 搜索 3: 搜索好友 2：产品搜索
    private int cancelState = 0;

    public interface ICoallBack {
        public void onClick();
    }
    private ICoallBack iCoallBack;
    public void setiCoallBack(ICoallBack iCoallBack){
        this.iCoallBack = iCoallBack;
    }

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SearchView, defStyle, 0);
        hintText = a.getString(R.styleable.SearchView_hint_text);
        searchIntype = a.getInteger(R.styleable.SearchView_type, 0);
        cancelState = a.getInteger(R.styleable.SearchView_cancel_btn, 0);

        mContext = context;
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.search_view_layout, this, true);
        rootView = (View) mView.findViewById(R.id.search_view_rootview);

        editText = (EditText) mView.findViewById(R.id.search_layout_editText);
        editText.setHint(hintText);
        editText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取焦点
                editText.setFocusable(true);
                editText.setFocusableInTouchMode(true);
                editText.setCursorVisible(true);
                Tools.showSoftInput(mContext, editText);
            }
        });

        btn = (TextView) mView.findViewById(R.id.search_view_btn);
        if (cancelState == 1){
            btn.setVisibility(GONE);
        }else{
            btn.setVisibility(VISIBLE);
            if (btn.getVisibility() == VISIBLE){
                btn.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (iCoallBack != null){
                            iCoallBack.onClick();
                        }
                    }
                });
            }
        }
    }

    /**
     * 设置提示文字
     * @param stringId
     */
    public void setEditHint(int stringId) {
        if (editText != null) {
            editText.setHint(stringId);
        }
    }

    public void setKeyListener(OnKeyListener onKeyListener) {
        if (editText != null) {
            editText.setOnKeyListener(onKeyListener);
        }
    }


    /**
     * 编辑的软键盘按键操作
     * @param onEditorActionListener
     */
    public void setEditorActionListener(TextView.OnEditorActionListener onEditorActionListener) {
        if (editText != null) {
            editText.setOnEditorActionListener(onEditorActionListener);
        }
    }


    /**
     * 更改背景色
     * @param color
     */
    public void setBgColor(int color){
        rootView.setBackgroundColor(mContext.getResources().getColor(color));
    }
}
