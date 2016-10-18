package com.manager.bean;

/**
 * 收货地址数据属性
 * @author donghuiyang
 * @create time 2016/6/1 0001.
 */
public class AddressBean extends BaseBean {

    public AddressBean(String id, String name, String phone, String content) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.addressContent = content;
    }

    public String getId() {
        return id;
    }

    public AddressBean setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AddressBean setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public AddressBean setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getAddressContent() {
        return addressContent;
    }

    public AddressBean setAddressContent(String addressContent) {
        this.addressContent = addressContent;
        return this;
    }

    public String getAddress1() {
        return address1;
    }

    public AddressBean setAddress1(String address1) {
        this.address1 = address1;
        return this;
    }

    public String getAddress2() {
        return address2;
    }

    public AddressBean setAddress2(String address2) {
        this.address2 = address2;
        return this;
    }

    public String getAddress3() {
        return address3;
    }

    public AddressBean setAddress3(String address3) {
        this.address3 = address3;
        return this;
    }

    private String id;

    //收货人
    private String name;
    //电话
    private String phone;
    //具体地址
    private String addressContent;

    //省
    private String address1 = "北京";
    //市
    private String address2 = "北京市";
    //区
    private String address3 = "西城区";

}
