package com.manager.photo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.manager.lotterypro.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ImageGridAdapter extends BaseAdapter{

    final String TAG = getClass().getSimpleName();

    public Map<String, String> map = new HashMap<String, String>();
    private int lastPosition = -1; //记录上一次选中的图片位置，-1表示未选中任何图片
    TextCallback textcallback = null;
    Activity act;
    List<ImageItem> dataList;
    public BitmapCache cache;
    private Handler mHandler;
    private int selectTotal = 0;

    public BitmapCache.ImageCallback callback = new BitmapCache.ImageCallback() {
        public void imageLoad(ImageView imageView, Bitmap bitmap,
                              Object... params) {
            if (imageView != null && bitmap != null) {
                String url = (String) params[0];
                if (url != null && url.equals((String) imageView.getTag())) {
                    ((ImageView) imageView).setImageBitmap(bitmap);
                } else {
                    Log.e(TAG, "callback, bmp not match");
                }
            } else {
                Log.e(TAG, "callback, bmp null");
            }
        }
    };

    public static interface TextCallback {
        public void onListen(int count);

        public void onAddItemListen(String imgPath1, String imgPath, int index);
        public void onRemoveItemListen(String imgPath, int index);
    }

    public void setTextCallback(TextCallback listener) {
        textcallback = listener;
    }

    public ImageGridAdapter(Activity act, List<ImageItem> list, Handler mHandler) {
        this.act = act;
        dataList = list;
        cache = new BitmapCache();
        this.mHandler = mHandler;
    }

    @Override
    public int getCount() {
        int count = 0;
        if (dataList != null) {
            count = dataList.size();
        }
        return count;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    class Holder {
        private View sub0;
        private View sub1;
        private ImageView iv;
        private CheckBox isSelected;
    }

    public void clear() {
        map.clear();
        map = null;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;

        if (convertView == null) {
            holder = new Holder();
            convertView = View.inflate(act, R.layout.photo_grid_item, null);
            holder.iv = (ImageView) convertView.findViewById(R.id.photo_grid_item_image);
            holder.isSelected = (CheckBox) convertView.findViewById(R.id.photo_grid_item_isselected_2);

            holder.sub0 = (View) convertView.findViewById(R.id.photo_grid_item_0);
            holder.sub1 = (View) convertView.findViewById(R.id.photo_grid_item_1);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        if (position == 0) {
            holder.sub1.setVisibility(View.VISIBLE);
            holder.sub0.setVisibility(View.GONE);
        } else {
            holder.sub1.setVisibility(View.GONE);
            holder.sub0.setVisibility(View.VISIBLE);

            ImageItem item = dataList.get(position);
            if (holder.isSelected.getVisibility() == View.VISIBLE){
                if (item.isSelected) {
                    holder.isSelected.setChecked(true);
                }else {
                    holder.isSelected.setChecked(false);
                }
            }

            holder.iv.setTag(item.imagePath);
            cache.displayBmp(holder.iv, item.thumbnailPath, item.imagePath, callback);
        }

        return convertView;
    }

    /**
     * 修改选中状态
     * @param position
     */
    public void changeState(int position) {
        ImageItem item = null;
        String path;

        if (position == 0) {
            if (lastPosition != -1) {
                item = dataList.get(lastPosition);
                item.isSelected = !item.isSelected;

                if (textcallback != null) {
                    map.remove(item.imagePath);
                    textcallback.onRemoveItemListen(item.imagePath, position);
                }

                lastPosition = -1;
                notifyDataSetChanged();         //通知适配器进行更新

            }

            return;
        }

        path = dataList.get(position).imagePath;
        if(lastPosition != -1) {
            item = dataList.get(lastPosition);
            item.isSelected = !item.isSelected;

            if (lastPosition == position && item.isSelected == false) {
                if (textcallback != null) {
                    map.remove(path);
                    textcallback.onRemoveItemListen(item.imagePath, position);
                }

                lastPosition = -1;
                notifyDataSetChanged();         //通知适配器进行更新

                return;
            }
        }

        {
            item = dataList.get(position);
            item.isSelected = !item.isSelected;
            lastPosition = position;                //记录本次选中的位置

            if (textcallback != null) {
                if (item.isSelected) {
                    map.put(path, path);
                    textcallback.onAddItemListen(item.thumbnailPath,item.imagePath, position);
                }
            }

            notifyDataSetChanged();         //通知适配器进行更新
        }
    }
}
