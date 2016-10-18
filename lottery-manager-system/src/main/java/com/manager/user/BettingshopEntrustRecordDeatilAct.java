package com.manager.user;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.manager.bean.SingleTicketBean;
import com.manager.common.Constants;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.stone.card.CardItemView;
import com.stone.card.CardSlidePanel;

import java.util.ArrayList;
import java.util.List;

/**
 * 站点 委托详情界面
 * @author donghuiyang
 * @create time 2016/6/12 0012.
 */
public class BettingshopEntrustRecordDeatilAct extends FragmentActivity implements View.OnClickListener{

    private CardSlidePanel.CardSwitchListener cardSwitchListener;

    //返回按钮
    private View backBtn;

    private CardSlidePanel cardSlidePanel;

    //订单列表
    private List<SingleTicketBean> mLists = new ArrayList<>();

    //订单总价格
    private int ordersPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.betting_entrust_record_detail_layout);

        initData();

        initTopView();
        initView();


    }

    /**
     * 设置标题
     */
    private void initTopView() {
        ViewGroup topView = (ViewGroup) findViewById(R.id.bettingshop_entrust_recodr_detail_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.entrust_str16);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    private void initView() {
        int size = mLists.size();
        cardSlidePanel = (CardSlidePanel) findViewById(R.id.image_slide_panel);
        for (int i=0;i<size;i++){
            CardItemView cardItemView = new CardItemView(this);
            cardSlidePanel.addView(cardItemView);
        }
        cardSlidePanel.initView();

        cardSwitchListener = new CardSlidePanel.CardSwitchListener() {

            @Override
            public void onShow(int index) {
                //Log.d("CardFragment", "正在显示-" + dataList.get(index).userName);
            }

            @Override
            public void onCardVanish(int index, int type) {
                //Log.d("CardFragment", "正在消失-" + dataList.get(index).userName + " 消失type=" + type);

            }

            @Override
            public void onItemClick(View cardView, int index) {
                //Log.d("CardFragment", "卡片点击-" + dataList.get(index).userName);
            }
        };
        cardSlidePanel.setCardSwitchListener(cardSwitchListener);
        cardSlidePanel.fillData(mLists);
    }


    /**
     * 初始化列表数据
     */
    private void initData() {
        mLists = Constants.EntrustLists;
        for (int i=0;i<mLists.size();i++){
            SingleTicketBean att = mLists.get(i);
            if (att != null){
                ordersPrice += att.getPrice();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v) {
            //返回按钮
            SysApplication.backBtn(this, null);
        }
    }
}
