package com.manager.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.manager.common.FontTools;

public class SideBar extends View {

	/**
	 * 触摸事件
	 */
	private ITouchingLetterChangedListener onTouchingLetterChangedListener;
	/**
	 * 侧边栏显示字母
	 */
	public String[] words = { "A", "B", "C", "D", "E", "F", "G", "H", "I","J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V","W", "X", "Y", "Z", "#" };

	public String[] newWords;
	private String specialStr = "#";
	/**
	 * 是否选中
	 */
	private int choose = -1;
	/**
	 * 提示显示文本框
	 */
	private TextView textViewDialog;
	/**
	 * 相应的画笔
	 */
	private Paint paint;

	private int sW,sH, singleH, textW, textH;
	private float textSize = 24f;
	private float xPos, yPos;

	public interface ITouchingLetterChangedListener {
		void OnTouchingLetterChanged(String cString);
	}

	/**
	 * 构造函数 数据初始化
	 * 
	 * @param context
	 *            上下文对象
	 * @param attrs
	 *            属性列表
	 * @param defStyleAttr
	 *            默认样式
	 */
	public SideBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		init();
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);

		sW = getWidth();// 获取对应宽度
		sH = getHeight();// 获取对应高度

		xPos = sW/2;
	}

	/**
	 * 初始化相应的数据
	 */
	private void init() {
		paint = new Paint();
		//paint.setTypeface(Typeface.DEFAULT_BOLD);
		paint.setAntiAlias(true);
		paint.setTextSize(textSize);

		textW = (int)paint.measureText(specialStr);
		textH = FontTools.getFontHeight(paint.getTextSize());
		singleH = (int)(textSize*1.5);

	}

	/**
	 * 构造函数 数据初始化
	 * @param context 上下文对象
	 * @param attrs  属性列表
	 */
	public SideBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	/**
	 * 构造函数 数据初始化
	 * @param context  上下文对象
	 */
	public SideBar(Context context) {
		this(context, null);
	}


	/**
	 * 绘制列表控件的方法
	 * 将要绘制的字母以从上到下的顺序绘制在一个指定区域
	 * 如果是进行选中的字母就进行高亮显示
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		if (newWords == null || newWords.length <= 0) return;

		//int height = getHeight();// 获取对应高度
		//int width = getWidth(); // 获取对应宽度
		int singleHeight = singleH/*sH / words.length*/;// 获取每一个字母的高度
		int len = newWords.length;
		for (int i = 0; i < newWords.length; i++) {
			paint.setColor(Color.rgb(206, 195, 178));

			// x坐标等于中间-字符串宽度的一半.
			float h = FontTools.getFontHeight(paint.getTextSize());
			float w = paint.measureText(newWords[i]);
			float xPos = sW / 2/* - paint.measureText(words[i]) / 2*/;
			//垂直居中
			//float yPos = sH/2 - (singleHeight * len) /2 + singleHeight * i + singleHeight - h/2;
			//靠上
			float yPos = singleHeight * i + singleHeight/2;

			// 选中的状态
			/*if (isdown) {
				if (choose == i){
					paint.setColor(Color.parseColor("#ff0000"));
					canvas.drawCircle(width/2,singleHeight * i + singleHeight - width/2,width/2,paint);
				}
				paint.setFakeBoldText(true);
			}*/

			canvas.drawText(newWords[i], xPos, yPos, paint);
			//paint.reset();// 重置画笔
		}
	}
	
	boolean isdown=false;
	/**
	 * 处理触摸事件的方法
	 * 用户按下时候，整个控件背景变化
	 * 根据按下y坐标 判断究竟用户按下那个字母
	 * 当前字母高亮显示 将其字母显示屏幕中央
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		if (newWords == null || newWords.length <= 0) return false;

		int action = event.getAction();
		final float y = event.getY();// 点击y坐标
		final int oldChoose = choose;
		final ITouchingLetterChangedListener listener = onTouchingLetterChangedListener;
		int len = newWords.length;
		final int c = (int) ((y) / getHeight() * words.length);// 点击y坐标所占总高度的比例*b数组的长度就等于点击b中的个数.
		//final int c = (int)((y - (sH-singleH*len)/2) / (singleH*len) * len);
		switch (action) {
		case MotionEvent.ACTION_UP:
			isdown=false;
			//setBackgroundResource(android.R.color.transparent);
			choose = -1;//
			invalidate();
			if (textViewDialog != null) {
				textViewDialog.setVisibility(View.INVISIBLE);
			}
			break;

		default:
			isdown=true;
			//setBackgroundResource(R.color.sidebar_bg_color);
			if (oldChoose != c) {
				if (c >= 0 && c < newWords.length) {
					if (listener != null) {
						listener.OnTouchingLetterChanged(newWords[c]);
					}
					if (textViewDialog != null) {
						textViewDialog.setText(newWords[c]);
						textViewDialog.setVisibility(View.VISIBLE);
					}

					choose = c;
					invalidate();
				}
			}
			break;
		}
		return true;
	}

	/**
	 * 字母改变的监听器
	 * @return  获取字母改变的监听器
	 */
	public ITouchingLetterChangedListener getOnTouchingLetterChangedListener() {
		return onTouchingLetterChangedListener;
	}

	/**
	 * 设置改变的监听器
	 * @param onTouchingLetterChangedListener 设置字母改变的监听器
	 */
	public void setOnTouchingLetterChangedListener(
			ITouchingLetterChangedListener onTouchingLetterChangedListener) {
		this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
	}
	/**
	 * 获取弹出 对话框
	 * @return  弹出对话框
	 */
	public TextView getTextViewDialog() {
		return textViewDialog;
	}

	/**
	 * 设置  对话框
	 * @param textViewDialog  文本的对话框
	 */
	public void setTextViewDialog(TextView textViewDialog) {
		this.textViewDialog = textViewDialog;
	}

	public void setWords(String[] words) {
		if (words == null) return;

		int len = words.length;
		if (newWords != null) {
			newWords = null;
		}

		newWords = new String[len + 1];
		System.arraycopy(words, 0, newWords, 0, len);
		newWords[len] = "#";
	}
}
