package com.manager.lotterycity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.manager.adapter.CommonAdapter;
import com.manager.adapter.ViewHolder;
import com.manager.common.Constants;
import com.manager.common.Tools;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.view.SearchView;

/**
 * 搜索即开票产品 界面
 * @author donghuiyang
 * @create time 2016/4/20 0020.
 */
public class SearchProductAct extends Activity implements View.OnClickListener{

    //返回按钮
    private View backBtn;

    //根view
    private View rootview;

    //搜索编辑框
    private SearchView searchView;

    private ListView mListView;
    private CommonAdapter<String> mListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_product_layout);

        initTopView();
        initView();
        initListView();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //隐藏软键盘
        Tools.hideSoftInput(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    /**
     * 设置标题
     */
    private void initTopView() {

        ViewGroup topView = (ViewGroup) findViewById(R.id.search_product_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.lottery_city_title1);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    /**
     * 获取控件对象
     */
    private void initView() {
        //搜索栏
        searchView = (SearchView) findViewById(R.id.search_product_searchview);
        searchView.setBgColor(R.color.bg_color_4);
        searchView.setEditorActionListener(onEditorActionListener);
    }

    private void initListView() {
        mListView = (ListView) findViewById(R.id.search_product_listview);
        mListViewAdapter = new CommonAdapter<String>(this, Constants.filterLists, R.layout.list_item_27) {
            @Override
            public void convert(ViewHolder helper, String item) {
                helper.setText(R.id.list_item_27_tv, item);
            }
        };
        mListView.setAdapter(mListViewAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //搜索后，返回列表界面
                SysApplication.backBtn(SearchProductAct.this, null);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //返回按钮
            SysApplication.backBtn(this, null);
        }
    }


    private TextView.OnEditorActionListener onEditorActionListener = new TextView.OnEditorActionListener() {

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				/*判断是否是“SEARCH”键*/
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                /*隐藏软键盘*/
                Tools.hideSoftInput(SearchProductAct.this, rootview);
                return true;
            }
            return false;
        }
    };
}
