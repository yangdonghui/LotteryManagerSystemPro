package com.manager.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

import com.manager.lotterypro.R;

/**
 * @Description:解决在scrollview中只显示第一行数据的问题
 * @author http://blog.csdn.net/finddreams
 */
public class MyGridView extends GridView {

	private Bitmap lineBmp;

	//是否划线的标记
	private int isFlag = 0;

	public MyGridView(Context context) {
		this(context, null);

	}

	public MyGridView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MyGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyGridView, defStyle, 0);
		isFlag = a.getInt(R.styleable.MyGridView_line_bmp, 0);
		if (isFlag == 0){
			lineBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.bg_line_home);
		}
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

	@Override
	protected void dispatchDraw(Canvas canvas){
		super.dispatchDraw(canvas);

		if (lineBmp == null) return;

		View localView1 = getChildAt(0);
		if (localView1 == null) {
			return;
		}
		int column = getWidth() / localView1.getWidth();
		int childCount = getChildCount();
		Paint localPaint;
		localPaint = new Paint();
		localPaint.setStrokeWidth((float)1.0); //设置线宽
		localPaint.setStyle(Paint.Style.STROKE);
		localPaint.setColor(0xffCDC4B2);
		for(int i = 0;i < childCount;i++){
			View cellView = getChildAt(i);
			if(((i + 1) % column == 0)
					|| ((i + 1) > (childCount - (childCount % column)))){
				//canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint);//i + 1) % column == 0
				//canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint); //(i + 1) > (childCount - (childCount % column))
				continue;
			}else{
				//canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
				//canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint);

				if (lineBmp != null) {
					canvas.drawBitmap(lineBmp, cellView.getRight(), cellView.getTop() + Math.abs(75 - lineBmp.getHeight()),localPaint);
				}
			}
		}

		/*if(childCount % column != 0){
			for(int j = 0 ;j < (column-childCount % column) ; j++){
				View lastView = getChildAt(childCount - 1);
				canvas.drawLine(lastView.getRight() + lastView.getWidth() * j, lastView.getTop(), lastView.getRight() + lastView.getWidth()* j, lastView.getBottom(), localPaint);
			}
		}*/
	}
}
