package com.manager.http;

import com.manager.helper.UserHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * 获取用户相关 请求 参数 JSONObject
 * @author donghuiyang
 * @create time 2016/5/30 0030.
 */
public class UserHttpParams {

    /**
     * http post请求
     *
     * @param paramString
     * @return
     * @throws IOException
     * @throws JSONException
     * @author shenglian
     */
    private static String postRequest(String action, JSONObject paramString){
        return HttpHelper.excutePost(action, paramString.toString());
    }

    /********************************************************验证码请求*********************************************************************/
    /**
     * 验证码请求
     *
     * @param iphoneNumber
     *            ：接收验证码的手机号
     * @param operateType
     *            ：获取验证码时的操作类型【1：注册 2：手机解绑 3：手机绑定 4：密码找回】
     * @return
     * @author donghuiyang
     */
    public static String VerCodeRequest(final String iphoneNumber,
                                 final int operateType) throws IOException, JSONException {

        return postRequest(HttpField.UserAction_GetVerCode, getVerCodeParam(iphoneNumber, operateType));
    }
    private static JSONObject getVerCodeParam(final String iphoneNumber,
                                      final int operateType) {

        LinkedHashMap<String, Object> param = new LinkedHashMap<String, Object>();
        param.put(HttpField.OPERATETYPE_STRING, operateType);
        param.put(HttpField.MOBILE_STRING, iphoneNumber);
        param.put(HttpField.REGIST_IP_STRING, DeviceHelper.getDeviceIP());
        param.put(HttpField.IMEI_STRING, DeviceHelper.getDeviceIMEI());

        return JsonUtils.object2Json(param);
    }

    /********************************************************注册请求*********************************************************************/
    /**
     * 注册请求
     * @param name
     *            ：用户名
     * @param pwd
     *            ：密码
     * @param usertype
     *            ：用户类型
     * @param vercode
     *            ：短信验证码
     * @param realname
     *            ：真实姓名
     * @param identity
     *            ：身份证
     * @param userSource
     *            ：注册来源
     * @return
     * @author donghuiyang
     */
    public static String userRegistRequest(final String name,
                                    final String pwd,
                                    final int usertype,
                                    final String iphone,
                                    final String vercode,
                                    final String realname,
                                    final String identity,
                                    final int userSource) {

        return postRequest(HttpField.UserAction_Register,getRegisterParam(name, pwd, usertype, iphone, vercode, realname, identity, userSource));
    }
    private static JSONObject getRegisterParam(final String name,
                                               final String pwd,
                                               final int usertype,
                                               final String iphone,
                                               final String vercode,
                                               final String realname,
                                               final String identity,
                                               final int userSource) {
        LinkedHashMap<String, Object> param = new LinkedHashMap<String, Object>();
        param.put(HttpField.USERNAME_STRING, name);
        param.put(HttpField.PASSWORD_STRING, pwd);
        param.put(HttpField.USERTYPE_STRING, usertype);
        param.put(HttpField.MOBILE_STRING, iphone);
        param.put(HttpField.VERCODE_STRING, vercode);

        if (usertype == UserHelper.BettingShopUser){
            param.put("caistation", "123456");

            param.put(HttpField.REALNAME_STRING, realname);
            param.put(HttpField.IDENTITY_STRING, identity);
        }

        param.put(HttpField.REGIST_IP_STRING, DeviceHelper.getDeviceIP()); // ip地址
        param.put(HttpField.REGISTER_SYSTEM_STRING, DeviceHelper.getDeviceSystemVersion()); // 设备系统
        param.put(HttpField.REGISTER_EQUIP_STRING, DeviceHelper.getDeviceType()); // 设备类型
        param.put(HttpField.REGISTSOURCE_STRING, userSource);
        param.put(HttpField.IMEI_STRING, DeviceHelper.getDeviceIMEI());

        return JsonUtils.object2Json(param);
    }

    /********************************************************登录请求*********************************************************************/
    /**
     * 登录请求
     * @param name：用户名
     * @param pwd：密码
     * @param userType:用户类型
     * @return
     * @throws IOException
     * @throws JSONException
     * @author donghuiyang
     */
    public static String userLoginRequest(final String name,
                                   final String pwd,
                                   final int userType) throws IOException, JSONException {

        return postRequest(HttpField.UserAction_Login, getLoginParam(name, pwd, userType));
    }
    private static JSONObject getLoginParam(final String name,
                                    final String pwd,
                                    final int userType) {
        LinkedHashMap<String, Object> param = new LinkedHashMap<>();
        param.put(HttpField.USERNAME_STRING, name);
        param.put(HttpField.PASSWORD_STRING, pwd);
        param.put(HttpField.USERTYPE_STRING, userType);

        return JsonUtils.object2Json(param);
    }

    /********************************************************找回密码请求*********************************************************************/
    /**
     * 找回密码请求的参数
     * @param username：账户名
     * @param phone：手机号
     * @param vercode：手机验证码
     * @param newPw：新密码
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public static String forgetPwdRequest(final String username,
                                   final String phone,
                                   final String vercode,
                                   final String newPw) throws IOException, JSONException {

        return postRequest(HttpField.UserAction_ModifyPwd, getForgetPwdParam(username, phone, vercode, newPw));

    }
    private static JSONObject getForgetPwdParam(final String username,
                                         final String phone,
                                         final String vercode, String newPw) {
        LinkedHashMap<String, Object> param = new LinkedHashMap<>();
        param.put(HttpField.USERNAME_STRING, username);
        param.put(HttpField.NEWPWD_STRING, newPw);
        param.put(HttpField.MOBILE_STRING, phone);
        param.put(HttpField.VERCODE_STRING, vercode);
        param.put(HttpField.REGIST_IP_STRING, DeviceHelper.getDeviceIP()); // ip地址
        param.put(HttpField.IMEI_STRING, DeviceHelper.getDeviceIMEI());    //imei

        return JsonUtils.object2Json(param);
    }

    /********************************************************修改密码请求*********************************************************************/
    /**
     * 修改密码的请求
     * @param userid
     *            ：用户id
     * @param newpwd
     *            ：新密码
     * @param oldpwd
     *            ：原密码
     * @return
     * @throws IOException
     * @throws JSONException
     * @author shenglian
     */
    public static String modifyPwdRequest(final String userid,
                                   final String newpwd,
                                   final String oldpwd) throws IOException, JSONException {

        return postRequest(HttpField.UserAction_ModifyPwd, getModifyPwdParam(userid, newpwd, oldpwd));

    }
    private static JSONObject getModifyPwdParam(final String userid,
                                        final String newpwd,
                                        final String oldpwd){
        LinkedHashMap<String, Object> param = new LinkedHashMap<>();
        param.put(HttpField.USERID_STRING, userid);
        param.put(HttpField.NEWPWD_STRING, newpwd);
        param.put(HttpField.OLDPWD_STRING, oldpwd);
        param.put(HttpField.REGIST_IP_STRING, DeviceHelper.getDeviceIP()); // ip地址
        param.put(HttpField.IMEI_STRING, DeviceHelper.getDeviceIMEI());    //imei

        return JsonUtils.object2Json(param);
    }

    /********************************************************修改资料请求*********************************************************************/
    /**
     * 修改资料 请求
     * @param userid：用户id
     * @param realname：真实姓名
     * @param identity：身份证号
     * @param email：邮箱
     * @param address：地址
     * @param sex：性别
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public static String modifyInfoRequest(final String userid,
                                    final String realname,
                                    final String identity,
                                    final String email,
                                    final String address,
                                    final int sex) throws IOException, JSONException {

        return postRequest(HttpField.UserAction_ModifyPwd, getModifyInfoParam(userid, realname, identity, email, address, sex));

    }
    private static JSONObject getModifyInfoParam(final String userid,
                                         final String realname,
                                         final String identity,
                                         final String email,
                                         final String address,
                                         final int sex){
        LinkedHashMap<String, Object> param = new LinkedHashMap<>();
        param.put(HttpField.USERID_STRING, userid);
        param.put(HttpField.REALNAME_STRING, realname);
        param.put(HttpField.IDENTITY_STRING, identity);
        param.put(HttpField.EMAIL_STRING, email);
        param.put(HttpField.ADDRESS_STRING, address);

        param.put(HttpField.REGIST_IP_STRING, DeviceHelper.getDeviceIP()); // ip地址
        param.put(HttpField.IMEI_STRING, DeviceHelper.getDeviceIMEI());    //imei

        return JsonUtils.object2Json(param);
    }
}
