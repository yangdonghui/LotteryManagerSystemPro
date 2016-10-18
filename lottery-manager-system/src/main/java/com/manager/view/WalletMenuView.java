package com.manager.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manager.Interface.ICoallBack;
import com.manager.bean.WidgetBean1;
import com.manager.lotterypro.R;

import java.util.ArrayList;


/**
 * 钱包首页菜单栏 view
 * Created by Administrator on 2016/3/2 0002.
 */
public class WalletMenuView extends LinearLayout implements View.OnClickListener{

    //上下文对象
    private Context mContext = null;
    //View对象
    private View mView = null;

    private View btn1, btn2, btn3;
    private ArrayList<TextView> allRadioBtns = new ArrayList<>();

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

    public WalletMenuView(Context context) {
        this(context, null);
    }

    public WalletMenuView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WalletMenuView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        mContext = context;

        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.my_wallet_top_menu, this, true);

        btn1 = (View) mView.findViewById(R.id.wallet_menu_btn1);
        btn1.setOnClickListener(this);
        btn2 = (View) mView.findViewById(R.id.wallet_menu_btn2);
        btn2.setOnClickListener(this);
        btn3 = (View) mView.findViewById(R.id.wallet_menu_btn3);
        btn3.setOnClickListener(this);

        TextView tv1 = (TextView) mView.findViewById(R.id.wallet_menu_tv1);
        TextView tv2 = (TextView) mView.findViewById(R.id.wallet_menu_tv2);
        TextView tv3 = (TextView) mView.findViewById(R.id.wallet_menu_tv3);

        allRadioBtns.add(tv1);
        allRadioBtns.add(tv2);
        allRadioBtns.add(tv3);
    }

    /**
     * 根据用户不同， 菜单项不同
     * @param lists
     */
    public void updateMenu(ArrayList<WidgetBean1> lists) {

        for (int i=0;i<allRadioBtns.size();i++){
            WidgetBean1 att = lists.get(i);
            allRadioBtns.get(i).setText(att.getText());
            allRadioBtns.get(i).setTag(att.getId());

            Drawable drawable = mContext.getResources().getDrawable(att.getIcon());
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());//必须设置图片大小，否则不显示
            allRadioBtns.get(i).setCompoundDrawables(null, drawable, null, null);
        }
    }

    @Override
    public void onClick(View v) {
        int index = -1;
        if (btn1 == v){
            index = 0;
        }else if (btn2 == v){
            index = 1;
        }else if (btn3 == v){
            index = 2;
        }

        if (index != -1){
            icallBack.onItemClick(index);
        }
    }
}
