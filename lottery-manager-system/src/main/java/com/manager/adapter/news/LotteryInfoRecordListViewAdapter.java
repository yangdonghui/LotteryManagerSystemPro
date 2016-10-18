package com.manager.adapter.news;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.manager.lotterypro.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 开奖信息 历史记录 list视图适配器
 * @author donghuiyang
 * @create time 2016/5/17 0017.
 */
public class LotteryInfoRecordListViewAdapter extends BaseAdapter {

    private ListView mListView;

    private Context context;                            //运行上下文
    private List<HashMap<String, Object>> listItems;    //商品信息集合
    private LayoutInflater listContainer;               //视图容器

    private boolean isFlag = false;
    public int h1 = 0, h2 = 0;

    private final class ViewHolder {
        //期号
        public TextView qihao;
        //事件
        public TextView time;
        //号码
        public ViewGroup numberViewGroup;

        public ViewGroup expandViewGroup;
    }

    public void setListView(ListView listView){
        this.mListView = listView;
    }

    public LotteryInfoRecordListViewAdapter(Context context, List<HashMap<String, Object>> listItems) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (listItems == null) {
            return null;
        }

        //自定义视图
        ViewHolder listItemView = null;
        if (convertView == null) {
            listItemView = new ViewHolder();
            //获取list_item布局文件的视图
            convertView = listContainer.inflate(R.layout.list_item_7, null);
            //获取控件对象
            listItemView.qihao = (TextView) convertView.findViewById(R.id.list_item_7_qihao_tv);
            listItemView.time = (TextView) convertView.findViewById(R.id.list_item_7_time_tv);
            listItemView.numberViewGroup = (ViewGroup) convertView.findViewById(R.id.list_item_7_viewGroup);
            listItemView.expandViewGroup = (ViewGroup) convertView.findViewById(R.id.list_item_7_detail);


            //设置控件集到convertView
            convertView.setTag(listItemView);
        } else {
            listItemView = (ViewHolder) convertView.getTag();
        }

        //扩展部分view
        boolean expand = (Boolean)listItems.get(position).get("ItemExpand");
        if (expand){
            listItemView.expandViewGroup.setVisibility(View.VISIBLE);
        }else{
            listItemView.expandViewGroup.setVisibility(View.GONE);
        }

        //设置文字
        listItemView.qihao.setText((String) listItems.get(position).get("ItemQihao"));
        listItemView.time.setText((String) listItems.get(position).get("ItemTime"));

        if (listItemView.numberViewGroup != null) {
            listItemView.numberViewGroup.removeAllViews();
        }
        int num1 = ((ArrayList<String>) listItems.get(position).get("ItemNumbers1")).size();
        int num2 = ((ArrayList<String>) listItems.get(position).get("ItemNumbers2")).size();
        for (int i = 0; i < num1; i++) {
            View v1 = LayoutInflater.from(context).inflate(R.layout.red_number, null);
            TextView tv1 = (TextView) v1.findViewById(R.id.number_red);

            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.setMargins(3, 0, 0, 0);
            layout.gravity= Gravity.CENTER_VERTICAL;
            v1.setLayoutParams(layout);
            listItemView.numberViewGroup.addView(v1);
            tv1.setText(((ArrayList<String>) listItems.get(position).get("ItemNumbers1")).get(i));
        }

        for (int i = 0; i < num2; i++) {
            View v2 = LayoutInflater.from(context).inflate(R.layout.blue_number, null);
            TextView tv2 = (TextView) v2.findViewById(R.id.number_blue);

            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.gravity= Gravity.CENTER_VERTICAL;
            if (i == 0){
                layout.setMargins(20, 0, 0, 0);
            }else{
                layout.setMargins(3, 0, 0, 0);
            }
            v2.setLayoutParams(layout);
            listItemView.numberViewGroup.addView(v2);
            tv2.setText(((ArrayList<String>) listItems.get(position).get("ItemNumbers2")).get(i));
        }

        if (!isFlag){
            listItemView.expandViewGroup.measure(0,0);
            h2 = listItemView.expandViewGroup.getMeasuredHeight();

            convertView.measure(0,0);
            h1 = convertView.getMeasuredHeight();

            isFlag = true;
        }

        return convertView;
    }

    public void updateSingleRow(int id) {

        if (mListView != null) {
            for (int i = 0; i < mListView.getChildCount(); i++){
                if (i == id){
                    View convertView = mListView.getChildAt(i);
                    ViewHolder holder = (ViewHolder)convertView.getTag();
                    boolean expand = (Boolean)listItems.get(id).get("ItemExpand");
                    if (expand){
                        holder.expandViewGroup.setVisibility(View.VISIBLE);
                    }else{
                        holder.expandViewGroup.setVisibility(View.GONE);
                    }
                    break;
                }
            }
        }
    }
}
