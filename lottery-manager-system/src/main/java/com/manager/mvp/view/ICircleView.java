package com.manager.mvp.view;

/**
 * 
* @ClassName: ICircleViewUpdateListener 
* @Description: view,服务器响应后更新界面 
* @author yiw
* @date 2015-12-28 下午4:13:04 
*
 */
public interface ICircleView {

	public void update2DeleteCircle(String circleId);
	public void update2DeleteFavort(int circlePosition, String favortId);
	public void update2DeleteComment(int circlePosition, String commentId);

}
