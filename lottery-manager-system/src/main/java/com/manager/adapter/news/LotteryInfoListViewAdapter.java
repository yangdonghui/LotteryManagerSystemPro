package com.manager.adapter.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manager.bean.LotteryInfoBean;
import com.manager.lotterypro.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 开奖信息主界面 list视图适配器
 * @author donghuiyang
 * @create time 2016/5/17 0017.
 */
public class LotteryInfoListViewAdapter extends BaseAdapter {

    private Context context;                            //运行上下文
    private List<LotteryInfoBean> listItems;    //商品信息集合
    private LayoutInflater listContainer;               //视图容器

    //tab索引
    private int tabType = 0;

    private final class ViewHolder {
        //图标
        public ImageView icon;
        //期号
        public TextView qihao;
        //事件
        public TextView time;
        //号码
        public ViewGroup numberViewGroup;

        //中奖总金额
        public TextView totalMoneyTv;
    }

    public LotteryInfoListViewAdapter(Context context, List<LotteryInfoBean> listItems, int tabType) {
        this.context = context;
        listContainer = LayoutInflater.from(context);   //创建视图容器并设置上下文
        this.listItems = listItems;
        this.tabType = tabType;
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
        //自定义视图
        ViewHolder listItemView = null;
        if (convertView == null) {
            listItemView = new ViewHolder();
            if (tabType == 0){
                //开奖信息

                //获取list_item布局文件的视图
                convertView = listContainer.inflate(R.layout.list_item_6_1, null);
                //获取控件对象
                listItemView.icon = (ImageView) convertView.findViewById(R.id.list_item_6_icon_imageview);
                listItemView.qihao = (TextView) convertView.findViewById(R.id.list_item_6_qihao_tv);
                listItemView.time = (TextView) convertView.findViewById(R.id.list_item_6_time_tv);
                listItemView.numberViewGroup = (ViewGroup) convertView.findViewById(R.id.list_item_6_viewGroup1);
            }else if (tabType == 1){
                //中奖信息

                //获取list_item布局文件的视图
                convertView = listContainer.inflate(R.layout.list_item_6_2, null);
                //获取控件对象
                listItemView.icon = (ImageView) convertView.findViewById(R.id.list_item_6_icon_imageview);
                listItemView.qihao = (TextView) convertView.findViewById(R.id.list_item_6_qihao_tv);
                listItemView.time = (TextView) convertView.findViewById(R.id.list_item_6_time_tv);
                listItemView.totalMoneyTv = (TextView) convertView.findViewById(R.id.list_item_6_total_money_tv);
            }

            //设置控件集到convertView
            assert convertView != null;
            convertView.setTag(listItemView);
        } else {
            listItemView = (ViewHolder) convertView.getTag();
        }

        //设置文字
        listItemView.qihao.setText((String) listItems.get(position).getLotteryQihao());
        listItemView.time.setText((String) listItems.get(position).getLotteryTime());

        if (tabType == 0){
            int type = (Integer)listItems.get(position).getLotteryType();
            if (type == 0){
                //双色球
                listItemView.icon.setImageResource(R.drawable.lottery_info_nav_01);
            }else if (type == 1){
                //大乐透
                listItemView.icon.setImageResource(R.drawable.lottery_info_nav_02);
            }
            //开奖信息
            if (listItemView.numberViewGroup != null) {
                listItemView.numberViewGroup.removeAllViews();
            }
            int num1 = ((ArrayList<String>) listItems.get(position).getLotteryNumbers1()).size();
            int num2 = ((ArrayList<String>) listItems.get(position).getLotteryNumbers2()).size();
            for (int i = 0; i < num1; i++) {
                View v1 = LayoutInflater.from(context).inflate(R.layout.red_number, null);
                TextView tv1 = (TextView) v1.findViewById(R.id.number_red);

                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layout.setMargins(3, 0, 0, 0);
                v1.setLayoutParams(layout);
                listItemView.numberViewGroup.addView(v1);
                tv1.setText(((ArrayList<String>) listItems.get(position).getLotteryNumbers1()).get(i));
            }

            for (int i = 0; i < num2; i++) {
                View v2 = LayoutInflater.from(context).inflate(R.layout.blue_number, null);
                TextView tv2 = (TextView) v2.findViewById(R.id.number_blue);

                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                if (i == 0){
                    layout.setMargins(20, 0, 0, 0);
                }else{
                    layout.setMargins(3, 0, 0, 0);
                }
                v2.setLayoutParams(layout);
                listItemView.numberViewGroup.addView(v2);
                tv2.setText(((ArrayList<String>) listItems.get(position).getLotteryNumbers2()).get(i));
            }
        }else if (tabType == 1){
            //中奖信息 总中奖金额

        }

        return convertView;
    }
}
