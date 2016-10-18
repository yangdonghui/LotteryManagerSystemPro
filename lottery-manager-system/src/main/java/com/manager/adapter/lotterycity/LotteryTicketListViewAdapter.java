package com.manager.adapter.lotterycity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.manager.bean.SingleTicketBean;
import com.manager.common.Constants;
import com.manager.lotterypro.R;

import java.util.HashMap;
import java.util.List;

/**
 * 彩票 票号 订单支付、 委托详情等 list视图适配器
 * @author donghuiyang
 * @create time 2016/5/17 0017.
 */
public class LotteryTicketListViewAdapter extends BaseAdapter {

    private Context context;                     //运行上下文
    private List<SingleTicketBean> listItems;    //商品信息集合
    private LayoutInflater listContainer;        //视图容器

    private int layoutType;                      //所在界面类型

    public HashMap<Integer, Boolean> isSelected;

    private final class ViewHolder {
        public TextView tv1;
        public TextView tv2;
        public TextView tv3;

        public View childView;
        public View childView1;
        public ListView listview;
    }

    public LotteryTicketListViewAdapter(Context context, List<SingleTicketBean> listItems, int type) {
        this.context = context;
        listContainer = LayoutInflater.from(context);   //创建视图容器并设置上下文
        this.listItems = listItems;

        this.layoutType = type;

        init();
    }

    // 初始化 设置所有checkbox都为未选择
    public void init() {
        isSelected = new HashMap<Integer, Boolean>();

        for (int i = 0; i < listItems.size(); i++) {
            if (layoutType == Constants.EntrustBetOrderConfirmActLayoutType || listItems.get(i).getTicketState() == 0){
                isSelected.put(i, true);
            }else{
                isSelected.put(i, false);
            }
        }
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
            convertView = listContainer.inflate(R.layout.list_item_17_2, null);
            //获取控件对象
            listItemView.tv1 = (TextView) convertView.findViewById(R.id.list_item_17_2_tv1);
            listItemView.tv2 = (TextView) convertView.findViewById(R.id.list_item_17_2_tv2);
            listItemView.tv3 = (TextView) convertView.findViewById(R.id.list_item_17_2_tv3);
            listItemView.childView = (View) convertView.findViewById(R.id.list_item_17_2_childview);
            listItemView.childView1 = (View) convertView.findViewById(R.id.list_item_17_2_childview1);
            listItemView.listview = (ListView)convertView.findViewById(R.id.list_item_17_2_listview);

            if (layoutType == Constants.EntrustBetOrderConfirmActLayoutType){
                //订单确认界面
                listItemView.childView.setVisibility(View.VISIBLE);
                listItemView.childView1.setVisibility(View.GONE);
            }

            //设置控件集到convertView
            convertView.setTag(listItemView);
        } else {
            listItemView = (ViewHolder) convertView.getTag();
        }

        //总注数
        listItemView.tv2.setText("x"+listItems.get(position).getMultiple() + "注");
        listItemView.tv3.setText(Constants.money_str + String.valueOf(listItems.get(position).getPrice()));
        listItemView.tv1.setText(listItems.get(position).getName() + "-" + listItems.get(position).getInfo());

        //号码列表
        LotteryTicketChildListViewAdapter myAdapter = new LotteryTicketChildListViewAdapter(context, listItems.get(position).getNumbersList());
        listItemView.listview.setAdapter(myAdapter);

        if (isSelected.get(position)){
            //展开
            listItemView.childView.setVisibility(View.VISIBLE);
        }else{
            listItemView.childView.setVisibility(View.GONE);
        }

        return convertView;
    }
}