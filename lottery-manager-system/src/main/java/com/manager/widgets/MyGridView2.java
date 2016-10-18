package com.manager.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

/**
 * @Description:解决在scrollview中只显示第一行数据的问题
 * @author http://blog.csdn.net/finddreams
 */
public class MyGridView2 extends GridView {

	//是否划线的标记
	private int isFlag = 0;

	public MyGridView2(Context context) {
		this(context, null);

	}

	public MyGridView2(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MyGridView2(Context context, AttributeSet attrs, int defStyle) {
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
				canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
				//canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint);
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
