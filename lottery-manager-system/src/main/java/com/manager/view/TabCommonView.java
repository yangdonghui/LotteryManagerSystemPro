package com.manager.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manager.common.Tools;
import com.manager.lotterypro.R;

import java.util.LinkedList;


/**
 * 通用tab标签 view
 * Created by Administrator on 2016/3/2 0002.
 */
public class TabCommonView extends LinearLayout implements View.OnClickListener{

    //选项卡下划线长度
    private int lineWidth = 0;
    //偏移量（手机屏幕宽度/3-选项卡长度）/2
    private int offset = 0;

    //上下文对象
    private Context mContext = null;
    //View对象
    private View mView = null;
    private ViewGroup textViewGroup;
    private LinkedList<TextView> textViews = new LinkedList<>();

    private int tabNum;
    private String[] tabTexts;

    private int tabType = 0;

    //当前显示的选项卡位置
    private int currentIndex = 0;
    //代表选项卡下的下划线的imageview
    private ImageView cursor = null;

    int screenWidth;


    /**
     * 一定一个接口
     */
    public interface ICoallBack{
        public void onClickView(int index);
    }
    /**
     * 初始化接口变量
     */
    ICoallBack icallBack = null;
    /**
     * 自定义控件的自定义事件
     * @param iBack 接口类型
     */
    public void setonClick(ICoallBack iBack)
    {
        icallBack = iBack;
    }


    public TabCommonView(Context context) {
        this(context, null);
    }

    public TabCommonView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabCommonView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        mContext = context;

        initView();
    }



    /**
     * 初始化View
     */
    private void initView() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.tab_common_layout, this, true);
        textViewGroup = (ViewGroup) mView.findViewById(R.id.tab_common_viewgroup);
        cursor = (ImageView) mView.findViewById(R.id.tab_common_cursor);
        updateImageViewCursor(R.drawable.rect_radius_14_2);
    }

    private void initImageView() {

        //获取屏幕宽度
        screenWidth = Tools.getWindowWidth((Activity)mContext);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, cursor.getLayoutParams().height);
        lp.width = screenWidth / tabNum;
        cursor.setLayoutParams(lp);
        lineWidth = lp.width;

        Log.e("","");
    }

    public TabCommonView setTabType(int tabType) {
        this.tabType = tabType;
        return this;
    }

    public void setTexts(final  String[] texts){
        tabTexts = texts;
        tabNum = tabTexts.length;
        initImageView();

        for (int i = 0; i < tabNum; i++) {
            TextView tv = new TextView(mContext);
            tv.setText(tabTexts[i]);
            tv.setTextAppearance(mContext, R.style.text_size_14_text_color_3);
            tv.setGravity(Gravity.CENTER);
            tv.setClickable(true);
            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            layout.gravity= Gravity.CENTER_VERTICAL|Gravity.CENTER;
            layout.weight = 1;
            tv.setLayoutParams(layout);

            textViewGroup.addView(tv);

            tv.setOnClickListener(this);
            textViews.add(tv);
        }
    }

    public void updateCursor(float positionOffset, int position){
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)cursor.getLayoutParams();
        /**
         * 利用currentIndex(当前所在页面)和position(下一个页面)以及offset来
         * 设置mTabLineIv的左边距 滑动场景：
         * 从左到右分别为0,1,2
         * 0->1; 1->2; 2->1; 1->0
         */

        if (currentIndex == position){

            lp.leftMargin = (int) (positionOffset * lineWidth + currentIndex * lineWidth);

        }else if (currentIndex > position){

            lp.leftMargin = (int) (-(1 - positionOffset) * lineWidth + currentIndex * lineWidth);

        }
        cursor.setLayoutParams(lp);
    }
    public void updateCursor(int position){
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)cursor.getLayoutParams();
        /**
         * 利用currentIndex(当前所在页面)和position(下一个页面)以及offset来
         * 设置mTabLineIv的左边距 滑动场景：
         * 从左到右分别为0,1,2
         * 0->1; 1->2; 2->1; 1->0
         */

        if (currentIndex == position){

            lp.leftMargin = (int) (currentIndex * lineWidth);

        }else if (currentIndex > position){

            //lp.leftMargin = (int) (-(1 - positionOffset) * lineWidth + currentIndex * lineWidth);

        }
        cursor.setLayoutParams(lp);
    }

    public void updateTab(int index) {
        setCurrentIndex(index);

        for (int i=0;i<tabNum;i++){
            if (currentIndex == i) {
                textViews.get(i).setTextAppearance(mContext, R.style.text_size_14_text_bg_color_6);
            }else{
                textViews.get(i).setTextAppearance(mContext, R.style.text_size_14_text_color_3);
            }
        }
    }
    public void updateTab(int index, int textStyle) {
        setCurrentIndex(index);

        for (int i=0;i<tabNum;i++){
            if (currentIndex == i) {
                textViews.get(i).setTextAppearance(mContext, textStyle);
            }else{
                textViews.get(i).setTextAppearance(mContext, R.style.text_size_14_text_color_3);
            }
        }
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public TabCommonView setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
        return this;
    }

    /**
     * 更新线的图片
     * @param styleID
     */
    public void updateImageViewCursor(int styleID){
        cursor.setImageResource(styleID);
    }

    public void updateView(){
        if (tabType == 1){
            //走势图相关
            if (currentIndex == 0) {
                updateImageViewCursor(R.drawable.rect_radius_32_1);
                updateTab(currentIndex, R.style.text_size_14_text_bg_color_19);
            } else if (currentIndex == 1) {
                updateImageViewCursor(R.drawable.rect_radius_32_2);
                updateTab(currentIndex, R.style.text_size_14_text_bg_color_18);
            }
        }else if (tabType == 2){
            //开 中奖信息
            updateTab(currentIndex, R.style.text_size_14_text_bg_color_6);
        }

        updateCursor(currentIndex);
    }

    public String getTabText(int index){
        return tabTexts[index];
    }

    @Override
    public void onClick(View v) {
        for (int i=0;i<tabNum;i++){
            if (textViews.get(i) == v){
                currentIndex = i;
                updateView();

                icallBack.onClickView(currentIndex);
                break;
            }
        }
    }
}
