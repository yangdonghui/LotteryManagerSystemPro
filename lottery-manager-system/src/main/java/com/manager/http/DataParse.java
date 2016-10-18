package com.manager.http;

import org.json.JSONObject;

/**
 * 数据解析 类
 * @author donghuiyang
 * @create time 2016/6/23 0023.
 */
public class DataParse {
    public String data;

    private String code;
    private String msg;
    private String contentData;



    public DataParse(String data) {
        this.data = data;

        JSONObject jsonObject = JsonUtils.string2Json(this.data);
        assert jsonObject != null;
        code = jsonObject.optString("code");
        msg = jsonObject.optString("message");
        contentData = jsonObject.optString("data");
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getContentData() {
        return contentData;
    }

    public JSONObject getContentJsonObject() {
        return JsonUtils.string2Json(contentData);
    }
}
