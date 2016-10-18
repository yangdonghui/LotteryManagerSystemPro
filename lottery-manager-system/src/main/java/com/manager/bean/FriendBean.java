package com.manager.bean;

import com.manager.lotterypro.R;

import java.util.List;

/**
 * 添加好友列表 数据 属性
 * @author donghuiyang
 * @create time 2016/5/26 0026.
 */
public class FriendBean extends BaseBean{
    private String id;

    private String name;        //名字
    private String pinYinName;  //名字的拼音的首字母

    //默认头像
    private int defaultIcon = R.mipmap.default_icon;
    private String iconUrl;     //头像
    private String info;        //备注

    private String sex;         //性别
    private String userName;    //账号

    private String sddress;     //地区
    private List<CommunityMainBean> communityList = null;   //好友的社区列表

    private int type=-1;        //类型

    private int state;          //状态 0：搜索 1: 被请求加好友 2：通过验证 3； 对方拒绝   4:通讯录
    private String stateStr;

    public String getId() {
        return id;
    }

    public FriendBean setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public FriendBean setName(String name) {
        this.name = name;
        return this;
    }

    public String getPinYinName() {
        return pinYinName;
    }

    public FriendBean setPinYinName(String pinYinName) {
        this.pinYinName = pinYinName;
        return this;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public FriendBean setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
        return this;
    }

    public FriendBean setDefaultIcon(int defaultIcon) {
        this.defaultIcon = defaultIcon;
        return this;
    }

    public int getDefaultIcon() {
        return defaultIcon;
    }

    public String getInfo() {
        return info;
    }

    public FriendBean setInfo(String info) {
        this.info = info;
        return this;
    }

    public int getType() {
        return type;
    }

    public FriendBean setType(int type) {
        this.type = type;
        return this;
    }

    public int getState() {
        return state;
    }

    public FriendBean setState(int state) {
        this.state = state;
        return this;
    }

    public String getStateStr() {
        return stateStr;
    }

    public FriendBean setStateStr(String stateStr) {
        this.stateStr = stateStr;
        return this;
    }

    public FriendBean(String name) {
        super();

        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public FriendBean setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public FriendBean setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getSddress() {
        return sddress;
    }

    public FriendBean setSddress(String sddress) {
        this.sddress = sddress;
        return this;
    }

    public List<CommunityMainBean> getCommunityList() {
        return communityList;
    }

    public FriendBean setCommunityList(List<CommunityMainBean> communityList) {
        this.communityList = communityList;
        return this;
    }

    public FriendBean(String id, String name, String iconUrl, int state) {
        super();

        this.id = id;
        this.name = name;
        this.iconUrl = iconUrl;
        this.state = state;
    }

    public FriendBean(String id, String name, String iconUrl, String pinYinName, int state) {
        super();

        this.id = id;
        this.name = name;
        this.pinYinName = pinYinName;
        this.iconUrl = iconUrl;

        this.state = state;
    }

    public FriendBean(String id, String name,  String info, String iconUrl, int state, String stateStr, String pinYinName) {
        super();

        this.id = id;
        this.name = name;
        this.pinYinName = pinYinName;
        this.iconUrl = iconUrl;
        this.info = info;
        this.state = state;
        this.stateStr = stateStr;
    }
}
