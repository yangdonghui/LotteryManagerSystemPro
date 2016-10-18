package com.manager.bean;

/**
 * 彩票城 属性
 * @author donghuiyang
 * @create time 2016/5/13 0013.
 */
public class LotteryCityBean extends BaseBean{


    public LotteryCityBean(String id, String title, String num1, String num2, String content, int iconID){
        super();

        this.lotteryId = id;
        this.lotteryTitle = title;
        this.lotteryNum1 = num1;
        this.lotteryNum2 = num2;
        this.iconID = iconID;
        this.lotteryContent = content;
    }

    public String getLotteryId() {
        return lotteryId;
    }

    public String getLotteryTitle() {
        return lotteryTitle;
    }

    public String getLotteryNum1() {
        return lotteryNum1;
    }

    public String getLotteryNum2() {
        return lotteryNum2;
    }

    public int getIconID() {
        return iconID;
    }

    public String getLotteryContent() {
        return lotteryContent;
    }

    //活动 编号
    private String lotteryId;
    //活动 标题
    private String lotteryTitle;
    //订阅数
    private String lotteryNum1;
    //关注数
    private String lotteryNum2;
    //icon
    private int iconID;
    //内容
    private String lotteryContent;

}
