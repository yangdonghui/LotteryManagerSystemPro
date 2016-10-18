package com.manager.user.site;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.manager.Interface.CommonClickListener;
import com.manager.Interface.ICoallBack3;
import com.manager.common.Tools;
import com.manager.lotterypro.R;
import com.manager.view.CommonPopupWindow;
import com.manager.view.SiteDeclareConsumableView;
import com.manager.view.SiteDeclareFaultView;

/**
 * 站点申报
 * Created by Administrator on 2016/4/19 0019.
 */
public class SiteDeclareAct extends Activity implements View.OnClickListener{
    public static SiteDeclareAct siteDeclareAct;

    //返回按钮 查看记录按钮
    private View backBtn, recordBtn;
    //title 主分类 子分类
    private TextView titleView, mainTypeTextView, childTypeTextView;
    private TextView userTextView, bettingshopIDTextView;

    //备注 期望时间 耗材申请数量 耗材剩余数量
    private EditText infoEditView, timeEditView,consumableDeclareNumEditView, consumableLeftNumEditView;
    private ImageView uploadImgV;
    private Button confirmBtn;

    private View rootView;

    //不同分类对应不同view
    private SiteDeclareFaultView itemsFaultViewGroup;
    private SiteDeclareConsumableView itemsConsumableViewGrop;

    //弹出框
    private CommonPopupWindow menuWindow;

    //当前选择的分类
    private int mainType = 0, childType = 0;
    //标注当前弹出框属于哪个控件
    private int typeWidget = -1;//0-主类 ； 1-子类1 ；2-子类2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.site_declare_layout);

        //初始化view
        initTopView();
        initView();
        updateItemsView();

        siteDeclareAct = this;
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        int tag = intent.getIntExtra("cancel", 0);
        if (tag == 1){
            //取消提交 清除输入内容
            clearContent();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        //隐藏软键盘
        Tools.hideSoftInput(this);

    }

    private void initTopView(){
        rootView = (View) findViewById(R.id.site_declare_rootview);

        ViewGroup topView = (ViewGroup)findViewById(R.id.site_declare_topview);
        titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        recordBtn = (View)findViewById(R.id.site_declare_record_btn);
        backBtn.setOnClickListener(this);
        recordBtn.setOnClickListener(this);
    }

    /**
     * 初始化view
     */
    private void initView() {

        userTextView = (TextView) findViewById(R.id.siter_declare_userinfo_include).findViewById(R.id.site_declare_username);
        bettingshopIDTextView = (TextView) findViewById(R.id.siter_declare_userinfo_include).findViewById(R.id.site_declare_bettingshop_id);

        mainTypeTextView = (TextView)findViewById(R.id.site_declare_main_type);
        mainTypeTextView.setOnClickListener(this);

        //故障申请子项view
        itemsFaultViewGroup = (SiteDeclareFaultView)findViewById(R.id.site_declare_fault_items_view);
        itemsFaultViewGroup.setClicklistener(new CommonClickListener() {
            @Override
            public void doConfirm() {
                typeWidget = 1;
                popWindow(R.array.declare_type_spingarry1, childType);
            }
        });

        //耗材申请子项view
        itemsConsumableViewGrop = (SiteDeclareConsumableView)findViewById(R.id.site_declare_consumable_items_view);
        itemsConsumableViewGrop.setClicklistener(new CommonClickListener() {
            @Override
            public void doConfirm() {
                typeWidget = 2;
                popWindow(R.array.declare_type_spingarry2, childType);
            }
        });

        //期望时间 view
        timeEditView = (EditText) findViewById(R.id.site_declare_time_editText);

        //确认按钮
        confirmBtn = (Button) findViewById(R.id.site_declare_confirm_btn);
        confirmBtn.setOnClickListener(this);

        titleView.setText(R.string.me_item_str_4);
    }

    /**
     * 取消提交申请，清除输入的内容
     */
    private void clearContent() {
        mainType = 0;
        timeEditView.setText("");
        if (mainType == 0){
            //故障申报
            itemsFaultViewGroup.setVisibility(View.VISIBLE);
            itemsConsumableViewGrop.setVisibility(View.GONE);

            mainTypeTextView.setText("故障申报");
            childTypeTextView.setText("打印机");
            infoEditView.setText("");
        }else if (mainType == 1) {
            //耗材申报
            itemsFaultViewGroup.setVisibility(View.GONE);
            itemsConsumableViewGrop.setVisibility(View.VISIBLE);

            mainTypeTextView.setText("耗材申报");
            childTypeTextView.setText("打印纸");
            infoEditView.setText("");
            consumableDeclareNumEditView.setText("");
            consumableLeftNumEditView.setText("");
        }
    }

    /**
     * 根据不同分类，显示不同的子项view
     */
    private void updateItemsView() {
        if (mainType == 0){
            //故障申报
            itemsFaultViewGroup.setVisibility(View.VISIBLE);
            itemsConsumableViewGrop.setVisibility(View.GONE);
            //子分类
            childTypeTextView = (TextView) itemsFaultViewGroup.findViewById(R.id.site_declare_child_type_fault);
            infoEditView = (EditText) findViewById(R.id.site_declare_child_type_fault_info_editText);
            uploadImgV = (ImageView) findViewById(R.id.site_declare_child_type_fault_upload_imageView);

        }else if (mainType == 1){
            //耗材申报
            itemsFaultViewGroup.setVisibility(View.GONE);
            itemsConsumableViewGrop.setVisibility(View.VISIBLE);

            //子分类
            childTypeTextView = (TextView) itemsFaultViewGroup.findViewById(R.id.site_declare_child_type_consumable);
            infoEditView = (EditText) findViewById(R.id.site_declare_child_type_consumable_info_tv);
            consumableDeclareNumEditView = (EditText) findViewById(R.id.site_declare_child_type_consumable_declarenum_tv);
            consumableLeftNumEditView = (EditText) findViewById(R.id.site_declare_child_type_consumable_leftnum_tv);
        }
    }

    private void popWindow(int arrayId, int curIndex) {
        Tools.hideSoftInput(this, rootView);

        if (menuWindow != null){
            menuWindow.dismiss();
            menuWindow = null;
        }

        //实例化
        final String[] items =this.getResources().getStringArray(arrayId);
        menuWindow = new CommonPopupWindow(this, items, curIndex);
        //显示窗口
        menuWindow.showAtLocation(rootView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
        menuWindow.setonClick(new ICoallBack3() {
            @Override
            public void doCancel() {
                menuWindow.dismiss();
            }

            @Override
            public void doConfirm(int pos) {
                menuWindow.dismiss();
                if (typeWidget == 0){
                    //主类
                    if (pos == mainType){
                        return;
                    }
                    childType = 0;
                    mainType = pos;
                    mainTypeTextView.setText(items[pos]);
                    updateItemsView();
                }else {
                    //子类
                    if (typeWidget == 1){
                        //故障报修 子类
                        if (pos == childType){
                            return;
                        }
                        childType = pos;
                        itemsFaultViewGroup.setChildType(childType, items[pos]);
                    }else if (typeWidget == 2){
                        //耗材申报 子类
                        if (pos == childType){
                            return;
                        }
                        childType = pos;
                        itemsConsumableViewGrop.setChildType(childType, items[pos]);
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //返回按钮
            finish();
        }else if(recordBtn == v) {
            //记录查看按钮
            Intent intent = new Intent(this, SiteDeclareRecordAct.class);
            startActivity(intent);
        }else if (v == confirmBtn){
            //确认按钮
            Intent intent = new Intent(this, SiteDeclareToDetailAct.class);
            startActivity(intent);
        }else if (mainTypeTextView == v){
            //选择主分类
            typeWidget = 0;
            popWindow(R.array.declare_type_spingarry, mainType);
        }
    }
}
