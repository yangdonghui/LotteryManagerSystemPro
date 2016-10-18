package com.manager.user.site;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.manager.common.Constants;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.view.OrderProgressView;

import java.util.ArrayList;

/**
 * 站点申报记录 故障报修 单条查看界面
 * @author donghuiyang
 * @create time 2016/4/19 0019.
 */
public class SiteDeclareRecordToDetailAct extends Activity implements View.OnClickListener{

    //title
    private TextView titleView = null;
    //返回按钮
    private View backBtn = null;

    //进度
    private OrderProgressView progressView;

    //子项view
    private ViewStub stub;
    private Button okBtn, cancelBtn, changeBtn;
    private Button starSumbitBtn;

    //评价子view
    private ViewGroup appraiseViewGroup;
    private ArrayList<ImageView> starImgVs = new ArrayList<>();
    private int curNum = 0;//当前评价数量
    private int[] starState = new int[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.site_declare_record_to_detail_layout);

        //初始化view
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        starImgVs.clear();
        starImgVs = null;
        starState = null;

    }

    /**
     * 获取控件
     */
    private void initView() {
        ViewGroup topView = (ViewGroup)findViewById(R.id.site_declare_record_to_detail_topview);
        titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        titleView.setText(R.string.site_declare_record_info);
        backBtn.setOnClickListener(this);

        progressView = (OrderProgressView) (findViewById(R.id.order_progress_include).findViewById(R.id.order_progress_view));
        progressView.setIconText(Constants.IconTexts1);

        stub = (ViewStub) findViewById(R.id.declare_record_to_detail_viewStub);
        stub.setLayoutResource(R.layout.consumable_detail_items_layout);
        stub.inflate();

        //按钮
        okBtn = (Button) findViewById(R.id.declare_record_to_detail_ok_btn);
        cancelBtn = (Button) findViewById(R.id.declare_record_to_detail_cancel_btn);
        changeBtn = (Button) findViewById(R.id.declare_record_to_detail_change_btn);
        okBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        changeBtn.setOnClickListener(this);

        appraiseViewGroup = (ViewGroup) findViewById(R.id.site_declare_record_detail_appraise_include);
        for (int i=0;i<5;i++){
            ViewGroup groupV = (ViewGroup) appraiseViewGroup.findViewById(R.id.service_assessments_viewgroup);
            ImageView v = (ImageView) groupV.getChildAt(i);
            v.setOnClickListener(this);
            starImgVs.add(v);
        }

        starSumbitBtn = (Button) appraiseViewGroup.findViewById(R.id.star_sumbit_button);
        starSumbitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v || changeBtn == v){
            //返回按钮  //变更按钮
            SysApplication.backBtn(this, null);
        }else if(okBtn == v){
            //提交按钮

        }else if(cancelBtn == v){
            //取消按钮
            finish();
        }else if(starSumbitBtn.getVisibility() == View.VISIBLE && starSumbitBtn == v) {
            //提交评价按钮

        }else{
            //评价星
            for (int i=0;i<5;i++){
                ImageView star = starImgVs.get(i);
                if (star != null && v == star){

                    if (i == 0 && curNum == i+1){
                        if (starState[0] == 1) {
                            starState[0] = 0;
                            star.setImageResource(R.mipmap.star_0);
                            break;
                        }
                    }

                    curNum = i+1;
                    for (int j=0;j<5;j++){
                        ImageView star1 = starImgVs.get(j);
                        if (j <= curNum-1){
                            if (starState[j] != 1){
                                starState[j] = 1;
                                star1.setImageResource(R.mipmap.star_1);
                            }
                        }else{
                            if (starState[j] != 0){
                                starState[j] = 0;
                                star1.setImageResource(R.mipmap.star_0);
                            }
                        }
                    }

                    break;
                }
            }
        }
    }
}
