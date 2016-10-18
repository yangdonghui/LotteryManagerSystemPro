package com.manager.adapter.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.manager.listener.DeclareRecordShowClickListener;
import com.manager.lotterypro.R;

import java.util.HashMap;
import java.util.List;

/**
 * 申请记录 list视图适配器
 * Created by Administrator on 2016/2/22 0022.
 */
public class DeclareRecordListViewAdapter extends BaseAdapter{

    private Context context;                            //运行上下文
    private List<HashMap<String, Object>> listItems;    //商品信息集合
    private LayoutInflater listContainer;               //视图容器

    private final static class ViewHolder {
        //图标
        public ImageView icon;
        //主分类
        public TextView mainType;
        //子分类
        public TextView childType;
        //时间
        public TextView time;
        //状态
        public TextView state;
        //编号
        public TextView number;
        //详情查看按钮
        public View detailBtn;

        public Button btnDel;
    }

    public DeclareRecordListViewAdapter(Context context, List<HashMap<String, Object>> listItems) {
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


    float startX, endX;
    Button curDel_btn;
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
            convertView = listContainer.inflate(R.layout.list_item_1, null);
            //获取控件对象
            //listItemView.icon = (ImageView) convertView.findViewById(R.id.list_item_1_icon_img);
            //listItemView.mainType = (TextView) convertView.findViewById(R.id.list_item_1_maintype_tv);
            listItemView.childType = (TextView) convertView.findViewById(R.id.list_item_1_childtype_tv);
            listItemView.time = (TextView) convertView.findViewById(R.id.list_item_1_time_tv);
            listItemView.state = (TextView) convertView.findViewById(R.id.list_item_1_state_tv);
            listItemView.number = (TextView) convertView.findViewById(R.id.list_item_1_number_tv);
            listItemView.detailBtn = (View) convertView.findViewById(R.id.list_item_1_showdetail_btn);

            //设置控件集到convertView
            convertView.setTag(listItemView);
        } else {
            listItemView = (ViewHolder) convertView.getTag();
        }

        //设置文字
        /*listItemView.mainType.setText((String) listItems.get(position).get("ItemMainType"));*/

        listItemView.time.setText((String) listItems.get(position).get("ItemTime"));
        listItemView.number.setText((String) listItems.get(position).get("ItemID"));
        listItemView.state.setText((String) listItems.get(position).get("ItemState"));
        listItemView.childType.setText((String) listItems.get(position).get("ItemChildType"));

        //状态不同显示不同颜色
        String text = listItemView.state.getText().toString();
        if (text.equals(context.getResources().getString(R.string.site_tab_content_0))) {
            //未分派
            listItemView.state.setTextColor(context.getResources().getColor(R.color.text_color_7));

        } else if (text.equals(context.getResources().getString(R.string.site_tab_content_1))) {
            //进行中
            listItemView.state.setTextColor(context.getResources().getColor(R.color.bg_color_6));

        } else if (text.equals(context.getResources().getString(R.string.site_tab_content_2))) {
            //进行中
            listItemView.state.setTextColor(context.getResources().getColor(R.color.text_color_3));
        }

        /*String maintext = listItemView.mainType.getText().toString();
        if (maintext.equals("耗材申报")){
            listItemView.icon.setImageResource(R.mipmap.site_icon_1);
        }*/

        String value = (String)listItems.get(position).get("ItemNumber");
        int id = Integer.valueOf(value);
        if (listItemView.detailBtn != null && listItemView.detailBtn.getVisibility() == View.VISIBLE) {
            //查看按钮
            listItemView.detailBtn.setOnClickListener(new DeclareRecordShowClickListener(context,id, true));
        }

        return convertView;
    }
}
