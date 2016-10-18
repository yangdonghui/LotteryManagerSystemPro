package com.manager.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manager.Interface.CommonClickListener;
import com.manager.lotterypro.R;


/**
 * 站点申报 耗材申请选项 view
 * Created by Administrator on 2016/3/2 0002.
 */
public class SiteDeclareConsumableView extends LinearLayout implements View.OnClickListener{

    //上下文对象
    private Context mContext = null;
    //View对象
    private View mView = null;

    private TextView childTypeView;

    private int childType;
    private String typeInfo;

    private CommonClickListener clickListener;

    @Override
    public void onClick(View v) {
        if (childTypeView == v){
            //选择分类
            clickListener.doConfirm();
        }
    }

    public void setClicklistener(CommonClickListener clickListenerInterface) {
        this.clickListener = clickListenerInterface;
    }

    public SiteDeclareConsumableView(Context context) {
        super(context);

        mContext = context;
        initView();
    }

    public SiteDeclareConsumableView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.site_declare_consumable_layout, this, true);
        childTypeView = (TextView) mView.findViewById(R.id.site_declare_child_type_consumable);
        childTypeView.setOnClickListener(this);
    }

    public void setChildType(int type, String info) {
        childType = type;
        typeInfo = info;

        childTypeView.setText(info);
    }
}
