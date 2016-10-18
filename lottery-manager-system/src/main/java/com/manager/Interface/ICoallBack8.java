package com.manager.Interface;

/**
 * icon touch事件回调
 * @author donghuiyang
 * @create time 2016/6/22 0022.
 */
public interface ICoallBack8 {
    public void onItemTouchDown(int imgID, int titleID, int styleID, int x, int y, boolean isFlip);
    public void onItemTouchUp();
    public void updateView(boolean flag);
}
