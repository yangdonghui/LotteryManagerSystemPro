package com.manager.bean;

/**
 * 社区主页 推荐社区 我的社区 数据结构  （头像 名称 ）
 * @author donghuiyang
 * @create time 2016/5/24 0024.
 */
public class CommunityMainBean extends BaseBean{

    public String getId() {
        return id;
    }

    public int getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public CommunityMainBean(String id, int icon, String name) {
        super();

        this.id = id;
        this.icon = icon;
        this.name = name;
    }

    //id
    private String id;
    //社区图标
    private int icon;
    //社区名
    private String name;

}
