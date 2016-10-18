package com.manager.adapter.lotterycity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.manager.Interface.ICoallBack2;
import com.manager.bean.NoteLotterybean;
import com.manager.lotterypro.R;

import java.util.List;

/**
 * 彩票 票号 订单支付、 委托详情等 list视图适配器
 * @author donghuiyang
 * @create time 2016/5/17 0017.
 */
public class SelectNumberListViewAdapter extends BaseAdapter {

    private Context context;                     //运行上下文
    private List<NoteLotterybean> listItems;    //商品信息集合
    private LayoutInflater listContainer;        //视图容器

    private final class ViewHolder {
        public TextView tv1;
        public View childView;
        public EditText editText;
        public TextView tv2;
        public ImageButton removeBtn;

        public GridView gridview1;
        public GridView gridview2;
    }

    /**
     * 初始化接口变量
     */
    ICoallBack2 icallBack = null;
    /**
     * 自定义控件的自定义事件
     * @param iBack 接口类型
     */
    public void setonClick(ICoallBack2 iBack)
    {
        icallBack = iBack;
    }

    public SelectNumberListViewAdapter(Context context, List<NoteLotterybean> listItems) {
        this.context = context;
        listContainer = LayoutInflater.from(context);   //创建视图容器并设置上下文
        this.listItems = listItems;
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
            convertView = listContainer.inflate(R.layout.list_item_17_22, null);
            //获取控件对象
            listItemView.tv1 = (TextView) convertView.findViewById(R.id.list_item_17_22_tv1);
            listItemView.tv2 = (TextView) convertView.findViewById(R.id.list_item_17_22_tv2);
            listItemView.childView = (View) convertView.findViewById(R.id.list_item_17_22_view);
            listItemView.editText = (EditText) convertView.findViewById(R.id.list_item_17_22_edittext);
            listItemView.removeBtn = (ImageButton) convertView.findViewById(R.id.list_item_17_22_remove_btn);
            listItemView.gridview1 = (GridView)convertView.findViewById(R.id.list_item_17_22_gridview1);
            listItemView.gridview2 = (GridView)convertView.findViewById(R.id.list_item_17_22_gridview2);

            listItemView.removeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //删除按钮
                    icallBack.removeBtn(position);
                }
            });

            if (listItems.get(position).getDoubleSingleType() == 1){
                //复式 不能修改注数
                listItemView.editText.setFocusable(false);
                listItemView.editText.setBackgroundDrawable(null);
                listItemView.editText.getLayoutParams().width = 40;
            }

            //设置控件集到convertView
            convertView.setTag(listItemView);
        } else {
            listItemView = (ViewHolder) convertView.getTag();
        }

        //编号
        listItems.get(position).setId(String.valueOf(position + 1));
        listItemView.tv1.setText(listItems.get(position).getId());
        //金额
        listItemView.tv2.setText(context.getString(R.string.money_str) + String.valueOf(listItems.get(position).getPrice()));

        //添加号码
        BallsGridviewAdapter redAdapter = new BallsGridviewAdapter(context, R.layout.red_number1, listItems.get(position).getNumbers().getNumbersList1(), 1);
        listItemView.gridview1.setAdapter(redAdapter);

        BallsGridviewAdapter blueAdapter = new BallsGridviewAdapter(context, R.layout.red_number1, listItems.get(position).getNumbers().getNumbersList2(), 2);
        listItemView.gridview2.setAdapter(blueAdapter);

        return convertView;
    }
}