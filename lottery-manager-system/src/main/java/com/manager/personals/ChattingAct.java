package com.manager.personals;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.manager.bean.FriendBean;
import com.manager.chat.helper.ChatMsgEntity;
import com.manager.chat.helper.ChatMsgViewAdapter;
import com.manager.common.Tools;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.view.InputView;

import java.util.ArrayList;

/**
 * 与 ...  聊天中 界面
 * @author donghuiyang
 * @create time 2016/5/27 0027.
 */
public class ChattingAct extends Activity implements View.OnClickListener{

    private String[] msgArray = new String[] { "有大吗", "有！你呢？", "我也有", "那上吧",
            "打啊！你放大啊！", "你咋不放大呢？留大抢人头啊？嗨！", "不解释", "妈呀...",
            "今晚去网吧包夜吧？", "有人吗？", "种子一大堆啊~还怕没片？", "OK,搞起！！" };

    private String[] dataArray = new String[] { "2016-05-22 18:00:02",
                                                "2016-05-22 18:10:22", "2012-05-22 18:11:24",
                                                "2016-05-22 18:20:23", "2012-05-22 18:30:31",
                                                "2016-05-22 18:35:37", "2012-05-22 18:40:13",
                                                "2016-05-22 18:50:26", "2012-05-22 18:52:57",
                                                "2016-05-22 18:55:11", "2012-05-22 18:56:45",
                                                "2016-05-22 18:57:33", };
    private final static int COUNT = 12;// 初始化数组总数

    //好友数据
    private FriendBean friendData;

    //返回按钮
    private View backBtn;

    //输入栏
    private InputView inputView;


    private ListView mListView;
    private ChatMsgViewAdapter mAdapter;// 消息视图的Adapter
    private ArrayList<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>();// 消息对象数组


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_layout);

        friendData = (FriendBean)getIntent().getSerializableExtra("data");

        initTopView();
        initChatView();

        initData();// 初始化数据
        mListView.setSelection(mAdapter.getCount() - 1);
    }

    @Override
    protected void onPause() {
        super.onPause();

        Tools.hideSoftInput(this, getCurrentFocus());
    }

    /**
     * 设置标题
     */
    private void initTopView() {
        //topview
        ViewGroup topView = (ViewGroup)findViewById(R.id.chat_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        if (friendData != null){
            titleView.setText(friendData.getName());
        }else{
            titleView.setText("小小");
        }
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    /**
     * 模拟加载消息历史，实际开发可以从数据库中读出
     */
    public void initData() {
        for (int i = 0; i < COUNT; i++) {
            ChatMsgEntity entity = new ChatMsgEntity();
            entity.setDate(dataArray[i]);
            if (i % 2 == 0) {
                entity.setName("肖B");
                entity.setMsgType(true);// 收到的消息
            } else {
                entity.setName("必败");
                entity.setMsgType(false);// 自己发送的消息
            }
            entity.setMessage(msgArray[i]);
            mDataArrays.add(entity);
        }

        mAdapter = new ChatMsgViewAdapter(this, mDataArrays);
        mListView.setAdapter(mAdapter);
    }

    /**
     * 初始化view
     */
    public void initChatView() {
        mListView = (ListView) findViewById(R.id.chatting_listview);
        inputView = (InputView) findViewById(R.id.chat_input_view);
        inputView.setonClick(new InputView.ICoallBack() {
            @Override
            public void onSend(String text) {
                //发送消息
                send(text);
            }
        });
    }

    /**
     * 发送消息
     */
    private void send(String contString) {
        if (contString.length() > 0) {
            ChatMsgEntity entity = new ChatMsgEntity();
            entity.setName("必败");
            entity.setDate(Tools.getDate());
            entity.setMessage(contString);
            entity.setMsgType(false);

            mDataArrays.add(entity);
            mAdapter.notifyDataSetChanged();// 通知ListView，数据已发生改变

            inputView.setEditTextContent("");// 清空编辑框数据

            mListView.setSelection(mListView.getCount() - 1);// 发送一条消息时，ListView显示选择最后一项
        }
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v) {
            //返回按钮
            SysApplication.backBtn(this, null);
        }
    }
}
