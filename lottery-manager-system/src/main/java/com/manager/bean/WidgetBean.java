package com.manager.bean;

/**
 * @author donghuiyang
 * @create time 2016/6/3 0003.
 */
public class WidgetBean extends BaseBean {
    //编号
    private String id;
    private String iconUrl;
    private int icon;
    private int text;

    //附加信息
    private String info1;
    private String info2;


    public String getIconUrl() {
        return iconUrl;
    }

    public int getIcon() {
        return icon;
    }

    public int getText() {
        return text;
    }

    public WidgetBean(final int icon, final int text) {
        this.icon = icon;
        this.text = text;
    }

    public WidgetBean(String id, final String icon, final int text) {
        this.iconUrl = icon;
        this.text = text;
        this.id = id;
    }
}
