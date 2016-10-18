package com.manager.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.manager.common.Tools;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.view.SelectAreaPopupWindow;

import java.util.HashMap;

/**
 * 修改收货地址界面
 * @author donghuiyang
 * @create time 2016/5/31 0031.
 */
public class UserEditShippingAddressAct extends Activity implements View.OnClickListener{
    //返回按钮
    private View backBtn;

    private EditText editText1, editText2, editText3;
    private TextView addressTv;

    private View deleteView;
    private Button deleteBtn;

    //地区列表 自定义的弹出框类
    SelectAreaPopupWindow menuWindow;

    private View rootview;

    //传递的地址数据
    HashMap<String, Object> map = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_edit_address_layout);

        Intent intent = getIntent();
        map = (HashMap<String, Object>)intent.getSerializableExtra("data");

        initTopView();
        initView();

    }

    /**
     * 设置标题
     */
    private void initTopView() {
        ViewGroup topView = (ViewGroup) findViewById(R.id.user_edit_address_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);

        if (map != null){
            //编辑地址
            titleView.setText(R.string.address_address_edit_title);
        }else{
            //添加地址
            titleView.setText(R.string.address_address_add_title);
        }

        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    private void initView() {
        rootview = (View) findViewById(R.id.user_edit_address_rootview);

        deleteView = (View) findViewById(R.id.edit_address_view);

        editText1 = (EditText) findViewById(R.id.edit_address_edit1);
        editText2 = (EditText) findViewById(R.id.edit_address_edit2);
        editText3 = (EditText) findViewById(R.id.edit_address_edit3);
        addressTv = (TextView) findViewById(R.id.edit_address_tv);
        addressTv.setOnClickListener(this);

        deleteBtn = (Button) findViewById(R.id.edit_address_delete_btn);
        deleteBtn.setOnClickListener(this);

        if (map != null){
            editText1.setText((String)map.get("ItemName"));
            editText2.setText((String)map.get("ItemPhone"));
            editText3.setText((String)map.get("ItemContent"));

            addressTv.setText((String) map.get("ItemAddress1") + (String) map.get("ItemAddress2") + (String) map.get("ItemAddress3"));

        }else{
            deleteView.setVisibility(View.GONE);
        }


    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //返回按钮
            SysApplication.backBtn(this, null);
        }else if (deleteBtn == v){
            //删除地址 按钮
        }else if (addressTv == v){
            //选择 地区
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
                    addressTv.setText(SysApplication.getProvinceData().getCurrentProviceName()
                            + " "
                            + SysApplication.getProvinceData().getCurrentCityName()
                            + " "
                            + SysApplication.getProvinceData().getCurrentDistrictName());
                }
            });
        }
    }
}
