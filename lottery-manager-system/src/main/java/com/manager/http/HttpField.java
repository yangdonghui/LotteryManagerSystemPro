package com.manager.http;

/**
 * HHTP 数据字段标识
 * @author donghuiyang
 * @create_time
 */
public class HttpField {
	public static final String ACTIONNAME_STRING = "actionName";
	public static final String VIEWID_STRING = "viewId";
	public static final String PARAM_STRING = "parameters";

	public static final String OPERATETYPE_STRING = "operateType";//获取验证码时的操作类型 1：注册 2：解除手机 3：绑定手机 4：密码找回
	public static final String MOBILE_STRING = "mobile";//手机号码
	public static final String EQUIPNAME_STRING = "equip";//用户设备名称
	public static final String IMEI_STRING = "imei";//移动设备码
	public static final String REGISTER_SYSTEM_STRING = "registSystem";//设备操作系统
	public static final String REGISTER_EQUIP_STRING = "registEquip";//设备类型
	public static final String REGIST_IP_STRING = "registIP";//注册时的IP
	public static final String REGIST_TIME_STRING = "registTime";//注册时间
	
	public static final String USERID_STRING = "userId";//用户ID
	public static final String USERNAME_STRING = "username";//用户名
	public static final String PASSWORD_STRING = "password";//密码
	public static final String USERTYPE_STRING = "usertype";//用户类型 1：彩民 2：投注站 3：其他
	public static final String VERCODE_STRING  = "verCode";//手机验证码
	public static final String REALNAME_STRING = "realname";//真实姓名
	public static final String IDENTITY_STRING = "identity";//身份证号
	public static final String EMAIL_STRING = "email";//email
	public static final String ADDRESS_STRING = "address";//联系地址
	public static final String BANKNUMBER_STRING = "bankNumber";//银行卡号
	public static final String BANK_STRING = "bank";//银行名称
	public static final String SEX_STRING = "sex";//性别
	public static final String REGISTSOURCE_STRING = "registSource";//用户来源  暂定为0

	public static final String NEWPWD_STRING = "newpassword";//新密码
	public static final String OLDPWD_STRING = "password";//旧密码

	public static final String UPDATE_IP_STRING = "updateIP";//修改资料时 设备IP地址
	public static final String UPDATE_TIME_STRING = "updatetime"; //完善资料时间
	public static final String INFO_STATE_STRING = "infoStatus"; //完善资料状态
	public static final String MOBILE_STATE_STRING = "MobileStatus"; //手机验证状态
	public static final String EQUIP_STATE_STRING = "emailstatus"; //Emai验证状态
	
	public static final String BETTINGSHOP_SERVICETYPE = "serviceType";//投注站申请服务类型
	public static final String BETTINGSHOP_SERVICEREQUEST_DEADLINES = "deadtime";//期望完成时间
	public static final String BETTINGSHOP_SERVICEREQUEST_STARTTIME = "startTime";//起始时间
	public static final String BETTINGSHOP_SERVICEREQUEST_ENDTTIME = "endTime";//截止时间
	public static final String BETTINGSHOP_SERVICEREQUEST_DETAIL = "detail";//申请备注
	
	public static final String MESSAGEID_STRING = "messageId";//消息id
	public static final String PAGENUM_USER_STRING = "pageNum";//条数
	public static final String PAGESIZE_USER_STRING = "pageSize";//显示条数
	public static final String MESSAGETILE_STRING = "title";//消息标题
	public static final String MESSAGECONTENTS_STRING = "contents";//消息内容
	public static final String MESSAGECREATETIME_STRING = "createtime";//消息创建时间
	public static final String MESSAGEREADSTATUS_STRING = "readStatus";//消息状态
	
	
	
	
	
	
	/************************************************协议名*************************************************/
	public static final String UserAction_GetVerCode = "xguanjia.client.action.UserAction$getVerCode";												//获取验证码
	public static final String UserAction_Register= "Lottery/api/register";							  												//用户注册
	public static final String UserAction_Login= "xguanjia.client.action.LoginAction$login";		  												//用户登录
	public static final String UserAction_ForgetPwd = "xguanjia.client.action.UserAction$forgetPwd";  												//忘记密码
	public static final String UserAction_ModifyPwd = "xguanjia.client.action.UserAction$modifyPwd";  												//修改密码
	public static final String UserAction_ModifyUserInfo = "xguanjia.client.action.UserAction$modifyUserInfo";										//修改资料
	public static final String UserAcion_BindOrUnbindMobile= "xguanjia.client.action.UserAction$bindMobile";										//绑定或解绑手机
	public static final String UserAction_LoginOut = "xguanjia.client.action.LoginAction$loginOut";													//用户注销退出
	public static final String UserAction_BankInfo = "xguanjia.client.action.BankAction$getBankInfo";												//用户银行信息
	
	public static final String UserAction_GetCaiServiceList = "com.xguanjia.maintain.client.action.ServiceAction$getCaiServiceList";				//获取投注站列表服务申请
	public static final String UserAction_CommitService = "com.xguanjia.maintain.client.action.ServiceAction$commitService";						//提交投注站服务申请
	
	public static final String UserAction_MessageList = "xguanjia.client.action.MessageAction$getMessageList";										//消息列表
	public static final String UserAction_MessageOperate = "xguanjia.client.action.MessageAction$operate";											//消息操作：阅读、删除
}
