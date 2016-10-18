package com.manager.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.manager.bean.UserBean;
import com.manager.common.AESEncryptor;


/**
 * 本地存储
 * @author donghuiyang
 * @create_time 下午2:17:52-2015-12-25-2015
 */
public class LocalStorage {
	
	private static Context mCtx = null;	//全局app上下文对象
	
	private static final String FILE_USER = "2016001_01";
	
	private static final String NAME_KEY = "STRING_KEY_0";
	private static final String PWD_KEY = "STRING_KEY_1";
	private static final String TYPE_KEY = "STRING_KEY_2";
	private static final String LOGIN_STATE_KEY = "STRING_KEY_3";
	private static final String DEFAULT_VALUE = "NONE";
	
	/**
	 * 设置上下文对象
	 * @param context
	 * @author shenglian
	 */
	public static void setContext(Context context) {
		mCtx = context;
	}

	/**
	 * 读取用户登录名以及密码，如果存在则进行自动登录
	 * @return
	 * @author shenglian
	 */
	public static UserBean readUserInfo() {
		try {
			SharedPreferences sp = mCtx.getSharedPreferences(FILE_USER, mCtx.MODE_PRIVATE);

			String type = sp.getString(TYPE_KEY, DEFAULT_VALUE);
			String name = sp.getString(NAME_KEY, DEFAULT_VALUE);
			String pwd = sp.getString(PWD_KEY, DEFAULT_VALUE);
			String isLogin = sp.getString(LOGIN_STATE_KEY, DEFAULT_VALUE);

			if (!name.equals(DEFAULT_VALUE) && !pwd.equals(DEFAULT_VALUE) && !type.equals(DEFAULT_VALUE)) {

				type = AESEncryptor.decrypt(type);

				int userType = Integer.valueOf(type);
				name = AESEncryptor.decrypt(name);
				pwd = AESEncryptor.decrypt(pwd);
				isLogin = AESEncryptor.decrypt(isLogin);

				UserBean userBean = new UserBean();
				userBean.setAllData(userType, name, pwd,"",isLogin);
				
				return userBean;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 存储用户登录名、密码(AES加密)
	 * @author shenglian
	 */
	public static void saveUserInfo(int type, String name, String pwd, String isLogin) {
		try {
			String typeEncoder = AESEncryptor.encrypt(String.valueOf(type));
			String nameEncoder = AESEncryptor.encrypt(name);
			String pwdEncoder = AESEncryptor.encrypt(pwd);
			String isLoginEncode = AESEncryptor.encrypt(isLogin);
			
			//存储
			SharedPreferences sp = mCtx.getSharedPreferences(FILE_USER, mCtx.MODE_PRIVATE);
			Editor editor = sp.edit();
			editor.putString(TYPE_KEY, typeEncoder);
			editor.putString(NAME_KEY, nameEncoder);
			editor.putString(PWD_KEY, pwdEncoder);
			editor.putString(LOGIN_STATE_KEY, isLoginEncode);

			editor.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 删除本地的用户
	 */
	public static void resetUserInfo(int type, String name, String pwd, int isLogin){
		try {
			String typeEncoder = AESEncryptor.encrypt(String.valueOf(type));
			String nameEncoder = AESEncryptor.encrypt(name);
			String pwdEncoder = AESEncryptor.encrypt(pwd);
			String isLoginEncode = AESEncryptor.encrypt(String.valueOf(isLogin));

			//存储
			SharedPreferences sp = mCtx.getSharedPreferences(FILE_USER, mCtx.MODE_PRIVATE);
			Editor editor = sp.edit();
			/*editor.putString(TYPE_KEY, typeEncoder);
			editor.putString(NAME_KEY, nameEncoder);
			editor.putString(PWD_KEY, pwdEncoder);*/
			editor.putString(LOGIN_STATE_KEY, isLoginEncode);

			editor.commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
