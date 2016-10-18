package com.manager.lotterypro;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.manager.helper.UserHelper;

/**
 *身份选择界面
 * Created by Administrator on 2016/4/19 0019.
 */
public class IdentitySelectAct extends Activity implements View.OnClickListener{

    View itemView0,itemView1,itemView2;

    //当前选择的用户类型
    public static int userType = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_identity_layout);

        itemView0 = (View)findViewById(R.id.identity_bg_view_0);
        itemView1 = (View)findViewById(R.id.identity_bg_view_1);
        itemView2 = (View)findViewById(R.id.identity_bg_view_2);

        itemView0.setOnClickListener(this);
        itemView1.setOnClickListener(this);
        itemView2.setOnClickListener(this);

        //添加到activity管理列表
        SysApplication.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        userType = -1;
    }

    @Override
    public void onClick(View v) {
        if(v == itemView0) {
            //彩民
            userType = UserHelper.LotteryUser;
            SysApplication.enterNext(this, LoginLotteryMainAct.class);

        }else if(v == itemView1) {
            //站点
            userType = UserHelper.BettingShopUser;
            SysApplication.enterNext(this, LoginBettingshopMainAct.class);

        }else if(v == itemView2) {
            //管理员
            userType = UserHelper.ManagerUser;
            SysApplication.enterNext(this, LoginManagerAct.class);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            SysApplication.exit();
            return true;
        }
        return false;
    }
}
