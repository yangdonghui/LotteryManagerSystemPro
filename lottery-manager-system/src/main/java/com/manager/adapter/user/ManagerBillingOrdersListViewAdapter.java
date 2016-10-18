package com.manager.adapter.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.manager.Interface.ICoallBack5;
import com.manager.adapter.CommonAdapter;
import com.manager.bean.ItemBean;
import com.manager.bean.OrdersBean;
import com.manager.lotterypro.R;

import java.util.HashMap;
import java.util.List;

/**
 * 管理员 即开票 订单管理等 list视图适配器
 * Created by Administrator on 2016/2/22 0022.
 */
public class ManagerBillingOrdersListViewAdapter extends BaseAdapter{

    private Context context;                            //运行上下文
    private List<OrdersBean> listItems;    //商品信息集合
    private LayoutInflater listContainer;               //视图容器
    private int tabIndex;

    private ListView mListView;
    CommonAdapter<ItemBean>[] mAdapters;

    public static HashMap<Integer, Boolean> isSelected;

    public final static class ViewHolder {
        //复选框
        public View checkboxParent;
        public CheckBox checkBox;
        //编号
        public TextView numberTv;
        //状态
        public TextView stateTv;

        //数据列表
        public ListView listView;

        //预计配送时间
        public TextView timeTv;

        //按钮
        public Button btn1;
        public Button btn2;

        public View faildView;
        public TextView faildTv;
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

    public void setListView(ListView listView) {
        this.mListView = listView;
    }

    public ManagerBillingOrdersListViewAdapter(Context context, List<OrdersBean> listItem, int tabIndex) {
        this.context = context;
        listContainer = LayoutInflater.from(context);   //创建视图容器并设置上下文
        this.listItems = listItem;
        mAdapters = new CommonAdapter[this.listItems.size()];

        this.tabIndex = tabIndex;

        init();
    }

    // 初始化 设置所有checkbox都为未选择
    public void init() {
        isSelected = new HashMap<Integer, Boolean>();
        for (int i = 0; i < listItems.size(); i++) {
            isSelected.put(i, true);
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


    float startX, endX;
    Button curDel_btn;
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        //自定义视图
        ViewHolder listItemView = null;
        if (convertView == null) {

            listItemView = new ViewHolder();
            //获取list_item布局文件的视图
            convertView = listContainer.inflate(R.layout.list_item_19, null);
            //获取控件对象
            listItemView.checkboxParent = (View) convertView.findViewById(R.id.list_item_19_view);
            if (tabIndex == 1 || tabIndex == 2){
                //打包 装车 显示checkbox样式
                listItemView.checkboxParent.setVisibility(View.VISIBLE);
            }
            listItemView.checkBox = (CheckBox) convertView.findViewById(R.id.list_item_19_checkBox);
            listItemView.numberTv = (TextView) convertView.findViewById(R.id.list_item_19_number_id_tv);
            listItemView.stateTv = (TextView) convertView.findViewById(R.id.list_item_19_state_tv);
            listItemView.timeTv = (TextView) convertView.findViewById(R.id.list_item_19_time_tv);
            listItemView.btn1 = (Button) convertView.findViewById(R.id.list_item_19_btn1);
            listItemView.btn2 = (Button) convertView.findViewById(R.id.list_item_19_btn2);

            listItemView.faildView = (View) convertView.findViewById(R.id.list_item_19_faild_view);
            listItemView.faildTv = (TextView) convertView.findViewById(R.id.list_item_19_faild_tv);

            listItemView.listView = (ListView) convertView.findViewById(R.id.list_item_19_listview);

            //设置控件集到convertView
            convertView.setTag(listItemView);
        } else {
            listItemView = (ViewHolder) convertView.getTag();
        }

        if (listItems == null) return convertView;

        ManagerBillingOrderChildListViewAdapter myAdapter = new ManagerBillingOrderChildListViewAdapter(context, listItems.get(position).getListData());
        listItemView.listView.setAdapter(myAdapter);

        listItemView.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                icallBack.doConfirm(position);
            }
        });
        listItemView.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //配送失败 按钮
                icallBack.doFaild(position);
            }
        });

        //打包中 装车中
        if (listItems.get(position).getState() == 0){
            listItemView.btn1.setText(R.string.manager_str_02);
            listItemView.btn2.setVisibility(View.GONE);
        }else if (listItems.get(position).getState() == 1){
            listItemView.btn1.setText(R.string.manager_str_03);
            listItemView.btn2.setVisibility(View.GONE);
        }

        if (listItemView.checkboxParent.getVisibility() == View.VISIBLE){
            final CheckBox checkBox = listItemView.checkBox;
            listItemView.checkboxParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isSelected.get(position)) {
                        isSelected.put(position, false);

                    }else if(!isSelected.get(position)) {
                        isSelected.put(position, true);
                    }

                    checkBox.setChecked(isSelected.get(position));
                    //更新全选按钮状态
                    if (icallBack != null){
                        icallBack.onClickCheckbox(isSelected.get(position));
                    }
                }
            });

            listItemView.checkBox.setChecked(isSelected.get(position));
        }

        listItemView.stateTv.setText(listItems.get(position).getstateStr());
        listItemView.numberTv.setText(listItems.get(position).getNumber());
        listItemView.timeTv.setText(listItems.get(position).getTime());

        if (listItems.get(position).getState() == 3){
            //再次配送中
            listItemView.faildView.setVisibility(View.VISIBLE);
        }else{
            listItemView.faildView.setVisibility(View.GONE);
        }

        if (listItems.get(position).getFaildStr() != null && !listItems.get(position).getFaildStr().equals("")){
            listItemView.faildTv.setText(listItems.get(position).getFaildStr());
        }

        return convertView;
    }

    /**
     * 更新某项item
     * @param id：item索引
     */
    public void updateItemFaild(long id, String str) {

        if (mListView != null) {
            for (int i = 0; i < mListView.getChildCount(); i++){
                if (i == id){
                    View convertView = mListView.getChildAt(i);
                    ViewHolder holder = (ViewHolder) convertView.getTag();
                    holder.faildView.setVisibility(View.VISIBLE);
                    holder.faildTv.setText(str);

                    listItems.get(i).setFaildStr(str);
                    listItems.get(i).setState(3);
                    break;
                }
            }
        }
    }
}
