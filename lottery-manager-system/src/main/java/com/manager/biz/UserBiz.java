package com.manager.biz;

import com.manager.bean.UserBean;
import com.manager.helper.UserHelper;
import com.manager.listener.OnUserListener;
import com.manager.lotterypro.SysApplication;
import com.manager.storage.LocalStorage;

/**
 * Created by Administrator on 2016/3/14 0014.
 */
public class UserBiz {

    /**
     * 登录事件
     * @param username
     * @param password
     * @param listener:事件监听
     */
    public void login(final int type,
                      final String username,
                      final String password,
                      final OnUserListener listener) {

        //模拟子线程耗时操作
        new Thread()
        {
            @Override
            public void run()
            {
                /*String content = UserHttpParams.userRegistRequest(username, password, type, phone, code,"","", 0);
                DataParse parse = new DataParse(content);
                if (parse != null){
                    String msg = parse.getMsg();
                    if (parse.getCode().equals("0")){
                        JSONObject jsonObject = parse.getContentJsonObject();
                        if (jsonObject != null){
                            UserBean userBean = new UserBean();
                            String type = jsonObject.optString(HttpField.USERTYPE_STRING);
                            if (type != null){
                                userBean.setAllData(Integer.valueOf(type)-1,
                                        jsonObject.optString(HttpField.USERNAME_STRING),
                                        jsonObject.optString(HttpField.PASSWORD_STRING),
                                        jsonObject.optString(HttpField.MOBILE_STRING));
                                LocalStorage.saveUserInfo(userBean.getUserType(), userBean.getUserName(), userBean.getUserPw());
                                SysApplication.userBean = userBean;
                                SysApplication.setLoginState(UserHelper.LOGGED_IN);
                                listener.onSuccess(msg, userBean);
                            }
                        }

                    }else{
                        listener.onFailed(msg);
                    }
                }*/

                UserBean userBean = new UserBean(type, username, password, "");
                LocalStorage.saveUserInfo(type, username, password, String.valueOf(userBean.getIsLogin()));
                SysApplication.userBean = userBean;
                SysApplication.setLoginState(UserHelper.LOGGED_IN);
                listener.onSuccess("",userBean);

            }
        }.start();

    }

    /**
     * 注册事件
     * @param type
     * @param username
     * @param password
     * @param phone
     * @param code          :验证码
     * @param listener      :事件监听
     */
    public void register(final int type,
                         final String username,
                         final String password,
                         final String phone,
                         final String code,
                         final OnUserListener listener) {


        //模拟子线程耗时操作
        new Thread()
        {
            @Override
            public void run()
            {
                /*String content = UserHttpParams.userRegistRequest(username, password, type, phone, code,"","", 0);
                DataParse parse = new DataParse(content);
                if (parse != null){
                    String msg = parse.getMsg();
                    if (parse.getCode().equals("0")){
                        JSONObject jsonObject = parse.getContentJsonObject();
                        if (jsonObject != null){
                            UserBean userBean = new UserBean();
                            String type = jsonObject.optString(HttpField.USERTYPE_STRING);
                            if (type != null){
                                userBean.setAllData(Integer.valueOf(type)-1,
                                        jsonObject.optString(HttpField.USERNAME_STRING),
                                        jsonObject.optString(HttpField.PASSWORD_STRING),
                                        jsonObject.optString(HttpField.MOBILE_STRING));
                                LocalStorage.saveUserInfo(userBean.getUserType(), userBean.getUserName(), userBean.getUserPw());
                                SysApplication.userBean = userBean;
                                SysApplication.setLoginState(UserHelper.LOGGED_IN);
                                listener.onSuccess(msg, userBean);
                            }
                        }

                    }else{
                        listener.onFailed(msg);
                    }
                }*/

                UserBean userBean = new UserBean();
                userBean.setAllData(type, username, password, phone,String.valueOf(1));
                LocalStorage.saveUserInfo(type, username, password, String.valueOf(userBean.getIsLogin()));
                SysApplication.userBean = userBean;
                SysApplication.setLoginState(UserHelper.LOGGED_IN);
                listener.onSuccess("",userBean);

            }
        }.start();


    /*UserBean userBean = new UserBean();
        userBean.setAllData(type,username, password, phone);

        LocalStorage.saveUserInfo(type, username, password);
        SysApplication.userBean = userBean;
        SysApplication.setLoginState(UserHelper.LOGGED_IN);

        listener.onSuccess(userBean);*/
    }

    /**
     * 修改密码
     * @param password
     */
    public void modifyPw(String password,
                         OnUserListener listener) {

    }
}
