package com.manager.widgets;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.manager.lotterypro.R;


/**
 * listview（带checkbox控件） 提示对话框
 * Created by Administrator on 2016/3/24 0024.
 */
public class TipDialog extends Dialog{

    Context context;
    private ClickListenerInterface clickListenerInterface;

    private ListView mListView;
    private Button confirmBtn, cancelBtn;

    public interface ClickListenerInterface {
        public void doConfirm();
        public void doCancel();
    }

    public TipDialog(Context context, int theme) {
        super(context, theme);

        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.delivery_failure_tip);

        initView();
    }

    /**
     *初始化控件
     */
    private void initView() {
        confirmBtn = (Button) findViewById(R.id.delivery_failure_tip_btn1);
        confirmBtn.setOnClickListener(clickListener);
        cancelBtn = (Button) findViewById(R.id.delivery_failure_tip_btn2);
        cancelBtn.setOnClickListener(clickListener);

        mListView = (ListView) findViewById(R.id.delivery_failure_tip_listview);
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
