package com.manager.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.manager.adapter.user.AddressListViewAdapter;
import com.manager.bean.AddressBean;
import com.manager.common.Constants;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 用户 地址管理界面
 * @author donghuiyang
 * @create time 2016/5/31 0031.
 */
public class UserAddressManagerAct extends Activity implements View.OnClickListener{
    //返回按钮
    private View backBtn;

    //新增地址按钮
    private Button addBtn;

    //listview
    private ListView mListView;
    private AddressListViewAdapter mListViewAdapter;
    ArrayList<HashMap<String, Object>> mLists = new ArrayList<HashMap<String, Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_address_manager_layout);

        initData();

        initTopView();
        initView();
        initListView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mLists.clear();
        mLists = null;
    }

    /**
     * 设置标题
     */
    private void initTopView() {
        ViewGroup topView = (ViewGroup) findViewById(R.id.user_address_manager_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.address_manager_title);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    private void initView() {
        addBtn = (Button) findViewById(R.id.address_manager_add_btn);
        addBtn.setOnClickListener(this);
    }

    private void initData() {
        for (int i=0;i< Constants.AddressLists.size();i++){
            AddressBean att = Constants.AddressLists.get(i);
            if (att != null){
                HashMap<String, Object> map = new HashMap<String, Object>();
                if (map != null){
                    map.put("ItemName", att.getName());
                    map.put("ItemPhone", att.getPhone());
                    map.put("ItemContent", att.getAddressContent());

                    map.put("ItemAddress1", att.getAddress1());
                    map.put("ItemAddress2", att.getAddress2());
                    map.put("ItemAddress3", att.getAddress3());
                    mLists.add(map);
                }
            }
        }
    }

    private void initListView() {
        mListView = (ListView) findViewById(R.id.user_address_manager_listview);
        mListViewAdapter = new AddressListViewAdapter(this, mLists);
        mListView.setAdapter(mListViewAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(UserAddressManagerAct.this, UserEditShippingAddressAct.class);

                HashMap<String, Object> map = mLists.get(position);
                intent.putExtra("data", map);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //返回按钮
            SysApplication.backBtn(this, null);
        }else if (addBtn == v){
            //新增地址按钮
            Intent intent = new Intent(UserAddressManagerAct.this, UserEditShippingAddressAct.class);
            startActivity(intent);
        }
    }
}
