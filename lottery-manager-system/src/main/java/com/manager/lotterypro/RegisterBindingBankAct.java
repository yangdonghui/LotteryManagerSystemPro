package com.manager.lotterypro;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manager.bean.UserBean;
import com.manager.common.Tools;
import com.manager.helper.UserHelper;

/**
 *注册后 绑定银行卡界面
 * Created by Administrator on 2016/4/19 0019.
 */
public class RegisterBindingBankAct extends Activity implements View.OnClickListener{
    //用户属性
    private UserBean userBean = SysApplication.userBean;

    //根view
    private View rootView;

    ViewGroup progressViewGroup;

    //返回按钮
    View backBtn;

    //下一步按钮
    private Button nextBtn;
    private View skipBtn;

    //编辑框控件
    private EditText editText1, editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_binding_bank_layout);

        userBean = SysApplication.userBean;

        initView();
        initProgressView();

        SysApplication.closePreAct();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //隐藏软键盘
        Tools.hideSoftInput(this);

    }

    /**
     * 初始化控件
     */
    private void initView() {
        rootView = (View) findViewById(R.id.register_binding_bank_rootview);

        //topview
        ViewGroup topView = (ViewGroup)findViewById(R.id.binding_bank_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(getResources().getString(R.string.register_info3));
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setVisibility(View.GONE);

        nextBtn = (Button) findViewById(R.id.binding_bank_next_btn);
        nextBtn.setOnClickListener(this);

        skipBtn = (View) findViewById(R.id.binding_bank_skip_btn);
        skipBtn.setOnClickListener(this);

        editText1 = (EditText) findViewById(R.id.binding_bank_edit1);
        editText2 = (EditText) findViewById(R.id.binding_bank_edit2);
    }

    private void initProgressView() {
        //进度栏
        progressViewGroup = (ViewGroup) findViewById(R.id.binding_bank_progress_include);

        ImageView view1 = (ImageView)progressViewGroup.findViewById(R.id.register_progress_view1);
        ImageView view2 = (ImageView)progressViewGroup.findViewById(R.id.register_progress_view2);
        TextView tv1 = (TextView)progressViewGroup.findViewById(R.id.register_progress_tv1);
        TextView tv2 = (TextView)progressViewGroup.findViewById(R.id.register_progress_tv2);

        LinearLayout layout = (LinearLayout) progressViewGroup.findViewById(R.id.register_progress_framelayout111);
        FrameLayout frameLayout3 = (FrameLayout) progressViewGroup.findViewById(R.id.register_progress_framelayout3);
        if (userBean != null && userBean.getUserType() == UserHelper.BettingShopUser){
            //站点注册 2步骤
            frameLayout3.setVisibility(View.GONE);
            progressViewGroup.findViewById(R.id.register_progress_line2).setVisibility(View.GONE);
            {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)layout.getLayoutParams();
                params.leftMargin = 215;
                params.rightMargin = 215;
                layout.setLayoutParams(params);
                layout.requestLayout();
            }

            view1.setImageResource(R.drawable.oval_shape_5);
            tv1.setTextColor(Color.WHITE);
        }else if (userBean != null && userBean.getUserType() == UserHelper.LotteryUser) {
            //彩民
            view1.setImageResource(R.drawable.oval_shape_5);
            tv1.setTextColor(Color.WHITE);

            view2.setImageResource(R.drawable.oval_shape_5);
            tv2.setTextColor(Color.WHITE);
        }
    }

    @Override
    public void onClick(View v) {
        if (nextBtn == v) {
            //下一步 请求
            SysApplication.Next(this, RegisterFinishAct.class);

        }else if(skipBtn == v) {
            //跳过 进入完成注册界面
            SysApplication.Next(this, RegisterFinishAct.class);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

}
