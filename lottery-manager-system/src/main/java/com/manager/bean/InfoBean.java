package com.manager.bean;

/**
 * 资讯 属性
 * @author donghuiyang
 * @create time 2016/5/13 0013.
 */
public class InfoBean extends BaseBean{

    public String getInfoId() {
        return infoId;
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public String getInfoContent() {
        return infoContent;
    }

    public int getIconID() {
        return iconID;
    }

    public InfoBean(String id, String title, String content, int iconID) {
        super();

        this.infoId = id;
        this.infoTitle = title;
        this.infoContent = content;
        this.iconID = iconID;
    }

    //资讯id
    private String infoId;
    //资讯标题
    private String infoTitle;
    //资讯内容
    private String infoContent;
    private int iconID;
}
