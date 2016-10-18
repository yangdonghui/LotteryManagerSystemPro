package com.manager.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.manager.view.anim.ScaleAnimationHelper;
import com.manager.lotterypro.R;
import com.manager.widgets.VerticalScrollTextView;

/**
 * 首界面 浮动的消息栏
 * @author donghuiyang
 * @create time 2016/4/27 0027.
 */
public class NewsColumnView extends LinearLayout implements View.OnClickListener{

    public static int NewsNone = 0;
    public static int NewsRunning = 1;
    public static int NewsOpen = 2;
    public static int NewsClose = 3;

    private Context context;

    //View对象
    private View mView = null;
    //布局填充器对象
    private LayoutInflater mLayoutInflater = null;

    private View mCloseBtn;
    private VerticalScrollTextView mTextView;
    private ImageView mTextTypeImgV;

    //当前是否展开状态 初始打开状态
    private int isOpen = NewsClose;
    private float maxScale = 1.0f, minScale = 0.0f, curScale;

    ScaleAnimationHelper scaleHelper1, scaleHelper2;

    public NewsColumnView(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public NewsColumnView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        mView = LayoutInflater.from(context).inflate(R.layout.news_column_main_view, this, true);

        mTextView = (VerticalScrollTextView) mView.findViewById(R.id.news_column_content_view);
        mTextView.setOnClickListener(this);

        mCloseBtn = (View) mView.findViewById(R.id.news_column_close_btn);
        mCloseBtn.setOnClickListener(this);

        mTextTypeImgV = (ImageView) mView.findViewById(R.id.news_column_type_icon_imageview);


        this.setVisibility(GONE);
    }

    public void runEnterAnim() {
        this.setVisibility(VISIBLE);

        scaleHelper2 = new ScaleAnimationHelper(this, NewsOpen, 300);
        scaleHelper2.ScaleOutAnimation(this);
        isOpen = NewsRunning;
    }

    public void runExitAnim() {
        scaleHelper1 = new ScaleAnimationHelper(this, NewsClose, 300);
        scaleHelper1.ScaleInAnimation(this);
        isOpen = NewsRunning;
    }

    public void updateView(int state) {
        this.clearAnimation();

        if (state == NewsOpen) {
            scaleHelper2 = null;
        }else if(state == NewsClose){
            scaleHelper1 = null;

            this.setVisibility(GONE);
        }

        isOpen = state;
    }

    /**
     * 广告栏是否打开
     * @return
     */
    public int isOpening(){
        if (isOpen == NewsOpen){
            return 0;
        }else if (isOpen == NewsClose){
            return 1;
        }

        return -1;
    }

    @Override
    public void onClick(View v) {
        if (isOpen == NewsRunning) return;

        if(v == mCloseBtn) {
            //关闭 启动关闭动画
            runExitAnim();
        }/*else if(v == mIconBtn){
            if (isOpen == NewsClose){
                runEnterAnim();
            }else if (isOpen == NewsOpen){
                runExitAnim();
            }
        }*/
    }
}
