package com.manager.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.manager.wheelbean.ProvinceData;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;

/**
 * 地址三级联动弹出界面
 * @author donghuiyang
 * @create time 2016/6/21 0021.
 */
public class SelectAreaPopupWindow extends PopupWindow implements View.OnClickListener, OnWheelChangedListener {

    private Context context;

    private View mMenuView;

    private WheelView mViewProvince;
    private WheelView mViewCity;
    private WheelView mViewDistrict;
    private TextView mBtnConfirm;
    private TextView mBtnCancel;

    private ProvinceData provinceData;

    /**
     * 一定一个接口
     */
    public interface ICoallBack{
        public void onBtnCancel();
        public void onBtnConfirm();
    }
    /**
     * 初始化接口变量
     */
    ICoallBack icallBack = null;
    /**
     * 自定义控件的自定义事件
     * @param iBack 接口类型
     */
    public void setonClick(ICoallBack iBack)
    {
        icallBack = iBack;
    }


    public SelectAreaPopupWindow(Activity context) {
        super(context);

        this.context = context;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.area_wheel_layout, null);

        init();

        setUpViews();
        setUpListener();
        setUpData();


    }

    private void init() {
        provinceData = SysApplication.getProvinceData();
        provinceData.setCurData("北京市", "北京市", "西城区");

        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LinearLayout.LayoutParams.FILL_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.popupAnimation);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xFF000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
    }

    private void setUpViews() {
        mViewProvince = (WheelView) mMenuView.findViewById(R.id.id_province);
        mViewCity = (WheelView) mMenuView.findViewById(R.id.id_city);
        mViewDistrict = (WheelView) mMenuView.findViewById(R.id.id_district);
        mBtnConfirm = (TextView) mMenuView.findViewById(R.id.area_wheel_btn_confirm);
        mBtnCancel = (TextView) mMenuView.findViewById(R.id.area_wheel_btn_cancel);
        mBtnCancel.setOnClickListener(this);
        mBtnConfirm.setOnClickListener(this);
    }

    private void setUpListener() {
        // 添加change事件
        mViewProvince.addChangingListener(this);
        // 添加change事件
        mViewCity.addChangingListener(this);
        // 添加change事件
        mViewDistrict.addChangingListener(this);
        // 添加onclick事件
        mBtnConfirm.setOnClickListener(this);
    }

    private void setUpData() {
        mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(context, provinceData.getProvinceDatas()));
        // 设置可见条目数量
        mViewProvince.setVisibleItems(7);
        mViewCity.setVisibleItems(7);
        mViewDistrict.setVisibleItems(7);
        mViewProvince.setCurrentItem(provinceData.getCurProviceNameIndex());
        updateCities();
        updateAreas();
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        // TODO Auto-generated method stub
        if (wheel == mViewProvince) {
            updateCities();
        } else if (wheel == mViewCity) {
            updateAreas();
        } else if (wheel == mViewDistrict) {
            provinceData.setCurrentDistrictName(provinceData.getDistrictDatasMap().get(provinceData.getCurrentCityName())[newValue]);
            provinceData.setCurrentZipCode(provinceData.getZipcodeDatasMap().get(provinceData.getCurrentDistrictName()));
        }
    }

    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateAreas() {
        int pCurrent = mViewCity.getCurrentItem();
        provinceData.setCurrentCityName(provinceData.getCitisDatasMap().get(provinceData.getCurrentProviceName())[pCurrent]);
        String[] areas = provinceData.getDistrictDatasMap().get(provinceData.getCurrentCityName());

        if (areas == null) {
            areas = new String[] { "" };
        }
        mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(context, areas));
        if (provinceData.getCurrentDistrictName().equals("")){
            mViewDistrict.setCurrentItem(0);
        }else{
            mViewDistrict.setCurrentItem(provinceData.getCurDistrictNameIndex());
        }
    }

    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities() {
        int pCurrent = mViewProvince.getCurrentItem();
        provinceData.setCurrentProviceName(provinceData.getProvinceDatas()[pCurrent]);
        String[] cities = provinceData.getCitisDatasMap().get(provinceData.getCurrentProviceName());
        if (cities == null) {
            cities = new String[] { "" };
        }
        mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(context, cities));
        if (provinceData.getCurrentCityName().equals("")){
            mViewCity.setCurrentItem(0);
        }else{
            mViewCity.setCurrentItem(provinceData.getCurCityNameIndex());
        }
        updateAreas();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.area_wheel_btn_cancel:
                icallBack.onBtnCancel();
                break;
            case R.id.area_wheel_btn_confirm:
                icallBack.onBtnConfirm();
                break;
            default:
                break;
        }
    }

    private void showSelectedResult() {
        Toast.makeText(context, "当前选中:" + provinceData.getCurrentProviceName() + "," + provinceData.getCurrentCityName() + ","
                + provinceData.getCurrentDistrictName() + "," + provinceData.getCurrentZipCode(), Toast.LENGTH_SHORT).show();
    }
}
