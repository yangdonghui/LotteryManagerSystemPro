package com.manager.lotterypro;

/**
 * 自定义Application
 * Created by Administrator on 2016/3/14 0014.
 */

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;


import com.manager.lotterycity.EntrustBetOrderConfirmAct;
import com.manager.user.ModifySelectMorePhotoAct;
import com.manager.user.ModifyHeadSelectPhotoAct;
import com.manager.wallet.WalletTransactionHistoryAct;
import com.manager.wheelbean.ProvinceData;
import com.manager.bean.UserBean;
import com.manager.helper.UserHelper;
import com.manager.http.DeviceHelper;
import com.manager.storage.DataStorage;
import com.manager.storage.LocalStorage;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 编写自己的Application，管理全局状态信息，比如Context、用户登录数据等
 * Created by Administrator on 2016/3/14 0014.
 */
@SuppressWarnings("ALL")
public class SysApplication extends Application {

    // 默认存放图片的路径
    public final static String DEFAULT_SAVE_IMAGE_PATH = Environment.getExternalStorageDirectory() + File.separator + "testDemo" + File.separator + "Images"
            + File.separator;

    //登录状态
    private static int loginState = UserHelper.NOT_LOGIN;

    //用户数据
    public static UserBean userBean = null;

    private static Context context;

    // 用于存放倒计时时间
    public static Map<String, Long> map;

    //运用list来保存们每一个activity是关键
    private static List<Activity> mList = new LinkedList<Activity>();

    public static RegisterLotteryAct registerLotteryAct;
    public static RegisterBettingshopAct registerBettingshopAct;
    public static MainAct mMainAct;
    public static WalletTransactionHistoryAct walletTransactionHistoryAct;
    public static ModifySelectMorePhotoAct modifySelectMorePhotoAct;
    public static ModifyHeadSelectPhotoAct modifyHeadSelectPhotoAct;
    public static EntrustBetOrderConfirmAct entrustBetOrderConfirmAct;

    //主界面的当前界面索引
    private int initIndex = -1;

    //所有地区数据
    public static ProvinceData provinceData = null;

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化数据
        init();
    }

    private void init() {
        //获取Context
        context = getApplicationContext();

        //设备 及 存储数据
        LocalStorage.setContext(context);
        DeviceHelper.setContext(context);

        userBean = LocalStorage.readUserInfo();
        //userBean.setUserType(UserHelper.ManagerUser);
        if (userBean != null && userBean.getIsLogin() == 1) {
            //已注册 已登录
            loginState = UserHelper.LOGGED_IN;
        }

        //初始化ImageLoader
        initImageLoader();
    }

    /**
     * 初始化imageLoader
     */
    private void initImageLoader() {
        DisplayImageOptions options = new DisplayImageOptions.Builder().showImageForEmptyUri(R.color.bg_no_photo)
                .showImageOnFail(R.color.bg_no_photo).showImageOnLoading(R.color.bg_no_photo).cacheInMemory(true)
                .cacheOnDisk(true).build();

        File cacheDir = new File(DEFAULT_SAVE_IMAGE_PATH);
        ImageLoaderConfiguration imageconfig = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(200)
                .diskCache(new UnlimitedDiskCache(cacheDir))
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .defaultDisplayImageOptions(options).build();

        ImageLoader.getInstance().init(imageconfig);
    }

    /**
     * 获取全局context对象
     *
     * @return
     */
    public synchronized static Context getAppContext() {
        return context;
    }

    private static void clearMemory(){
        DataStorage.removeAllData();

        provinceData = null;

        mList.clear();
        mList = null;
    }

    /**
     * 是否登录
     *
     * @return
     */
    public static boolean isLogin() {
        if (userBean == null || loginState != UserHelper.LOGGED_IN) {
            //未登录
            return false;
        }
        return true;
    }

    /**
     * 保存登录状态
     *
     * @param state
     */
    public static void setLoginState(int state) {
        loginState = state;
    }

    /**
     * 用户类型
     *
     * @return
     */
    public static final int getUserType() {
        return userBean.getUserType();
    }

    public static final String getUserIdentity() {
        return userBean.getUserIdentity();
    }

    public static final UserBean getUserBean() {
        return userBean;
    }


    /**
     * add Activity
     * @param activity
     */
    public static void addActivity(Activity activity) {
        if (mList.contains(activity)){
            return;
        }
        mList.add(activity);
    }

    public static void removeActivity(Activity activity) {
        mList.remove(activity);
    }

    /**
     * 关闭每一个list内的activity
     */
    @SuppressWarnings("finally")
    public static void exit() {
        //noinspection finally
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            clearMemory();
            System.exit(0);
        }
    }

    /**
     * 关闭之前的界面
     */
    public static void closePreAct() {
        int size = mList.size();
        Log.e("","");
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mList.clear();
        }
    }

    /**
     * 杀进程
     */
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

    /**
     * 获取所有地区数据
     * @return
     */
    public static ProvinceData getProvinceData() {
        if (provinceData == null){
            provinceData = new ProvinceData(context);
        }

        return provinceData;
    }

    /**
     * 登录模块返回按钮的操作
     * @param context
     * @param a
     */
    public static void backBtn(Context context, Class a){
        ((Activity)context).finish();
    }
    /**
     * 登录模块进入下一界面的操作
     * @param context
     * @param a
     */
    public static void enterNext(Context context, Class a){
        Intent intent = new Intent(context, a);
        context.startActivity(intent);
        //((Activity)context).finish();

        //((Activity)context).overridePendingTransition(0, 0);
        //((Activity)context).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        //添加到activity管理列表
        addActivity((Activity) context);
    }

    public static void enterNext1(Context context, Class a){
        Intent intent = new Intent(context, a);
        context.startActivity(intent);
    }

    public static void Next(Context context, Class a){
        Intent intent = new Intent(context, a);
        context.startActivity(intent);
        ((Activity)context).finish();

        /*//添加到activity管理列表
        addActivity((Activity) context);*/
    }

    /**
     * 退出登录
     */
    public static void exitLogin(Activity activity){
        userBean.setIsLogin(0);
        LocalStorage.resetUserInfo(userBean.getUserType(), userBean.getUserName(), userBean.getUserPw(), userBean.getIsLogin());
        DataStorage.resetLists();

        closePreAct();
        activity.finish();

        /*Intent intent = new Intent(activity, IdentitySelectAct.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);*/

        SysApplication.Next(activity, IdentitySelectAct.class);
    }
}
