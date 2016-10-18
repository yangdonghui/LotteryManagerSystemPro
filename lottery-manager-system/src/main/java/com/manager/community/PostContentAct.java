package com.manager.community;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.manager.common.Tools;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;

/**
 * 帖子内容界面
 * @author donghuiyang
 * @create time 2016/4/20 0020.
 */
public class PostContentAct extends Activity implements View.OnClickListener{
    //返回按钮
    private View backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_detail_layout);

        initTopView();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //隐藏软键盘
        Tools.hideSoftInput(this);
    }

    /**
     * 获取控件对象
     */
    private void initTopView() {
        ViewGroup topView = (ViewGroup)findViewById(R.id.post_detail_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.site_declare_info7);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == backBtn){
            //返回按钮
            SysApplication.backBtn(this, null);
        }
    }
}
