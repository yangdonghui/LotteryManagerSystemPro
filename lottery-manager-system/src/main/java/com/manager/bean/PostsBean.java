package com.manager.bean;

import java.util.List;

/**
 * @author donghuiyang
 * @create time 2016/5/24 0024.
 */
public class PostsBean extends BaseBean{

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public String getFavortNum() {
        return favortNum;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public String getType() {
        return type;
    }

    public String getLinkImg() {
        return linkImg;
    }

    public String getLinkTitle() {
        return linkTitle;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public UserInCommunity getUser() {
        return user;
    }

    public PostsBean setId(String id) {
        this.id = id;
        return this;
    }

    public PostsBean setTitle(String title) {
        this.title = title;
        return this;
    }

    public PostsBean setContent(String content) {
        this.content = content;
        return this;
    }

    public PostsBean setTime(String time) {
        this.time = time;
        return this;
    }

    public PostsBean setFavortNum(String favortNum) {
        this.favortNum = favortNum;
        return this;
    }

    public PostsBean setCommentNum(String commentNum) {
        this.commentNum = commentNum;
        return this;
    }

    public PostsBean setType(String type) {
        this.type = type;
        return this;
    }

    public PostsBean setLinkImg(String linkImg) {
        this.linkImg = linkImg;
        return this;
    }

    public PostsBean setLinkTitle(String linkTitle) {
        this.linkTitle = linkTitle;
        return this;
    }

    public PostsBean setPhotos(List<String> photos) {
        this.photos = photos;
        return this;
    }

    public PostsBean setUser(UserInCommunity user) {
        this.user = user;
        return this;
    }

    //帖子id
    private String id;
    //帖子标题
    private String title;
    //帖子内容
    private String content;
    //帖子创建时间
    private String time;
    //帖子赞的数量
    private String favortNum;
    //帖子评论数量
    private String commentNum;

    //1:链接  2:图片
    private String type;
    //链接图片
    private String linkImg;
    //链接标题
    private String linkTitle;
    //图片列表
    private List<String> photos;

    //发帖人的信息
    private UserInCommunity user;
}
