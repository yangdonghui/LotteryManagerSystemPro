package com.manager.bean;

import java.util.ArrayList;

/**
 * 彩票号码属性
 * @author donghuiyang
 * @create time 2016/6/13 0013.
 */
public class NumberBean extends BaseBean{

    public NumberBean(){}

    public NumberBean(String id,
                      ArrayList<String> numbers1,
                      ArrayList<String> numbers2) {
        this.id = id;

        this.numbersList1 = numbers1;
        this.numbersList2 = numbers2;

    }

    public String getId() {
        return id;
    }

    public NumberBean setId(String id) {
        this.id = id;
        return this;
    }


    public ArrayList<String> getNumbersList1() {
        return numbersList1;
    }

    public NumberBean setNumbersList1(ArrayList<String> numbersList) {
        if (this.numbersList1 == null) {
            this.numbersList1 = new ArrayList<>();
        }

        this.numbersList1.clear();
        this.numbersList1.addAll(numbersList);

        return this;
    }

    public ArrayList<String> getNumbersList2() {
        return numbersList2;
    }

    public NumberBean setNumbersList2(ArrayList<String> numbersList) {
        if (this.numbersList2 == null) {
            this.numbersList2 = new ArrayList<>();
        }

        this.numbersList2.clear();
        this.numbersList2.addAll(numbersList);

        return this;
    }

    private String id;
    //号码组
    private ArrayList<String> numbersList1;
    private ArrayList<String> numbersList2;
}
