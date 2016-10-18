package com.manager.adapter.community;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.manager.adapter.community.viewholder.CircleViewHolder;
import com.manager.adapter.community.viewholder.ImageViewHolder;
import com.manager.adapter.community.viewholder.URLViewHolder;
import com.manager.adapter.community.viewholder.VideoViewHolder;
import com.manager.common.DatasUtil;
import com.manager.common.UrlUtils;
import com.manager.community.CommentDialog;
import com.manager.community.ImagePagerActivity;
import com.manager.community.bean.ActionItem;
import com.manager.community.bean.CircleItem;
import com.manager.community.bean.CommentConfig;
import com.manager.community.bean.CommentItem;
import com.manager.community.bean.FavortItem;
import com.manager.community.mvp.presenter.CirclePresenter;
import com.manager.community.widgets.CircleVideoView;
import com.manager.community.widgets.CommentListView;
import com.manager.community.widgets.MultiImageView;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.widgets.SnsPopupWindow;

import java.util.List;

/**
 * Created by yiwei on 16/5/17.
 */
public class CircleAdapter extends BaseRecycleViewAdapter {

    public final static int TYPE_HEAD = 0;

    private static final int STATE_IDLE = 0;
    private static final int STATE_ACTIVED = 1;
    private static final int STATE_DEACTIVED = 2;
    private int videoState = STATE_IDLE;
    public static final int HEADVIEW_SIZE = 1;

    int curPlayIndex=-1;

    private CirclePresenter presenter;
    private Context context;
    public void setCirclePresenter(CirclePresenter presenter){
        this.presenter = presenter;
    }

    public CircleAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return TYPE_HEAD;
        }

        int itemType = 0;
        CircleItem item = (CircleItem) datas.get(position-1);
        if (CircleItem.TYPE_URL.equals(item.getType())) {
            itemType = CircleViewHolder.TYPE_URL;
        } else if (CircleItem.TYPE_IMG.equals(item.getType())) {
            itemType = CircleViewHolder.TYPE_IMAGE;
        } else if(CircleItem.TYPE_VIDEO.equals(item.getType())){
            itemType = CircleViewHolder.TYPE_VIDEO;
        }
        return itemType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        if(viewType == TYPE_HEAD){
            View headView = LayoutInflater.from(parent.getContext()).inflate(R.layout.head_circle, parent, false);
            viewHolder = new HeaderViewHolder(headView);
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_circle_item, parent, false);

            if(viewType == CircleViewHolder.TYPE_URL){
                viewHolder = new URLViewHolder(view);
            }else if(viewType == CircleViewHolder.TYPE_IMAGE){
                viewHolder = new ImageViewHolder(view);
            }else if(viewType == CircleViewHolder.TYPE_VIDEO){
                viewHolder = new VideoViewHolder(view);
            }
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        if(getItemViewType(position)==TYPE_HEAD){
            //HeaderViewHolder holder = (HeaderViewHolder) viewHolder;
        }else{
            final int circlePosition = position - HEADVIEW_SIZE;
            final CircleViewHolder holder = (CircleViewHolder) viewHolder;
            CircleItem circleItem = (CircleItem) datas.get(circlePosition);
            final String circleId = circleItem.getId();
            String name = circleItem.getUser().getName();
            String headImg = circleItem.getUser().getHeadUrl();
            final String content = circleItem.getContent();
            String createTime = circleItem.getCreateTime();
            final List<FavortItem> favortDatas = circleItem.getFavorters();
            final List<CommentItem> commentsDatas = circleItem.getComments();
            boolean hasFavort = circleItem.hasFavort();
            boolean hasComment = circleItem.hasComment();

            //圆形头像
            //Glide.with(context).load(headImg).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.color.bg_no_photo).transform(new GlideCircleTransform(context)).into(holder.headIv);
            //默认矩形头像
            Glide.with(context).load(headImg).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.headIv);
            //ImageLoader.getInstance().displayImage(headImg, holder.headIv);
            holder.nameTv.setText(name);
            holder.timeTv.setText(createTime);

            if(!TextUtils.isEmpty(content)){
                holder.contentTv.setText(UrlUtils.formatUrlString(content));
            }
            holder.contentTv.setVisibility(TextUtils.isEmpty(content) ? View.GONE : View.VISIBLE);

            if(DatasUtil.curUser.getId().equals(circleItem.getUser().getId())){
                holder.deleteBtn.setVisibility(View.VISIBLE);
            }else{
                holder.deleteBtn.setVisibility(View.GONE);
            }
            holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //删除
                    if(presenter!=null){
                        presenter.deleteCircle(circleId);
                    }
                }
            });
            if(hasFavort || hasComment){
                if(hasFavort){//处理点赞列表
                    //文字
                    /*holder.praiseListView.setOnItemClickListener(new PraiseListView.OnItemClickListener() {
                        @Override
                        public void onClick(int position) {
                            String userName = favortDatas.get(position).getUser().getName();
                            String userId = favortDatas.get(position).getUser().getId();
                            Toast.makeText(SysApplication.getAppContext(), userName + " &id = " + userId, Toast.LENGTH_SHORT).show();
                        }
                    });

                    holder.praiseListView.setDatas(favortDatas);
                    holder.praiseListView.setVisibility(View.VISIBLE);*/

                    //头像
                    holder.praiseListView1.setDatas(favortDatas);
                    holder.praiseListView1.setVisibility(View.VISIBLE);
                    holder.praiseListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String userName = favortDatas.get(position).getUser().getName();
                            String userId = favortDatas.get(position).getUser().getId();
                            Toast.makeText(SysApplication.getAppContext(), userName + " &id = " + userId, Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    //holder.praiseListView.setVisibility(View.GONE);

                    holder.praiseListView1.setVisibility(View.GONE);
                }

                if(hasComment){//处理评论列表
                    holder.commentList.setOnItemClickListener(new CommentListView.OnItemClickListener() {
                        @Override
                        public void onItemClick(int commentPosition) {
                            CommentItem commentItem = commentsDatas.get(commentPosition);
                            if(DatasUtil.curUser.getId().equals(commentItem.getUser().getId())){//复制或者删除自己的评论

                                CommentDialog dialog = new CommentDialog(context, presenter, commentItem, circlePosition);
                                dialog.show();

                            }else{//回复别人的评论
                                if(presenter != null){
                                    CommentConfig config = new CommentConfig();
                                    config.circlePosition = circlePosition;
                                    config.commentPosition = commentPosition;
                                    config.commentType = CommentConfig.Type.REPLY;
                                    config.replyUser = commentItem.getUser();
                                    presenter.showEditTextBody(config);
                                }
                            }
                        }
                    });
                    holder.commentList.setOnItemLongClickListener(new CommentListView.OnItemLongClickListener() {
                        @Override
                        public void onItemLongClick(int commentPosition) {
                            //长按进行复制或者删除
                            CommentItem commentItem = commentsDatas.get(commentPosition);
                            CommentDialog dialog = new CommentDialog(context, presenter, commentItem, circlePosition);
                            dialog.show();
                        }
                    });
                    holder.commentList.setDatas(commentsDatas);
                    holder.commentList.setVisibility(View.VISIBLE);

                }else {
                    holder.commentList.setVisibility(View.GONE);
                }
                holder.digCommentBody.setVisibility(View.VISIBLE);
            }else{
                holder.digCommentBody.setVisibility(View.GONE);
            }

            holder.digLine.setVisibility(hasFavort && hasComment ? View.VISIBLE : View.GONE);

            final SnsPopupWindow snsPopupWindow = holder.snsPopupWindow;
            //判断是否已点赞
            String curUserFavortId = circleItem.getCurUserFavortId(DatasUtil.curUser.getId());
            if(!TextUtils.isEmpty(curUserFavortId)){
                snsPopupWindow.getmActionItems().get(0).mTitle = "取消";
                //snsPopupWindow.getmActionItems().get(0).mDrawable = context.getResources().getDrawable(R.mipmap.circle_zan_1);
            }else{
                snsPopupWindow.getmActionItems().get(0).mTitle = "赞";
                //snsPopupWindow.getmActionItems().get(0).mDrawable = context.getResources().getDrawable(R.mipmap.circle_zan);
            }
            snsPopupWindow.update();
            snsPopupWindow.setmItemClickListener(new PopupItemClickListener(circlePosition, circleItem, curUserFavortId));
            holder.snsBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    //弹出popupwindow
                    snsPopupWindow.showPopupWindow(view);
                }
            });

            holder.urlTipTv.setVisibility(View.GONE);
            switch (holder.viewType) {
                case CircleViewHolder.TYPE_URL:// 处理链接动态的链接内容和和图片
                    if(holder instanceof URLViewHolder){
                        String linkImg = circleItem.getLinkImg();
                        String linkTitle = circleItem.getLinkTitle();
                        Glide.with(context).load(linkImg).into(((URLViewHolder) holder).urlImageIv);
                        //ImageLoader.getInstance().displayImage(linkImg, ((URLViewHolder)holder).urlImageIv);
                        ((URLViewHolder)holder).urlContentTv.setText(linkTitle);
                        ((URLViewHolder)holder).urlBody.setVisibility(View.VISIBLE);
                        ((URLViewHolder)holder).urlTipTv.setVisibility(View.VISIBLE);
                    }

                    break;
                case CircleViewHolder.TYPE_IMAGE:// 处理图片
                    if(holder instanceof ImageViewHolder){
                        final List<String> photos = circleItem.getPhotos();
                        if (photos != null && photos.size() > 0) {
                            ((ImageViewHolder)holder).multiImageView.setVisibility(View.VISIBLE);
                            ((ImageViewHolder)holder).multiImageView.setList(photos);
                            ((ImageViewHolder)holder).multiImageView.setOnItemClickListener(new MultiImageView.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    //imagesize是作为loading时的图片size
                                    ImagePagerActivity.ImageSize imageSize = new ImagePagerActivity.ImageSize(view.getMeasuredWidth(), view.getMeasuredHeight());
                                    ImagePagerActivity.startImagePagerActivity(context, photos, position, imageSize);
                                }
                            });
                        } else {
                            ((ImageViewHolder)holder).multiImageView.setVisibility(View.GONE);
                        }
                    }

                    break;
                case CircleViewHolder.TYPE_VIDEO:
                    if(holder instanceof VideoViewHolder){
                        ((VideoViewHolder)holder).videoView.setVideoUrl(circleItem.getVideoUrl());
                        ((VideoViewHolder)holder).videoView.setVideoImgUrl(circleItem.getVideoImgUrl());//视频封面图片
                        ((VideoViewHolder)holder).videoView.setPostion(position);
                        ((VideoViewHolder)holder).videoView.setOnPlayClickListener(new CircleVideoView.OnPlayClickListener() {
                            @Override
                            public void onPlayClick(int pos) {
                                curPlayIndex = pos;
                            }
                        });
                    }

                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return datas.size()+1;//有head需要加1
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder{

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class PopupItemClickListener implements SnsPopupWindow.OnItemClickListener{
        private String mFavorId;
        //动态在列表中的位置
        private int mCirclePosition;
        private long mLasttime = 0;
        private CircleItem mCircleItem;

        public PopupItemClickListener(int circlePosition, CircleItem circleItem, String favorId){
            this.mFavorId = favorId;
            this.mCirclePosition = circlePosition;
            this.mCircleItem = circleItem;
        }

        @Override
        public void onItemClick(ActionItem actionitem, int position) {
            switch (position) {
                case 0://点赞、取消点赞
                    if(System.currentTimeMillis()-mLasttime<700)//防止快速点击操作
                        return;
                    mLasttime = System.currentTimeMillis();
                    if(presenter != null){
                        if ("赞".equals(actionitem.mTitle.toString())) {
                            presenter.addFavort(mCirclePosition);
                        } else {//取消点赞
                            presenter.deleteFavort(mCirclePosition, mFavorId);
                        }
                    }
                    break;
                case 1://发布评论
                    if(presenter != null){
                        CommentConfig config = new CommentConfig();
                        config.circlePosition = mCirclePosition;
                        config.commentType = CommentConfig.Type.PUBLIC;
                        presenter.showEditTextBody(config);
                    }
                    break;
                case 2://私聊
                    break;
                default:
                    break;
            }
        }
    }
}