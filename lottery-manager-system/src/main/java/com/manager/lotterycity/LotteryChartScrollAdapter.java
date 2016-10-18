package com.manager.lotterycity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.manager.adapter.lotterycity.GridViewAdapter1;
import com.manager.common.Tools;
import com.manager.lotterypro.R;
import com.manager.widgets.CHScrollView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 走势图 垂直滚动数据
 * @author donghuiyang
 * @create time 2016/7/19 0019.
 */
public class LotteryChartScrollAdapter extends SimpleAdapter {
    private List<? extends Map<String, ?>> datas;
    private int res;
    private String[] from;
    private int[] to;
    private Context context;

    public interface ICoallBack {
        public void dealWithScrollView(CHScrollView hScrollView);
    }


    private ICoallBack iCoallBack;
    public void setCallBack(ICoallBack iCoallBack) {
        this.iCoallBack = iCoallBack;
    }

    public LotteryChartScrollAdapter(Context context,
                                     List<? extends Map<String, ?>> data, int resource,
                                     String[] from, int[] to, int childNum) {
        super(context, data, resource, from, to);
        this.context = context;
        this.datas = data;
        this.res = resource;
        this.from = from;
        this.to = to;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = LayoutInflater.from(context).inflate(res, null);
            //第一次初始化的时候装进来 事件监听
            addHViews((CHScrollView) v.findViewById(R.id.chart_item_ssq_item_scroll_title));

            View[] views = new View[to.length];
            for (int i = 0; i < to.length; i++) {
                views[i] = v.findViewById(to[i]);
            }

            //数据
            ArrayList<Integer> childLists = (ArrayList<Integer>)(datas.get(position).get(from[1]));
            GridView gridview = (GridView) views[1];
            setGridView(gridview, childLists);

            v.setTag(views);
        }
        View[] holders = (View[]) v.getTag();
        //rootview
        View rootview = holders[2];
        if (position % 2 == 0){
            //偶数
            rootview.setBackgroundColor(Color.WHITE);
        }else{
            //奇数
            rootview.setBackgroundColor(context.getResources().getColor(R.color.bg_color_27));
        }

        //添加表头
        TextView title = (TextView) holders[0];
        title.setText(this.datas.get(position).get(from[0]).toString());

        return v;
    }

    /**
     * 设置GirdView参数，绑定数据
     */
    private void setGridView(GridView gridView, List<Integer> datas) {
        Tools.updateHorizontalGridView((Activity) context, datas.size(), 50, 1, gridView);

        GridViewAdapter1 adapter = new GridViewAdapter1(context, datas);
        gridView.setAdapter(adapter);
    }

    /**
     * 添加滚动事件
     * @param hScrollView
     */
    public void addHViews(final CHScrollView hScrollView) {
        if (iCoallBack == null) return;
        iCoallBack.dealWithScrollView(hScrollView);
    }

}
