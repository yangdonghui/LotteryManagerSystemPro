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
import com.manager.common.StringHelper;
import com.manager.helper.UserHelper;
import com.manager.lotterypro.R;

import java.util.HashMap;
import java.util.List;

/**
 * 委托记录 list视图适配器
 * Created by Administrator on 2016/2/22 0022.
 */
public class EntrustRecordListViewAdapter extends BaseAdapter{

    private Context context;                            //运行上下文
    private List<OrdersBean> listItems;    //商品信息集合
    private LayoutInflater listContainer;               //视图容器
    private int type, tabIndex;

    public static HashMap<Integer, Boolean> isSelected;

    private final static class ViewHolder {
        //复选框
        public CheckBox checkBox;
        //编号
        public TextView numberTv;
        //状态
        public TextView stateTv;

        //数据列表
        public ListView listView;

        //价格
        public TextView priceTv;

        //按钮
        public Button btn1;
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

    public EntrustRecordListViewAdapter(Context context, List<OrdersBean> listItems, int type, int tabIndex) {
        this.context = context;
        listContainer = LayoutInflater.from(context);   //创建视图容器并设置上下文
        this.listItems = listItems;

        this.type = type;
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
        if (listItems == null) {
            return null;
        }

        //自定义视图
        OrdersBean item = listItems.get(position);

        ViewHolder listItemView = null;
        if (convertView == null) {
            listItemView = new ViewHolder();
            //获取list_item布局文件的视图
            convertView = listContainer.inflate(R.layout.list_item_16, null);
            //获取控件对象
            listItemView.checkBox = (CheckBox) convertView.findViewById(R.id.list_item_16_checkBox);
            listItemView.numberTv = (TextView) convertView.findViewById(R.id.list_item_16_number_id_tv);
            listItemView.stateTv = (TextView) convertView.findViewById(R.id.list_item_16_state_tv);
            listItemView.priceTv = (TextView) convertView.findViewById(R.id.list_item_16_price_tv);
            listItemView.btn1 = (Button) convertView.findViewById(R.id.list_item_16_btn1);

            if (type == UserHelper.LotteryUser){
                //彩民
                if (item.getState() != 0 && item.getState() != 4){
                    //除了 未付款 待取票之外 隐藏
                    listItemView.btn1.setVisibility(View.GONE);
                }

            }else {
                //投注站 管理员 按钮
                listItemView.btn1.setText(R.string.entrust_str_2);
            }

            if (type == UserHelper.LotteryUser && tabIndex == 1){//彩民版
                //待出票  待付款 显示checkbox样式
                listItemView.checkBox.setButtonDrawable(R.drawable.pay_way_check);
                listItemView.checkBox.setChecked(isSelected.get(position));
            }

            listItemView.listView = (ListView) convertView.findViewById(R.id.list_item_16_listview);
            CommonAdapter<ItemBean> myAdapter = new CommonAdapter<ItemBean>(context, listItems.get(position).getListData(), R.layout.list_item_14) {
                @Override
                public void convert(com.manager.adapter.ViewHolder helper, ItemBean item) {
                    helper.setText(R.id.list_item_14_tv1, item.getItemName());
                    helper.setText(R.id.list_item_14_tv2, StringHelper.getPriceStr(item.getPrice()));
                    helper.setText(R.id.list_item_14_tv3, StringHelper.getNumStr(item.getNum()));
                }
            };
            listItemView.listView.setAdapter(myAdapter);

            //设置控件集到convertView
            convertView.setTag(listItemView);
        } else {
            listItemView = (ViewHolder) convertView.getTag();
        }

        if (tabIndex == 1){
            final CheckBox checkBox = listItemView.checkBox;
            listItemView.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isSelected.get(position)) {
                        isSelected.put(position, false);

                    } else if (!isSelected.get(position)) {
                        isSelected.put(position, true);
                    }

                    checkBox.setChecked(isSelected.get(position));
                    //更新全选按钮状态
                    icallBack.onClickCheckbox(isSelected.get(position));
                }
            });

            listItemView.checkBox.setChecked(isSelected.get(position));

        }

        listItemView.numberTv.setText(listItems.get(position).getNumber());
        listItemView.priceTv.setText(listItems.get(position).getPrice());

        return convertView;
    }
}
