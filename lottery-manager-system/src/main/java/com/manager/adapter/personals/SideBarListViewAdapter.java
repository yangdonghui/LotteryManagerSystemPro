package com.manager.adapter.personals;

/**
 * @author donghuiyang
 * @create time 2016/4/20 0020.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.manager.bean.FriendBean;
import com.manager.lotterypro.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class SideBarListViewAdapter extends BaseAdapter {
    private Context context;

    private int fromType;

    private List<FriendBean> list;
    private List<FriendBean> addList;

    private ViewHolder viewHolder;

    public interface ICoallBack {
        public void showCheckImage(Bitmap bitmap, FriendBean user);
        public void deleteImage(FriendBean user);
    }

    private ICoallBack iCoallBack;
    public void setiCoallBack(ICoallBack iCoallBack){
        this.iCoallBack = iCoallBack;
    }

    public SideBarListViewAdapter(Context context, List<FriendBean> list, List<FriendBean> addList, int fromType) {
        this.context = context;
        this.list = list;
        this.addList = addList;

        this.fromType = fromType;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean isEnabled(int position) {
        // TODO Auto-generated method stub
        if (list.get(position).getType() == 0)// 如果是字母索引
            return false;// 表示不能点击
        return super.isEnabled(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        String item = list.get(position).getName();
        viewHolder = new ViewHolder();
        if (list.get(position).getType() == 0){
            convertView = LayoutInflater.from(context).inflate(R.layout.index,
                    null);
            viewHolder.indexTv = (TextView) convertView
                    .findViewById(R.id.indexTv);

            viewHolder.indexTv.setText(list.get(position).getName());
        }else if(list.get(position).getType() == -2){
            //添加好友界面 手机联系人等
            convertView = LayoutInflater.from(context).inflate(R.layout.item,
                    null);
            viewHolder.itemTv = (TextView) convertView
                    .findViewById(R.id.itemTv);

            viewHolder.itemInfoTv = (TextView) convertView.findViewById(R.id.contact_item_info_Tv);

            viewHolder.itemIcon = (ImageView) convertView.findViewById(R.id.contact_item_icon);

            //名称
            viewHolder.itemTv.setText(list.get(position).getName());
            //默认icon
            viewHolder.itemIcon.setImageResource(list.get(position).getDefaultIcon());
            viewHolder.itemInfoTv.setText(list.get(position).getInfo());

        }else{
            convertView = LayoutInflater.from(context).inflate(R.layout.item,
                    null);
            viewHolder.itemTv = (TextView) convertView
                    .findViewById(R.id.itemTv);

            viewHolder.itemInfoTv = (TextView) convertView
                    .findViewById(R.id.contact_item_info_Tv);
            viewHolder.itemInfoTv.setVisibility(View.GONE);

            viewHolder.itemIcon = (ImageView) convertView.findViewById(R.id.contact_item_icon);

            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.contact_item_checkBox);

            final ImageView itemIcon = viewHolder.itemIcon;

            if (fromType != -1){
                viewHolder.checkBox.setVisibility(View.VISIBLE);
                if (addList != null && addList.contains(list.get(position).getName())){
                    viewHolder.checkBox.setChecked(true);
                }

                viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            Bitmap bitmap = null;
                            bitmap = ((BitmapDrawable) itemIcon.getDrawable()).getBitmap();
                            if (iCoallBack != null){
                                iCoallBack.showCheckImage(bitmap, list.get(position));
                            }
                        } else {
                            // 用户显示在滑动栏删除
                            iCoallBack.deleteImage(list.get(position));
                        }
                    }
                });
            }

            //名称
            viewHolder.itemTv.setText(list.get(position).getName());

            if (list.get(position).getType() == -1){
                //默认icon
                viewHolder.itemIcon.setImageResource(list.get(position).getDefaultIcon());
            }else if (list.get(position).getType() == 1){
                //头像 url
                ImageLoader.getInstance().displayImage(list.get(position).getIconUrl(), viewHolder.itemIcon);
            }
        }

        /*if (item.length() == 1) {
            convertView = LayoutInflater.from(context).inflate(R.layout.index,
                    null);
            viewHolder.indexTv = (TextView) convertView
                    .findViewById(R.id.indexTv);
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.item,
                    null);
            viewHolder.itemTv = (TextView) convertView
                    .findViewById(R.id.itemTv);

            viewHolder.itemInfoTv = (TextView) convertView
                    .findViewById(R.id.contact_item_info_Tv);
            viewHolder.itemInfoTv.setVisibility(View.GONE);

            viewHolder.itemIcon = (ImageView) convertView.findViewById(R.id.contact_item_icon);
        }
        if (item.length() == 1) {
            viewHolder.indexTv.setText(list.get(position).getName());
        } else {
            //名称
            viewHolder.itemTv.setText(list.get(position).getName());

            //头像
            ImageLoader.getInstance().displayImage(list.get(position).getIconUrl(), viewHolder.itemIcon);
        }*/
        return convertView;
    }

    private class ViewHolder {
        private CheckBox checkBox;

        private TextView indexTv;
        private TextView itemTv;

        private TextView itemInfoTv;

        private ImageView itemIcon;
    }

}
