package com.manager.wallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.manager.adapter.CommonAdapter;
import com.manager.adapter.ViewHolder;
import com.manager.bean.WidgetBean1;
import com.manager.common.Constants;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;

import java.util.List;

/**
 * @author donghuiyang
 * @create time 2016/6/30 0030.
 */
public class TransferAccountFilterAct extends Activity implements View.OnClickListener {

    //返回按钮
    private View backBtn;

    private GridView gridView;
    private CommonAdapter<WidgetBean1> gridViewAdapter;
    private List<WidgetBean1> mLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transfer_account_filter_layout);

        initData();

        initTopView();
        initGridView();
    }

    /**
     * 设置标题
     */
    private void initTopView() {
        ViewGroup topView = (ViewGroup) findViewById(R.id.transfer_account_filter_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.wallet_str_9);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    private void initData() {
        mLists = Constants.FilterLists;
    }

    private void initGridView() {
        gridView = (GridView) findViewById(R.id.transfer_account_filter_gridview);
        gridViewAdapter = new CommonAdapter<WidgetBean1>(this, mLists, R.layout.grid_item_11) {
            @Override
            public void convert(ViewHolder helper, WidgetBean1 item) {
                helper.setText(R.id.grid_view_11_tv, item.getText());
            }
        };
        gridView.setAdapter(gridViewAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position < mLists.size()-1){
                    //除最后一个之外
                    Intent intent = SysApplication.walletTransactionHistoryAct.getIntent();
                    intent.putExtra("data", position);

                    SysApplication.backBtn(TransferAccountFilterAct.this, null);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v) {
            //返回按钮
            SysApplication.backBtn(this, null);
        }
    }
}
