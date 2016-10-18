package com.manager.common;

/**
 * 字符串工具类
 *
 * @author donghuiyang
 * @create time 2016/4/20 0020.
 */

import android.content.Context;
import android.text.TextUtils;

import com.manager.lotterypro.R;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper {

    //字符串范围
    public static final int MaxLen = 16;
    public static final int MinLen = 6;

    private static final String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";

    /**
     * 检查输入用户名格式(6-16 位 数字和字母组合)
     * @param username
     * @return
     * @author donghuiyang
     */
    public static boolean checkUserName(String username) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(username);
        return m.matches();
    }

    /**
     * 检查输入的密码格式
     * @param secondStr
     * @return
     * @author donghuiyang
     */
    public static boolean checkPassword(String secondStr) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(secondStr);
        return m.matches();
    }
    public static boolean checkAgainPassword(String firstStr, String secondStr) {
        if (firstStr.equals(secondStr)){
            return true;
        }
        return false;
    }

    /**
     * 检查手机号码格式
     * @param strPhone
     * @return
     * @author donghuiyang
     */
    public static boolean isPhone(String strPhone) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(strPhone);
        return m.matches();
    }

    /**
     * 检查邮箱格式
     *
     * @param strEmail
     *            ：邮箱账户名
     * @return true：格式正确，否则不正确
     * @author donghuiyang
     */
    public static boolean isEmail(String strEmail) {
        String strPattern = "^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }

    /**
     * 检查身份证号格式
     * @param scardID
     * @return
     * @author donghuiyang
     */
    public static boolean isSardID(String scardID) {
        // 定义判别用户身份证号的正则表达式（要么是15位，要么是18位，最后一位可以为字母）
        Pattern p = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
        // 通过Pattern获得Matcher
        Matcher m = p.matcher (scardID);
        return m.matches();
    }

    /**
     * 匹配短信中间的6个数字（验证码等）
     *
     * @param patternContent
     * @return
     */
    private static String patternCode(String patternContent) {
        if (TextUtils.isEmpty(patternContent)) {
            return null;
        }
        String patternCoder = "(?<!\\d)\\d{6}(?!\\d)";
        Pattern p = Pattern.compile(patternCoder);
        Matcher matcher = p.matcher(patternContent);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    /**********************************************************************************************************************/

    /**
     * 得到 全拼
     *
     * @param src
     * @return
     */
    public static String getPingYin(String src) {
        char[] t1 = null;
        t1 = src.toCharArray();
        String[] t2 = new String[t1.length];
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断是否为汉字字符
                if (java.lang.Character.toString(t1[i]).matches(
                        "[\\u4E00-\\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                    t4 += t2[0];
                } else {
                    t4 += java.lang.Character.toString(t1[i]);
                }
            }
            return t4;
        } catch (BadHanyuPinyinOutputFormatCombination e1) {
            e1.printStackTrace();
        }
        return t4;
    }

    /**
     * 得到首字母
     *
     * @param str
     * @return
     */
    public static String getHeadChar(String str) {

        String convert = "";
        char word = str.charAt(0);
        String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
        if (pinyinArray != null) {
            convert += pinyinArray[0].charAt(0);
        } else {
            convert += word;
        }
        return convert.toUpperCase();
    }

    /**
     * 得到中文首字母缩写
     *
     * @param str
     * @return
     */
    public static String getPinYinHeadChar(String str) {

        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert.toUpperCase();
    }

    /**
     * 手机号中间四位 * 号代替
     * @param mobile
     * @return
     */
    public static String getHidePhone(String mobile){
        return (mobile.substring(0,3)+"****"+mobile.substring(7,mobile.length()));
    }

    public static String intToString(int value) {
        String a;
        if (value < 10){
            a = "0" + String.valueOf(value);
        }else{
            a = String.valueOf(value);
        }

        return a;
    }

    public static String getPriceStr(int price) {
        return "¥" + String.valueOf(price);
    }

    public static String getNumStr(int num) {
        return "x" + String.valueOf(num);
    }

    /**
     * 金额中是否包含小数点
     * @param price
     * @return
     */
    public static boolean isStringHaveFloat(String price){
        return price.contains(".");
    }

    public static int StringFloatNum(String price) {
        int index = price.indexOf(".");
        int len = price.length();
        return len - index;
    }

    /**
     * 价格拼接字符串
     * @param context
     * @param price
     * @return
     */
    public static String getMoneyStr(Context context, float price){
        return "共" + context.getResources().getString(R.string.money_str) + " " + String.valueOf(price);
    }
    public static String getMoneyStr1(Context context, float price){
        return context.getResources().getString(R.string.money_str) + " " + String.valueOf(price);
    }
}
