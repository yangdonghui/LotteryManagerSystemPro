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
import com.manager.Interface.ICoallBack2;
import com.manager.Interface.ICoallBack9;
import com.manager.bean.ProductBean;
import com.manager.lotterypro.R;
import com.manager.view.ChangeBuyNumView;

import java.util.List;

/**
 * 购物车 listview适配器
 *
 * @author http://blog.csdn.net/finddreams
 * @Description:gridview的Adapter
 */
public class CartListViewAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater listContainer;               //视图容器

    private List<ProductBean> listItems;    //商品信息集合

    private ListView mListView;

    private final static class ViewHolder {
        //修改数量view
        ChangeBuyNumView changeNumBtn;
        /*View subNumBtn;
        View addNumBtn;
        EditText numTv;*/

        public View removeBtn;
        //标题
        public TextView title;
        //面值
        public TextView value;
        public TextView valueIInfo;
        public TextView valueUnit;

        //价格
        public TextView price;
        //单位
        public TextView unit;
    }

    //初始化接口变量 删除操作
    ICoallBack2 icallBack = null;
    //初始化接口变量 修改数量
    ICoallBack10 icallBack1 = null;
    /**
     * 自定义控件的自定义事件
     * @param iBack 接口类型
     */
    public void setonClick(ICoallBack2 iBack)
    {
        icallBack = iBack;
    }
    public void setonClick1(ICoallBack10 iBack)
    {
        icallBack1 = iBack;
    }

    public CartListViewAdapter(Context context, List<ProductBean> listItems) {
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
            convertView = listContainer.inflate(R.layout.list_item_13, null);
            //获取控件对象
            listItemView.changeNumBtn = (ChangeBuyNumView) convertView.findViewById(R.id.list_item_13_changeview);
            listItemView.removeBtn = (View) convertView.findViewById(R.id.list_item_13_remove_btn);
            listItemView.title = (TextView) convertView.findViewById(R.id.list_item_13_title_tv);
            listItemView.value = (TextView) convertView.findViewById(R.id.list_item_13_value_tv);
            listItemView.valueIInfo = (TextView) convertView.findViewById(R.id.list_item_13_value_tv2);
            listItemView.valueUnit = (TextView) convertView.findViewById(R.id.list_item_13_value_tv1);
            listItemView.price = (TextView) convertView.findViewById(R.id.list_item_13_price_tv1);
            listItemView.unit = (TextView) convertView.findViewById(R.id.list_item_13_price_tv33);

            //点击进入
            listItemView.removeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    icallBack.removeBtn(position);
                }
            });

            //设置控件集到convertView
            convertView.setTag(listItemView);
        } else {
            listItemView = (ViewHolder) convertView.getTag();
        }

        listItemView.title.setText(listItems.get(position).getTitle());
        listItemView.value.setText(listItems.get(position).getValue());
        listItemView.price.setText(mContext.getResources().getString(R.string.money_str) + listItems.get(position).getPrice());

        int type = listItems.get(position).getType();
        if (type == 2){
            //耗材
            listItemView.valueUnit.setText(listItems.get(position).getUnit());
        }else{
            //即开票
            listItemView.valueUnit.setText("元");
            listItemView.valueIInfo.setText("面值");
        }

        int buyNum = Integer.parseInt(listItems.get(position).getBuyNum());
        listItemView.changeNumBtn.setNum(buyNum);
        listItemView.changeNumBtn.setonClick(new ICoallBack9() {
            @Override
            public void modifyNumClick(int value, int btnState) {
                if (btnState == 0) {
                    //减号
                    //从清单中清除
                    Log.e("", "listItemView.changeNumBtn======remove");
                    icallBack1.onSubNum(value,position);
                } else if (btnState == 1) {
                    //更新购物车清单（某个商品的数量）
                    Log.e("", "listItemView.changeNumBtn======add");
                    icallBack1.onAddNum(value,position);
                }


                if (value <= 0) {
                    //从清单中清除
                    Log.e("", "listItemView.changeNumBtn======remove");
                    icallBack1.onSubNum(value,position);
                } else {
                    //更新购物车清单（某个商品的数量）
                    Log.e("", "listItemView.changeNumBtn======add");
                    icallBack1.onAddNum(value,position);
                }
            }
        });

        Log.e("", "=============listItemView.changeNumBtn=====================");

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

                    break;
                }
            }
        }
    }

}
