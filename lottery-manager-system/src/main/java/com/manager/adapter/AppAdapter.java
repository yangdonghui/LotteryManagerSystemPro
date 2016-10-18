package com.manager.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.manager.lotterypro.R;

public class AppAdapter extends BaseAdapter {
	private List<Map> mList;
	private Context mContext;
	public static final int APP_PAGE_SIZE = 3;
	private PackageManager pm;
	
	public AppAdapter(Context context, List<Map> list, int page) {
		mContext = context;
		pm = context.getPackageManager();
		
		mList = new ArrayList<Map>();
		int i = page * APP_PAGE_SIZE;
		int iEnd = i+APP_PAGE_SIZE;
		while ((i<list.size()) && (i<iEnd)) {
			mList.add(list.get(i));
			i++;
		}
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Map appInfo = mList.get(position);
		AppItem appItem;
		if (convertView == null) {
			View v = LayoutInflater.from(mContext).inflate(R.layout.grid_item_0, null);
			
			appItem = new AppItem();
			appItem.mAppHead = (TextView)v.findViewById(R.id.home_grid_head_textView);
			appItem.mAppSubHead = (TextView)v.findViewById(R.id.home_grid_subhead_textView);

			v.setTag(appItem);
			convertView = v;
		} else {
			appItem = (AppItem)convertView.getTag();
		}
		appItem.mAppHead.setText("人气社区" + appInfo.get("name").toString());
		appItem.mAppSubHead.setText("双色球已开奖");
		
		return convertView;
	}

	/**
	 * 每个应用显示的内容，包括图标和名称
	 * @author Yao.GUET
	 *
	 */
	class AppItem {
		TextView mAppSubHead;
		TextView mAppHead;
	}
}
