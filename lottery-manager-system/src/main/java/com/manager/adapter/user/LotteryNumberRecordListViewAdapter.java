package com.manager.adapter.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.manager.Interface.ICoallBack7;
import com.manager.bean.UserSelectNumberBean;
import com.manager.lotterypro.R;

import java.util.HashMap;
import java.util.List;

/**
 * 我的彩票记录 list视图适配器
 * Created by Administrator on 2016/2/22 0022.
 */
public class LotteryNumberRecordListViewAdapter extends BaseAdapter{

    private Context context;                            //运行上下文
    private List<UserSelectNumberBean> listItems;    //商品信息集合
    private LayoutInflater listContainer;               //视图容器

    LotteryNumberRecordChildListViewAdapter[] mAdapters;
    public static HashMap<Integer, Boolean> isSelected;
    private ListView mListView;

    private final static class ViewHolder {
        public View checkBoxParent;
        //复选框
        public CheckBox checkBox;
        //编号
        public TextView nameTv;
        //状态
        public TextView timeTv;

        //数据列表
        public ListView listView;
    }

    public LotteryNumberRecordListViewAdapter(Context context, List<UserSelectNumberBean> listItems) {
        this.context = context;
        listContainer = LayoutInflater.from(context);   //创建视图容器并设置上下文
        this.listItems = listItems;

        mAdapters = new LotteryNumberRecordChildListViewAdapter[this.listItems.size()];
        init();
    }

    // 初始化 设置所有checkbox都为未选择
    public void init() {
        isSelected = new HashMap<Integer, Boolean>();

        for (int i = 0; i < listItems.size(); i++) {
            isSelected.put(i, true);
        }
    }

    public void updateAllChildCheck(boolean flag, int position) {
        for (int i = 0; i < mAdapters[position].isChildSelected.size(); i++) {
            if (mAdapters[position].isChildSelected.get(i) != flag){
                mAdapters[position].isChildSelected.put(i, flag);
            }
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
        if (listItems == null) {
            return null;
        }

        //自定义视图
        UserSelectNumberBean item = listItems.get(position);

        ViewHolder listItemView = null;
        if (convertView == null) {
            listItemView = new ViewHolder();
            //获取list_item布局文件的视图
            convertView = listContainer.inflate(R.layout.list_item_17_1, null);
            //获取控件对象
            listItemView.checkBoxParent = (View) convertView.findViewById(R.id.list_item_17_1_checkbox_view);
            listItemView.checkBox = (CheckBox) convertView.findViewById(R.id.list_item_17_1_checkbox);
            listItemView.nameTv = (TextView) convertView.findViewById(R.id.list_item_17_1_tv1);
            listItemView.timeTv = (TextView) convertView.findViewById(R.id.list_item_17_1_tv2);

            listItemView.listView = (ListView) convertView.findViewById(R.id.list_item_17_1_listview);

            //设置控件集到convertView
            convertView.setTag(listItemView);
        } else {
            listItemView = (ViewHolder) convertView.getTag();
        }

        final CheckBox checkBox = listItemView.checkBox;
        final ListView listView = listItemView.listView;
        listItemView.checkBoxParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSelected.get(position)) {
                    isSelected.put(position, false);

                } else if (!isSelected.get(position)) {
                    isSelected.put(position, true);
                }

                checkBox.setChecked(isSelected.get(position));
                //更新全选按钮状态
                //updateAllChildCheck(isSelected.get(position), position);
                mAdapters[position].updateAllRowCheckBox(listView, isSelected.get(position));

            }
        });


        listItemView.checkBox.setChecked(isSelected.get(position));
        listItemView.nameTv.setText(item.getLotteryName());
        //listItemView.timeTv.setText(String.valueOf(item.getTime()));

        //号码列表
        final CheckBox checkBox1 = listItemView.checkBox;
        mAdapters[position] = new LotteryNumberRecordChildListViewAdapter(context, listItems.get(position).getNumbersList(), isSelected.get(position), listItemView.checkBox, position);
        mAdapters[position].setListView(listItemView.listView);
        mAdapters[position].setonClick(new ICoallBack7() {
            @Override
            public void onClickCheckbox(boolean flag) {
                //更新主类 取消被选中
                isSelected.put(position, flag);
                //checkBox1.setChecked(isSelected.get(position));
                updateSingleRowCheckbox(position, flag);
            }
        });
        listItemView.listView.setAdapter(mAdapters[position]);

        return convertView;
    }

    public void setListView(ListView listView) {
        this.mListView = listView;
    }


    /**
     * 更新某项item的checkbox状态
     * @param id：item索引
     * @param flag:状态值
     */
    public void updateSingleRowCheckbox(long id, boolean flag) {

        if (mListView != null) {
            for (int i = 0; i < mListView.getChildCount(); i++){
                if (i == id){
                    View convertView = mListView.getChildAt(i);
                    ViewHolder holder = (ViewHolder) convertView.getTag();
                    holder.checkBox.setChecked(flag);
                    break;
                }
            }
        }
    }
}
