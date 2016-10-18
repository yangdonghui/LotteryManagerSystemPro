package com.manager.adapter.lotterycity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.manager.Interface.ICoallBack10;
import com.manager.Interface.ICoallBack9;
import com.manager.bean.ProductBean;
import com.manager.lotterypro.R;
import com.manager.view.ChangeBuyNumView;

import java.util.List;

/**
 * 产品列表（即开票 耗材） listview适配器
 *
 * @author http://blog.csdn.net/finddreams
 * @Description:gridview的Adapter
 */
public class ProductListViewAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater listContainer;               //视图容器

    private List<ProductBean> listItems;    //商品信息集合

    private ListView mListView;

    private final static class ViewHolder {
        public View rootview;

        //修改数量view
        ChangeBuyNumView changeNumBtn;

        //标题
        public TextView title;
        //附属 （面值 或 限购）
        public TextView value;
        public TextView valueIInfo;
        public TextView valueUnit;
        //价格
        public TextView price;
        //单位
        public TextView unit;

        //库存
        public View inventoryView;
        public TextView inventoryValue;

        //查看详情触摸区域
        public View itemBtn;
    }

    /**
     * 初始化接口变量
     */
    ICoallBack10 icallBack = null;
    /**
     * 自定义控件的自定义事件
     * @param iBack 接口类型
     */
    public void setonClick(ICoallBack10 iBack)
    {
        icallBack = iBack;
    }

    public ProductListViewAdapter(Context context, List<ProductBean> listItems) {
        super();
        this.mContext = context;
        listContainer = LayoutInflater.from(context);   //创建视图容器并设置上下文
        this.listItems = listItems;
    }

    public void setListView(ListView listView){
        mListView = listView;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
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
            convertView = listContainer.inflate(R.layout.list_item_13_1, null);
            //获取控件对象
            listItemView.rootview = (View) convertView.findViewById(R.id.list_item_13_1_rootview);
            listItemView.title = (TextView) convertView.findViewById(R.id.list_item_13_1_title_tv);
            listItemView.value = (TextView) convertView.findViewById(R.id.list_item_13_1_value_tv);
            listItemView.valueIInfo = (TextView) convertView.findViewById(R.id.list_item_13_1_value_tv2);
            listItemView.valueUnit = (TextView) convertView.findViewById(R.id.list_item_13_1_value_tv1);
            listItemView.price = (TextView) convertView.findViewById(R.id.list_item_13_1_price_tv1);
            listItemView.unit = (TextView) convertView.findViewById(R.id.list_item_13_1_price_tv2);
            listItemView.inventoryView = (View) convertView.findViewById(R.id.list_item_13_1_inventory_view);
            listItemView.inventoryValue = (TextView) convertView.findViewById(R.id.list_item_13_1_inventory_tv);

            listItemView.itemBtn = (View) convertView.findViewById(R.id.list_item_13_1_item_view1);
            //点击进入
            listItemView.itemBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (icallBack != null){
                        icallBack.onItemClick(position);
                    }
                }
            });

            listItemView.changeNumBtn = (ChangeBuyNumView) (convertView.findViewById(R.id.list_item_13_1_changeview));

            if (position%2 == 0){
                //奇数
                listItemView.rootview.setBackgroundColor(mContext.getResources().getColor(R.color.bg_color_7));
            }else {
                //偶数
                listItemView.rootview.setBackgroundColor(mContext.getResources().getColor(R.color.bg_color_27));
            }

            //设置控件集到convertView
            convertView.setTag(listItemView);
        } else {
            listItemView = (ViewHolder) convertView.getTag();
        }

        listItemView.title.setText(listItems.get(position).getTitle());
        listItemView.value.setText(listItems.get(position).getValue());
        listItemView.price.setText(mContext.getResources().getString(R.string.money_str) + listItems.get(position).getPrice());
        listItemView.unit.setText(listItems.get(position).getUnit());

        int type = listItems.get(position).getType();
        if (type == 2){
            //耗材
            listItemView.valueUnit.setText(listItems.get(position).getUnit());
        }else{
            listItemView.valueUnit.setText("元");
            listItemView.valueIInfo.setText("面值");
        }

        int leftNum = Integer.parseInt(listItems.get(position).getLeftNum());
        if (leftNum > 0){
            listItemView.inventoryView.setVisibility(View.VISIBLE);
        }

        listItemView.changeNumBtn.setNum(Integer.parseInt(listItems.get(position).getBuyNum()));
        listItemView.changeNumBtn.setonClick(new ICoallBack9() {
            @Override
            public void modifyNumClick(int value, int btnState) {
                if (btnState == 0){
                    //减号
                    //从清单中清除
                    Log.e("","listItemView.changeNumBtn======remove");
                    listItems.get(position).setBuyNum(String.valueOf(value));
                    icallBack.onSubNum(value,position);
                }else if (btnState == 1){
                    //更新购物车清单（某个商品的数量）
                    Log.e("","listItemView.changeNumBtn======add");

                    listItems.get(position).setBuyNum(String.valueOf(value));
                    icallBack.onAddNum(value,position);
                }
            }
        });

        return convertView;
    }

    /**
     * 更新某项item
     */
    public void updateItemView(long id) {

        if (mListView != null) {
            for (int i = 0; i < mListView.getChildCount(); i++){
                if (i == id){
                    View convertView = mListView.getChildAt(i);
                    ViewHolder holder = (ViewHolder) convertView.getTag();

                    holder.changeNumBtn.setNum(Integer.parseInt(listItems.get((int)id).getBuyNum()));

                    break;
                }
            }
        }
    }
}
