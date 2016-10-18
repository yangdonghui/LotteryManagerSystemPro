package com.manager.widgets;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.manager.lotterypro.R;


/**
 * 发帖类型 菜单提示框
 * Created by Administrator on 2016/3/24 0024.
 */
public class PhotoMenuDialog extends Dialog{

    Context context;

    //类型view
    private View tv1, tv2, tv3, tv4;

    private ClickListenerInterface clickListenerInterface;

    public interface ClickListenerInterface {
        public void doConfirm(int type);
    }

    public PhotoMenuDialog(Context context, int theme) {
        super(context, theme);

        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_menu_layout);

        initView();
    }

    private void initView(){
        tv1 = (View) findViewById(R.id.photo_menu_tv1);
        tv1.setOnClickListener(clickListener);
        tv2 = (View) findViewById(R.id.photo_menu_tv2);
        tv2.setOnClickListener(clickListener);
        tv3 = (View) findViewById(R.id.photo_menu_tv3);
        tv3.setOnClickListener(clickListener);
        tv4 = (View) findViewById(R.id.photo_menu_tv4);
        tv4.setOnClickListener(clickListener);
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
            if (v == tv1){
                //视频
                clickListenerInterface.doConfirm(0);
            }else if (v == tv2){
                //拍照
                clickListenerInterface.doConfirm(1);
            }else if (v == tv3){
                //从相册选择
                clickListenerInterface.doConfirm(2);
            }else if (v == tv4){
                closeDialog();
            }
        }
    }
}
