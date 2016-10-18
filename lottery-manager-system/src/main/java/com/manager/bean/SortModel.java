package com.manager.bean;

/**
 * 通讯录中索引数据属性
 * @author donghuiyang
 * @create time 2016/4/20 0020.
 */
public class SortModel extends BaseBean{
    private String name;        //名字
    private String pinYinName;  //名字的拼音的首字母

    public String type;

    public SortModel(String name) {
        super();

        this.name = name;
    }

    public SortModel(String name, String pinYinName) {
        super();

        this.name = name;
        this.pinYinName = pinYinName;
    }

    public SortModel(String name, String pinYinName, String type) {
        super();

        this.name = name;
        this.pinYinName = pinYinName;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinYinName() {
        return pinYinName;
    }

    public void setPinYinName(String pinYinName) {
        this.pinYinName = pinYinName;
    }

}
