package com.manager.widgets;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.manager.lotterypro.R;


/**
 * 删除提示框
 * Created by Administrator on 2016/3/24 0024.
 */
public class DeleteTipDialog extends Dialog{

    Context context;
    private ClickListenerInterface clickListenerInterface;

    private Button confirmBtn, cancelBtn;

    public interface ClickListenerInterface {
        public void doConfirm();
        public void doCancel();
    }

    public DeleteTipDialog(Context context, int theme) {
        super(context, theme);

        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_tip);

        initView();
    }

    /**
     *初始化控件
     */
    private void initView() {
        confirmBtn = (Button) findViewById(R.id.delete_tip_btn1);
        confirmBtn.setOnClickListener(clickListener);
        cancelBtn = (Button) findViewById(R.id.delete_tip_btn2);
        cancelBtn.setOnClickListener(clickListener);
    }

    /**
     * 关闭处理
     */
    public void closeDialog() {
        this.dismiss();
    }

    public void setClicklistener(ClickListenerInterface clickListenerInterface) {
        this.clickListenerInterface = clickListenerInterface;
    }

    private ClickListener clickListener = new ClickListener();
    private class ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if (confirmBtn == v){
                //确定
                clickListenerInterface.doConfirm();
            }else if (cancelBtn == v){
                //取消
                clickListenerInterface.doCancel();
            }
        }
    }
}
