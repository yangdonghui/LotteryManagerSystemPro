package com.manager.adapter.community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.manager.lotterypro.R;

import java.util.HashMap;
import java.util.List;

/**
 * 社区列表 list视图适配器
 * @author donghuiyang
 * @create time 2016/5/17 0017.
 */
public class CommunityListViewAdapter extends BaseAdapter {

    private Context context;                            //运行上下文
    private List<HashMap<String, Object>> listItems;    //商品信息集合
    private LayoutInflater listContainer;               //视图容器

    private final class ViewHolder {
        //图标
        public ImageView icon;
        //标题
        public TextView name;
        //内容
        public TextView content;
        //帖子数量
        public TextView num;
    }

    public CommunityListViewAdapter(Context context, List<HashMap<String, Object>> listItems) {
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
        //自定义视图
        ViewHolder listItemView = null;
        if (convertView == null) {
            listItemView = new ViewHolder();
            //获取list_item布局文件的视图
            convertView = listContainer.inflate(R.layout.list_item_8, null);
            //获取控件对象
            listItemView.icon = (ImageView) convertView.findViewById(R.id.list_item_8_icon_imageview);
            listItemView.name = (TextView) convertView.findViewById(R.id.list_item_8_title_tv);
            listItemView.content = (TextView) convertView.findViewById(R.id.list_item_8_info1_tv);
            listItemView.num = (TextView) convertView.findViewById(R.id.list_item_8_info_tv);

            //设置控件集到convertView
            convertView.setTag(listItemView);
        } else {
            listItemView = (ViewHolder) convertView.getTag();
        }

        if (listItems == null) return convertView;
        //设置文字
        listItemView.icon.setImageResource((Integer)listItems.get(position).get("ItemIcon"));
        listItemView.name.setText((String) listItems.get(position).get("ItemName"));
        listItemView.content.setText((String) listItems.get(position).get("ItemContent"));
        listItemView.num.setText((String) listItems.get(position).get("ItemNum"));

        return convertView;
    }
}