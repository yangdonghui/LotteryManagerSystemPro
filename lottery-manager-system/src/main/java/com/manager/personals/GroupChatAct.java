package com.manager.personals;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manager.adapter.personals.SideBarListViewAdapter;
import com.manager.bean.FriendBean;
import com.manager.common.Constants;
import com.manager.common.StringHelper;
import com.manager.common.Tools;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.widgets.SideBarListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;

/**
 * 发起群聊 界面
 * @author donghuiyang
 * @create time 2016/4/20 0020.
 */
public class GroupChatAct extends Activity implements View.OnClickListener{
    //返回按钮
    private View backBtn;

    //群聊人数
    private TextView groupNumTv;

    // 可滑动的显示选中用户的View
    private HorizontalScrollView scrollView;
    private LinearLayout menuLinerLayout;
    //搜索
    private ImageView searchImg;
    private EditText editText;
    private int total = 0;
    private List<String> addList = new ArrayList<String>();

    //listview
    private SideBarListView sideBarListView;

    private List<FriendBean> persons = null;
    private List<FriendBean> newPersons = new ArrayList<FriendBean>();
    private String[] words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_group_chat_layout);

        initTopView();
        initList();

        initView();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //隐藏软键盘
        Tools.hideSoftInput(this);
    }

    @Override
    protected void onDestroy() {
        newPersons.clear();
        newPersons = null;

        super.onDestroy();
    }


    /**
     * 设置标题
     */
    private void initTopView() {
        //topview
        ViewGroup topView = (ViewGroup)findViewById(R.id.launch_group_chat_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.comunity_string7);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);

        groupNumTv = (TextView) findViewById(R.id.launch_group_chat_num_tv);
        groupNumTv.setOnClickListener(this);
    }

    private void initView(){
        scrollView = (HorizontalScrollView) findViewById(R.id.search_horizontalScrollView);
        menuLinerLayout = (LinearLayout) findViewById(R.id.linearLayoutMenu);
        searchImg = (ImageView) findViewById(R.id.search_icon_imgv);
        editText = (EditText) findViewById(R.id.search_layout_editText);
        //搜索栏搜索
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (s.length() > 0) {
                    String str_s = editText.getText().toString().trim();
                    List<FriendBean> user_temp = new ArrayList<FriendBean>();
                    for (FriendBean user : newPersons) {
                        String uesrname = user.getName();
                        if (uesrname.contains(str_s)) {
                            user_temp.add(user);
                        }
                        //adapter = new ListAdapter(MainActivity.this, user_temp);
                        //listView.setAdapter(adapter);
                        sideBarListView.updateListView(user_temp, addList, iCoallBack);
                    }
                } else {
                    /*adapter = new ListAdapter(MainActivity.this, allUserList);
                    listView.setAdapter(adapter);*/
                    sideBarListView.updateListView(newPersons, addList, iCoallBack);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        sideBarListView.getListview().setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(final AdapterView<?> parent, View view,
                                    final int position, long id) {
                final CheckBox checkBox = (CheckBox) view.findViewById(R.id.contact_item_checkBox);
                checkBox.toggle();
            }
        });
    }

    Timer timer=new Timer();
    /**
     * 显示选择的头像
     * @param bitmap
     * @param glufineid
     */
    private void showCheckImage(Bitmap bitmap, FriendBean glufineid) {
        total++;
        // 包含TextView的LinearLayout
        // 参数设置
        android.widget.LinearLayout.LayoutParams menuLinerLayoutParames = new LinearLayout.LayoutParams(
                70, 70);
        View view = LayoutInflater.from(this).inflate(R.layout.header_item, null);
        ImageView images = (ImageView) view.findViewById(R.id.iv_avatar);
        menuLinerLayoutParames.setMargins(6, 6, 6, 6);

        // 设置id，方便后面删除
        view.setTag(glufineid);
        if (bitmap == null) {
            images.setImageResource(R.mipmap.default_icon1);
        } else {
            images.setImageBitmap(bitmap);
        }

        menuLinerLayout.addView(view, menuLinerLayoutParames);
        groupNumTv.setText(getString(R.string.setting_info_btn3) + "(" + total + ")");
        if (total > 0) {
            if (searchImg.getVisibility() == View.VISIBLE) {
                searchImg.setVisibility(View.GONE);
            }

            //清空
            editText.setText("");
        }
        addList.add(glufineid.getName());

        //更新滚动区域
        if (total == 6){
            scrollView.getLayoutParams().width = 70 * 6 + (12*5 + 6*2);
        }else if(total < 6){
            if (total > 0){
                scrollView.getLayoutParams().width = 70 * total + (12*(total-1) + 6);
            }else{
                scrollView.getLayoutParams().width = 0;
            }
        }else{
            //定时器异步更新
            if (total > 6){
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        scrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
                    }
                },100L);
            }
        }
    }

    /**
     * 删除选择的头像
     * @param glufineid
     */
    private void deleteImage(FriendBean glufineid) {
        View view = (View) menuLinerLayout.findViewWithTag(glufineid);

        menuLinerLayout.removeView(view);
        total--;
        groupNumTv.setText(getString(R.string.setting_info_btn3)+"(" + total + ")");
        addList.remove(glufineid.getName());
        if (total < 1) {
            if (searchImg.getVisibility() == View.GONE) {
                searchImg.setVisibility(View.VISIBLE);
            }
        }

        //更新滚动区域
        if (total == 6){
            scrollView.getLayoutParams().width = 70 * 6 + (12*5 + 6*2);
        }else if(total < 6){
            if (total > 0){
                scrollView.getLayoutParams().width = 70 * total + (12*(total-1) + 6);
            }else{
                scrollView.getLayoutParams().width = 0;
            }
        }
    }

    /**
     * 设置list
     */
    private void initList() {

        sideBarListView = (SideBarListView) findViewById(R.id.group_chat_listview);
        setData();
        String[] allNames = sortIndex(persons);
        sortList(allNames);
        sideBarListView.setData(newPersons, words);
        words = null;
        persons = null;
        sideBarListView.setAdapterCallBack(iCoallBack);
        sideBarListView.getListview().setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
    }

    /**
     * 重新排序获得一个新的List集合
     *
     * @param allNames
     */
    private void sortList(String[] allNames) {
        for (int i = 0; i < allNames.length; i++) {
            if (allNames[i].length() != 1) {
                for (int j = 0; j < persons.size(); j++) {
                    if (allNames[i].equals(persons.get(j).getPinYinName())) {
                        FriendBean p = new FriendBean(persons.get(j).getId(),
                                persons.get(j).getName(),
                                persons.get(j).getIconUrl(),
                                persons.get(j).getPinYinName(),
                                persons.get(j).getState());
                        p.setType(1);
                        newPersons.add(p);
                    }
                }
            } else {
                FriendBean p = new FriendBean(allNames[i]);
                p.setType(0);
                newPersons.add(p);
            }
        }
    }

    /**
     * 获取排序后的新数据
     *
     * @param persons
     * @return
     */
    public String[] sortIndex(List<FriendBean> persons) {
        TreeSet<String> set = new TreeSet<String>();
        // 获取初始化数据源中的首字母，添加到set中
        for (FriendBean person : persons) {
            set.add(StringHelper.getPinYinHeadChar(person.getName()).substring(0, 1));
        }
        // 新数组的长度为原数据加上set的大小
        if (words == null) {
            words = new String[set.size()];
        }
        String[] names = new String[persons.size() + set.size()];
        int i = 0;
        for (String string : set) {
            names[i] = string;
            words[i] = string;
            i++;
        }

        String[] pinYinNames = new String[persons.size()];
        for (int j = 0; j < persons.size(); j++) {
            String name = StringHelper.getPingYin(persons.get(j).getName());
            persons.get(j).setPinYinName(name);
            pinYinNames[j] = name;

        }
        // 将原数据拷贝到新数据中
        System.arraycopy(pinYinNames, 0, names, set.size(), pinYinNames.length);
        // 自动按照首字母排序
        Arrays.sort(names, String.CASE_INSENSITIVE_ORDER);
        return names;
    }

    /**
     * 设置模拟数据
     */
    private void setData() {
        persons = new ArrayList<FriendBean>();
        persons.addAll(Constants.cantactLists);

    }

    @Override
    public void onClick(View view) {
        if (view == backBtn){
            SysApplication.backBtn(this, null);
        }
    }

    //列表数据checkbox 回调
    SideBarListViewAdapter.ICoallBack iCoallBack = new SideBarListViewAdapter.ICoallBack() {
        @Override
        public void showCheckImage(Bitmap bitmap, FriendBean user) {
            //添加选中
            GroupChatAct.this.showCheckImage(bitmap, user);
        }

        @Override
        public void deleteImage(FriendBean user) {
            //移除选中
            GroupChatAct.this.deleteImage(user);
        }
    };
}
