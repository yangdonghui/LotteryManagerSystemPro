package com.manager.adapter.user;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.manager.Interface.ICoallBack7;
import com.manager.adapter.lotterycity.BallsGridviewAdapter;
import com.manager.bean.NoteLotterybean;
import com.manager.common.Tools;
import com.manager.lotterypro.R;

import java.util.HashMap;
import java.util.List;

/**
 * 我的彩票 记录 子list视图适配器
 *
 * @author donghuiyang
 * @create time 2016/5/17 0017.
 */
public class LotteryNumberRecordChildListViewAdapter extends BaseAdapter {
    private ListView listView;

    private Context context;                     //运行上下文
    private List<NoteLotterybean> listItems;    //商品信息集合
    private LayoutInflater listContainer;        //视图容器

    private CheckBox parentCheckbox;
    private int parentIndex;
    private boolean isAllChecked = false;
    public HashMap<Integer, Boolean> isChildSelected;

    private final class ViewHolder {
        public View childCheckBoxParent;
        //复选框
        public CheckBox childCheckBox;

        public TextView childTv1;
        public EditText childEditText;

        public GridView childGridview1;
        public GridView childGridview2;
    }

    /**
     * 初始化接口变量
     */
    ICoallBack7 icallBack = null;

    /**
     * 自定义控件的自定义事件
     *
     * @param iBack 接口类型
     */
    public void setonClick(ICoallBack7 iBack) {
        icallBack = iBack;
    }

    public void setListView(ListView listView){
        this.listView = listView;
    }

    public LotteryNumberRecordChildListViewAdapter(Context context, List<NoteLotterybean> listItems, boolean isAllChecked, CheckBox checkBox, int parentIndex) {
        this.context = context;
        listContainer = LayoutInflater.from(context);   //创建视图容器并设置上下文
        this.listItems = listItems;
        this.isAllChecked = isAllChecked;

        this.parentCheckbox = checkBox;
        this.parentIndex = parentIndex;

        init();
    }

    // 初始化 设置所有checkbox都为未选择
    public void init() {
        isChildSelected = new HashMap<Integer, Boolean>();
        for (int i = 0; i < listItems.size(); i++) {
            isChildSelected.put(i, isAllChecked);
        }
    }

    private boolean checkAllSelected() {
        if (listItems == null) return false;
        int count = 0;
        for (int i = 0; i < listItems.size(); i++) {
            boolean flag = isChildSelected.get(i);
            if (flag) {
                count++;
            } else {
                //主类 复选框 取消选中
                /*LotteryNumberRecordListViewAdapter.isSelected.put(parentIndex, false);
                parentCheckbox.setChecked(false);*/
                return false;
            }
        }

        if (count >= listItems.size()) {
            //子类全部被选中
            /*LotteryNumberRecordListViewAdapter.isSelected.put(parentIndex, true);
            parentCheckbox.setChecked(true);*/
            return true;
        }

        return false;
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
            convertView = listContainer.inflate(R.layout.list_item_17_23, null);
            //获取控件对象
            listItemView.childCheckBoxParent = (View) convertView.findViewById(R.id.list_item_17_23_checkbox_view);
            listItemView.childCheckBox = (CheckBox) convertView.findViewById(R.id.list_item_17_23_checkbox);

            listItemView.childTv1 = (TextView) convertView.findViewById(R.id.list_item_17_23_tv1);
            listItemView.childEditText = (EditText) convertView.findViewById(R.id.list_item_17_23_edit);

            listItemView.childGridview1 = (GridView) convertView.findViewById(R.id.list_item_17_23_gridview1);
            listItemView.childGridview2 = (GridView) convertView.findViewById(R.id.list_item_17_23_gridview2);

            if (listItems.get(position).getDoubleSingleType() == 1) {
                //复式 不能修改注数
                listItemView.childEditText.setFocusable(false);
                listItemView.childEditText.setBackgroundDrawable(null);
                listItemView.childEditText.getLayoutParams().width = 40;
            }

            //设置控件集到convertView
            convertView.setTag(listItemView);
        } else {
            listItemView = (ViewHolder) convertView.getTag();
        }

        final CheckBox checkBox = listItemView.childCheckBox;
        listItemView.childCheckBoxParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isChildSelected.get(position)) {
                    isChildSelected.put(position, false);

                } else if (!isChildSelected.get(position)) {
                    isChildSelected.put(position, true);
                }

                checkBox.setChecked(isChildSelected.get(position));
                //更新全选按钮状态
                icallBack.onClickCheckbox(checkAllSelected());
                //checkAllSelected();
            }
        });

        listItemView.childCheckBox.setChecked(isChildSelected.get(position));
        //金额
        final TextView tvMoney = listItemView.childTv1;
        listItemView.childTv1.setText(String.valueOf(listItems.get(position).getPrice()));
        //注数
        final EditText editText = listItemView.childEditText;
        listItemView.childEditText.setText(String.valueOf(listItems.get(position).getMultiple()));
        listItemView.childEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setFocusable(true);
                editText.setFocusableInTouchMode(true);
                editText.setCursorVisible(true);
                Tools.showSoftInput(context, editText);
            }
        });
        listItemView.childEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.e("", "");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("", "");

                /*if (editText.getText().equals("")){
                    editText.setText("1");
                    //Tools.showToast(context, "注数不能为空!");
                }*/
            }

            @Override
            public void afterTextChanged(Editable s) {
                String currStr = s.toString();
                int zhushu = 1, money = 2 * zhushu;
                if (currStr == null || "".equals(currStr)) {
                    Tools.showToast(context, "注数不能为空!");
                    zhushu = 1;
                } else {
                    zhushu = Integer.valueOf(currStr);
                    money = zhushu * 2;
                }

                listItems.get(position).setMultiple(zhushu);
                listItems.get(position).setPrice(money);

                updateAllRowView(position);
            }
        });
        listItemView.childEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                /*判断是否是“Done”键*/
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    /*隐藏软键盘*/
                    if (editText.getText().toString().equals("")){
                        listItems.get(position).setMultiple(1);
                        editText.setText("1");
                    }

                    Tools.hideSoftInput((Activity)context);

                    return true;
                }
                return false;
            }
        });

        //添加号码
        if (listItems.get(position).getNumbers().getNumbersList1() != null) {
            BallsGridviewAdapter redAdapter = new BallsGridviewAdapter(context, R.layout.red_number1, listItems.get(position).getNumbers().getNumbersList1(), 1);
            listItemView.childGridview1.setAdapter(redAdapter);
        }

        if (listItems.get(position).getNumbers().getNumbersList2() != null) {
            BallsGridviewAdapter blueAdapter = new BallsGridviewAdapter(context, R.layout.red_number1, listItems.get(position).getNumbers().getNumbersList2(), 2);
            listItemView.childGridview2.setAdapter(blueAdapter);
        }

        return convertView;
    }

    /**
     * 更新所有item的checkbox状态
     * @param listView： 父类
     * @param flag: 状态值
     */
    public void updateAllRowCheckBox(ListView listView,boolean flag) {

        if (listView != null) {
            for (int i = 0; i < listView.getChildCount(); i++){
                View convertView = listView.getChildAt(i);
                //getView(i, convertView, listView);
                ViewHolder holder = (ViewHolder) convertView.getTag();
                holder.childCheckBox.setChecked(flag);

                isChildSelected.put(i, flag);
            }
        }
    }
    public void updateAllRowView(int position) {

        if (listView != null) {
            for (int i = 0; i < listView.getChildCount(); i++){
                if (i == position){
                    View convertView = listView.getChildAt(i);
                    ViewHolder holder = (ViewHolder) convertView.getTag();
                    //金额
                    holder.childTv1.setText(String.valueOf(listItems.get(position).getPrice()));
                    //注数
                    //holder.childEditText.setText(String.valueOf(listItems.get(position).getMultiple()));

                    break;
                }
            }
        }
    }
}