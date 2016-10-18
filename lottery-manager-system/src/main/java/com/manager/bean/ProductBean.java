package com.manager.bean;

/**
 * 彩票城 产品的属性（即开票 耗材）
 * @author donghuiyang
 * @create time 2016/6/1 0001.
 */
public class ProductBean extends BaseBean {

    public ProductBean(){}

    public ProductBean(String id, String iconUrl, String title, String price, String unit) {
        this.id = id;
        this.type = type;
        this.iconUrl = iconUrl;
        this.title = title;
        this.price = price;
        this.unit = unit;
    }

    public ProductBean(String id, int type,  String iconUrl, String title, String price, String unit, String value) {
        this.id = id;
        this.type = type;
        this.iconUrl = iconUrl;
        this.title = title;
        this.price = price;
        this.unit = unit;
        this.value = value;
    }

    public ProductBean(String id, int type, String iconUrl, String title, String price, String unit, String value, String buyNum, String leftNum) {
        this.id = id;
        this.type = type;
        this.iconUrl = iconUrl;
        this.title = title;
        this.price = price;
        this.unit = unit;
        this.value = value;
        this.buyNum = buyNum;
        this.leftNum = leftNum;
    }

    public String getId() {
        return id;
    }

    public ProductBean setId(String id) {
        this.id = id;
        return this;
    }

    public int getType() {
        return type;
    }

    public ProductBean setType(int type) {
        this.type = type;
        return this;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public ProductBean setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ProductBean setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public ProductBean setPrice(String price) {
        this.price = price;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public ProductBean setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public String getBuyNum() {
        return buyNum;
    }

    public ProductBean setBuyNum(String buyNum) {
        this.buyNum = buyNum;
        return this;
    }

    public String getLeftNum() {
        return leftNum;
    }

    public ProductBean setLeftNum(String leftNum) {
        this.leftNum = leftNum;
        return this;
    }

    public String getValue() {
        return value;
    }

    public ProductBean setValue(String value) {
        this.value = value;
        return this;
    }

    //id
    private String id;
    //类型
    private int type;
    //图片链接地址
    private String iconUrl = "";
    //名称
    private String title;
    //单价
    private String price;
    //单位
    private String unit;
    //购买的数量
    private String buyNum = "0";
    //剩余数量
    private String leftNum = "0";
    //附属 （面值 或 限购）
    private String value;

}
