package com.manager.adapter.community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.manager.adapter.BaseViewHolder;
import com.manager.lotterypro.R;

import java.util.List;

/**
 * 搜索社区 热门搜索数据 适配器
 * Created by Administrator on 2016/2/22 0022.
 */
public class SearchGridViewAdapter extends BaseAdapter {
    //上下文对象
    private Context mContext = null;

    //数据列表
    private List<String> listItems;    //商品信息集合


    private final static class GridViewHolder {
        public TextView title;
    }

    public SearchGridViewAdapter(Context context, List<String> listItems) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_item_2, parent, false);
        }

        TextView title = BaseViewHolder.get(convertView, R.id.grid_item_2_title);
        title.setText((String) listItems.get(position));

        return convertView;
    }
}
