package com.manager.user.site;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;

import com.manager.lotterypro.R;

/**
 * 站点申报 确认界面
 * @author donghuiyang
 * @create time 2016/4/19 0019.
 */
public class SiteDeclareToDetailAct extends Activity implements View.OnClickListener{

    //title
    private TextView titleView = null;
    //返回按钮
    private View backBtn = null;

    //子项view
    private ViewStub stub;
    private Button okBtn, cancelBtn, changeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.site_declare_to_detail_layout);

        initView();
    }

    private void initView() {
        ViewGroup topView = (ViewGroup)findViewById(R.id.site_declare_to_detail_topview);
        titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        titleView.setText(R.string.site_declare_record_info);

        backBtn.setOnClickListener(this);

        stub = (ViewStub) findViewById(R.id.declare_to_detail_viewStub);
        stub.setLayoutResource(R.layout.consumable_detail_items_layout);
        stub.inflate();

        //按钮
        okBtn = (Button) findViewById(R.id.declare_to_detail_ok_btn);
        cancelBtn = (Button) findViewById(R.id.declare_to_detail_cancel_btn);
        changeBtn = (Button) findViewById(R.id.declare_to_detail_change_btn);
        okBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        changeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v || changeBtn == v){
            //返回按钮  //变更按钮
            finish();
        }else if(okBtn == v){
            //提交按钮

        }else if(cancelBtn == v){
            //取消按钮
            Intent intent = SiteDeclareAct.siteDeclareAct.getIntent();
            intent.putExtra("cancel",1);

            finish();
        }
    }
}
