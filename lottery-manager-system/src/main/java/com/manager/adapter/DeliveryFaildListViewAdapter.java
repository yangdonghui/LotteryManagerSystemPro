package com.manager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.manager.Interface.ICoallBack5;
import com.manager.lotterypro.R;

import java.util.HashMap;
import java.util.List;

/**
 * 开奖信息主界面 list视图适配器
 * @author donghuiyang
 * @create time 2016/5/17 0017.
 */
public class DeliveryFaildListViewAdapter extends BaseAdapter {

    private Context context;                            //运行上下文
    private List<String> listItems;    //商品信息集合
    private LayoutInflater listContainer;               //视图容器

    public static HashMap<Integer, Boolean> isSelected;

    private final class ViewHolder {
        public View checkboxParent;
        public CheckBox checkBox;
    }

    /**
     * 初始化接口变量
     */
    ICoallBack5 icallBack = null;
    /**
     * 自定义控件的自定义事件
     * @param iBack 接口类型
     */
    public void setonClick(ICoallBack5 iBack)
    {
        icallBack = iBack;
    }

    public DeliveryFaildListViewAdapter(Context context, List<String> listItems) {
        this.context = context;
        listContainer = LayoutInflater.from(context);   //创建视图容器并设置上下文
        this.listItems = listItems;

        init();
    }

    // 初始化 设置所有checkbox都为未选择
    public void init() {
        isSelected = new HashMap<Integer, Boolean>();
        for (int i = 0; i < listItems.size(); i++) {
            isSelected.put(i, false);
        }
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
        //自定义视图
        ViewHolder listItemView = null;
        if (convertView == null) {
            listItemView = new ViewHolder();
            //获取list_item布局文件的视图
            convertView = listContainer.inflate(R.layout.list_item_20, null);
            //获取控件对象
            listItemView.checkboxParent = (View) convertView.findViewById(R.id.list_item_20_view);
            listItemView.checkBox = (CheckBox) convertView.findViewById(R.id.list_item_20_checkBox);

            //设置控件集到convertView
            convertView.setTag(listItemView);
        } else {
            listItemView = (ViewHolder) convertView.getTag();
        }

        final CheckBox checkBox = listItemView.checkBox;
        listItemView.checkboxParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<isSelected.size();i++){
                    if (position == i){
                        isSelected.put(position, true);
                        checkBox.setChecked(isSelected.get(position));
                    }else{
                        isSelected.put(i, false);
                    }
                }

                if (icallBack != null){
                    //选择item
                    icallBack.doConfirm(position);
                }
            }
        });

        listItemView.checkBox.setChecked(isSelected.get(position));

        //设置文字
        listItemView.checkBox.setText((String) listItems.get(position));

        return convertView;
    }
}
