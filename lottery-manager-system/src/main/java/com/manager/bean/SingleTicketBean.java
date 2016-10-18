package com.manager.bean;

import java.util.ArrayList;

/**
 * 单张彩票 数据
 * @author donghuiyang
 * @create time 2016/6/12 0012.
 */
public class SingleTicketBean{

    public SingleTicketBean(String id,
                            int doubleSingleType,
                            String info,
                            int lotteryType,
                            String lotteryName,
                            int multiple,
                            ArrayList<NoteLotterybean> numbers,
                            int state) {
        this.id = id;
        this.doubleSingleType = doubleSingleType;
        this.info = info;
        this.type = lotteryType;
        this.name = lotteryName;
        this.multiple = multiple;
        this.ticketState = state;

        setNumbersList(numbers);
    }

    public String getId() {
        return id;
    }

    public SingleTicketBean setId(String id) {
        this.id = id;
        return this;
    }

    public int getDoubleSingleType() {
        return doubleSingleType;
    }

    public SingleTicketBean setDoubleSingleType(int doubleSingleType) {
        this.doubleSingleType = doubleSingleType;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public SingleTicketBean setInfo(String info) {
        this.info = info;
        return this;
    }

    public int getType() {
        return type;
    }

    public SingleTicketBean setType(int type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public SingleTicketBean setName(String name) {
        this.name = name;
        return this;
    }


    public ArrayList<NoteLotterybean> getNumbersList() {
        return numbersList;
    }

    public SingleTicketBean setNumbersList(ArrayList<NoteLotterybean> numbersList) {
        /*if (this.numbersList == null){
            this.numbersList = new ArrayList<>();
        }

        this.numbersList.clear();
        this.numbersList.addAll(numbersList);*/

        this.numbersList = numbersList;

        price = 0;
        for (int i=0;i<this.numbersList.size();i++){
            NoteLotterybean att = this.numbersList.get(i);
            if (att != null){
                price += att.getPrice();
            }
        }
        return this;
    }

    public int getPrice() {
        return price;
    }

    public SingleTicketBean setPrice(int price) {
        this.price = price;
        return this;
    }

    public int getMultiple() {
        return multiple;
    }

    public SingleTicketBean setMultiple(int multiple) {
        this.multiple = multiple;
        return this;
    }

    public int getTicketState() {
        return ticketState;
    }

    public SingleTicketBean setTicketState(int ticketState) {
        this.ticketState = ticketState;
        return this;
    }

    //id
    private String id;
    //复式 单式区分
    private int doubleSingleType;
    private String info;
    //彩种
    private int type;
    //彩种名称
    private String name;

    private int price;

    private int multiple;

    //号码数据
    private ArrayList<NoteLotterybean> numbersList;

    //出票状态
    private int ticketState;
}
