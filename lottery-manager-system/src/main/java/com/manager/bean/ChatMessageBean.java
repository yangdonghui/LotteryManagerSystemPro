package com.manager.bean;

/**
 * @author donghuiyang
 * @create time 2016/7/28 0028.
 */
public class ChatMessageBean extends BaseBean {

    public ChatMessageBean(String id, String time, String message){
        this.id = id;
        this.time = time;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public ChatMessageBean setId(String id) {
        this.id = id;
        return this;
    }

    public String getTime() {
        return time;
    }

    public ChatMessageBean setTime(String time) {
        this.time = time;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ChatMessageBean setMessage(String message) {
        this.message = message;
        return this;
    }

    private String id;
    private String time;
    private String message;
    //消息类型 发送或接收
    private String msgType;
}
