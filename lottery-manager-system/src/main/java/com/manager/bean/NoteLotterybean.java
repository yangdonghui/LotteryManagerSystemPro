package com.manager.bean;

/**
 * 单注彩票数据
 * @author donghuiyang
 * @create time 2016/6/12 0012.
 */
public class NoteLotterybean{

    public NoteLotterybean(String id,
                           int doubleSingleType,
                           int type,
                           String name,
                           int multiple,
                           int price,
                           String from) {
        this.id = id;
        this.doubleSingleType = doubleSingleType;
        this.type = type;
        this.name = name;
        this.multiple = multiple;
        this.fromSource = from;
        this.price = price;
    }

    public NoteLotterybean(String id,
                            int doubleSingleType,
                            int type,
                            String name,
                            int multiple,
                            int price,
                            String from,
                            NumberBean numbers) {
        this.id = id;
        this.doubleSingleType = doubleSingleType;
        this.type = type;
        this.name = name;
        this.multiple = multiple;
        this.fromSource = from;
        this.price = price;

        this.numbers = numbers;
    }

    public String getId() {
        return id;
    }

    public NoteLotterybean setId(String id) {
        this.id = id;
        return this;
    }

    public int getDoubleSingleType() {
        return doubleSingleType;
    }

    public NoteLotterybean setDoubleSingleType(int doubleSingleType) {
        this.doubleSingleType = doubleSingleType;
        return this;
    }

    public int getType() {
        return type;
    }

    public NoteLotterybean setType(int type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public NoteLotterybean setName(String name) {
        this.name = name;
        return this;
    }

    public NumberBean getNumbers() {
        return numbers;
    }

    public NoteLotterybean setNumbers(NumberBean numbers) {
        if (this.numbers == null){
            this.numbers = new NumberBean();
        }

        this.numbers.setId(numbers.getId());
        this.numbers.setNumbersList1(numbers.getNumbersList1());
        this.numbers.setNumbersList2(numbers.getNumbersList2());


        return this;
    }


    public int getMultiple() {
        return multiple;
    }

    public NoteLotterybean setMultiple(int multiple) {
        this.multiple = multiple;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public NoteLotterybean setPrice(int price) {
        this.price = price;
        return this;
    }

    public String getFromSource() {
        return fromSource;
    }

    public NoteLotterybean setFromSource(String fromSource) {
        this.fromSource = fromSource;
        return this;
    }

    //id
    private String id;
    //复式 单式区分
    private int doubleSingleType;
    //彩种
    private int type;
    //彩种名称
    private String name;
    //号码组
    private NumberBean numbers;
    //倍数 或 注数
    private int multiple;
    //价格
    private int price;

    //来源 （机选）
    private String fromSource;
}
