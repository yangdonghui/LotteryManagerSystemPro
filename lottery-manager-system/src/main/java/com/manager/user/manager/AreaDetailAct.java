package com.manager.user.manager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.manager.bean.BettingshopBean;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 辖区 详细界面
 * @author donghuiyang
 * @create time 2016/6/28 0028.
 */
public class AreaDetailAct extends Activity implements View.OnClickListener{
    //返回按钮
    private View backBtn;

    //相关空间
    private ImageView iconImgV;
    private TextView nameTv, accountTv, addressTv, phoneTv, moneyTv;

    //站点辖区属性
    private BettingshopBean bettingshopBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.area_detail_layout);

        Intent intent = getIntent();
        bettingshopBean = (BettingshopBean)intent.getSerializableExtra("data");

        initTopView();
        initView();
        updateView();
    }

    /**
     * 设置标题
     */
    private void initTopView() {
        ViewGroup topView = (ViewGroup) findViewById(R.id.area_detail_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.manager_str_17);

        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    private void initView() {
        iconImgV = (ImageView) findViewById(R.id.area_detail_icon_imgv);

        nameTv = (TextView) findViewById(R.id.area_detail_realname_tv);
        accountTv = (TextView) findViewById(R.id.area_detail_name_tv);
        addressTv = (TextView) findViewById(R.id.area_detail_address_tv);
        phoneTv = (TextView) findViewById(R.id.area_detail_phone_tv);
        moneyTv = (TextView) findViewById(R.id.area_detail_money_tv);
    }

    private void updateView() {
        if (bettingshopBean != null){
            if (bettingshopBean.getIconUrl() != null && !bettingshopBean.getIconUrl().equals("")){
                ImageLoader.getInstance().displayImage(bettingshopBean.getIconUrl(), iconImgV);
            }

            nameTv.setText(bettingshopBean.getRealName());
            accountTv.setText(bettingshopBean.getBettingshopID());
            addressTv.setText(bettingshopBean.getAddress());
            phoneTv.setText(bettingshopBean.getUserPhone());
            moneyTv.setText(bettingshopBean.getSaleMoney());
        }
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v) {
            //返回按钮
            SysApplication.backBtn(this, null);
        }
    }
}
