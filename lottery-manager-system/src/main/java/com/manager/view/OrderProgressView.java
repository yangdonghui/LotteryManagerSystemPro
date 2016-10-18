package com.manager.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import com.manager.lotterypro.R;

import java.util.ArrayList;

/**
 * 订单进度view
 * @author donghuiyang
 * @create time 2016/5/12 0012.
 */
public class OrderProgressView extends View {
    private Context context;
    //View对象
    private int width, height;

    private Matrix matrix = new Matrix();
    private Paint paint;

    private ArrayList<Bitmap> iconBmps = new ArrayList<>();

    private String[] iconTexts;

    private boolean isInit = false, isLoad = false;
    private Point centerPos = new Point();
    private float dis = 0;
    //申请进度状态
    private int state = 1;

    private static final int TEXTSIZE = 22;
    private static final int leftDis = 100;
    private static final int rightDis = 100;

    private static final int IconImgs[] = {
            R.mipmap.progress_icon_0,
            R.mipmap.progress_icon_1,
            R.mipmap.progress_icon_2,
    };

    public OrderProgressView(Context context) {
       this(context, null);
    }

    public OrderProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        initPaint();
    }

    public void setIconText(String[] texts) {
        iconTexts = texts;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (iconTexts == null || iconTexts.length <= 0) return;
        if (iconBmps.size() <= 0) return;

        paint.setColor(getResources().getColor(R.color.text_color_3));
        canvas.drawLine(leftDis, centerPos.y, leftDis + width, centerPos.y, paint);

        for (int i=0;i<iconTexts.length;i++){
            Bitmap bmp = iconBmps.get(0);
            if (i == state){
                //当前icon
                bmp = iconBmps.get(1);
            }else if (i < state){
                //完成icon
                bmp = iconBmps.get(2);
            }

            canvas.drawBitmap(bmp, leftDis + i * dis - bmp.getWidth() / 2, centerPos.y - bmp.getHeight() / 2, paint);

            float x = (leftDis + i * dis);
            float y = centerPos.y + bmp.getHeight();
            if (i == 1 || i == 3) {
                y = centerPos.y - bmp.getHeight()*0.8f;
            }

            /*if (i <= state){
                paint.setColor(getResources().getColor(R.color.text_color_3));
            }else {
                paint.setColor(getResources().getColor(R.color.text_color_4));
            }*/
            paint.setColor(getResources().getColor(R.color.text_color_2));

            canvas.drawText(iconTexts[i], x, y, paint);

            //时间
            paint.setColor(getResources().getColor(R.color.text_color_19));
            if (i == 1 || i == 3) {
                y -= TEXTSIZE+8;
            }else{
                y += TEXTSIZE+8;
            }
            canvas.drawText("2016-06-01 12:00", x, y, paint);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        width = getWidth() - (leftDis+ rightDis);// 获取对应宽度
        height = getHeight();// 获取对应高度

        centerPos.x = width / 2;
        centerPos.y = height / 2;
        dis = width / (iconTexts.length-1);

        if (!isLoad) {
            LoadBmp();
            isLoad = true;
        }
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);// 消除锯齿

        paint.setStrokeWidth((float) 1.0); //设置线宽
        //paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(TEXTSIZE);
        paint.setTextAlign(Paint.Align.CENTER);
    }

    /**
     * 加载图片
     */
    private void LoadBmp() {

        for (int i = 0; i < IconImgs.length; i++) {
            Bitmap bmp1 = BitmapFactory.decodeResource(getResources(), IconImgs[i]);
            iconBmps.add(bmp1);
        }
    }
}
