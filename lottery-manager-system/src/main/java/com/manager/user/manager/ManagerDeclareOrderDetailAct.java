package com.manager.user.manager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

import com.manager.bean.DeclareOrdersBean;
import com.manager.community.widgets.MultiImageView;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;

/**
 * 管理员 申报订单管理界面
 * @author donghuiyang
 * @create time 2016/6/28 0028.
 */
public class ManagerDeclareOrderDetailAct extends Activity implements View.OnClickListener{
    //返回按钮
    private View backBtn = null;

    private View imgView;

    //子项view
    private ViewStub stub;
    private ViewStub imgViewStub;
    private MultiImageView multiImageView;

    //订单属性
    private DeclareOrdersBean ordersBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_declare_order_to_detail_layout);

        Intent intent = getIntent();
        ordersBean = (DeclareOrdersBean) intent.getSerializableExtra("data");

        initTopView();
        initView();
        updateView();
    }

    /**
     * 获取控件
     */
    private void initTopView() {
        ViewGroup topView = (ViewGroup) findViewById(R.id.manager_declare_order_to_detail_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        titleView.setText(R.string.site_declare_record_info);

        backBtn.setOnClickListener(this);
    }

    private void initView() {

        stub = (ViewStub) findViewById(R.id.manager_declare_order_to_detail_viewStub);
        if (ordersBean != null){
            if (ordersBean.getType() == 1){
                //维修
                stub.setLayoutResource(R.layout.fault_detail_items_layout);
                stub.inflate();

                imgView = (View) findViewById(R.id.fault_detail_img_view);
                imgView.setVisibility(View.VISIBLE);

                imgViewStub = (ViewStub) findViewById(R.id.fault_detail_linkOrImgViewStub);

            }else if (ordersBean.getType() == 0){
                //耗材
                stub.setLayoutResource(R.layout.consumable_detail_items_layout);
                stub.inflate();
            }

        }
    }

    private void updateView() {
        if (imgView != null && imgView.getVisibility() == View.VISIBLE){
            imgViewStub.setLayoutResource(R.layout.viewstub_imgbody);
            imgViewStub.inflate();

            MultiImageView.MAX_WIDTH = 0;
            multiImageView = (MultiImageView) findViewById(R.id.multiImagView);
        }
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //返回按钮
            SysApplication.backBtn(this, null);
        }
    }
}
