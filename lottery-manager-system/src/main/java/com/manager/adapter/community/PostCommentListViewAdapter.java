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
 * 帖子详情中 评论 list视图适配器
 * @author donghuiyang
 * @create time 2016/5/17 0017.
 */
public class PostCommentListViewAdapter extends BaseAdapter {
    private static final int ITEM_VIEW_TYPE_DEFAULT = 0;
    private static final int ITEM_VIEW_TYPE_URL = 1;
    private static final int ITEM_VIEW_TYPE_IMAGE = 2;
    private static final int ITEM_VIEW_TYPE_VEDIO = 3;


    private static final String ITEM_TYPE_URL = "1";
    private static final String ITEM_TYPE_IMAGE = "2";
    private static final String ITEM_TYPE_VEDIO = "3";
    private static final int ITEM_VIEW_TYPE_COUNT = 3;


    private Context context;                            //运行上下文
    private List<HashMap<String, Object>> listItems;    //商品信息集合
    private LayoutInflater listContainer;               //视图容器

    private final class ViewHolder {
        //图标
        public ImageView icon;
        //昵称
        public TextView name;

        //内容
        public TextView content;

        //时间
        public TextView time;

        /** 图片*/
        public ViewGroup multiImageView;

        public View favortView;
        public TextView favortTv;

        public View commentView;

        public TextView floorView;

    }

    public PostCommentListViewAdapter(Context context, List<HashMap<String, Object>> listItems) {
        this.context = context;
        listContainer = LayoutInflater.from(context);   //创建视图容器并设置上下文
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        if (listItems == null || listItems.size() <= 0){
            return 5;
        }
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
    public int getItemViewType(int position) {

        return ITEM_VIEW_TYPE_DEFAULT;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (listItems == null) {
            return null;
        }

        //自定义视图
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            //获取list_item布局文件的视图
            convertView = listContainer.inflate(R.layout.list_item_10, null);

            holder.icon = (ImageView) convertView.findViewById(R.id.list_item_10_icon_imageview);
            holder.name = (TextView) convertView.findViewById(R.id.list_item_10_nick_tv);
            holder.content = (TextView) convertView.findViewById(R.id.list_item_10_content_tv);
            holder.time = (TextView) convertView.findViewById(R.id.list_item_10_time_tv);

            holder.floorView = (TextView) convertView.findViewById(R.id.list_item_10_floor_tv);

            holder.favortTv = (TextView) convertView.findViewById(R.id.list_item_10_favort_tv);
            holder.favortView = (View) convertView.findViewById(R.id.list_item_10_favort_view);

            holder.commentView = (View) convertView.findViewById(R.id.list_item_10_comment_view);

            //设置控件集到convertView
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //设置文字
        /*ImageLoader.getInstance().displayImage((String) listItems.get(position).get("ItemIcon"), holder.icon);

        holder.name.setText((String) listItems.get(position).get("ItemName"));
        holder.floorView.setText((String) listItems.get(position).get("ItemFloor"));
        holder.content.setVisibility(TextUtils.isEmpty((String) listItems.get(position).get("ItemContent")) ? View.GONE : View.VISIBLE);
        holder.time.setText((String)listItems.get(position).get("ItemTime"));
        holder.favortTv.setText((String) listItems.get(position).get("ItemFavort"));*/

        return convertView;
    }
}