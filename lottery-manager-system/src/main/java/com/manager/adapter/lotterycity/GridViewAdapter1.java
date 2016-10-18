package com.manager.adapter.lotterycity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.manager.common.StringHelper;
import com.manager.lotterypro.R;

import java.util.List;

/**
 * 走势图中数字gridview适配器
 * @author donghuiyang
 * @create time 2016/7/20 0020.
 */
public class GridViewAdapter1 extends BaseAdapter {
    Context context;
    private LayoutInflater mInflater = null;
    List<Integer> list;

    public static class ViewHolder {
        //球
        ImageView bgImg;
        //遗漏数据 或 中奖号码
        TextView tvView;
    }

    public GridViewAdapter1(Context _context, List<Integer> _list) {
        this.list = _list;
        this.context = _context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.list_item_26_1, null);
            holder.tvView = (TextView)convertView.findViewById(R.id.list_item_26_1_tv);
            holder.bgImg = (ImageView) convertView.findViewById(R.id.list_item_26_1_bgimg);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        //填充数据
        Integer value = list.get(position);
        if (value ==null) return convertView;

        int a = list.get(position).intValue();
        if (a == 0){
            //遗漏为0 为中奖号码
            holder.bgImg.setVisibility(View.VISIBLE);
            holder.tvView.setTextAppearance(context, R.style.text_size_12_text_white);

            //类似 01,02，11
            holder.tvView.setText(StringHelper.intToString(position+1));

        }else{
            //显示遗漏号
            holder.bgImg.setVisibility(View.GONE);
            holder.tvView.setTextAppearance(context, R.style.text_size_12_view_line);

            holder.tvView.setText(String.valueOf(list.get(position).intValue()));
        }

        return convertView;
    }
}