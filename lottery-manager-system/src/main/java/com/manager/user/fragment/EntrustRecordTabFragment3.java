package com.manager.user.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.manager.lotterypro.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 待出票 界面
 * Created by Administrator on 2016/3/23 0023.
 */
public class EntrustRecordTabFragment3 extends Fragment {


    //上下文对象
    private Context mContext = null;

    //list控件
    private ListView mListView;

    //生成动态数组，加入数据
    ArrayList<HashMap<String, Object>> mLists = new ArrayList<HashMap<String, Object>>();


    //根视图缓存
    private View rootView = null;
    //静态对象
    private static EntrustRecordTabFragment3 mTabFragment = null;

    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static EntrustRecordTabFragment3 newInstance() {
        if (mTabFragment == null) {
            mTabFragment = new EntrustRecordTabFragment3();
        }

        return mTabFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mLists = null;
        mTabFragment=null;
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
            rootView = inflater.inflate(R.layout.entrust_record_tab,null);//注意不要指定父视图

            initView();
            initListView();
        }

        return rootView;
    }

    private void initView(){

    }

    private void initListView() {

    }
}
