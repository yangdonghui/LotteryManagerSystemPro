package com.manager.http;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class JsonUtils {
	public static JSONObject object2Json(Map<String,Object> map) {

		return new JSONObject(map);
	}

	public static JSONObject string2Json(String str) {
		try {
			return new JSONObject(str);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 获取json中的key “data” 后的jsonObject
	 * @param jsonObject
	 * @param data:key
	 * @return
	 */
	public static JSONObject getChildJson(JSONObject jsonObject, String data) {
		if (jsonObject != null){
			return jsonObject.optJSONObject(data);
		}

		return null;
	}

	/**
	 * 获取json中的key “data” JSONArray
	 * @param jsonObject 外层jsonObject
	 * @param data:key
	 * @return
	 */
	public static JSONArray getChildJsonArray(JSONObject jsonObject, String data) {
		if (jsonObject != null){
			return jsonObject.optJSONArray(data);
		}

		return null;
	}
}
