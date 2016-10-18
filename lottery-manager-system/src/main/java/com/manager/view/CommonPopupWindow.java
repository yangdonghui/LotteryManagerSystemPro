package com.manager.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.manager.Interface.ICoallBack3;
import com.manager.lotterypro.R;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;

/**
 * 地址三级联动弹出界面
 * @author donghuiyang
 * @create time 2016/6/21 0021.
 */
public class CommonPopupWindow extends PopupWindow implements View.OnClickListener, OnWheelChangedListener {

    private Context context;

    private View mMenuView;

    private WheelView mWheelView;
    private TextView mBtnConfirm;
    private TextView mBtnCancel;

    private int curIndex= 0;
    private String[] datas;

    private static final int WheelItemNum = 7;

    private int wheelNum = WheelItemNum;

    /**
     * 初始化接口变量
     */
    ICoallBack3 icallBack = null;
    /**
     * 自定义控件的自定义事件
     * @param iBack 接口类型
     */
    public void setonClick(ICoallBack3 iBack)
    {
        icallBack = iBack;
    }


    public CommonPopupWindow(Activity context, String[] datas, int curIndex) {
        super(context);

        if (curIndex < 0){
            curIndex = 0;
        }

        this.context = context;
        this.curIndex = curIndex;
        this.datas = datas;

        if (this.datas.length < WheelItemNum){

            if (this.datas.length % 2 == 0){
                //偶数 +1
                wheelNum = this.datas.length + 1;
            }else{
                //奇数
                wheelNum = this.datas.length;
            }
        }

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.common_menu_layout, null);

        init();

        setUpViews();
        setUpListener();
        setUpData();

    }

    private void init() {
        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LinearLayout.LayoutParams.FILL_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.popupAnimation);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xFF000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
    }

    private void setUpViews() {
        mWheelView = (WheelView) mMenuView.findViewById(R.id.common_menu_wheelview);

        mBtnConfirm = (TextView) mMenuView.findViewById(R.id.common_menu_btn_confirm);
        mBtnCancel = (TextView) mMenuView.findViewById(R.id.common_menu_btn_cancel);
        mBtnCancel.setOnClickListener(this);
        mBtnConfirm.setOnClickListener(this);
    }

    private void setUpListener() {
        // 添加change事件
        mWheelView.addChangingListener(this);

        // 添加onclick事件
        mBtnConfirm.setOnClickListener(this);
    }

    private void setUpData() {
        mWheelView.setViewAdapter(new ArrayWheelAdapter<String>(context, datas));
        // 设置可见条目数量
        mWheelView.setVisibleItems(wheelNum);
        mWheelView.setCurrentItem(curIndex);
    }

    /**
     * 更新数据
     */
    private void updateWheel() {
        curIndex = mWheelView.getCurrentItem();
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        // TODO Auto-generated method stub
        if (wheel == mWheelView) {
            updateWheel();
        }
    }

    @Override
    public void onClick(View v) {
        if (mBtnCancel == v){
            //取消按钮
            icallBack.doCancel();
        }else if (mBtnConfirm == v){
            //确定按钮
            icallBack.doConfirm(mWheelView.getCurrentItem());
        }
    }

}
