package com.manager.adapter.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.manager.bean.DeclareFaultBean;
import com.manager.lotterypro.R;

import java.util.List;

/**
 * 我的彩票 记录 子list视图适配器
 *
 * @author donghuiyang
 * @create time 2016/5/17 0017.
 */
public class ManagerFaultOrderChildListViewAdapter extends BaseAdapter {

    private Context context;                     //运行上下文
    private List<DeclareFaultBean> listItems;    //商品信息集合
    private LayoutInflater listContainer;        //视图容器

    private final class ViewHolder {

        public TextView name;
        public TextView address;
        public TextView phone;
    }


    public ManagerFaultOrderChildListViewAdapter(Context context, List<DeclareFaultBean> listItems) {
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
            convertView = listContainer.inflate(R.layout.list_item_24, null);
            //获取控件对象

            listItemView.name = (TextView) convertView.findViewById(R.id.list_item_24_name_tv);
            listItemView.address = (TextView) convertView.findViewById(R.id.list_item_24_address_tv);
            listItemView.phone = (TextView) convertView.findViewById(R.id.list_item_24_phone_tv);

            //设置控件集到convertView
            convertView.setTag(listItemView);
        } else {
            listItemView = (ViewHolder) convertView.getTag();
        }

        if (listItems == null) {
            return convertView;
        }

        listItemView.name.setText(listItems.get(position).getDeclareInfo());
        if (listItems.get(position).getBettingshopBean() != null){
            listItemView.address.setText(listItems.get(position).getBettingshopBean().getAddress());
            listItemView.phone.setText(listItems.get(position).getBettingshopBean().getUserPhone());
        }

        return convertView;
    }
}