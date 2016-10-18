package com.manager.lotterypro;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manager.bean.UserBean;
import com.manager.helper.UserHelper;

/**
 *注册后 提示 界面
 * Created by Administrator on 2016/4/19 0019.
 */
public class RegisterFinishAct extends Activity implements View.OnClickListener{
    //用户属性
    private UserBean userBean = SysApplication.userBean;

    //下一步按钮
    private Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_finish_layout);

        userBean = SysApplication.userBean;

        initView();
        initProgressView();
    }

    /**
     * 初始化控件
     */
    private void initView() {

        //topview
        ViewGroup topView = (ViewGroup)findViewById(R.id.register_finish_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(getResources().getString(R.string.register_info4));
        View backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setVisibility(View.GONE);

        nextBtn = (Button) findViewById(R.id.register_finish_ok_btn);
        nextBtn.setOnClickListener(this);
    }

    private void initProgressView() {
        //进度栏
        ViewGroup progressViewGroup = (ViewGroup) findViewById(R.id.register_finish_progress_include);

        ImageView view1 = (ImageView)progressViewGroup.findViewById(R.id.register_progress_view1);
        ImageView view2 = (ImageView)progressViewGroup.findViewById(R.id.register_progress_view2);
        ImageView view3 = (ImageView)progressViewGroup.findViewById(R.id.register_progress_view3);
        TextView tv1 = (TextView)progressViewGroup.findViewById(R.id.register_progress_tv1);
        TextView tv2 = (TextView)progressViewGroup.findViewById(R.id.register_progress_tv2);
        TextView tv3 = (TextView)progressViewGroup.findViewById(R.id.register_progress_tv3);

        LinearLayout layout = (LinearLayout) progressViewGroup.findViewById(R.id.register_progress_framelayout111);
        View frameLayout3 = (View) progressViewGroup.findViewById(R.id.register_progress_framelayout3);
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

            view2.setImageResource(R.drawable.oval_shape_5);
            tv2.setTextColor(Color.WHITE);

        }else if (userBean != null && userBean.getUserType() == UserHelper.LotteryUser) {
            //彩民
            view1.setImageResource(R.drawable.oval_shape_5);
            tv1.setTextColor(Color.WHITE);

            view2.setImageResource(R.drawable.oval_shape_5);
            tv2.setTextColor(Color.WHITE);

            view3.setImageResource(R.drawable.oval_shape_5);
            tv3.setTextColor(Color.WHITE);
        }
    }

    @Override
    public void onClick(View v) {
        if (nextBtn == v) {
            //下一步
            //mUserLoginPresenter.login();

            SysApplication.Next(this, MainAct.class);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

}
