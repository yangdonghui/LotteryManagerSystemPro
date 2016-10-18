package com.manager.http;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * 设备相关数据
 * @author donghuiyang
 * @create_time
 */
public class DeviceHelper {
	
	/**
	 * 设备类型
	 */
	public static enum DeviceType{
		IPHONE,
		IPAD
	}
	
	//设备系统类型
	public static final String deviceSystemTypeString = "android";
	public static final String ipadString = "ipad";
	public static final String iphoneString = "iphone";

	public static int DefaultValue = 1;
	
	//上下文对象
	private static Context mCtx = null;

	//移动设备管理对象
	private TelephonyManager mTelephonemanager = null;

	// 管理类对象实例
    private static DeviceHelper instance;
	/**
	 * 单例
	 *
	 * @return 类对象
	 */
	public static DeviceHelper getInstance() {
		if (instance == null)
			instance = new DeviceHelper();
		return instance;
	}

    /**
	 * 设置上下文对象
	 * @param context
	 * @author shenglian
	 */
	public static void setContext(Context context) {
		mCtx = context;
	}

	/**
	 * 获取手机型号
	 * @return
	 * @author shenglian
	 */
	public static String getDeviceModel() {
		return Build.MODEL;
	}
	
	/**
	 * 获取设备系统版本号
	 * @return
	 * @author shenglian
	 */
	public static String getDeviceSystemVersion() {
		return deviceSystemTypeString + Build.VERSION.RELEASE;
	}
	
	/**
	 * 获取设备IMEI
	 * @return
	 * @author shenglian
	 */
	public static String getDeviceIMEI() {
		return DeviceHelper.getInstance().getDeviceImeiString();
	}
	
	/**
	 * 获取设备IP
	 * @return
	 * @author shenglian
	 */
	public static String getDeviceIP() {
		return DeviceHelper.getInstance().getIpAddress();
	}
    
	/**
	 * 获取手机国际识别码IMEI
	 * */
	public DeviceHelper() {
		mTelephonemanager = (TelephonyManager) mCtx.getSystemService(Context.TELEPHONY_SERVICE);
		
	}
	
	/**
	 * 获取设备类型：iphone ipad pc
	 * @return
	 * @author shenglian
	 */
	public static String getDeviceType() {
		return DeviceHelper.getInstance().isPad();
	}
	
	/**
	 * 获得应用当前的版本号
	 *
	 * @return
	 */
	public String getVersion() {
		try {
			// 获取packagemanager的实例
			PackageManager packageManager = mCtx.getPackageManager();
			// getPackageName()是你当前类的包名，0代表是获取版本信息
			PackageInfo packInfo = packageManager.getPackageInfo(mCtx.getPackageName(), 0);
			return packInfo.versionName;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 获取设备的IMEI字符串
	 * 
	 * @return 设备IMEI
	 * @author shenglian
	 */
	public String getDeviceImeiString() {

		if (mTelephonemanager == null) {
			return null;
		}

		return mTelephonemanager.getDeviceId();
	}

	/**
	 * 获取手机服务商信息
	 * 
	 * */
	public String getProvidersName() {
		String providerName = null;
		try {
			String IMSI = mTelephonemanager.getSubscriberId();
			// IMSI前面三位460是国家号码，其次的两位是运营商代号，00、02是中国移动，01是联通，03是电信。
			System.out.print("IMSI是：" + IMSI);
			if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
				providerName = "中国移动";
			} else if (IMSI.startsWith("46001")) {
				providerName = "中国联通";
			} else if (IMSI.startsWith("46003")) {
				providerName = "中国电信";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return providerName;

	}

	/**
	 * 获取手机号码
	 * */
	public String getNativePhoneNumber() {

		String nativephonenumber = null;
		nativephonenumber = mTelephonemanager.getLine1Number();

		return nativephonenumber;
	}
	
	/**
	 * 获取设备ip
	 * @return 设备ip
	 * @author shenglian
	 */
	public String getIpAddress() {     
        try {     
            for (Enumeration<NetworkInterface> en = NetworkInterface     
                    .getNetworkInterfaces(); en.hasMoreElements();) {     
                NetworkInterface intf = en.nextElement();     
                for (Enumeration<InetAddress> enumIpAddr = intf     
                        .getInetAddresses(); enumIpAddr.hasMoreElements();) {     
                    InetAddress inetAddress = enumIpAddr.nextElement();     
                    if (!inetAddress.isLoopbackAddress()) {     
                        return inetAddress.getHostAddress();
                    }     
                }     
            }     
        } catch (IOException ex) {     
            Log.d("WifiPreferenceIpAddress", ex.toString());
        }     
        return null;     
    }
	public String getDeviceIp() {
		// 获取wifi服务
		WifiManager wifiManager = (WifiManager) mCtx.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		int ipAddress = wifiInfo.getIpAddress();

		return intToIp(ipAddress);
	}
	/**
	 * 获取设备的mac地址
	 * @return mac地址
	 * @author shenglian
	 */
	public String getDeviceMacAddress() {     
        WifiManager wifi = (WifiManager) mCtx.getSystemService(Context.WIFI_SERVICE);     
        WifiInfo info = wifi.getConnectionInfo();     
        if (null != info){
        	return info.getMacAddress();
        }
        return null;     
    } 
	
	/**
	 * 判断是否为平板
	 * 
	 * @return
	 */
	public String isPad() {
		WindowManager wm = (WindowManager) mCtx.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		DisplayMetrics dm = new DisplayMetrics();
		display.getMetrics(dm);
		double x = Math.pow(dm.widthPixels / dm.xdpi, 2);
		double y = Math.pow(dm.heightPixels / dm.ydpi, 2);
		// 屏幕尺寸
		double screenInches = Math.sqrt(x + y);
		// 大于6尺寸则为Pad
		if (screenInches >= 6.0) {
			return ipadString;
		}
		return iphoneString;
	}

	
	/**
	 * int转换为ip地址
	 * @param i：ip地址的十进制形式
	 * @return
	 * @author shenglian
	 */
	private String intToIp(int i) {

		return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF) + "." + (i >> 24 & 0xFF);
	}

	/**
	 * 获取设备信息
	 * @return
	 * @author shenglian
	 */
	public String getDeviceInfo() {
	    try{
	      org.json.JSONObject json = new org.json.JSONObject();
	      TelephonyManager tm = (TelephonyManager) mCtx
	          .getSystemService(Context.TELEPHONY_SERVICE);

	      String device_id = tm.getDeviceId();

	      WifiManager wifi = (WifiManager) mCtx.getSystemService(Context.WIFI_SERVICE);

	      String mac = wifi.getConnectionInfo().getMacAddress();
	      json.put("mac", mac);

	      if( TextUtils.isEmpty(device_id) ){
	        device_id = mac;
	      }

	      if( TextUtils.isEmpty(device_id) ){
	        device_id = android.provider.Settings.Secure.getString(mCtx.getContentResolver(),android.provider.Settings.Secure.ANDROID_ID);
	      }

	      json.put("device_id", device_id);

	      return json.toString();
	    }catch(Exception e){
	      e.printStackTrace();
	    }
	  return null;
	}

}
