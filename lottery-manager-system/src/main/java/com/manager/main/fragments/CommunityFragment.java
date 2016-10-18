package com.manager.main.fragments;


import android.content.Intent;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.manager.adapter.community.CircleAdapter;
import com.manager.bean.UserBean;
import com.manager.common.Tools;
import com.manager.community.CommunityPhotoListAct;
import com.manager.community.SendPostAct;
import com.manager.community.bean.CircleItem;
import com.manager.community.bean.CommentConfig;
import com.manager.community.bean.CommentItem;
import com.manager.community.bean.FavortItem;
import com.manager.community.mvp.contract.CircleContract;
import com.manager.community.mvp.presenter.CirclePresenter;
import com.manager.community.widgets.CommentListView;
import com.manager.community.widgets.RecycleViewDivider;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.widgets.PhotoMenuDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * 社区首界面
 *
 * @author donghuiyang
 * @create time 2016/4/20 0020.
 */
public class CommunityFragment extends Fragment implements View.OnClickListener, CircleContract.View{
    protected static final String TAG = "CommunityFragment";

    private final static int TYPE_PULLREFRESH = 1;
    private final static int TYPE_UPLOADREFRESH = 2;

    //用户
    public UserBean mUserBean = SysApplication.userBean;
    //上下文对象
    private Context mContext = null;

    //发表类型
    private int sendType = 0;

    //发帖入口
    private ImageButton photoBtn;
    //头像
    private View headImg;

    private LinearLayout edittextbody;
    private EditText editText;
    private ImageView sendIv;

    private CircleAdapter circleAdapter;
    private CirclePresenter presenter;
    private CommentConfig commentConfig;
    private SuperRecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private RelativeLayout bodyLayout;

    private int screenHeight;
    private int editTextBodyHeight;
    private int currentKeyboardH;
    private int selectCircleItemH;
    private int selectCommentItemOffset;


    private List<Integer> datas = new ArrayList<Integer>(){
        {
            add(R.mipmap.debug_community2);
            add(R.mipmap.debug_community3);
        }
    };

    private View rootView = null;

    private static CommunityFragment mCommunityFragment = null;
    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static CommunityFragment newInstance() {
        if (mCommunityFragment == null) {
            mCommunityFragment = new CommunityFragment();
        }

        return mCommunityFragment;
    }

    /**
     * 使用静态工厂方法，将外部传入的参数可以通过Fragment.setArgument保存在它自己身上，
     * 这样我们可以在Fragment.onCreate(...)调用的时候将这些参数取出来
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getActivity();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mCommunityFragment = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (null != rootView) {
            //缓存view
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (null != parent) {
                parent.removeView(rootView);
            }
        } else {
            rootView = inflater.inflate(R.layout.community_fragment_layout, container, false);

            initTopView();
            initView();

            //圈子相关
            presenter = new CirclePresenter(this);
            initContent();
            presenter.loadData(TYPE_PULLREFRESH);
        }

        return rootView;
    }

    /**
     * 初始化top标题
     */
    private void initTopView() {
        ViewGroup topView = (ViewGroup) rootView.findViewById(R.id.community_fragment_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.comunity_title);
        View backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setVisibility(View.GONE);
    }

    private void initView(){
        photoBtn = (ImageButton) rootView.findViewById(R.id.community_photo_btn);
        photoBtn.setOnClickListener(this);
        photoBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.e("", "");

                Intent intent = new Intent(mContext, SendPostAct.class);
                intent.putExtra("sendType", sendType);
                startActivity(intent);

                return true;
            }
        });

        //点击头像进入 自己的相册界面
        /*headImg = (View) rootView.findViewById(R.id.community_head_imgv);
        headImg.setOnClickListener(this);*/

        /*ListView listView = (ListView) rootView.findViewById(R.id.community_fragment_listview);
        CommonAdapter<Integer> adapter = new CommonAdapter<Integer>(mContext, datas, R.layout.list_item_debug) {
            @Override
            public void convert(ViewHolder helper, Integer item) {
                helper.setImageResource(R.id.list_item_debug_icon, item.intValue());
            }
        };
        listView.setAdapter(adapter);*/
    }

    private void initContent(){
        recyclerView = (SuperRecyclerView) rootView.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.getMoreProgressView().getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        //分割线
        //recyclerView.addItemDecoration(new DivItemDecoration(50, true));
        recyclerView.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.VERTICAL, 2, getResources().getColor(R.color.im_line_color), true));

        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (edittextbody.getVisibility() == View.VISIBLE) {
                    updateEditTextBodyVisible(View.GONE, null);
                    return true;
                }
                return false;
            }
        });

        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.loadData(TYPE_PULLREFRESH);
                        recyclerView.setRefreshing(false);
                    }
                }, 2000);
            }
        });

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Glide.with(mContext).resumeRequests();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState != RecyclerView.SCROLL_STATE_IDLE) {
                    Glide.with(mContext).pauseRequests();
                }

            }
        });


        circleAdapter = new CircleAdapter(mContext);
        circleAdapter.setCirclePresenter(presenter);
        recyclerView.setAdapter(circleAdapter);

        edittextbody = (LinearLayout) rootView.findViewById(R.id.editTextBodyLl);
        editText = (EditText) rootView.findViewById(R.id.circleEt);
        sendIv = (ImageView) rootView.findViewById(R.id.sendIv);
        sendIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presenter != null) {
                    //发布评论
                    String content = editText.getText().toString().trim();
                    if (TextUtils.isEmpty(content)) {
                        Toast.makeText(mContext, "评论内容不能为空...", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    presenter.addComment(content, commentConfig);
                }
                updateEditTextBodyVisible(View.GONE, null);
            }
        });

        setViewTreeObserver();
    }

    private void setViewTreeObserver() {
        bodyLayout = (RelativeLayout) rootView.findViewById(R.id.bodyLayout);
        final ViewTreeObserver swipeRefreshLayoutVTO = bodyLayout.getViewTreeObserver();
        swipeRefreshLayoutVTO.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                Rect r = new Rect();
                bodyLayout.getWindowVisibleDisplayFrame(r);
                int statusBarH = getStatusBarHeight();//状态栏高度
                int screenH = bodyLayout.getRootView().getHeight();
                if (r.top != statusBarH) {
                    //在这个demo中r.top代表的是状态栏高度，在沉浸式状态栏时r.top＝0，通过getStatusBarHeight获取状态栏高度
                    r.top = statusBarH;
                }
                int keyboardH = screenH - (r.bottom - r.top);
                Log.d(TAG, "screenH＝ " + screenH + " &keyboardH = " + keyboardH + " &r.bottom=" + r.bottom + " &top=" + r.top + " &statusBarH=" + statusBarH);

                if (keyboardH == currentKeyboardH) {//有变化时才处理，否则会陷入死循环
                    return;
                }

                currentKeyboardH = keyboardH;
                screenHeight = screenH;//应用屏幕的高度
                editTextBodyHeight = edittextbody.getHeight();

                if (keyboardH < 150) {//说明是隐藏键盘的情况
                    updateEditTextBodyVisible(View.GONE, null);
                    return;
                }
                //偏移listview
                if (layoutManager != null && commentConfig != null) {
                    layoutManager.scrollToPositionWithOffset(commentConfig.circlePosition + CircleAdapter.HEADVIEW_SIZE, getListviewOffset(commentConfig));
                }
            }
        });
    }

    /**
     * 获取状态栏高度
     * @return
     */
    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 测量偏移量
     * @param commentConfig
     * @return
     */
    private int getListviewOffset(CommentConfig commentConfig) {
        if(commentConfig == null)
            return 0;
        //这里如果你的listview上面还有其它占高度的控件，则需要减去该控件高度，listview的headview除外。
        //int listviewOffset = mScreenHeight - mSelectCircleItemH - mCurrentKeyboardH - mEditTextBodyHeight;
        int listviewOffset = screenHeight - selectCircleItemH - currentKeyboardH - editTextBodyHeight - 100/*titleBar.getHeight()*/;
        if(commentConfig.commentType == CommentConfig.Type.REPLY){
            //回复评论的情况
            listviewOffset = listviewOffset + selectCommentItemOffset;
        }
        Log.i(TAG, "listviewOffset : " + listviewOffset);
        return listviewOffset;
    }

    private void measureCircleItemHighAndCommentItemOffset(CommentConfig commentConfig){
        if(commentConfig == null)
            return;

        int firstPosition = layoutManager.findFirstVisibleItemPosition();
        //只能返回当前可见区域（列表可滚动）的子项
        View selectCircleItem = layoutManager.getChildAt(commentConfig.circlePosition + CircleAdapter.HEADVIEW_SIZE - firstPosition);

        if(selectCircleItem != null){
            selectCircleItemH = selectCircleItem.getHeight();
        }

        if(commentConfig.commentType == CommentConfig.Type.REPLY){
            //回复评论的情况
            assert selectCircleItem != null;
            CommentListView commentLv = (CommentListView) selectCircleItem.findViewById(R.id.commentList);
            if(commentLv!=null){
                //找到要回复的评论view,计算出该view距离所属动态底部的距离
                View selectCommentItem = commentLv.getChildAt(commentConfig.commentPosition);
                if(selectCommentItem != null){
                    //选择的commentItem距选择的CircleItem底部的距离
                    selectCommentItemOffset = 0;
                    View parentView = selectCommentItem;
                    do {
                        int subItemBottom = parentView.getBottom();
                        parentView = (View) parentView.getParent();
                        if(parentView != null){
                            selectCommentItemOffset += (parentView.getHeight() - subItemBottom);
                        }
                    } while (parentView != null && parentView != selectCircleItem);
                }
            }
        }
    }

    /**
     * 发表 类型的菜单
     */
    private void showMenu(){
        PhotoMenuDialog menuDialog = new PhotoMenuDialog(mContext, R.style.CustomDialog1);
        Window window = menuDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.popupAnimation);  //添加动画
        menuDialog.show();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int)(Tools.getWindowWidth(getActivity())); //设置宽度
        window.setAttributes(lp);

        menuDialog.setClicklistener(new PhotoMenuDialog.ClickListenerInterface() {
            @Override
            public void doConfirm(int type) {
                switch (type) {
                    case 0: {
                        //小视频
                    }
                    break;
                    case 1: {
                        //拍照
                    }
                    break;
                    case 2: {
                        //从手机相册选择
                    }
                    break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == photoBtn){
            //发帖入口
            showMenu();
        }else if (v == headImg){
            //头像 进入本人相册
            SysApplication.enterNext1(mContext, CommunityPhotoListAct.class);
        }
    }

    /************************************************************************************************************/
    @Override
    public void update2DeleteCircle(String circleId) {
        List<CircleItem> circleItems = circleAdapter.getDatas();
        for(int i=0; i<circleItems.size(); i++){
            if(circleId.equals(circleItems.get(i).getId())){
                circleItems.remove(i);
                circleAdapter.notifyDataSetChanged();
                return;
            }
        }
    }

    @Override
    public void update2AddFavorite(int circlePosition, FavortItem addItem) {
        if(addItem != null){
            CircleItem item = (CircleItem) circleAdapter.getDatas().get(circlePosition);
            item.getFavorters().add(addItem);
            circleAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void update2DeleteFavort(int circlePosition, String favortId) {
        CircleItem item = (CircleItem) circleAdapter.getDatas().get(circlePosition);
        List<FavortItem> items = item.getFavorters();
        for(int i=0; i<items.size(); i++){
            if(favortId.equals(items.get(i).getId())){
                items.remove(i);
                circleAdapter.notifyDataSetChanged();
                return;
            }
        }
    }

    @Override
    public void update2AddComment(int circlePosition, CommentItem addItem) {
        if(addItem != null){
            CircleItem item = (CircleItem) circleAdapter.getDatas().get(circlePosition);
            item.getComments().add(addItem);
            circleAdapter.notifyDataSetChanged();
        }
        //清空评论文本
        editText.setText("");
    }

    @Override
    public void update2DeleteComment(int circlePosition, String commentId) {
        CircleItem item = (CircleItem) circleAdapter.getDatas().get(circlePosition);
        List<CommentItem> items = item.getComments();
        for(int i=0; i<items.size(); i++){
            if(commentId.equals(items.get(i).getId())){
                items.remove(i);
                circleAdapter.notifyDataSetChanged();
                return;
            }
        }
    }

    @Override
    public void updateEditTextBodyVisible(int visibility, CommentConfig commentConfig) {
        this.commentConfig = commentConfig;
        edittextbody.setVisibility(visibility);

        measureCircleItemHighAndCommentItemOffset(commentConfig);

        if(View.VISIBLE==visibility){
            editText.requestFocus();
            //弹出键盘
            Tools.showSoftInput( editText.getContext(),  editText);

        }else if(View.GONE==visibility){
            //隐藏键盘
            Tools.hideSoftInput( editText.getContext(),  editText);
        }
    }

    @Override
    public void update2loadData(int loadType, List<CircleItem> datas) {
        if (loadType == TYPE_PULLREFRESH){
            circleAdapter.setDatas(datas);
        }else if(loadType == TYPE_UPLOADREFRESH){
            circleAdapter.getDatas().addAll(datas);
        }
        circleAdapter.notifyDataSetChanged();

        if(circleAdapter.getDatas().size()<45 + CircleAdapter.HEADVIEW_SIZE){
            recyclerView.setupMoreListener(new OnMoreListener() {
                @Override
                public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            presenter.loadData(TYPE_UPLOADREFRESH);
                        }
                    }, 2000);

                }
            }, 1);
        }else{
            recyclerView.removeMoreListener();
            recyclerView.hideMoreProgress();
        }
    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String errorMsg) {

    }
}
