package com.manager.adapter.lotterycity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.manager.Interface.ICoallBack10;
import com.manager.Interface.ICoallBack9;
import com.manager.bean.ProductBean;
import com.manager.lotterypro.R;
import com.manager.view.ChangeBuyNumView;

import java.util.List;

/**
 * 即开票 产品 gridview适配器
 * @author http://blog.csdn.net/finddreams
 * @Description:gridview的Adapter
 */
public class BillingGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater listContainer;               //视图容器

    private List<ProductBean> listItems;    //商品信息集合

    private GridView mGridView;

    private final static class ViewHolder {
        //修改数量view
        ChangeBuyNumView changeNumBtn;

        public View itemBtn;
        //图标
        public ImageView icon;

        //标题
        public TextView title;

        //价格
        public TextView price;

        //单位
        public TextView unit;

        //购买按钮
        public Button buyBtn;
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

    public BillingGridViewAdapter(Context context, List<ProductBean> listItems) {
        super();
        this.mContext = context;
        listContainer = LayoutInflater.from(context);   //创建视图容器并设置上下文
        this.listItems = listItems;
    }

    public void setGridView(GridView gridView){
        mGridView = gridView;
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
            convertView = listContainer.inflate(R.layout.grid_item_3, null);
            //获取控件对象
            listItemView.itemBtn = (View) convertView.findViewById(R.id.grid_item_3_view);
            listItemView.icon = (ImageView) convertView.findViewById(R.id.grid_item_3_icon_imgv);
            listItemView.title = (TextView) convertView.findViewById(R.id.grid_item_3_title_tv);
            listItemView.price = (TextView) convertView.findViewById(R.id.grid_item_3_price_tv);
            listItemView.unit = (TextView) convertView.findViewById(R.id.grid_item_3_price_tv1);
            listItemView.buyBtn = (Button) convertView.findViewById(R.id.grid_item_3_buy_btn);

            listItemView.changeNumBtn = (ChangeBuyNumView) (convertView.findViewById(R.id.grid_item_3_changeview));

            //点击进入
            listItemView.itemBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    icallBack.onItemClick(position);
                }
            });

            //设置控件集到convertView
            convertView.setTag(listItemView);
        } else {
            listItemView = (ViewHolder) convertView.getTag();
        }

        //设置
        listItemView.title.setText(listItems.get(position).getTitle());
        listItemView.price.setText(mContext.getResources().getString(R.string.money_str) + listItems.get(position).getPrice());
        listItemView.unit.setText(listItems.get(position).getUnit());

        listItemView.changeNumBtn.setonClick(new ICoallBack9() {
            @Override
            public void modifyNumClick(int value, int btnState) {
                if (btnState == 0){
                    //减号
                    //从清单中清除
                    Log.e("","listItemView.changeNumBtn======remove");
                    icallBack.onSubNum(value,position);
                }else if (btnState == 1){
                    //更新购物车清单（某个商品的数量）
                    Log.e("","listItemView.changeNumBtn======add");
                    icallBack.onAddNum(value,position);
                }


                if (value <= 0) {
                    //从清单中清除
                    Log.e("", "listItemView.changeNumBtn======remove");
                    icallBack.onSubNum(value,position);
                } else {
                    //更新购物车清单（某个商品的数量）
                    Log.e("", "listItemView.changeNumBtn======add");
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

        if (mGridView != null) {
            for (int i = 0; i < mGridView.getChildCount(); i++){
                if (i == id){
                    View convertView = mGridView.getChildAt(i);
                    ViewHolder holder = (ViewHolder) convertView.getTag();

                    holder.changeNumBtn.setNum(Integer.parseInt(listItems.get((int)id).getBuyNum()));

                    break;
                }
            }
        }
    }

}
