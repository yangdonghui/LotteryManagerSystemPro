package com.manager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.manager.lotterypro.R;

import java.util.List;

/**
 * 彩票城中的横向滑动的 数据适配器
 * @author donghuiyang
 * @create time 2016/5/6 0006.
 */
public class HorizontalScrollViewAdapter1
{

	private Context mContext;
	private LayoutInflater mInflater;
	private List<Integer> mDatas;

	public HorizontalScrollViewAdapter1(Context context, List<Integer> mDatas)
	{
		this.mContext = context;
		mInflater = LayoutInflater.from(context);
		this.mDatas = mDatas;
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
			convertView = mInflater.inflate(
					R.layout.horizontal_scroll_index_gallery_item2, parent, false);
			viewHolder.mImg = (ImageView) convertView
					.findViewById(R.id.id_index_gallery_item_image2);
			viewHolder.mText = (TextView) convertView
					.findViewById(R.id.id_index_gallery_item_text2);
			viewHolder.mBtn = (Button) convertView.findViewById(R.id.id_index_gallery_item_btn2);

			convertView.setTag(viewHolder);
		} else
		{
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.mImg.setImageResource(mDatas.get(position));
		viewHolder.mText.setText("体彩评说");

		return convertView;
	}

	private class ViewHolder
	{
		ImageView mImg;
		TextView mText;
		Button mBtn;
	}

}
