package com.manager.widgets;

import android.content.Context;
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
public class MyGridView1 extends GridView {
	public MyGridView1(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyGridView1(Context context) {
		super(context);
	}

	public MyGridView1(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
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
		View localView1 = getChildAt(0);
		if (localView1 == null) {
			return;
		}
		int column = getWidth() / localView1.getWidth();
		int childCount = getChildCount();
		int page = (int)Math.ceil(childCount/(float)column);
		Paint localPaint;
		localPaint = new Paint();
		localPaint.setStrokeWidth((float) 1.0); //设置线宽
		localPaint.setStyle(Paint.Style.STROKE);
		localPaint.setColor(getContext().getResources().getColor(R.color.view_line_2));

		for (int i=0;i<page;i++){
			for (int j=0;j<column;j++){
				View cellView = getChildAt(i*column+j);
				if (cellView != null){
					if (i==0){
						canvas.drawLine(cellView.getLeft(), cellView.getTop(), cellView.getRight(), cellView.getTop(), localPaint);
						if(j==0){
							canvas.drawLine(cellView.getLeft(), cellView.getTop(), cellView.getLeft(), cellView.getBottom(), localPaint);
						}

						canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
						canvas.drawLine(cellView.getLeft(), cellView.getBottom()-1, cellView.getRight(), cellView.getBottom()-1, localPaint);
					}else if (i == page-1){
						canvas.drawLine(cellView.getLeft(), cellView.getBottom()-1, cellView.getRight(), cellView.getBottom()-1, localPaint);
						if(j==0){
							canvas.drawLine(cellView.getLeft(), cellView.getTop(), cellView.getLeft(), cellView.getBottom(), localPaint);
						}

						canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
						canvas.drawLine(cellView.getLeft(), cellView.getBottom()-1, cellView.getRight(), cellView.getBottom()-1, localPaint);
					}else{
						canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
						canvas.drawLine(cellView.getLeft(), cellView.getBottom()-1, cellView.getRight(), cellView.getBottom()-1, localPaint);
					}
				}
			}
		}

		/*
		for(int i = 0;i < childCount;i++){
			View cellView = getChildAt(i);
			if((i + 1) % column == 0){
				//canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint);
				//canvas.drawLine(cellView.getLeft(), cellView.getTop(), cellView.getRight(), cellView.getTop(), localPaint);
			}
			if(i / column == 0 ){
				//第一行 第一列 最后一列
				canvas.drawLine(cellView.getLeft(), cellView.getTop(), cellView.getRight(), cellView.getTop(), localPaint);
				if (i % column == 0){
					canvas.drawLine(cellView.getLeft(), cellView.getTop(), cellView.getLeft(), cellView.getBottom(), localPaint);
				}else if(i % column == column-1){
					canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
				}
			}else if((i+1) / column == page-1){
				//最后一行 第一列
				canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint);
				if (i % column == 0){
					canvas.drawLine(cellView.getLeft(), cellView.getTop(), cellView.getLeft(), cellView.getBottom(), localPaint);
				}else if(i % column == column-1){
					canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
				}
			}else if((i + 1) > (childCount - (childCount % column))){
				//canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint);
				//canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
			}else{
				canvas.drawLine(cellView.getLeft(), cellView.getTop(), cellView.getRight(), cellView.getTop(), localPaint);
				canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint);
				canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
				canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint);
			}
		}

		if(childCount % column != 0){
			for(int j = 0 ;j < (column-childCount % column) ; j++){
				View lastView = getChildAt(childCount - 1);
				//canvas.drawLine(lastView.getRight() + lastView.getWidth() * j, lastView.getTop(), lastView.getRight() + lastView.getWidth()* j, lastView.getBottom(), localPaint);
			}
		}
		*/
	}
}
