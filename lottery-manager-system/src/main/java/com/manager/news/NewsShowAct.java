package com.manager.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.manager.bean.NewsBean;
import com.manager.lotterypro.R;

/**
 * 消息查看 界面
 * @author donghuiyang
 * @create time 2016/5/13 0013.
 */
public class NewsShowAct extends Activity implements View.OnClickListener{
    //返回按钮
    private View backBtn;

    private TextView titleTv, timeTv, contentTv, infoTv;
    private ImageView iconImg;

    NewsBean newsBean = null;
    int type;
    int mainType = 0; //1：标注是活动界面进入

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_show_layout);

        Intent intent = getIntent();
        newsBean = (NewsBean)intent.getSerializableExtra("data");
        if (newsBean != null) {
            type = Integer.valueOf(newsBean.getNewsType());
        }

        initView();
        updateContent();
    }

    @Override
    protected void onDestroy() {
        newsBean = null;

        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //返回按钮
            finish();
        }
    }

    /**
     * 获取控件对象
     */
    private void initView() {
        ViewGroup topView = (ViewGroup)findViewById(R.id.news_show_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText("");

        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);

        titleTv = (TextView) findViewById(R.id.news_show_title_tv);
        timeTv = (TextView) findViewById(R.id.news_show_time_tv);
        contentTv = (TextView) findViewById(R.id.news_show_content_tv);
        infoTv = (TextView) findViewById(R.id.news_show_info_tv);
        iconImg = (ImageView) findViewById(R.id.news_show_icon_img);
    }

    /**
     * 消息类型不同
     */
    private void updateContent() {
        int iconID = newsBean.getIconID();
        String comeFromStr = newsBean.getComeFromStr();
        if (comeFromStr == null){
            infoTv.setText("");
        }
        switch (type){
            case 1:{
                //公告
                titleTv.setText(getString(R.string.news_item_str_6) + " " + newsBean.getNewsTitle());
                timeTv.setText(newsBean.getNewsTime());
                if (comeFromStr != null){
                    infoTv.setText(getString(R.string.news_item_str_15) + " " + comeFromStr);
                }
            }
            break;
            case 2:{
                //通知
                titleTv.setText(getString(R.string.news_item_str_7) + " " + newsBean.getNewsTitle());
                timeTv.setText(newsBean.getNewsTime());
                if (comeFromStr != null){
                    infoTv.setText(getString(R.string.news_item_str_15) + " " + comeFromStr);
                }
            }
            break;
            case 3:{
                //活动
                titleTv.setText(getString(R.string.news_item_str_12) + " " + newsBean.getNewsTitle());
                timeTv.setText(getString(R.string.news_item_str_13) + " " + newsBean.getNewsTime());

                if (comeFromStr != null){
                    infoTv.setText(getString(R.string.news_item_str_14) + " " + comeFromStr);
                }
            }
            break;
            case 4:{
                //资讯
                titleTv.setText(newsBean.getNewsTitle());
                timeTv.setText("");
            }
            break;
        }

        contentTv.setText(newsBean.getNewsContent());

        if (iconID == -1){
            iconImg.setVisibility(View.GONE);
        }else{
            iconImg.setVisibility(View.VISIBLE);
            iconImg.setImageResource(iconID);
        }
    }
}
