package com.manager.bean;

/**
 * @author donghuiyang
 * @create time 2016/6/3 0003.
 */
public class WidgetBean1 extends BaseBean {
    private String id;
    private int icon;
    private String text;

    //附加信息
    int num1;
    private String info1;
    int num2;
    private String info2;

    int num3;
    int num4;

    public String getId() {
        return id;
    }

    public int getIcon() {
        return icon;
    }

    public String getText() {
        return text;
    }

    public int getNum1() {
        return num1;
    }

    public String getInfo1() {
        return info1;
    }

    public int getNum2() {
        return num2;
    }

    public String getInfo2() {
        return info2;
    }

    public int getNum3() {
        return num3;
    }

    public int getNum4() {
        return num4;
    }

    public WidgetBean1(final  String id, final int icon, final String text) {
        this.id = id;
        this.icon = icon;
        this.text = text;
    }

    public WidgetBean1(String id,
                          int icon,
                          String name,
                          int num1,
                          String info1,
                          int num2,
                          String info2,
                          int num3,
                          int num4) {
        this.id = id;
        this.icon = icon;
        this.text = name;

        this.num1 = num1;
        this.info1 = info1;

        this.num2 = num2;
        this.info2 = info2;

        this.num3 = num3;
        this.num4 = num4;
    }
}
