package com.manager.adapter.community;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.manager.community.widgets.MultiImageView;
import com.manager.lotterypro.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.HashMap;
import java.util.List;

/**
 * 帖子列表 list视图适配器
 * @author donghuiyang
 * @create time 2016/5/17 0017.
 */
public class PostsListViewAdapter extends BaseAdapter {
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
        //标题
        public TextView name;

        //内容
        public TextView title;
        //内容
        public TextView content;

        //时间
        public TextView time;

        /** 图片*/
        public MultiImageView multiImageView;

        public View favortView;
        public TextView favortTv;

        public View commentView;
        public TextView commentTv;

    }

    public PostsListViewAdapter(Context context, List<HashMap<String, Object>> listItems) {
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
    public int getItemViewType(int position) {
        int itemType = ITEM_VIEW_TYPE_DEFAULT;
        String type = (String)listItems.get(position).get("ItemType");
        if (ITEM_TYPE_URL.equals(type)) {
            itemType = ITEM_VIEW_TYPE_URL;
        } else if (ITEM_TYPE_IMAGE.equals(type)) {
            itemType = ITEM_VIEW_TYPE_IMAGE;
        } else if (ITEM_TYPE_VEDIO.equals(type)) {
            itemType = ITEM_VIEW_TYPE_VEDIO;
        }else {
            itemType = ITEM_VIEW_TYPE_DEFAULT;
        }
        return itemType;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (listItems == null) {
            return null;
        }

        int itemViewType = getItemViewType(position);
        //自定义视图
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            //获取list_item布局文件的视图
            convertView = listContainer.inflate(R.layout.list_item_9, null);

            //获取控件对象
            ViewStub linkOrImgViewStub = (ViewStub) convertView.findViewById(R.id.linkOrImgViewStub1);
            switch (itemViewType) {
                case ITEM_VIEW_TYPE_URL:// 链接view
                    break;
                case ITEM_VIEW_TYPE_IMAGE:// 图片view
                    linkOrImgViewStub.setLayoutResource(R.layout.viewstub_imgbody);
                    linkOrImgViewStub.inflate();

                    MultiImageView.MAX_WIDTH = 0;
                    MultiImageView multiImageView = (MultiImageView) convertView.findViewById(R.id.multiImagView);
                    if(multiImageView != null){
                        holder.multiImageView = multiImageView;
                    }
                    break;
                case ITEM_VIEW_TYPE_VEDIO://视频
                    break;

                default:
                    break;
            }

            holder.icon = (ImageView) convertView.findViewById(R.id.list_item_9_icon_imageview);
            holder.name = (TextView) convertView.findViewById(R.id.list_item_9_nick_tv);
            holder.title = (TextView) convertView.findViewById(R.id.list_item_9_title_tv);
            holder.content = (TextView) convertView.findViewById(R.id.list_item_9_content_tv);
            holder.time = (TextView) convertView.findViewById(R.id.list_item_9_time_tv);
            holder.favortTv = (TextView) convertView.findViewById(R.id.list_item_9_favort_tv);
            holder.favortView = (View) convertView.findViewById(R.id.list_item_9_favort_view);

            holder.commentTv = (TextView) convertView.findViewById(R.id.list_item_9_comment_tv);
            holder.commentView = (View) convertView.findViewById(R.id.list_item_9_comment_view);

            //设置控件集到convertView
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //设置文字
        ImageLoader.getInstance().displayImage((String) listItems.get(position).get("ItemIcon"), holder.icon);

        holder.name.setText((String) listItems.get(position).get("ItemName"));
        holder.title.setText((String) listItems.get(position).get("ItemTitle"));
        holder.content.setVisibility(TextUtils.isEmpty((String) listItems.get(position).get("ItemContent")) ? View.GONE : View.VISIBLE);
        holder.time.setText((String)listItems.get(position).get("ItemTime"));
        holder.favortTv.setText((String) listItems.get(position).get("ItemFavort"));
        holder.commentTv.setText((String) listItems.get(position).get("ItemComment"));

        switch (itemViewType) {
            case ITEM_VIEW_TYPE_URL:// 处理链接动态的链接内容和和图片
                break;
            case ITEM_VIEW_TYPE_IMAGE:// 处理图片
                final List<String> photos = (List<String>)listItems.get(position).get("ItemPhotos");
                if (photos != null && photos.size() > 0) {
                    holder.multiImageView.setVisibility(View.VISIBLE);
                    holder.multiImageView.setList(photos);
                    /*holder.multiImageView.setOnItemClickListener(new MultiImageView.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            // 因为单张图片时，图片实际大小是自适应的，imageLoader缓存时是按测量尺寸缓存的
						ImagePagerActivity.imageSize = new ImageSize(view.getMeasuredWidth(), view.getMeasuredHeight());
						ImagePagerActivity.startImagePagerActivity(mContext, photos, position);
                        }
                    });*/
                } else {
                    holder.multiImageView.setVisibility(View.GONE);
                }

                break;
            default:
                break;
        }

        return convertView;
    }
}