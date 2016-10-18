package com.manager.personals;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.manager.adapter.personals.ChatListViewAdapter;
import com.manager.bean.UserBean;
import com.manager.common.Constants;
import com.manager.common.Tools;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.view.ContactMenuPopupWindow;
import com.manager.view.SearchView;

/**
 * 私聊界面
 * @author donghuiyang
 * @create time 2016/4/20 0020.
 */
public class PrivateChatAct extends Activity implements View.OnClickListener{

    //用户属性
    private UserBean userBean = SysApplication.userBean;

    //弹出框
    private ContactMenuPopupWindow menuWindow;

    //根view
    private View rootView;
    private ViewGroup topView;

    //返回按钮
    private View backBtn;

    //通讯录按钮
    private View mContactsBtn;

    //搜索编辑框
    private SearchView searchView;

    //listview
    private ListView mListView;
    private ChatListViewAdapter mListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.private_chat_layout);

        //初始化控件 并 设置标题
        initTopView();
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
    protected void onResume() {
        super.onResume();

        if (mListViewAdapter != null){
            mListViewAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 设置标题
     */
    private void initTopView() {
        rootView = (View) findViewById(R.id.private_chat_rootview);

        //topview
        topView = (ViewGroup) findViewById(R.id.private_chat_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.friend_str0);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    /**
     * 初始化view
     */
    private void initView(){
        //通讯录按钮
        mContactsBtn = (View) findViewById(R.id.private_chat_contacts_btn);
        mContactsBtn.setOnClickListener(this);

        //搜索栏
        searchView = (SearchView) findViewById(R.id.private_chat_search_view);
        searchView.setEditorActionListener(onEditorActionListener);
    }

    private void initList(){
        mListView = (ListView) findViewById(R.id.private_chat_listview);
        mListViewAdapter = new ChatListViewAdapter(this, Constants.chatLists);
        mListView.setAdapter(mListViewAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int state = Constants.chatLists.get(position).getState();
                if (state == 0) {
                    //更新阅读状态
                    Constants.chatLists.get(position).setState(1);
                }
                SysApplication.enterNext1(PrivateChatAct.this, ChattingAct.class);
            }
        });
    }

    /**
     * 选项弹出框
     */
    private void popWindow() {
        Tools.hideSoftInput(this);

        if (menuWindow != null){
            menuWindow.dismiss();
            menuWindow = null;
        }

        //实例化
        menuWindow = new ContactMenuPopupWindow(this, Constants.ContactMenuLists, listener);
        //显示窗口
        showAsDropDown();
    }

    public void showAsDropDown() {
        int xPos = -menuWindow.getWidth() / 2 + topView.getWidth();

        menuWindow.showAsDropDown(topView, xPos, 4);

        //menuWindow.showAtLocation(mContactsBtn, Gravity.RIGHT, 0, 0);
        //menuWindow.showAtLocation(mContactsBtn, Gravity.NO_GRAVITY, location[0]+mContactsBtn.getWidth(), location[1] - 100);
    }

    AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            switch (i){
                case 0:{
                    //通讯录
                    SysApplication.enterNext1(PrivateChatAct.this, ContactsAct.class);
                }
                break;
                case 1:{
                    //添加好友
                    SysApplication.enterNext1(PrivateChatAct.this, AddFriendAct.class);
                }
                break;
                case 2:{
                    //发起群聊
                    SysApplication.enterNext1(PrivateChatAct.this, GroupChatAct.class);
                }
                break;
                default:
                    break;
            }

            menuWindow.dismiss();
        }
    };

    @Override
    public void onClick(View v) {
        if (backBtn == v) {
            //返回按钮
            SysApplication.backBtn(this, null);

        }else if (mContactsBtn == v){
            //弹出菜单
            popWindow();
        }
    }

    private TextView.OnEditorActionListener onEditorActionListener = new TextView.OnEditorActionListener() {

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            /*判断是否是“SEARCH”键*/
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                /*隐藏软键盘*/
                Tools.hideSoftInput(PrivateChatAct.this, rootView);
                return true;
            }
            return false;
        }
    };
}
