package com.manager.adapter.user;

import android.content.Context;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manager.bean.BankBean;
import com.manager.lotterypro.R;
import com.manager.widgets.LetterSpacingTextView;

import java.util.List;

/**
 * 银行卡管理 list视图适配器
 * @author donghuiyang
 * @create time 2016/5/17 0017.
 */
public class BankManagerListViewAdapter extends BaseAdapter {

    private Context context;                            //运行上下文
    private List<BankBean> listItems;    //商品信息集合
    private LayoutInflater listContainer;               //视图容器

    private final class ViewHolder {
        public View bgImgv;
        public ImageView bigImgv;
        public ImageView smallImgv;

        public TextView tv1;
        public TextView tv2;
        public ViewGroup numberViewGroup;
    }

    public BankManagerListViewAdapter(Context context, List<BankBean> listItems) {
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
            convertView = listContainer.inflate(R.layout.bank_list_item, null);
            //获取控件对象
            listItemView.bgImgv = (View) convertView.findViewById(R.id.bank_list_item_imgv1);
            listItemView.bigImgv = (ImageView) convertView.findViewById(R.id.bank_list_item_imgv2);
            listItemView.smallImgv = (ImageView) convertView.findViewById(R.id.bank_list_item_smallicon_imgv);

            listItemView.tv1 = (TextView) convertView.findViewById(R.id.bank_list_item_tv1);
            listItemView.tv2 = (TextView) convertView.findViewById(R.id.bank_list_item_tv2);
            listItemView.numberViewGroup = (ViewGroup) convertView.findViewById(R.id.bank_list_item_number_view);

            //设置控件集到convertView
            convertView.setTag(listItemView);
        } else {
            listItemView = (ViewHolder) convertView.getTag();
        }

        //设置文字
        listItemView.tv1.setText(listItems.get(position).getName());
        listItemView.tv2.setText(listItems.get(position).getTypeInfo());

        int bgImage = -1;
        switch (listItems.get(position).getBgColor()){
            case 1:{
                bgImage = R.drawable.rect_radius_30_1;
            }
            break;
            case 2:{
                bgImage = R.drawable.rect_radius_30_2;
            }
            break;
            case 3:{
                bgImage = R.drawable.rect_radius_30_3;
            }
            break;
            default:
                break;
        }
        listItemView.bgImgv.setBackgroundResource(bgImage);
        listItemView.bigImgv.setImageResource(listItems.get(position).getBigIcon());
        listItemView.smallImgv.setImageResource(listItems.get(position).getSmallIcon());

        listItemView.numberViewGroup.removeAllViews();

        String number = listItems.get(position).getNumber();
        int size = number.length() / 4;
        for (int i=0;i<4;i++){
            int start = i * 4;
            int end = start + 4;
            String sub = number.substring(start, end);
            View v = LayoutInflater.from(context).inflate(R.layout.bank_number_layout, null);
            LetterSpacingTextView tv = (LetterSpacingTextView) v.findViewById(R.id.bank_number_tv);
            tv.setLetterSpacing(10); //参数为 float 类型。可另设其他值如 0 或者默认值 LetterSpacingTextView.LetterSpacing.NORMAL
            tv.setText(sub);

            if (i < 3){
                TextPaint tp = tv.getPaint();
                tp.setFakeBoldText(true);
            }

            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            if (i != 0){
                layout.setMargins(30, 0, 0, 0);
            }
            layout.gravity= Gravity.CENTER_VERTICAL;
            v.setLayoutParams(layout);
            listItemView.numberViewGroup.addView(v);

        }

        return convertView;
    }
}