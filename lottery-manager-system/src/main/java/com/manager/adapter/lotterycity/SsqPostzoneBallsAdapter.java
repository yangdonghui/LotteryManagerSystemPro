
package com.manager.adapter.lotterycity;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.manager.Interface.ICoallBack11;
import com.manager.Interface.ICoallBack22;
import com.manager.lotterypro.R;


/**
 * 双色球 、大乐透等 适配器
 * @author Administrator
 *
 */
public class SsqPostzoneBallsAdapter extends ArrayAdapter{
	
	private Context mContext = null;
	private String[] mBallNums = null;
	private LayoutInflater mInflater = null; 
	private int layoutId;

	private GridView mGridView;
	private ArrayList selectBallNumList;
	private int ballColor;
	public HashMap<Integer, Boolean> isSelected;


	/**
	 * 初始化接口变量
	 */
	ICoallBack11 icallBack1 = null;
	ICoallBack22 icallBack2 = null;

	/**
	 * 自定义控件的自定义事件
	 * @param iBack 接口类型
	 */
	public void setonClick(ICoallBack11 iBack)
	{
		icallBack1 = iBack;
	}
	public void setonClick(ICoallBack22 iBack)
	{
		icallBack2 = iBack;
	}

	public SsqPostzoneBallsAdapter(Context context, int textViewResourceId,String[] ballNums, ArrayList ballNumList, int ballColor) {
		super(context, textViewResourceId);
		mInflater = LayoutInflater.from(context); 
		mContext = context;
		mBallNums = ballNums;
		layoutId = textViewResourceId;

		this.selectBallNumList = ballNumList;
		this.ballColor = ballColor;

		init();
	}

	public void setGridView(GridView gridView){
		this.mGridView = gridView;
	}

	// 初始化 设置所有checkbox都为未选择
	public void init() {
		isSelected = new HashMap<Integer, Boolean>();

		for (int i = 0; i < mBallNums.length; i++) {
			isSelected.put(i, false);
		}
	}


	@Override
	public int getCount() {
		return mBallNums.length;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		//此处一定要重新new出来，不要给控件设置tag.不要使用缓存机制。
		ViewHolder holder = new ViewHolder();
		if (convertView == null){
			convertView = mInflater.inflate(layoutId, null);
			holder.ballView = (TextView)convertView.findViewById(R.id.number_white);
			holder.ballBg = (ImageView) convertView.findViewById(R.id.number_white_imgv);
			holder.ballView.setTag("0");

			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}

		//设置球号码
		holder.ballView.setText(mBallNums[position]);
		/*if (position+1>=10) {
			holder.ballView.setText(mBallNums[position]);
		}else {
			holder.ballView.setText("0" + (position + 1));
		}*/

		if (isSelected.get(position)){
			holder.ballView.setTag("1");
			if (ballColor == 1){
				//红球
				holder.ballBg.setBackgroundResource(R.drawable.oval_shape_2);

			}else if (ballColor == 2){
				//蓝球
				holder.ballBg.setBackgroundResource(R.drawable.oval_shape_3);
			}
			//数字为白色
			holder.ballView.setTextAppearance(mContext, R.style.text_size_12_text_white);
		}else{
			holder.ballView.setTag("0");
			holder.ballBg.setBackgroundResource(R.drawable.oval_shape_6);
			holder.ballView.setTextAppearance(mContext, R.style.text_size_12_text_color_2);
		}

		/*if (this.selectBallNumList.size() > 0){
			for (int i=0;i<selectBallNumList.size();i++) {
				if (selectBallNumList.get(i).equals(holder.ballView.getText().toString())){
					holder.ballView.setTag("1");
					if (ballColor == 1){
						//红球
						holder.ballBg.setBackgroundResource(R.drawable.oval_shape_2);

					}else if (ballColor == 2){
						//蓝球
						holder.ballBg.setBackgroundResource(R.drawable.oval_shape_3);
					}

					//数字为白色
					holder.ballView.setTextAppearance(mContext, R.style.text_size_12_text_white);

				}
			}
		}else{
			//清空
			holder.ballView.setTag("0");
			holder.ballBg.setBackgroundResource(R.drawable.oval_shape_6);
			holder.ballView.setTextAppearance(mContext, R.style.text_size_12_text_color_2);
		}*/

		final TextView ballTv = holder.ballView;
		final ImageView ballBg = holder.ballBg;
		final String value = holder.ballView.getText().toString();
		holder.ballBg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String tag = ballTv.getTag().toString();
				if (tag.equals("0")){
					ballTv.setTag("1");

					isSelected.put(position, true);
					updateItemView(position, true);

					if (ballColor == 1){
						//添加红球
						if (icallBack1 != null){
							icallBack1.addRedView(value);
						}
					}else if (ballColor == 2){
						//添加蓝球
						if (icallBack2 !=null){
							icallBack2.addBlueView(value);
						}
					}
				}else if (tag.equals("1")){
					ballTv.setTag("0");

					ballBg.setBackgroundResource(R.drawable.oval_shape_6);
					ballTv.setTextAppearance(mContext, R.style.text_size_12_text_color_2);

					isSelected.put(position, false);
					updateItemView(position, false);

					if (ballColor == 1){
						//删除红球
						if (icallBack1 != null){
							icallBack1.removeRedView(value);
						}
					}else if (ballColor == 2){
						//删除蓝球
						if (icallBack2 != null){
							icallBack2.removeBlueView(value);
						}
					}
				}
			}
		});

		return convertView;
	}

	/**
	 * 清除选号
	 */
	public void clearSelectNumbers(){
		if (mGridView != null) {
			for (int i = 0; i < mGridView.getChildCount(); i++) {
				if (isSelected.get(i) == true) {
					isSelected.put(i, false);

					View convertView = mGridView.getChildAt(i);
					ViewHolder holder = (ViewHolder) convertView.getTag();

					if (isSelected.get(i)){
						holder.ballView.setTag("1");
						if (ballColor == 1){
							//红球
							holder.ballBg.setBackgroundResource(R.drawable.oval_shape_2);

						}else if (ballColor == 2){
							//蓝球
							holder.ballBg.setBackgroundResource(R.drawable.oval_shape_3);
						}
						//数字为白色
						holder.ballView.setTextAppearance(mContext, R.style.text_size_12_text_white);
					}else{
						holder.ballView.setTag("0");
						holder.ballBg.setBackgroundResource(R.drawable.oval_shape_6);
						holder.ballView.setTextAppearance(mContext, R.style.text_size_12_text_color_2);
					}
				}
			}
		}
	}

	/**
	 *	随机选号 view样式更新
	 */
	public void updateAllSelectNumberView(ArrayList<Integer> ballNumList){
		if (ballNumList == null) return;

		for (int i=0;i<ballNumList.size();i++){
			int index = ballNumList.get(i) - 1;
			isSelected.put(index, true);
			updateItemView(index, true);
		}
	}

	/**
	 * 更新某项item
	 */
	public void updateItemView(long id, boolean flag) {

		if (mGridView != null) {
			for (int i = 0; i < mGridView.getChildCount(); i++){
				if (i == id){
					View convertView = mGridView.getChildAt(i);
					ViewHolder holder = (ViewHolder) convertView.getTag();

					if (flag){
						holder.ballView.setTag("1");
						if (ballColor == 1){
							//红球
							holder.ballBg.setBackgroundResource(R.drawable.oval_shape_2);

						}else if (ballColor == 2){
							//蓝球
							holder.ballBg.setBackgroundResource(R.drawable.oval_shape_3);
						}
						//数字为白色
						holder.ballView.setTextAppearance(mContext, R.style.text_size_12_text_white);
					}else{
						holder.ballView.setTag("0");
						holder.ballBg.setBackgroundResource(R.drawable.oval_shape_6);
						holder.ballView.setTextAppearance(mContext, R.style.text_size_12_text_color_2);
					}

					break;
				}
			}
		}
	}

	public static class ViewHolder {
		TextView ballView;
		ImageView ballBg;
	}
}

