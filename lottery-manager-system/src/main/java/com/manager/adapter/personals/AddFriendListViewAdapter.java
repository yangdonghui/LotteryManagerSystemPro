package com.manager.adapter.personals;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.manager.lotterypro.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.HashMap;
import java.util.List;

/**
 * 添加好友 list视图适配器
 * @author donghuiyang
 * @create time 2016/5/17 0017.
 */
public class AddFriendListViewAdapter extends BaseAdapter {

    private Context context;                            //运行上下文
    private List<HashMap<String, Object>> listItems;    //商品信息集合
    private LayoutInflater listContainer;               //视图容器

    private final class ViewHolder {
        //图标
        public ImageView icon;
        //名称
        public TextView name;
        //内容
        public TextView content;
        //按钮
        public TextView operateBtn;
    }

    public AddFriendListViewAdapter(Context context, List<HashMap<String, Object>> listItems) {
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
        int state = (Integer) listItems.get(position).get("ItemState");

        if (convertView == null) {
            listItemView = new ViewHolder();
            //获取list_item布局文件的视图
            convertView = listContainer.inflate(R.layout.item, null);
            //获取控件对象
            listItemView.icon = (ImageView) convertView.findViewById(R.id.contact_item_icon);
            listItemView.name = (TextView) convertView.findViewById(R.id.itemTv);
            listItemView.content = (TextView) convertView.findViewById(R.id.contact_item_info_Tv);

            listItemView.operateBtn = (TextView) convertView.findViewById(R.id.contact_item_add_btn);
            listItemView.operateBtn.setVisibility(View.VISIBLE);
            switch (state){
                case 0:
                case 1:{
                    //接受  //添加
                    listItemView.operateBtn.setVisibility(View.VISIBLE);
                    listItemView.operateBtn.setBackgroundResource(R.drawable.rect_radius_18);
                    listItemView.operateBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //接受 添加操作
                        }
                    });
                }
                break;
                case 2:
                case 3:{
                    //等待验证 //已添加
                    listItemView.operateBtn.setVisibility(View.VISIBLE);
                    listItemView.operateBtn.setBackgroundDrawable(null);
                }
                break;
                default:
                    break;
            }

            //设置控件集到convertView
            convertView.setTag(listItemView);
        } else {
            listItemView = (ViewHolder) convertView.getTag();
        }

        //设置
        ImageLoader.getInstance().displayImage((String) listItems.get(position).get("ItemIcon"), listItemView.icon);

        listItemView.name.setText((String) listItems.get(position).get("ItemName"));
        listItemView.content.setText((String) listItems.get(position).get("ItemInfo"));
        listItemView.operateBtn.setText((String) listItems.get(position).get("ItemStateStr"));



        return convertView;
    }
}