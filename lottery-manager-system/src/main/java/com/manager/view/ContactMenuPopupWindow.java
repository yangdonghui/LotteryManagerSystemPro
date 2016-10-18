package com.manager.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.manager.adapter.CommonAdapter;
import com.manager.adapter.ViewHolder;
import com.manager.bean.WidgetBean1;
import com.manager.lotterypro.R;

import java.util.List;

/**
 * 地址三级联动弹出界面
 * @author donghuiyang
 * @create time 2016/6/21 0021.
 */
public class ContactMenuPopupWindow extends PopupWindow{

    private Context context;

    private View mMenuView;
    private ListView listview;
    private CommonAdapter<WidgetBean1> adapter;

    private List<WidgetBean1> lists;

    private AdapterView.OnItemClickListener listener = null;

    public ContactMenuPopupWindow(Activity context, List<WidgetBean1> lists, AdapterView.OnItemClickListener listener) {
        super(context);

        this.context = context;
        this.lists = lists;
        this.listener = listener;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.contact_menu_layout, null);

        initList();
        init();

    }

    private void init() {
        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(250);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        //this.setAnimationStyle(R.style.popupAnimation);

        this.setOutsideTouchable(true);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.update();
    }

    private void initList(){
        listview = (ListView) mMenuView.findViewById(R.id.contact_menu_listview);
        adapter = new CommonAdapter<WidgetBean1>(context, lists, R.layout.list_item_29) {
            @Override
            public void convert(ViewHolder helper, WidgetBean1 item) {
                helper.setText(R.id.list_item_29_tv1, item.getText());
                helper.setImageResource(R.id.list_item_29_icon, item.getIcon());
            }
        };

        listview.setAdapter(adapter);
        listview.setFocusableInTouchMode(true);
        listview.setFocusable(true);
        listview.setOnItemClickListener(listener);

    }
}
