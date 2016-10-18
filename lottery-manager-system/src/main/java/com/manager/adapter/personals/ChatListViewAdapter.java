package com.manager.adapter.personals;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.manager.bean.ChatBean;
import com.manager.bean.ChatMessageBean;
import com.manager.lotterypro.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 最近聊天人 list视图适配器
 * @author donghuiyang
 * @create time 2016/5/17 0017.
 */
public class ChatListViewAdapter extends BaseAdapter {

    private Context context;                            //运行上下文
    private List<ChatBean> listItems;    //商品信息集合
    private LayoutInflater listContainer;               //视图容器

    private final class ViewHolder {
        //图标
        public ImageView icon;
        //名称
        public TextView name;
        //事件
        public TextView time;
        //内容
        public TextView content;

        public View numView;
        public TextView numTv;
    }

    public ChatListViewAdapter(Context context, List<ChatBean> listItems) {
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
            convertView = listContainer.inflate(R.layout.list_item_28, null);
            //获取控件对象
            listItemView.icon = (ImageView) convertView.findViewById(R.id.list_item_28_icon);
            listItemView.name = (TextView) convertView.findViewById(R.id.list_item_28_tv1);
            listItemView.time = (TextView) convertView.findViewById(R.id.list_item_28_tv2);
            listItemView.content = (TextView) convertView.findViewById(R.id.list_item_28_tv3);

            listItemView.numView = (View) convertView.findViewById(R.id.list_item_28_message_num_tipview);
            listItemView.numTv = (TextView) convertView.findViewById(R.id.list_item_28_tv4);

            //设置控件集到convertView
            convertView.setTag(listItemView);
        } else {
            listItemView = (ViewHolder) convertView.getTag();
        }

        int state = listItems.get(position).getState();
        //设置
        ImageLoader.getInstance().displayImage((String) listItems.get(position).getIconUrl(), listItemView.icon);
        listItemView.name.setText((String) listItems.get(position).getUserName());

        ArrayList<ChatMessageBean> messageLists = listItems.get(position).getMessageLists();
        if (messageLists != null && messageLists.size() > 0) {
            listItemView.time.setText(messageLists.get(0).getTime());
            listItemView.content.setText(messageLists.get(0).getMessage());

            ViewGroup.LayoutParams lp = listItemView.numView.getLayoutParams();

            if (state == 0) {
                //未读
                if (messageLists.size() <= 99) {
                    listItemView.numTv.setText(String.valueOf(messageLists.size()));
                    lp.width = 26;
                    lp.height = 26;
                } else {
                    listItemView.numTv.setVisibility(View.GONE);
                    lp.width = 15;
                    lp.height = 15;
                }

                listItemView.numView.setLayoutParams(lp);
                listItemView.numView.setVisibility(View.VISIBLE);

            }else if (state == 1){
                //已读 取消 消息提示
                listItemView.numView.setVisibility(View.GONE);
            }
        }

        return convertView;
    }
}