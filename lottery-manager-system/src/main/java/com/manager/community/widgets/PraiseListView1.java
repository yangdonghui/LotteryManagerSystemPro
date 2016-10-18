package com.manager.community.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.manager.adapter.CommonAdapter;
import com.manager.adapter.ViewHolder;
import com.manager.community.bean.FavortItem;
import com.manager.lotterypro.R;

import java.util.List;

/**
 * Created by yiwei on 16/7/9.
 */
public class PraiseListView1 extends LinearLayout{

    private Context context;

    //View对象
    private View mView = null;
    private GridView mGridView;
    private CommonAdapter<FavortItem> adapter;

    private List<FavortItem> datas;
    private AdapterView.OnItemClickListener onItemClickListener;

    public AdapterView.OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public PraiseListView1(Context context) {
        super(context);

        this.context = context;
        init();
    }

    public PraiseListView1(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        init();
    }

    public PraiseListView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.context = context;
        init();
    }

    private void init(){
        mView = LayoutInflater.from(context).inflate(R.layout.layout_praisel_view, this, true);

        mGridView = (GridView) mView.findViewById(R.id.praisegridview);

    }

    public List<FavortItem> getDatas() {
        return datas;
    }
    public void setDatas(List<FavortItem> datas) {
        this.datas = datas;

        //添加号码
        adapter = new CommonAdapter<FavortItem>(context, datas, R.layout.header_item) {
            @Override
            public void convert(ViewHolder helper, FavortItem item) {
                //头像
                if (item != null){
                    helper.setImageByUrl(R.id.iv_avatar, item.getUser().getHeadUrl());
                }
            }
        };
        mGridView.setAdapter(adapter);
        mGridView.setOnItemClickListener(onItemClickListener);
    }
}
