package com.manager.storage;

import android.util.Log;

import com.manager.bean.ProductBean;
import com.manager.bean.UserSelectNumberBean;

import java.util.ArrayList;

/**
 * 解析以及存储的数据列表
 * @author donghuiyang
 * @create time 2016/6/14 0014.
 */
public class DataStorage {
    //我的彩票 列表
    public static ArrayList<UserSelectNumberBean> myLotteryNumberLists = new ArrayList<>();

    //购物车 列表
    public static ArrayList<ProductBean> myCartLists = new ArrayList<>();;

    /**
     * 清除所有内存数据
     */
    public static void removeAllData() {
        if (myLotteryNumberLists != null){
            myLotteryNumberLists.clear();
            myLotteryNumberLists = null;
        }

        if (myCartLists != null){
            myCartLists.clear();
            myCartLists = null;
        }
    }

    /**
     * 清除购物车 及 选号
     */
    public static void resetLists(){
        if (myLotteryNumberLists != null){
            myLotteryNumberLists.clear();
        }

        if (myCartLists != null){
            myCartLists.clear();
        }
    }

    /**
     * 彩票选号 界面 保存选号
     * @param type
     * @param name
     * @param time
     * @param numbersList
     */
    public static void saveSelectNumbers(int type, String name, String time, ArrayList numbersList){
        if (myLotteryNumberLists == null){
            myLotteryNumberLists = new ArrayList<>();
        }

        int size = myLotteryNumberLists.size();
        UserSelectNumberBean data = new UserSelectNumberBean(String.valueOf(size), type, name, "", numbersList);
        myLotteryNumberLists.add(data);

        size = myLotteryNumberLists.size();
        Log.e("saveSelectNumbers=", String.valueOf(size));
    }

    /**
     * 保存商品到购物车
     * @param att
     */
    public static void saveProductInCart(int num, ProductBean att){
        if (myCartLists == null){
            myCartLists = new ArrayList<>();
        }

        ProductBean data = isHaveProductInCart(att);
        if (data == null){
            ProductBean dataNew = new ProductBean(att.getId(),
                    att.getType(),
                    att.getIconUrl(),
                    att.getTitle(),
                    att.getPrice(),
                    att.getUnit(),
                    att.getValue(),
                    att.getBuyNum(),
                    att.getLeftNum());

            myCartLists.add(dataNew);
        }else{
            data.setBuyNum(String.valueOf(num));
        }

        int size = myCartLists.size();
        Log.e("saveProductToCart=", String.valueOf(size));
    }

    /**
     * 判断产品是否加入购物车
     * @param att
     * @return
     */
    private static ProductBean isHaveProductInCart(ProductBean att){
        if (myCartLists == null || myCartLists.size() <= 0) return null;

        for (ProductBean data:myCartLists){
            if (data.getType() == att.getType()){
                if (data.getId() == att.getId()){
                    return data;
                }
            }
        }

        return null;
    }

    /**
     * 修改购物车产品的数量
     * @param att
     */
    public static void subProductInCart(int num, ProductBean att){
        if (myCartLists == null) return;

        ProductBean data = isHaveProductInCart(att);
        if (data != null){
            data.setBuyNum(String.valueOf(num));

            if (Integer.parseInt(data.getBuyNum()) <= 0){
                //从购物车删除
                myCartLists.remove(att);
            }
        }


        int size = myCartLists.size();
        Log.e("subProductInCart=", String.valueOf(size));
    }

    /**
     * 删除购物车中的某项数据
     * @param position
     */
    public static void removeProductInCart(int position){
        if (myCartLists == null) return;

        myCartLists.remove(position);

        int size = myCartLists.size();
        Log.e("removeProductInCart=", String.valueOf(size));
    }

    /**
     * 计算购物车产品价格
     * @return
     */
    public static float calculationPriceInCart(){
        float price = 0.0f;

        for (ProductBean att:myCartLists){
            float value = Float.valueOf(att.getPrice());
            int buyNum = Integer.parseInt(att.getBuyNum());
            price +=  value* buyNum;
        }

        return price;
    }

    public static int getCartListSize(){
        if (myCartLists == null) return 0;

        return myCartLists.size();
    }

    /**
     * 计算购物车中产品数量
     * @return
     */
    public static int calculationNumInCart(){
        if (myCartLists == null) return 0;

        int count = 0;
        for (ProductBean att:myCartLists){
            int buyNum = Integer.parseInt(att.getBuyNum());
            count +=  buyNum;
        }

        return count;
    }
}
