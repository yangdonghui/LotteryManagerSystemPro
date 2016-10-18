package com.manager.adapter.user;

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
 * 地址管理 list视图适配器
 * @author donghuiyang
 * @create time 2016/5/17 0017.
 */
public class AddressListViewAdapter extends BaseAdapter {

    private Context context;                            //运行上下文
    private List<HashMap<String, Object>> listItems;    //商品信息集合
    private LayoutInflater listContainer;               //视图容器

    private final class ViewHolder {
        //收货人
        public TextView name;
        //电话
        public TextView phone;
        //地址
        public TextView content;
    }

    public AddressListViewAdapter(Context context, List<HashMap<String, Object>> listItems) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (listItems == null) {
            return null;
        }

        //自定义视图
        ViewHolder listItemView = null;
        if (convertView == null) {
            listItemView = new ViewHolder();
            //获取list_item布局文件的视图
            convertView = listContainer.inflate(R.layout.list_item_11, null);
            //获取控件对象
            listItemView.name = (TextView) convertView.findViewById(R.id.list_item_11_tv1);
            listItemView.phone = (TextView) convertView.findViewById(R.id.list_item_11_tv2);
            listItemView.content = (TextView) convertView.findViewById(R.id.list_item_11_tv3);

            //设置控件集到convertView
            convertView.setTag(listItemView);
        } else {
            listItemView = (ViewHolder) convertView.getTag();
        }

        //设置文字
        listItemView.name.setText((String) listItems.get(position).get("ItemName"));
        listItemView.phone.setText((String) listItems.get(position).get("ItemPhone"));
        listItemView.content.setText((String) listItems.get(position).get("ItemContent"));

        return convertView;
    }
}