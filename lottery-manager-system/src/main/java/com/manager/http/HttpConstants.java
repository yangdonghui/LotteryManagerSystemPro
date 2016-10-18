package com.manager.http;

/**
 * HTTP相关常量
 * @author donghuiyang
 * @create time 2016/5/19 0019.
 */
public class HttpConstants {

    //协议地址
    public static final String SeverUrl = "http://192.168.1.96:8080/lsp/ServiceServlet";

    //请求验证码的类型
    public static enum SecurityCodeType{
        NONE_SECURITYCODE,
        REGISTER_SECURITYCODE,//注册验证码
        UNLOCKIPHONE_SECURITYCODE,//解除手机验证码
        BINDINGIPHONE_SECURITYCODE,//绑定手机验证码
        FORGOTPASSWORD_SECURITYCODE//密码找回验证码
    }
}
