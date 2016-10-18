package com.manager.adapter.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fortysevendeg.swipelistview.SwipeListView;
import com.manager.bean.UserBean;
import com.manager.lotterypro.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 站点 管理雇员list视图适配器
 * @author donghuiyang
 * @create time 2016/5/17 0017.
 */
public class EmployeeManagerListViewAdapter extends BaseAdapter {

    private Context context;                            //运行上下文
    private List<UserBean> listItems;    //商品信息集合
    private LayoutInflater listContainer;               //视图容器

    private SwipeListView mSwipeListView ;

    private final class ViewHolder {
        public ImageView iconImgv;

        public TextView tv1;
        public TextView tv2;

        //删除按钮
        public Button removeBtn;
    }

    public EmployeeManagerListViewAdapter(Context context, List<UserBean> listItems, SwipeListView mSwipeListView) {
        this.context = context;
        listContainer = LayoutInflater.from(context);   //创建视图容器并设置上下文
        this.listItems = listItems;

        this.mSwipeListView = mSwipeListView;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (listItems == null) {
            return null;
        }

        //自定义视图
        ViewHolder listItemView = null;
        if (convertView == null) {
            listItemView = new ViewHolder();
            //获取list_item布局文件的视图
            convertView = listContainer.inflate(R.layout.list_item_22, null);
            //获取控件对象
            listItemView.iconImgv = (ImageView) convertView.findViewById(R.id.list_item_22_icon_imgv);

            listItemView.tv1 = (TextView) convertView.findViewById(R.id.list_item_22_name_tv);
            listItemView.tv2 = (TextView) convertView.findViewById(R.id.list_item_22_username_tv);

            listItemView.removeBtn = (Button) convertView.findViewById(R.id.list_item_22_removebtn);

            //设置控件集到convertView
            convertView.setTag(listItemView);
        } else {
            listItemView = (ViewHolder) convertView.getTag();
        }

        //设置文字
        listItemView.tv1.setText((String)listItems.get(position).getRealName());
        listItemView.tv2.setText((String)listItems.get(position).getUserName());

        String iconUrl = listItems.get(position).getIconUrl();
        if (iconUrl != null && !iconUrl.equals("")){
            //头像
            ImageLoader.getInstance().displayImage(iconUrl, listItemView.iconImgv);
        }else{
            listItemView.iconImgv.setImageResource(R.mipmap.default_icon);
        }

        listItemView.removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeListView.closeAnimate(position);
                mSwipeListView.dismiss(position);
            }
        });

        return convertView;
    }
}