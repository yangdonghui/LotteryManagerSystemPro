package com.manager.bean;

/**
 * 消息属性
 * @author donghuiyang
 * @create time 2016/5/13 0013.
 */
public class NewsBean extends BaseBean{

    public String getNewsTime() {
        return newsTime;
    }

    public String getNewsTime1() {
        return newsTime1;
    }

    public String getNewsId() {
        return newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public String getNewsType() {
        return newsType;
    }

    public String getNewsReadState() {
        return newsReadState;
    }

    public String getComeFromStr() {
        return comeFromStr;
    }

    public int getIconID() {
        return iconID;
    }

    public NewsBean(String id, String title, String content, String time, String type, String state, String comeFromStr){
        super();

        this.newsId = id;
        this.newsTitle = title;
        this.newsContent = content;
        this.newsTime = time;
        this.newsType = type;
        this.newsReadState = state;
        this.comeFromStr = comeFromStr;
    }

    public NewsBean(String id, String title, String time1,  String time, String content, int iconID, String comeFromStr){
        super();

        this.newsId = id;
        this.newsTitle = title;
        this.newsContent = content;
        this.newsTime = time;
        this.newsType = "3";
        this.comeFromStr = comeFromStr;
        this.iconID = iconID;
    }

    //消息 编号
    private String newsId;
    //消息 标题
    private String newsTitle;
    //消息 内容
    private String newsContent;
    //消息 时间
    private String newsTime;
    private String newsTime1;
    //消息类型
    private String newsType;
    //已读状态
    private String newsReadState;
    //来自
    private String comeFromStr;

    private int iconID = -1;

}
