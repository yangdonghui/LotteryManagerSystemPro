package com.manager.common;

import java.io.UnsupportedEncodingException;

import android.util.Log;
import android.util.Base64;

/**
 * base64加密解密
 * Created by Administrator on 2016/2/19 0019.
 */
public class Base64Utils {
    public static String base64Encoder(String content) {
        // 在这里使用的是encode方式，返回的是byte类型加密数据，可使用new String转为String类型
        String strBase64 = new String(Base64.encode(content.getBytes(), Base64.DEFAULT));
        Log.i("Test", "encode >>>" + strBase64);

        // 这里 encodeToString 则直接将返回String类型的加密数据
        //String enToStr = Base64.encodeToString(content.getBytes(), Base64.DEFAULT);
        //Log.i("Test", "encodeToString >>> " + enToStr);

        return strBase64;

    }

    public static String base64Encoder(byte[] data) {
        return new String(Base64.decode(data, Base64.DEFAULT));
    }

    public static String base64Decoder(String content) {
        try {
            return new String(Base64.decode(content.getBytes("UTF-8"), Base64.DEFAULT));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}
