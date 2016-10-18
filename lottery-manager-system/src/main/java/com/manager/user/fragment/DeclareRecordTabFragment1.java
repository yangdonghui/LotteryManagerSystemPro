package com.manager.user.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.manager.adapter.user.DeclareRecordListViewAdapter;
import com.manager.bean.UserDeclareBean;
import com.manager.common.Constants;
import com.manager.lotterypro.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 全部申请界面
 * Created by Administrator on 2016/3/23 0023.
 */
public class DeclareRecordTabFragment1 extends Fragment {


    //上下文对象
    private Context mContext = null;

    //list控件
    private ListView mListView;
    private DeclareRecordListViewAdapter mListViewAdapter;
    //生成动态数组，加入数据
    ArrayList<HashMap<String, Object>> mLists = new ArrayList<HashMap<String, Object>>();
    //list无数据的提示
    private View tipView;

    //根视图缓存
    private View rootView = null;
    //静态对象
    private static DeclareRecordTabFragment1 mDeclareTabFragment = null;

    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static DeclareRecordTabFragment1 newInstance() {
        if (mDeclareTabFragment == null) {
            mDeclareTabFragment = new DeclareRecordTabFragment1();
        }

        return mDeclareTabFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getActivity();

        InitData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mLists = null;
        mListViewAdapter = null;
        mDeclareTabFragment=null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        if (null != rootView) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (null != parent) {
                parent.removeView(rootView);
            }
        } else {
            rootView = inflater.inflate(R.layout.site_record_tab,null);//注意不要指定父视图

            //初始化控件
            initView();
            initListView();

            //mThreadLoad.start();
        }

        return rootView;
    }

    private void initView() {
        tipView = (View) rootView.findViewById(R.id.site_record_tab_listview_no);

        updateTipView();
    }

    private void updateTipView() {
        if (mLists != null && mLists.size() > 0){
            if (tipView.getVisibility() != View.GONE){
                tipView.setVisibility(View.GONE);
            }else {
                tipView.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * 初始化list数据
     */
    private void InitData() {
        //添加数据
        for(int i=0;i< Constants.DeclareLists.size();i++)
        {
            UserDeclareBean att = Constants.DeclareLists.get(i);
            if (att.getDeclareState().equals("未受理")){
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("ItemNumber", String.valueOf(att.getID()));
                map.put("ItemChildType", att.getDeclareChildType());
                map.put("ItemTime", att.getDeclareTime());
                map.put("ItemID", att.getDeclareNumber());
                map.put("ItemState", att.getDeclareState());
                mLists.add(map);
            }
        }
    }

    /**
     * 初始化视图
     */
    private void initListView() {
        mListView = (ListView) rootView.findViewById(R.id.site_record_tab_listview);
        mListViewAdapter = new DeclareRecordListViewAdapter(mContext, mLists); //创建适配器
        mListView.setAdapter(mListViewAdapter);
        mListViewAdapter.notifyDataSetChanged();
    }

    private Handler progresshandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0: {
                    /*((TextView) rootView.findViewById(R.id.textView57)).setVisibility(View.GONE);
                    mListView.setAdapter(mListViewAdapter);
                    mListViewAdapter.notifyDataSetChanged();*/
                }
            }
        }
    };

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            InitData();

            Message msg = new Message();
            msg.what = 0;
            progresshandler.sendMessage(msg);
        }
    };

    private Thread mThreadLoad = new Thread(runnable) ;
}
