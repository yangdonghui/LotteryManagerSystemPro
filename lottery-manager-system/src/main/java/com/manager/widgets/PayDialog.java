package com.manager.widgets;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.manager.common.Tools;
import com.manager.lotterypro.R;


/**
 * 付款提示对话框
 * Created by Administrator on 2016/3/24 0024.
 */
public class PayDialog extends Dialog {

    //充值提示对话框
    public static final int DialogPayType = 0;
    //充值提示对话框
    public static final int DialogWithdrawType = 1;
    //充值提示对话框
    public static final int DialogSitePayType = 2;

    Context context;
    private ClickListenerInterface clickListenerInterface;

    private int mType = 0;
    private TextView mValueTv, mInfoTv;
    private String mValue, mInfo;
    //关闭按钮
    private ImageButton closeBtn;

    //支付方式相关
    private View PayWayView;
    private ViewGroup payViewGroup;

    private SecurityPasswordEditText passwordEditText;

    public interface ClickListenerInterface {
        public void doConfirm();
        public void doCancel();
    }

    public PayDialog(Context context, int type, String value, String info, int theme) {
        super(context,theme);

        this.context = context;
        mType = type;
        mValue = value;
        mInfo = info;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_pay_layout);

        /*WindowManager.LayoutParams params = this.getWindow().getAttributes();
        params.width = 680;
        params.height = 580 ;
        this.getWindow().setAttributes(params);*/

        initView();

        Tools.showSoftInput(context, passwordEditText.getSecurityEdit());
    }

    /**
     *初始化控件
     */
    private void initView() {
        mInfoTv = (TextView) findViewById(R.id.doalog_pay_tv1);
        mInfoTv.setText(mInfo);

        mValueTv = (TextView) findViewById(R.id.doalog_pay_tv2);
        mValueTv.setText(mValue);

        closeBtn = (ImageButton) findViewById(R.id.pay_dialog_close_btn);
        closeBtn.setOnClickListener(clickListener);

        passwordEditText = (SecurityPasswordEditText) findViewById(R.id.dialog_pay_pass_edit);
        passwordEditText.setEditInterface(new SecurityPasswordEditText.EditInterface() {
            @Override
            public void doConfirm() {
                //密码输入完成 进行交易
                Log.e("", "");
                clickListenerInterface.doConfirm();
            }
        });

        PayWayView = (View) findViewById(R.id.dialog_pay_way_view);
        payViewGroup = (ViewGroup) findViewById(R.id.dialog_pay_way_include);
    }

    /**
     * 关闭处理
     */
    public void closeDialog() {
        Tools.hideSoftInput(context, passwordEditText.getSecurityEdit());
        this.dismiss();
    }


    private void updateInfo() {
        if (mType == 1){
            //转账 站点缴费 显示
            PayWayView.setVisibility(View.VISIBLE);
            PayWayView.setOnClickListener(clickListener);
        }
    }

    public void setClicklistener(ClickListenerInterface clickListenerInterface) {
        this.clickListenerInterface = clickListenerInterface;
    }

    private ClickListener clickListener = new ClickListener();
    private class ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if (closeBtn == v){
                //关闭按钮
                Tools.hideSoftInput(context, passwordEditText.getSecurityEdit());
                clickListenerInterface.doCancel();
            }else if (PayWayView == v){
                //选择 支付方式界面

            }
        }

    }

    ;

}
