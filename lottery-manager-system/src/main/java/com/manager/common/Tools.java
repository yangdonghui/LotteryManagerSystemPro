package com.manager.common;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.manager.bean.ColumnInfo;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 工具类
 * Created by Administrator on 2016/2/19 0019.
 */
public class Tools {

    private static float widthSrc = 720;
    private static float heightSrc = 1280;

    /**
     * 获取设备SDK版本
     * @return
     */
    public static int getAndroidSDKVersion() {
        int version = 0;
        try {
            version = Integer.valueOf(android.os.Build.VERSION.SDK);
        } catch (NumberFormatException e) {
        }
        return version;
    }

    /**
     * 获取手机的密度
     */
    public static float getDensity(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.density;
    }

    /**
     * 根据屏幕分辨率获取对应的字体大小
     * @param context
     * @param pxValue
     * @return
     */
    public static float px2pxByHVGA(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return ((pxValue + 0.5f) * scale + 0.5f);
    }

    /**
     * 获取范围内的随机数
     * @param min
     * @param max
     * @return
     */
    public static int random(int min, int max) {
        return (int) (Math.random() * max + min);
    }

    /**
     * dip转换成px
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转换成dip
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 字符数组 转 整数数组
     * @param arrayList
     * @return
     */
    public static ArrayList<Integer> convertArrayList(ArrayList<String> arrayList){
        if (arrayList == null || arrayList.size() <= 0) return null;

        ArrayList<Integer> arryInt = new ArrayList<Integer>();
        for (int i=0;i<arrayList.size();i++){
            String str = arrayList.get(i);
            arryInt.add(Integer.valueOf(str));
        }

        return arryInt;
    }
    public static ArrayList<String> convertIntegerArrayList(ArrayList<Integer> arrayList){
        if (arrayList == null || arrayList.size() <= 0) return null;

        ArrayList<String> arryString = new ArrayList<String>();
        for (int i=0;i<arrayList.size();i++){
            int intValue = arrayList.get(i);
            arryString.add(StringHelper.intToString(intValue));
        }

        return arryString;
    }

    /**
     * View宽，高
     * @param v
     * @return
     */
    public static int[] getLocation(View v) {
        int[] loc = new int[4];
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        loc[0] = location[0];
        loc[1] = location[1];
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        v.measure(w, h);

        loc[2] = v.getMeasuredWidth();
        loc[3] = v.getMeasuredHeight();

        return loc;
    }

    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 获取App View区域
     * @param activity
     * @return
     */
    public static Rect getViewRect(Activity activity) {
        Rect outRect = new Rect();
        activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getDrawingRect(outRect);

        return outRect;
    }

    public static Rect getWindowRect(Activity activity) {
        Display a = activity.getWindowManager().getDefaultDisplay();

        return new Rect(0, 0, a.getWidth(), a.getHeight());
    }

    /**
     * 获取屏幕的宽
     * @param activity
     * @return
     */
    public static int getWindowWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        //获取屏幕宽度
        return dm.widthPixels;
    }

    /**
     * 获取屏幕的高
     * @param activity
     * @return
     */
    public static int getWindowHeight(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        //获取屏幕宽度
        return dm.heightPixels;
    }
    public static Point getWinWH(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);

        return new Point(dm.widthPixels, dm.heightPixels);
    }

    public static float getScale(Context context) {
        Point wh = getWinWH(context);

        float scaleX = wh.x / widthSrc;
        float scaleY = wh.y / heightSrc;
        if (scaleX >= scaleY) {
            return scaleX;
        }

        return scaleX;
    }

    public static float getScaleX(Context context) {
        Point wh = getWinWH(context);

        return wh.x / widthSrc;
    }
    public static float getScaleY(Context context) {
        Point wh = getWinWH(context);

        return wh.y / heightSrc;
    }

    /**
     * 隐藏软键盘
     * @param context
     * @param v
     * @return
     */
    public static boolean hideSoftInput(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen = imm.isActive();//isOpen若返回true，则表示输入法打开
        if (isOpen) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘

            return true;
        }

        return false;
    }

    public static boolean hideSoftInput(Activity activity) {
        if (activity.getCurrentFocus() == null) return false;

        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen = imm.isActive();//isOpen若返回true，则表示输入法打开
        if (isOpen) {
            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0); //强制隐藏键盘
            return true;
        }

        return false;
    }

    /**
     * 显示软键盘
     *
     * @param edit
     */
    public static void showSoftInput(final Context context,final EditText edit) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                edit.requestFocus();
                imm.showSoftInput(edit, InputMethodManager.SHOW_FORCED);
            }
        }, 100);
    }

    public static void ForceShowSoftInput(final Context context,final EditText edit) {
        edit.requestFocus();

        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 发送消息时，获取当前事件
     *
     * @return 当前时间
     */
    public static String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(new Date());
    }

    /**
     * 用时间戳生成照片名称
     *
     * @return
     */
    public static String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }

    /**
     * 清空数据
     * @param list
     */
    public static void clearLists(ArrayList list) {
        if (list != null){
            list.clear();
        }
    }

    /**
     * 根据手机屏幕宽度，计算gridview每个单元格的宽度
     * @param context
     * @param width 单元格预设宽度
     * @param padding 单元格间距
     * @return
     */
    public static ColumnInfo calculateColumnWidthAndCountInRow(Context context ,int width,int padding){
        int screenWidth = getWindowWidth((Activity)context);
        ColumnInfo colInfo = new ColumnInfo();
        int colCount = 0;
        //判断屏幕是否刚好能容纳下整数个单元格，若不能，则将多出的宽度保存到space中
        int space = screenWidth % width;

        if( space == 0 ){ //正好容纳下
            colCount = screenWidth / width;
        }else if( space >= ( width / 2 ) ){ //多出的宽度大于单元格宽度的一半时，则去除最后一个单元格，将其所占的宽度平分并增加到其他每个单元格中
            colCount = screenWidth / width;
            space = width - space;
            width = width + space / colCount;
        }else{  //多出的宽度小于单元格宽度的一半时，则将多出的宽度平分，并让每个单元格减去平分后的宽度
            colCount = screenWidth / width + 1;
            width = width - space / colCount;
        }

        colInfo.space = space;
        colInfo.countInRow = colCount;
        //计算出每行的间距总宽度，并根据单元格的数量重新调整单元格的宽度
        colInfo.width = width - (( colCount + 1 ) * padding ) / colCount;

        return colInfo;
    }

    /**
     * 重新计算listview的高度
     * @param listView
     */
    public static int setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return 0;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);

        return params.height;
    }

    /**
     * 根据屏幕宽度 适配girdview
     * @param listSize 数据长度
     * @param itemW item宽
     * @param interval  间隔
     * @param gridView gridview控件
     */
    public static void updateGridView(Activity activity, int listSize, int itemW, int interval, GridView gridView){

        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) gridView.getLayoutParams();
        int sw = getWindowWidth(activity) - (lp.leftMargin + lp.rightMargin);
        int rowNum = sw/itemW;
        interval = (sw - rowNum * itemW) / (rowNum-1);

        gridView.setColumnWidth(itemW); // 设置列表项宽
        gridView.setHorizontalSpacing(interval); // 设置列表项水平间距
        gridView.setVerticalSpacing(interval);
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setNumColumns(rowNum);
    }

    /**
     * 更新 水平滚动gridview 控件的宽度
     * @param activity 上下文对象
     * @param listSize 数据长度
     * @param itemW item宽度
     * @param interval 间隔
     * @param gridView gridview控件
     */
    public static void updateHorizontalGridView(Activity activity, int listSize, int itemW, int interval, GridView gridView){

        float density = getDensity(activity);
        int gridviewWidth = (int) (((listSize * (itemW) + (listSize-1)*interval))/* * density*/);
        int itemWidth = (int) (itemW/* * density*/);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);
        gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        gridView.setColumnWidth(itemWidth); // 设置列表项宽
        gridView.setHorizontalSpacing(interval); // 设置列表项水平间距
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setNumColumns(listSize); // 设置列数量=列表集合数
    }

    /**
     * 提示
     * @param context
     * @param str
     */
    public static void showToast(Context context, String str){
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(Activity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        activity.getWindow().setAttributes(lp);
    }
}
