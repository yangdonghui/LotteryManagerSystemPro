package com.manager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.manager.lotterypro.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/2/22 0022.
 */
public class GridViewAdapter extends BaseAdapter {
    //上下文对象
    private Context mContext = null;

    //数据列表
    private List<HashMap<String, Object>> listItems;    //商品信息集合


    private final static class GridViewHolder {
        public TextView title;
        public TextView info;
    }

    public GridViewAdapter(Context context, List<HashMap<String, Object>> listItems) {
        super();
        this.mContext = context;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {

        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_item_0, parent, false);

        }

        TextView title = BaseViewHolder.get(convertView, R.id.home_grid_head_textView);
        TextView info = BaseViewHolder.get(convertView, R.id.home_grid_subhead_textView);

        title.setText("人气社区");
        info.setText("双色球已开奖");

        return convertView;
    }
}
