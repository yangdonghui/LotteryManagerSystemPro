package com.manager.bean;

import java.util.ArrayList;

/**
 * 聊天 消息列表数据结构
 * @author donghuiyang
 * @create time 2016/7/28 0028.
 */
public class ChatBean extends BaseBean {

    public ChatBean(String id, String iconUrl, String userName, String time, ArrayList lists, int state){
        this.id = id;
        this.iconUrl = iconUrl;
        this.userName = userName;
        this.messageTime = time;
        this.messageLists = lists;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public ChatBean setId(String id) {
        this.id = id;
        return this;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public ChatBean setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public ChatBean setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public ChatBean setMessageTime(String messageTime) {
        this.messageTime = messageTime;
        return this;
    }

    public ArrayList<ChatMessageBean> getMessageLists() {
        return messageLists;
    }

    public ChatBean setMessageLists(ArrayList<ChatMessageBean> messageLists) {
        this.messageLists = messageLists;
        return this;
    }

    public int getState() {
        return state;
    }

    public ChatBean setState(int state) {
        this.state = state;
        return this;
    }

    //id
    private String id;

    //消息来源好友 头像
    private String iconUrl;
    //名称
    private String userName;
    //消息事件
    private String messageTime;

    //消息列表
    private ArrayList<ChatMessageBean> messageLists;

    //状态
    private int state;


}
