
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
public class BallsGridviewAdapter extends ArrayAdapter{

	private Context mContext = null;
	private ArrayList<String> mBallNums = null;
	private LayoutInflater mInflater = null;
	private int layoutId;

	private int ballColor;


	public BallsGridviewAdapter(Context context, int textViewResourceId, ArrayList<String> ballNums, int ballColor) {
		super(context, textViewResourceId);
		mInflater = LayoutInflater.from(context); 
		mContext = context;
		mBallNums = ballNums;
		layoutId = textViewResourceId;

		this.ballColor = ballColor;
	}
	
	
	public static class ViewHolder {
		TextView ballView;
	}
	

	@Override
	public int getCount() {
		return mBallNums.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//此处一定要重新new出来，不要给控件设置tag.不要使用缓存机制。
		ViewHolder holder = new ViewHolder();
		if (convertView == null){
			convertView = mInflater.inflate(layoutId, null);
			holder.ballView = (TextView)convertView.findViewById(R.id.number_red1);
			holder.ballView.setTag("0");

			if (ballColor == 2){
				//蓝球
				holder.ballView.setTextAppearance(mContext,R.style.text_size_12_text_bg_color_12);
			}

			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}

		//设置球号码
		if (mBallNums.get(position).trim().length() > 1) {
			holder.ballView.setText(mBallNums.get(position));
		}else {
			holder.ballView.setText("0"+ mBallNums.get(position));
		}

		return convertView;
	}
}

