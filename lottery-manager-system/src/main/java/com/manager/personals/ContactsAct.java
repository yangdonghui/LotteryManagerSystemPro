package com.manager.personals;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.manager.bean.FriendBean;
import com.manager.bean.WidgetBean1;
import com.manager.common.Constants;
import com.manager.common.StringHelper;
import com.manager.common.Tools;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.wallet.WalletTransferAccountsAct;
import com.manager.widgets.SideBarListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/**
 * 通讯录界面
 * @author donghuiyang
 * @create time 2016/4/20 0020.
 */
public class ContactsAct extends Activity implements OnClickListener{

    //返回按钮
    private View backBtn;

    //添加好友按钮
    private View addFriendBtn;

    private List<FriendBean> persons = null;
    private List<FriendBean> newPersons = new ArrayList<FriendBean>();
    private String[] words;

    SideBarListView sideBarListView;

    //从哪页面来
    private int fromPage = 0;

    private static List<WidgetBean1> initLists = new ArrayList<WidgetBean1>(){
        {
            add(new WidgetBean1("0", R.mipmap.contact_icon1, "新的朋友"));
            add(new WidgetBean1("1", R.mipmap.contact_icon3, "群聊"));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_layout);

        Intent intent = getIntent();
        fromPage = intent.getIntExtra("pageType", fromPage);

        initView();
        initList();
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
    private void initView() {
        //topview
        ViewGroup topView = (ViewGroup)findViewById(R.id.contact_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.contacts_info_1);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);

        addFriendBtn = (View) findViewById(R.id.contact_add_btn);
        addFriendBtn.setOnClickListener(this);
    }

    /**
     * 设置list
     */
    private void initList() {

        sideBarListView = (SideBarListView) findViewById(R.id.contact_listview2);
        sideBarListView.setChildView(R.layout.search_view_layout);
        setData();
        String[] allNames = sortIndex(persons);
        sortList(allNames);
        sideBarListView.setData(newPersons, words);
        words = null;
        persons = null;

        sideBarListView.setItemClickListener(listViewItemClickListener);
    }

    /**
     * 重新排序获得一个新的List集合
     *
     * @param allNames
     */
    private void sortList(String[] allNames) {
        //添加默认item
        for (WidgetBean1 att:initLists){
            FriendBean p = new FriendBean(att.getText());
            p.setDefaultIcon(att.getIcon());
            newPersons.add(p);
        }

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
    public void onClick(View v) {
        if (backBtn == v) {
            //返回按钮
            SysApplication.backBtn(this, null);
        }else if(addFriendBtn == v) {
            //添加好友 入口

            SysApplication.enterNext1(this, AddFriendAct.class);
        }
    }

    //查看好友详细资料
    private AdapterView.OnItemClickListener listViewItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.e("", "");
            FriendBean friend = (FriendBean)sideBarListView.getItem(position);
            if (friend != null){
                if (friend.getType() == -1){
                    if (position == 0){
                        //新的朋友
                        SysApplication.enterNext1(ContactsAct.this, NewFriendAct.class);
                    }else if (position == 1){
                        //群聊
                    }
                }else{
                    if (fromPage == 1){
                        //选择转账好友
                        Intent intent = new Intent(ContactsAct.this, WalletTransferAccountsAct.class);
                        intent.putExtra("data", friend);
                        startActivity(intent);

                    }else{
                        //查看好友详细数据
                        Intent intent = new Intent(ContactsAct.this, FriendInfoAct.class);
                        intent.putExtra("data", friend);
                        startActivity(intent);
                    }
                }
            }
        }
    };
}
