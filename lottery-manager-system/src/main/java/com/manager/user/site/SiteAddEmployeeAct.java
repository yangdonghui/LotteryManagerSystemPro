package com.manager.user.site;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.manager.Interface.ICoallBack3;
import com.manager.bean.UserBean;
import com.manager.common.Tools;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.view.CommonPopupWindow;

/**
 * 站点添加雇员 或 修改雇员资料界面
 * @author donghuiyang
 * @create time 2016/6/27 0027.
 */
public class SiteAddEmployeeAct extends Activity implements View.OnClickListener, View.OnLayoutChangeListener{
    //当前雇员信息
    private UserBean employeeBean;

    //弹出框
    private CommonPopupWindow menuWindow;
    private int typeWidget = -1;

    //当前性别 账号状态
    private int sex = -1, accountState = -1;

    //标题名
    TextView titleView;
    //返回按钮
    private View backBtn;
    //新增 或 保存按钮
    private Button confirmBtn;

    //textview edittext控件
    private TextView usernameTv;
    private TextView tv1,tv2;
    private EditText edit1,edit2,edit3,edit4,edit5;

    //Activity最外层的Layout视图
    private View activityRootView;
    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.site_add_employee_layout);

        Intent intent = getIntent();
        employeeBean = (UserBean) intent.getSerializableExtra("data");
        if (employeeBean != null){
            sex = employeeBean.getSex();
            accountState = employeeBean.getAccountState();
        }

        listenerSoftKeybord();
        initTopView();
        initView();
        updateView();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //隐藏键盘
        Tools.hideSoftInput(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //添加layout大小发生改变监听器
        activityRootView.addOnLayoutChangeListener(this);

    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        //现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起
        if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {
            //Toast.makeText(this, "监听到软键盘弹起...", Toast.LENGTH_SHORT).show();


        } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {
            //Toast.makeText(this, "监听到软件盘关闭...", Toast.LENGTH_SHORT).show();

            //隐藏软键盘
            TextView v1 = (TextView)this.getCurrentFocus();
            assert v1 != null;
            v1.setFocusable(false);
            v1.setCursorVisible(false);
            Tools.hideSoftInput(this);
        }
    }

    /**
     * 设置标题
     */
    private void initTopView() {
        ViewGroup topView = (ViewGroup) findViewById(R.id.site_add_employee_topview);
        titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.setting_info_btn5);//R.string.setting_info_1
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    private void initView() {
        confirmBtn = (Button) findViewById(R.id.site_add_employee_btn);
        usernameTv = (TextView) findViewById(R.id.site_add_employee_username_tv);

        tv1 = (TextView) findViewById(R.id.site_add_employee_tv1);
        tv1.setOnClickListener(this);

        tv2 = (TextView) findViewById(R.id.site_add_employee_tv2);
        tv2.setOnClickListener(this);

        edit1 = (EditText) findViewById(R.id.site_add_employee_edit1);
        edit1.setOnClickListener(this);

        edit2 = (EditText) findViewById(R.id.site_add_employee_edit2);
        edit2.setOnClickListener(this);

        edit3 = (EditText) findViewById(R.id.site_add_employee_edit3);
        edit3.setOnClickListener(this);
        edit3.setOnKeyListener(onKeyListener);

        edit4 = (EditText) findViewById(R.id.site_add_employee_edit4);
        edit4.setOnClickListener(this);

        edit5 = (EditText) findViewById(R.id.site_add_employee_edit5);
        edit5.setOnClickListener(this);
    }

    private void updateView() {
        if (employeeBean == null){
            titleView.setText(R.string.setting_info_btn5);
            confirmBtn.setText(R.string.setting_info_btn3);
        }else{
            titleView.setText(R.string.setting_info_1);
            confirmBtn.setText(R.string.address_manager_btn1);

            usernameTv.setText(employeeBean.getUserName());

            edit1.setText(employeeBean.getRealName());
            edit2.setText(employeeBean.getCardNumber());
            edit3.setText(employeeBean.getUserPhone());

            tv1.setText(employeeBean.getRealName());
            if (employeeBean.getSex() == 0){
                tv1.setText("男");
            }else if (employeeBean.getSex() == 1){
                tv1.setText("女");
            }
        }
    }

    /**
     * 处理编辑框 获取焦点 显示光标和软键盘
     * @param edit
     */
    private void dealWithEdit(EditText edit){
        edit.setFocusable(true);
        edit.setFocusableInTouchMode(true);
        edit.setCursorVisible(true);
        Tools.showSoftInput(this, edit);
    }

    /**
     * 选项弹出框
     * @param arrayId
     * @param curIndex
     */
    private void popWindow(int arrayId, int curIndex) {
        Tools.hideSoftInput(this);

        if (menuWindow != null){
            menuWindow.dismiss();
            menuWindow = null;
        }

        //实例化
        final String[] items =this.getResources().getStringArray(arrayId);
        menuWindow = new CommonPopupWindow(this, items, curIndex);
        //显示窗口
        menuWindow.showAtLocation(activityRootView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
        menuWindow.setonClick(new ICoallBack3() {
            @Override
            public void doCancel() {
                menuWindow.dismiss();
            }

            @Override
            public void doConfirm(int pos) {
                menuWindow.dismiss();
                if (typeWidget == 0) {
                    //设置性别
                    tv1.setText(items[pos]);

                    if (employeeBean != null) {
                        employeeBean.setSex(pos);
                    }

                } else if (typeWidget == 1) {
                    //设置账号状态
                    if (employeeBean != null) {
                        employeeBean.setAccountState(pos);
                    }

                    if (pos == 0) {
                        //启用 红色标记
                        tv2.setTextAppearance(SiteAddEmployeeAct.this, R.style.text_size_14_text_color_10);
                    } else if (pos == 1) {
                        //停用
                        tv2.setTextAppearance(SiteAddEmployeeAct.this, R.style.text_size_14_text_color_3);
                    }
                    tv2.setText(items[pos]);

                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //返回按钮
            SysApplication.backBtn(this, null);
        }else if (tv1 == v){
            //设置性别
            typeWidget = 0;

            popWindow(R.array.sex_spingarry, sex);
        }else if (tv2 == v){
            //设置账号状态
            typeWidget = 1;

            popWindow(R.array.account_state_spingarry, accountState);
        }else{
            //处理edittext控件输入
            dealWithEdit((EditText)v);
        }
    }

    private void listenerSoftKeybord(){
        activityRootView = (View) findViewById(R.id.site_add_employee_rootview);

        //获取屏幕高度
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight / 3;
    }

    private View.OnKeyListener onKeyListener = new View.OnKeyListener() {

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
				/*隐藏软键盘*/
                TextView v1 = (TextView)SiteAddEmployeeAct.this.getCurrentFocus();
                v1.setFocusable(false);
                v1.setCursorVisible(false);
                Tools.hideSoftInput(SiteAddEmployeeAct.this);

                return true;
            }
            return false;
        }
    };
}
