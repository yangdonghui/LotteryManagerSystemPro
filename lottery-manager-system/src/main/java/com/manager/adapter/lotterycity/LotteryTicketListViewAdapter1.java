package com.manager.adapter.lotterycity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.manager.bean.SingleTicketBean;
import com.manager.common.Constants;
import com.manager.lotterypro.R;

import java.util.List;

/**
 * 彩票 票号 订单支付、 委托详情等 list视图适配器
 * @author donghuiyang
 * @create time 2016/5/17 0017.
 */
public class LotteryTicketListViewAdapter1 extends BaseExpandableListAdapter {

    private Context context;                     //运行上下文
    private List<SingleTicketBean> listItems;    //商品信息集合
    private LayoutInflater listContainer;        //视图容器

    private int layoutType;

    @Override
    public int getGroupCount() {
        return listItems.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listItems.get(groupPosition).getNumbersList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listItems.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listItems.get(groupPosition).getNumbersList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (listItems == null) {
            return null;
        }

        //自定义视图
        ViewHolder listItemView = null;
        if (convertView == null) {
            listItemView = new ViewHolder();
            //获取list_item布局文件的视图
            convertView = listContainer.inflate(R.layout.list_item_17_2, null);
            //获取控件对象
            listItemView.tv1 = (TextView) convertView.findViewById(R.id.list_item_17_2_tv1);
            listItemView.tv4 = (TextView) convertView.findViewById(R.id.list_item_17_2_tv11);
            listItemView.tv5 = (TextView) convertView.findViewById(R.id.list_item_17_2_tv12);

            listItemView.tv2 = (TextView) convertView.findViewById(R.id.list_item_17_2_tv2);
            listItemView.tv3 = (TextView) convertView.findViewById(R.id.list_item_17_2_tv3);

            //设置控件集到convertView
            convertView.setTag(listItemView);
        } else {
            listItemView = (ViewHolder) convertView.getTag();
        }

        int doubleSingleType = listItems.get(groupPosition).getDoubleSingleType();
        /*if (doubleSingleType == 1){
            //复式 倍数
            listItemView.tv2.setText(R.string.entrust_str_9);
        }else if (doubleSingleType == 0){
            //单式  号码组数量
            int size = listItems.get(groupPosition).getNumbersList().size();
            listItemView.tv2.setText("x"+String.valueOf(size));
        }*/

        //金额
        //listItemView.tv3.setText(R.string.entrust_str_10);
        //listItemView.tv3.setTextAppearance(context, R.style.text_size_12_text_color_3);

        listItemView.tv1.setText(listItems.get(groupPosition).getName());
        listItemView.tv5.setText(listItems.get(groupPosition).getInfo());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        //自定义视图
        ChildViewHolder listItemView = null;
        if (convertView == null) {
            listItemView = new ChildViewHolder();
            //获取list_item布局文件的视图
            convertView = listContainer.inflate(R.layout.list_item_17_21, null);
            //获取控件对象
            listItemView.tv1 = (TextView) convertView.findViewById(R.id.list_item_17_21_tv1);
            listItemView.tv2 = (TextView) convertView.findViewById(R.id.list_item_17_21_tv2);
            listItemView.tv3 = (TextView) convertView.findViewById(R.id.list_item_17_21_tv3);
            listItemView.gridview1 = (GridView)convertView.findViewById(R.id.list_item_17_21_gridview1);
            listItemView.gridview2 = (GridView)convertView.findViewById(R.id.list_item_17_21_gridview2);

            BallsGridviewAdapter redAdapter = new BallsGridviewAdapter(context, R.layout.red_number1, listItems.get(groupPosition).getNumbersList().get(childPosition).getNumbers().getNumbersList1(), 1);
            listItemView.gridview1.setAdapter(redAdapter);

            BallsGridviewAdapter blueAdapter = new BallsGridviewAdapter(context, R.layout.red_number1, listItems.get(groupPosition).getNumbersList().get(childPosition).getNumbers().getNumbersList2(), 2);
            listItemView.gridview2.setAdapter(blueAdapter);

            if (listItems.get(groupPosition).getDoubleSingleType() == 1){
                //复式 隐藏编号
                listItemView.tv1.setVisibility(View.GONE);
            }else if (listItems.get(groupPosition).getDoubleSingleType() == 0){
                listItemView.tv1.setVisibility(View.VISIBLE);
            }


            //设置控件集到convertView
            convertView.setTag(listItemView);
        } else {
            listItemView = (ChildViewHolder) convertView.getTag();
        }

        listItemView.tv1.setText(listItems.get(groupPosition).getNumbersList().get(childPosition).getId());
        listItemView.tv2.setText(listItems.get(groupPosition).getNumbersList().get(childPosition).getFromSource());
        listItemView.tv3.setText(Constants.money_str + String.valueOf(listItems.get(groupPosition).getNumbersList().get(childPosition).getPrice()));

        if (listItemView.gridview1.getAdapter() != null){
            ((BallsGridviewAdapter)listItemView.gridview1.getAdapter()).notifyDataSetChanged();
        }

        if (listItemView.gridview2.getAdapter() != null){
            ((BallsGridviewAdapter)listItemView.gridview2.getAdapter()).notifyDataSetChanged();
        }

        /*if (childPosition >= listItems.get(groupPosition).getNumbersList().size()-1){
            LinearLayout ll = new LinearLayout(context);
            ll.setOrientation(LinearLayout.VERTICAL);
            ImageView line = new ImageView(context);
            line.setImageResource(R.drawable.line_single_0);
            ll.addView(line);
            TextView textView = new TextView(context);
            textView.setText("aaaaaaaaa");
            ll.addView(textView);

            ((ViewGroup)parent).addView(ll);
        }*/

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private final class ViewHolder {
        //彩种
        public TextView tv1;
        //期号
        public TextView tv2;
        //单复式
        public TextView tv3;

        //注数
        public TextView tv4;
        //总金额
        public TextView tv5;
    }

    private final class ChildViewHolder {
        public TextView tv1;
        public TextView tv2;
        public TextView tv3;

        public GridView gridview1;
        public GridView gridview2;
    }

    public LotteryTicketListViewAdapter1(Context context, List<SingleTicketBean> listItems, int type) {
        this.context = context;
        listContainer = LayoutInflater.from(context);   //创建视图容器并设置上下文
        this.listItems = listItems;

        this.layoutType = type;
    }
}