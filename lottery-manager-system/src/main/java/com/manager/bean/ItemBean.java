package com.manager.bean;

/**
 * 购买物品的属性 （即开票 选号委托等）
 * @author donghuiyang
 * @create time 2016/6/8 0008.
 */
public class ItemBean extends BaseBean {
    public ItemBean(String id, String name, int num, int price) {
        this.itemID = id;
        this.itemName = name;
        this.num = num;
        this.price = price;
    }

    public String getItemID() {
        return itemID;
    }

    public ItemBean setItemID(String itemID) {
        this.itemID = itemID;
        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public ItemBean setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public int getNum() {
        return num;
    }

    public ItemBean setNum(int num) {
        this.num = num;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public ItemBean setPrice(int price) {
        this.price = price;
        return this;
    }

    private String itemID;
    private String itemName;
    private int num;
    private int price;

}
