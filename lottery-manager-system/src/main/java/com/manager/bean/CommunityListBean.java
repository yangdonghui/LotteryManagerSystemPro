package com.manager.bean;

/**
 * 社区列表数据结构
 * @author donghuiyang
 * @create time 2016/5/24 0024.
 */
public class CommunityListBean extends BaseBean{

    public String getId() {
        return id;
    }

    public int getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public String getNum() {
        return num;
    }

    public CommunityListBean(String id, int icon, String name, String content, String num) {
        super();

        this.id = id;
        this.icon = icon;
        this.name = name;
        this.content = content;
        this.num = num;
    }

    //id
    private String id;
    //社区图标
    private int icon;
    //社区名
    private String name;
    //社区介绍
    private String content;
    //帖子数量
    private String num;

}
