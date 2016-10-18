package com.manager.user.manager.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import com.manager.Interface.ICoallBack5;
import com.manager.adapter.user.ManagerConsumableOrdersListViewAdapter;
import com.manager.bean.DeclareOrdersBean;
import com.manager.bean.UserBean;
import com.manager.common.Constants;
import com.manager.helper.UserHelper;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.user.manager.ManagerDeclareOrderDetailAct;

import java.util.ArrayList;

/**
 * 管理员 管理站点申报订单 打包 界面
 * Created by Administrator on 2016/3/23 0023.
 */
public class ManagerConsumableOrdersTabFragment2 extends Fragment {

    private UserBean userBean = SysApplication.userBean;

    //上下文对象
    private Context mContext = null;

    //底部提示栏
    private View bottomTipView;
    private ViewGroup tipViewGroup;
    private Button tipBtn;
    private CheckBox allCheckBox;

    //list控件
    private ListView mListView;
    private ManagerConsumableOrdersListViewAdapter mListViewAdapter;
    //生成动态数组，加入数据
    ArrayList<DeclareOrdersBean> mLists = new ArrayList<>();
    //list无数据的提示
    private View tipView;

    //根视图缓存
    private View rootView = null;
    //静态对象
    private static ManagerConsumableOrdersTabFragment2 mTabFragment = null;

    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static ManagerConsumableOrdersTabFragment2 newInstance() {
        if (mTabFragment == null) {
            mTabFragment = new ManagerConsumableOrdersTabFragment2();
        }

        return mTabFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
        mContext = getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mLists.clear();
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
            updateTipView();

            initListView();

        }

        return rootView;
    }

    private void initView() {
        //底部 全选栏
        bottomTipView = (ViewGroup) rootView.findViewById(R.id.entrust_record_tab_tip_view);
        updateTipView();

        tipView = (View) rootView.findViewById(R.id.entrust_record_tab_listview_no);
        updateListTipView();
    }

    private void updateTipView() {
        if (bottomTipView != null) {
            if (mLists != null && mLists.size() > 0){
                bottomTipView.setVisibility(View.VISIBLE);

                if (userBean.getUserType() == UserHelper.ManagerUser){
                    tipViewGroup = (ViewGroup) rootView.findViewById(R.id.entrust_record_tab_tip_include);
                    tipBtn = (Button) tipViewGroup.findViewById(R.id.entrust_tip1_btn1);
                    tipBtn.setText(R.string.manager_str_02);
                    tipBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //全选 打包完成

                        }
                    });

                    allCheckBox = (CheckBox) tipViewGroup.findViewById(R.id.entrust_tip1_checkBox);
                    allCheckBox.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            boolean isFlag = allCheckBox.isChecked();
                            updateData(isFlag);
                        }
                    });
                }

            }else {
                bottomTipView.setVisibility(View.GONE);
            }
        }
    }

    private void updateData(boolean isChecked) {
        for (int i = 0; i < mLists.size(); i++) {
            ManagerConsumableOrdersListViewAdapter.isSelected.put(i, isChecked);
        }

        mListViewAdapter.notifyDataSetChanged();
    }

    private boolean isAllCheck() {
        int count = 0;
        for (int i = 0; i < mLists.size(); i++) {
            if (ManagerConsumableOrdersListViewAdapter.isSelected.get(i)){
                count ++;
            }
        }

        if (count >= mLists.size()){
            return true;
        }

        return false;
    }

    private void updateListTipView() {
        if (mLists != null && mLists.size() > 0){
            if (tipView.getVisibility() != View.GONE){
                tipView.setVisibility(View.GONE);
            }else {
                tipView.setVisibility(View.VISIBLE);
            }
        }
    }

    private void initData() {
        for (int i=0;i<Constants.ManagerOrderLists.size();i++){
            DeclareOrdersBean att = Constants.ManagerOrderLists.get(i);
            if (att != null && att.getState() == 0){
                mLists.add(att);
            }
        }
    }

    private void initListView() {
        mListView = (ListView) rootView.findViewById(R.id.entrust_record_tab_listview);
        mListViewAdapter = new ManagerConsumableOrdersListViewAdapter(mContext, mLists, 1);
        mListView.setAdapter(mListViewAdapter);

        mListViewAdapter.setonClick(new ICoallBack5() {
            @Override
            public void onClickCheckbox(boolean flag) {
                if (bottomTipView != null) {
                    if (!flag && allCheckBox.isChecked()) {
                        allCheckBox.setChecked(false);
                    } else if (!allCheckBox.isChecked() && isAllCheck()) {
                        allCheckBox.setChecked(true);
                    }

                    bottomTipView.invalidate();
                }
            }

            @Override
            public void doConfirm(int pos) {
                //确定 按钮

            }

            @Override
            public void doFaild(int pos) {
                //失败按钮  无

            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, ManagerDeclareOrderDetailAct.class);
                intent.putExtra("data", mLists.get(position));
                startActivity(intent);
            }
        });
    }
}
