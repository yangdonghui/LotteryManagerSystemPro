package com.manager.bean;

/**
 * 银行卡属性
 * @author donghuiyang
 * @create time 2016/6/17 0017.
 */
public class BankBean extends BaseBean {
    public BankBean(String id,
                    int type,
                    String info,
                    String name,
                    String number,
                    int smallIcon,
                    int bigIcon,
                    int bgColor) {

        this.id = id;
        this.type = type;
        this.typeInfo = info;
        this.name = name;
        this.number = number;
        this.smallIcon = smallIcon;
        this.bigIcon = bigIcon;
        this.bgColor = bgColor;

    }

    public String getId() {
        return id;
    }

    public BankBean setId(String id) {
        this.id = id;
        return this;
    }

    public int getType() {
        return type;
    }

    public BankBean setType(int type) {
        this.type = type;
        return this;
    }

    public String getTypeInfo() {
        return typeInfo;
    }

    public BankBean setTypeInfo(String typeInfo) {
        this.typeInfo = typeInfo;
        return this;
    }

    public String getName() {
        return name;
    }

    public BankBean setName(String name) {
        this.name = name;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public BankBean setNumber(String number) {
        this.number = number;
        return this;
    }

    public int getSmallIcon() {
        return smallIcon;
    }

    public BankBean setSmallIcon(int smallIcon) {
        this.smallIcon = smallIcon;
        return this;
    }

    public int getBigIcon() {
        return bigIcon;
    }

    public BankBean setBigIcon(int bigIcon) {
        this.bigIcon = bigIcon;
        return this;
    }

    public int getBgColor() {
        return bgColor;
    }

    public BankBean setBgColor(int bgColor) {
        this.bgColor = bgColor;
        return this;
    }

    //编号
    private String id;
    //类型
    private int type;
    //类型描述
    private String typeInfo;
    //名称
    private String name;
    //卡号
    private String number;
    //小icon
    private int smallIcon;
    //大icon
    private int bigIcon;

    private int bgColor;

}
