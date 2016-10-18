package com.manager.Interface;

/**
 * item 点击选择  修改购物数量
 * @author donghuiyang
 * @create time 2016/6/22 0022.
 */
public interface ICoallBack10 {
    public void onItemClick(int pos);

    public void onAddNum(int value, int pos);
    public void onSubNum(int value, int pos);
}
