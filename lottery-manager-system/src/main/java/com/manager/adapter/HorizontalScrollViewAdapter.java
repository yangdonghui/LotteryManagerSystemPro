package com.manager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.manager.lotterypro.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HorizontalScrollViewAdapter extends BaseAdapter
{

	private Context mContext;
	private LayoutInflater mInflater;
	private List<Integer> mDatas;
	private List<HashMap<String, Object>> listItems = new ArrayList<>();
	private int layoutType;

	public HorizontalScrollViewAdapter(Context context, List<Integer> mDatas, int layoutType)
	{
		this.mContext = context;
		mInflater = LayoutInflater.from(context);
		this.mDatas = mDatas;
		this.layoutType = layoutType;
	}

	public HorizontalScrollViewAdapter(Context context, List<HashMap<String, Object>> mDatas, int layoutType, int num)
	{
		this.mContext = context;
		mInflater = LayoutInflater.from(context);
		this.listItems = mDatas;
		this.layoutType = layoutType;
	}

	public int getCount()
	{
		return mDatas.size();
	}

	public Object getItem(int position)
	{
		return mDatas.get(position);
	}

	public long getItemId(int position)
	{
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder viewHolder = null;
		if (convertView == null)
		{
			viewHolder = new ViewHolder();
			if (layoutType == 0 || layoutType == 3  || layoutType == 4) {
				//推荐社区 我的社区 列表子项
				convertView = mInflater.inflate(
						R.layout.horizontal_scroll_index_gallery_item, parent, false);
				viewHolder.mImg = (ImageView) convertView
						.findViewById(R.id.id_index_gallery_item_image);
				viewHolder.mText = (TextView) convertView
						.findViewById(R.id.id_index_gallery_item_text);

				if (layoutType == 4){
					//好友 详细界面 社区列表 修改字体大小
					viewHolder.mText.setTextAppearance(mContext, R.style.text_size_12_text_color_3);
				}
			}else if(layoutType == 1) {
				//我的好友列表子项
				convertView = mInflater.inflate(
						R.layout.horizontal_scroll_index_gallery_item1, parent, false);
				viewHolder.mImg = (ImageView) convertView
						.findViewById(R.id.id_index_gallery_item_image1);
				viewHolder.mText = (TextView) convertView
						.findViewById(R.id.id_index_gallery_item_text1);
			}else if (layoutType == 2){
				//彩票城 推荐列表
				convertView = mInflater.inflate(
						R.layout.horizontal_scroll_index_gallery_item2, parent, false);
				viewHolder.mImg = (ImageView) convertView
						.findViewById(R.id.id_index_gallery_item_image2);
				viewHolder.mText = (TextView) convertView
						.findViewById(R.id.id_index_gallery_item_text2);
				viewHolder.mBtn = (Button) convertView.findViewById(R.id.id_index_gallery_item_btn2);
			}

			assert convertView != null;
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}


		if (mDatas == null || mDatas.size() <= 0) return null;

		viewHolder.mImg.setImageResource(mDatas.get(position));

		if (layoutType == 0){
			viewHolder.mText.setText("体彩社区");
		}else if(layoutType == 1) {
			viewHolder.mText.setText("小小");
		}else if(layoutType == 2){
			viewHolder.mText.setText("体彩评说");
		}else if (layoutType == 3) {
			viewHolder.mText.setText("我的社区");
		}

		return convertView;
	}

	private class ViewHolder
	{
		ImageView mImg;
		TextView mText;
		Button mBtn;
	}

}
