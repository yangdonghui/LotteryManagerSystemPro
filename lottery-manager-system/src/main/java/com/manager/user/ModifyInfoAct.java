package com.manager.user;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.manager.bean.UserBean;
import com.manager.common.Tools;
import com.manager.helper.UserHelper;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.view.SelectAreaPopupWindow;

/**
 * 修改个人资料界面
 *
 * @author donghuiyang
 * @create time 2016/4/19 0019.
 */
public class ModifyInfoAct extends Activity implements View.OnClickListener {

    private UserBean userBean = SysApplication.userBean;

    //返回按钮
    private View backBtn;

    private View rootview;

    //地址view父类
    private View addressParentView;

    //text控件
    private TextView areaTv;
    private TextView addressTv;
    //地区列表 自定义的弹出框类
    SelectAreaPopupWindow menuWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_info_layout);

        initTopView();
        initView();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //隐藏软键盘
        Tools.hideSoftInput(this);

    }

    private void initTopView() {
        rootview = (View) findViewById(R.id.modify_info_rootview);

        ViewGroup topView = (ViewGroup) findViewById(R.id.modify_info_topview);

        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.setting_info_1);

        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    private void initView() {
        areaTv = (TextView) findViewById(R.id.modify_info_tv2);
        areaTv.setOnClickListener(this);

        addressTv = (TextView) findViewById(R.id.modify_info_tv3);
        addressTv.setOnClickListener(this);

        addressParentView = (View) findViewById(R.id.modify_info_address_view);
        if (userBean != null && userBean.getUserType() == UserHelper.BettingShopUser){
            addressParentView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v) {
            //返回按钮
            finish();
        } else if(addressTv == v) {
            //地址管理
            SysApplication.enterNext1(this, UserAddressManagerAct.class);
        }else if (areaTv == v) {
            Tools.hideSoftInput(this, rootview);

            //地区设置 实例化SelectAreaPopupWindow
            menuWindow = new SelectAreaPopupWindow(this);
            //显示窗口
            menuWindow.showAtLocation(rootview, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置

            menuWindow.setonClick(new SelectAreaPopupWindow.ICoallBack() {

                @Override
                public void onBtnCancel() {
                    menuWindow.dismiss();
                }

                @Override
                public void onBtnConfirm() {
                    menuWindow.dismiss();

                    areaTv.setText(SysApplication.getProvinceData().getCurrentProviceName()
                                    + " "
                                    + SysApplication.getProvinceData().getCurrentCityName()
                                    + " "
                                    + SysApplication.getProvinceData().getCurrentDistrictName());
                }
            });
        }
    }
}
