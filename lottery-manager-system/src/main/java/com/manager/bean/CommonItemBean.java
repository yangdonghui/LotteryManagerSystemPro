package com.manager.bean;

/**
 * 图标 文字 编号 的item 属性
 * @author donghuiyang
 * @create time 2016/6/15 0015.
 */
public class CommonItemBean {

    public CommonItemBean(String id,
                          String iconUrl,
                          String name,
                          String info1,
                          String info2) {
        this.id = id;
        this.iconUrl = iconUrl;
        this.name = name;
        this.info1 = info1;
        this.info2 = info2;
    }

    public String getId() {
        return id;
    }

    public CommonItemBean setId(String id) {
        this.id = id;
        return this;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public CommonItemBean setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
        return this;
    }

    public String getName() {
        return name;
    }

    public CommonItemBean setName(String name) {
        this.name = name;
        return this;
    }

    public String getInfo1() {
        return info1;
    }

    public CommonItemBean setInfo1(String info1) {
        this.info1 = info1;
        return this;
    }

    public String getInfo2() {
        return info2;
    }

    public CommonItemBean setInfo2(String info2) {
        this.info2 = info2;
        return this;
    }

    //编号
    private String id;
    //图片
    private String iconUrl;
    //名称
    private String name;

    //附加信息
    private String info1;
    private String info2;

}
