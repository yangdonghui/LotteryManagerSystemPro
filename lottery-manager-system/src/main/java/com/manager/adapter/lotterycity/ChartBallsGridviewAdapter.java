
package com.manager.adapter.lotterycity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.manager.lotterypro.R;

import java.util.ArrayList;


/**
 * 添加 球数据列表 适配器
 * @author Administrator
 *
 */
public class ChartBallsGridviewAdapter extends ArrayAdapter{

	private Context mContext = null;
	private ArrayList<String> mRedBallNums = null;
	private ArrayList<String> mBlueBallNums = null;

	private LayoutInflater mInflater = null;
	private int layoutId;


	public ChartBallsGridviewAdapter(Context context, int textViewResourceId, ArrayList<String> redNums, ArrayList<String> blueNums) {
		super(context, textViewResourceId);
		mInflater = LayoutInflater.from(context);
		mContext = context;
		mRedBallNums = redNums;
		mBlueBallNums = blueNums;


		layoutId = textViewResourceId;
	}
	
	
	public static class ViewHolder {
		TextView ballView;
	}
	

	@Override
	public int getCount() {
		int count = 0;
		if (mRedBallNums != null && mRedBallNums.size() > 0)
			count += mRedBallNums.size();

		if (mBlueBallNums != null && mBlueBallNums.size() > 0)
			count += mBlueBallNums.size();
		return count;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//此处一定要重新new出来，不要给控件设置tag.不要使用缓存机制。
		ViewHolder holder = new ViewHolder();
		if (convertView == null){
			convertView = mInflater.inflate(layoutId, null);
			holder.ballView = (TextView)convertView.findViewById(R.id.number_red1);
			holder.ballView.setTag("0");

			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}

		int redNums = 0;
		if (mRedBallNums != null && mRedBallNums.size() > 0){
			redNums = mRedBallNums.size();
		}

		if (mRedBallNums != null && mRedBallNums.size() > 0 && position < mRedBallNums.size()){
			if (mRedBallNums.get(position).trim().length() > 1) {
				holder.ballView.setText(mRedBallNums.get(position));
			}else {
				holder.ballView.setText("0"+ mRedBallNums.get(position));
			}

			holder.ballView.setTextAppearance(mContext,R.style.text_size_12_text_bg_color_11);

		}else if (mBlueBallNums != null && mBlueBallNums.size() > 0 && position >= mRedBallNums.size()){
			//蓝球
			int index = position - redNums;
			if (mBlueBallNums.get(index).trim().length() > 1) {
				holder.ballView.setText(mBlueBallNums.get(index));
			} else {
				holder.ballView.setText("0"+ mBlueBallNums.get(index));
			}

			//蓝球
			holder.ballView.setTextAppearance(mContext,R.style.text_size_12_text_bg_color_12);
		}

		return convertView;
	}
}

