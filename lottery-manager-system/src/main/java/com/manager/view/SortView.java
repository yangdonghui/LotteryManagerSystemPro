package com.manager.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manager.lotterypro.R;


/**
 * 发帖 输入栏 view
 * Created by Administrator on 2016/3/2 0002.
 */
public class SortView extends LinearLayout{

    private final static int TabNum = 4;

    //上下文对象
    private Context mContext = null;
    //View对象
    private View mView = null;

    private ViewGroup[] childViews = new ViewGroup[TabNum];
    private int tabIndex = 0;
    private int[] tabState = new int[4];


    /**
     * 一定一个接口
     */
    public interface ICoallBack{
        public void onClickView(int index, int upOrdown);
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

    public SortView(Context context) {
        super(context);

        mContext = context;
        initView();
    }

    public SortView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.sort_view_layout, this, true);
        childViews[0] = (ViewGroup) mView.findViewById(R.id.sort_view_tab1);
        childViews[1] = (ViewGroup) mView.findViewById(R.id.sort_view_tab2);
        childViews[2] = (ViewGroup) mView.findViewById(R.id.sort_view_tab3);
        childViews[3] = (ViewGroup) mView.findViewById(R.id.sort_view_tab4);

        for (int i=0;i<TabNum;i++){
            tabState[i] = 0;

            childViews[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Click(v);
                }
            });
        }
    }

    private void updateTabView(int index) {
        if (index == -1)return;
        if (index == tabIndex){
            if (tabState[index] == 0){
                tabState[index] = 1;
            }else if(tabState[index] == 1){
                tabState[index] = 0;
            }
            arrowsView(index);
            return;
        }

        tabIndex = index;
        for (int i=0;i<TabNum;i++){
            if (tabIndex == i){
                //更改选中tab的样式
                childViews[i].setBackgroundColor(getResources().getColor(R.color.text_color_3));
                ((TextView)childViews[i].getChildAt(0)).setTextAppearance(mContext, R.style.text_size_14_text_white);
            }else{
                childViews[i].setBackgroundColor(Color.WHITE);
                ((TextView)childViews[i].getChildAt(0)).setTextAppearance(mContext, R.style.text_size_14_text_color_3);
            }
        }
    }

    private void arrowsView(int index){
        if (index != 0){
            if (tabState[index] == -1 || tabState[index] == 0){
                //从高到低
                ((ImageView)childViews[index].getChildAt(1)).setImageResource(R.mipmap.billing_nav_02);
            }else if (tabState[index] == 1){
                //从低到高
                ((ImageView)childViews[index].getChildAt(1)).setImageResource(R.mipmap.billing_nav_01);

            }
        }
    }

    /**
     * 点击事件监听
     * @param v
     */
    public void Click(View v) {
        int index = -1;
        for (int i=0;i<TabNum;i++){
            if (childViews[i] == v){
                index = i;
                break;
            }
        }

        updateTabView(index);

        // 返回这个自定义控件的值，使用回调实现
        icallBack.onClickView(tabIndex, tabState[tabIndex]);

    }
}
