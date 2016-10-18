package com.manager.bean;

import java.util.LinkedList;

/**
 * 委托投注 属性结构
 * @author donghuiyang
 * @create time 2016/6/7 0007.
 */
public class EntrustBetBean extends BaseBean{

    public EntrustBetBean(String id, String name, int number, int value) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public EntrustBetBean setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public EntrustBetBean setName(String name) {
        this.name = name;
        return this;
    }

    public int getNumber() {
        return number;
    }

    public EntrustBetBean setNumber(int number) {
        this.number = number;
        return this;
    }

    public int getValue() {
        return value;
    }

    public EntrustBetBean setValue(int value) {
        this.value = value;
        return this;
    }

    public LinkedList<Integer> getNumbers1() {
        return numbers1;
    }

    public EntrustBetBean setNumbers1(LinkedList<Integer> numbers1) {
        if (this.numbers1 == null){
            this.numbers1 = new LinkedList<>();
        }else{
            this.numbers1.clear();
        }
        this.numbers1.addAll(numbers1);
        return this;
    }

    public LinkedList<Integer> getNumbers2() {
        return numbers2;
    }

    public EntrustBetBean setNumbers2(LinkedList<Integer> numbers2) {
        if (this.numbers2 == null){
            this.numbers2 = new LinkedList<>();
        }else{
            this.numbers2.clear();
        }

        this.numbers2.addAll(numbers2);
        return this;
    }

    //id
    private String id;
    //彩种名称
    private String name;
    //票数 5个单式一张 复试单独一张
    private int number;
    //价格
    private int value;

    //号码
    private LinkedList<Integer> numbers1;
    private LinkedList<Integer> numbers2;
}
