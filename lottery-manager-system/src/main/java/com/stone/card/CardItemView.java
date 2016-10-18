package com.stone.card;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;
import com.manager.adapter.lotterycity.LotteryTicketChildListViewAdapter;
import com.manager.bean.SingleTicketBean;
import com.manager.lotterypro.R;

/**
 * 卡片View项
 *
 * @author xmuSistone
 */
@SuppressLint("NewApi")
public class CardItemView extends FrameLayout {
    private Context context;

    private Spring springX, springY;
    /*public ImageView imageView;
    public View maskView;
    private TextView userNameTv;
    private TextView imageNumTv;
    private TextView likeNumTv;*/
    private ListView listView;
    private TextView pageNumTv;
    private CardSlidePanel parentView;
    private View topLayout, bottomLayout;

    //数据索引值
    private int index = -1;

    public CardItemView(Context context) {
        this(context, null);
    }

    public CardItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CardItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;

        inflate(context, R.layout.list_item_17_3, this);
        /*imageView = (ImageView) findViewById(R.id.card_image_view);
        maskView = findViewById(R.id.maskView);
        userNameTv = (TextView) findViewById(R.id.card_user_name);
        imageNumTv = (TextView) findViewById(R.id.card_pic_num);
        likeNumTv = (TextView) findViewById(R.id.card_like);*/
        listView = (ListView) findViewById(R.id.list_item_17_3_listview);
        pageNumTv = (TextView) findViewById(R.id.card_bottom_page_num_tv);
        topLayout = findViewById(R.id.card_top_layout);
        bottomLayout = findViewById(R.id.card_bottom_layout);
        initSpring();
    }

    private void initSpring() {
        SpringConfig springConfig = SpringConfig.fromBouncinessAndSpeed(15, 20);
        SpringSystem mSpringSystem = SpringSystem.create();
        springX = mSpringSystem.createSpring().setSpringConfig(springConfig);
        springY = mSpringSystem.createSpring().setSpringConfig(springConfig);

        springX.addListener(new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                int xPos = (int) spring.getCurrentValue();
                setScreenX(xPos);
                parentView.onViewPosChanged(CardItemView.this);
            }
        });

        springY.addListener(new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                int yPos = (int) spring.getCurrentValue();
                setScreenY(yPos);
                parentView.onViewPosChanged(CardItemView.this);
            }
        });
    }

    public int getIndex() {
        return index;
    }

    public void fillData(int index, int total, SingleTicketBean itemData) {
        this.index = index;

        //页码
        pageNumTv.setText(String.valueOf(index+1) + " / " + String.valueOf(total));
        //号码列表
        LotteryTicketChildListViewAdapter myAdapter = new LotteryTicketChildListViewAdapter(context, itemData.getNumbersList());
        listView.setAdapter(myAdapter);

        /*ImageLoader.getInstance().displayImage(itemData.imagePath, imageView);
        userNameTv.setText(itemData.userName);
        imageNumTv.setText(itemData.imageNum + "");
        likeNumTv.setText(itemData.likeNum + "");*/
    }


    /**
     * 动画移动到某个位置
     */
    public void animTo(int xPos, int yPos) {
        setCurrentSpringPos(getLeft(), getTop());
        springX.setEndValue(xPos);
        springY.setEndValue(yPos);
    }

    /**
     * 设置当前spring位置
     */
    private void setCurrentSpringPos(int xPos, int yPos) {
        springX.setCurrentValue(xPos);
        springY.setCurrentValue(yPos);
    }

    public void setScreenX(int screenX) {
        this.offsetLeftAndRight(screenX - getLeft());
    }

    public void setScreenY(int screenY) {
        this.offsetTopAndBottom(screenY - getTop());
    }

    public void setParentView(CardSlidePanel parentView) {
        this.parentView = parentView;
    }

    public void onStartDragging() {
        springX.setAtRest();
        springY.setAtRest();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            // 兼容ViewPager，触点需要按在可滑动区域才行
            boolean shouldCapture = shouldCapture((int) ev.getX(), (int) ev.getY());
            if (shouldCapture) {
                parentView.getParent().requestDisallowInterceptTouchEvent(true);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 判断(x, y)是否在可滑动的矩形区域内
     * 这个函数也被CardSlidePanel调用
     *
     * @param x 按下时的x坐标
     * @param y 按下时的y坐标
     * @return 是否在可滑动的矩形区域
     */
    public boolean shouldCapture(int x, int y) {
        int captureLeft = topLayout.getLeft() + topLayout.getPaddingLeft();
        int captureTop = topLayout.getTop() + topLayout.getPaddingTop();
        int captureRight = bottomLayout.getRight() - bottomLayout.getPaddingRight();
        int captureBottom = bottomLayout.getBottom() - bottomLayout.getPaddingBottom();

        if (x > captureLeft && x < captureRight && y > captureTop && y < captureBottom) {
            return true;
        }
        return false;
    }
}
