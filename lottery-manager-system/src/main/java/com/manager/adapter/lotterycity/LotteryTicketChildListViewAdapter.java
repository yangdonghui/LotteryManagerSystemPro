package com.manager.adapter.lotterycity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.manager.bean.NoteLotterybean;
import com.manager.common.Constants;
import com.manager.lotterypro.R;

import java.util.List;

/**
 * 彩票 票号 订单支付、 委托详情等 list视图适配器
 * @author donghuiyang
 * @create time 2016/5/17 0017.
 */
public class LotteryTicketChildListViewAdapter extends BaseAdapter {

    private Context context;                     //运行上下文
    private List<NoteLotterybean> listItems;    //商品信息集合
    private LayoutInflater listContainer;        //视图容器

    private final class ViewHolder {
        public TextView tv1;
        public TextView tv2;
        public TextView tv3;

        public GridView gridview1;
        public GridView gridview2;
    }

    public LotteryTicketChildListViewAdapter(Context context, List<NoteLotterybean> listItems) {
        this.context = context;
        listContainer = LayoutInflater.from(context);   //创建视图容器并设置上下文
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (listItems == null) {
            return null;
        }

        //自定义视图
        ViewHolder listItemView = null;
        if (convertView == null) {
            listItemView = new ViewHolder();
            //获取list_item布局文件的视图
            convertView = listContainer.inflate(R.layout.list_item_17_21, null);
            //获取控件对象
            listItemView.tv1 = (TextView) convertView.findViewById(R.id.list_item_17_21_tv1);
            listItemView.tv2 = (TextView) convertView.findViewById(R.id.list_item_17_21_tv2);
            listItemView.tv3 = (TextView) convertView.findViewById(R.id.list_item_17_21_tv3);
            listItemView.gridview1 = (GridView)convertView.findViewById(R.id.list_item_17_21_gridview1);
            listItemView.gridview2 = (GridView)convertView.findViewById(R.id.list_item_17_21_gridview2);

            BallsGridviewAdapter redAdapter = new BallsGridviewAdapter(context, R.layout.red_number1, listItems.get(position).getNumbers().getNumbersList1(), 1);
            listItemView.gridview1.setAdapter(redAdapter);

            BallsGridviewAdapter blueAdapter = new BallsGridviewAdapter(context, R.layout.red_number1, listItems.get(position).getNumbers().getNumbersList2(), 2);
            listItemView.gridview2.setAdapter(blueAdapter);

            if (listItems.get(position).getDoubleSingleType() == 1){
                //复式 隐藏编号
                listItemView.tv1.setVisibility(View.GONE);
            }

            //设置控件集到convertView
            convertView.setTag(listItemView);
        } else {
            listItemView = (ViewHolder) convertView.getTag();
        }

        listItemView.tv1.setText(listItems.get(position).getId());
        listItemView.tv2.setText("x"+listItems.get(position).getMultiple() + "注");
        listItemView.tv3.setText(Constants.money_str + String.valueOf(listItems.get(position).getPrice()));

        if (listItemView.gridview1.getAdapter() != null){
            ((BallsGridviewAdapter)listItemView.gridview1.getAdapter()).notifyDataSetChanged();
        }

        if (listItemView.gridview2.getAdapter() != null){
            ((BallsGridviewAdapter)listItemView.gridview2.getAdapter()).notifyDataSetChanged();
        }

        return convertView;
    }
}