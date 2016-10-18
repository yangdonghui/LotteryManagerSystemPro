package com.manager.adapter.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.manager.bean.ItemBean;
import com.manager.common.StringHelper;
import com.manager.lotterypro.R;

import java.util.List;

/**
 * 管理员 即开票订单中 产品列表 子list视图适配器
 *
 * @author donghuiyang
 * @create time 2016/5/17 0017.
 */
public class ManagerBillingOrderChildListViewAdapter extends BaseAdapter {

    private Context context;                     //运行上下文
    private List<ItemBean> listItems;    //商品信息集合
    private LayoutInflater listContainer;        //视图容器

    private final class ViewHolder {

        public TextView title;
        public TextView price;
        public TextView num;
    }


    public ManagerBillingOrderChildListViewAdapter(Context context, List<ItemBean> listItems) {
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


        //自定义视图
        ViewHolder listItemView = null;
        if (convertView == null) {
            listItemView = new ViewHolder();
            //获取list_item布局文件的视图
            convertView = listContainer.inflate(R.layout.list_item_14, null);
            //获取控件对象

            listItemView.title = (TextView) convertView.findViewById(R.id.list_item_14_tv1);
            listItemView.price = (TextView) convertView.findViewById(R.id.list_item_14_tv2);
            listItemView.num = (TextView) convertView.findViewById(R.id.list_item_14_tv3);

            //设置控件集到convertView
            convertView.setTag(listItemView);
        } else {
            listItemView = (ViewHolder) convertView.getTag();
        }

        if (listItems == null) {
            return convertView;
        }

        listItemView.title.setText(listItems.get(position).getItemName());
        listItemView.price.setText(StringHelper.getPriceStr(listItems.get(position).getPrice()));
        listItemView.num.setText(StringHelper.getNumStr(listItems.get(position).getNum()));

        String price = String.valueOf(listItems.get(position).getPrice());
        if (price != null && !price.equals("")){
            if (price.equals("0")){
                listItemView.price.setText("免费");
            }else{
                listItemView.price.setText(context.getString(R.string.money_str) + price);
            }
        }else{
            listItemView.price.setVisibility(View.GONE);
        }

        return convertView;
    }
}