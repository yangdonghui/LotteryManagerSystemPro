package com.manager.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.manager.adapter.personals.SideBarListViewAdapter;
import com.manager.bean.FriendBean;
import com.manager.lotterypro.R;

import java.util.HashMap;
import java.util.List;


public class SideBarListView extends FrameLayout {

    private Context context;

    private int fromType = -1;

    private ScrollView scrollView;
    private int scrollTop, scrollBottom;

    private ViewStub childViewStub;
    private View subView;
    private ListView listview;
    private SideBarListViewAdapter adapter = null;

    private TextView tv_dialog;
    public SideBar sidebar;
    private String[] indexStr = null;

    private List<FriendBean> newPersons;
    private HashMap<String, Integer> selector;// 存放含有索引字母的位置

    public SideBarListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.layout_listview_sidebar, this);

        this.context = context;
        initView();

        TypedArray styled = getContext().obtainStyledAttributes(attrs, R.styleable.SideBarListView);
        fromType = styled.getInt(R.styleable.SideBarListView_fromType, fromType);
    }

    public void setChildView(int layoutId){
        childViewStub = (ViewStub) findViewById(R.id.sider_viewStub);
        childViewStub.setLayoutResource(layoutId);
        subView = childViewStub.inflate();

        Log.e("", "");
    }

    private void initView() {
        scrollView = (ScrollView)findViewById(R.id.sidebar_scrollview);

        //通讯录数据
        listview = (ListView) findViewById(R.id.listview);
        tv_dialog = (TextView) findViewById(R.id.tv_dialog);
        sidebar = (SideBar) findViewById(R.id.sidebar);
        sidebar.setTextViewDialog(tv_dialog);

        /**
         * 根据用户点击那个字母将listview移动到相应位置
         */
        sidebar.setOnTouchingLetterChangedListener(new SideBar.ITouchingLetterChangedListener() {

            @Override
            public void OnTouchingLetterChanged(String cString) {
                if (cString.length() > 0) {
                    if (selector.containsKey(cString)) {
                        int pos = selector.get(cString);
                        /*if (listview.getHeaderViewsCount() > 0) {// 防止ListView有标题栏，本例中没有。
                            listview.setSelectionFromTop(pos + listview.getHeaderViewsCount(), 0);
                        } else {
                            listview.setSelectionFromTop(pos, 0);// 滑动到第一项
                        }*/


                        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                        if (subView == null) {
                            height = 0;
                        } else {
                            subView.measure(width, height);
                            height = subView.getMeasuredHeight();
                        }

                        float y = listview.getChildAt(pos).getY();
                        scrollView.scrollTo(0, (int) y + height);

                    } else if (cString.equals("#")) {
                        // 滑动到第一项
                        scrollView.fullScroll(ScrollView.FOCUS_UP);

                        /*if (listview.getHeaderViewsCount() > 0) {// 防止ListView有标题栏，本例中没有。
                            listview.setSelectionFromTop(0 + listview.getHeaderViewsCount(), 0);
                        } else {
                            listview.setSelectionFromTop(0, 0);// 滑动到第一项
                        }*/
                    }
                }
            }
        });
    }

    public void setData(List<FriendBean> persons , String[] words) {
        newPersons = persons;
        //indexStr = sidebar.words;
        indexStr = words;
        sidebar.setWords(indexStr);

        selector = new HashMap<String, Integer>();
        for (int j = 0; j < indexStr.length; j++) {// 循环字母表，找出newPersons中对应字母的位置
            for (int i = 0; i < newPersons.size(); i++) {
                if (newPersons.get(i).getName().equals(indexStr[j])) {
                    selector.put(indexStr[j], i);
                }
            }
        }

        adapter = new SideBarListViewAdapter(context, newPersons, null, fromType);
        listview.setAdapter(adapter);

        scrollTop = scrollView.getTop();
        scrollBottom = scrollView.getBottom();
    }

    /**
     * 添加listview单击事件监听
     * @param itemClickListener
     */
    public void setItemClickListener(AdapterView.OnItemClickListener itemClickListener) {
        if (listview == null) return;

        listview.setOnItemClickListener(itemClickListener);
    }

    public Object getItem(int position) {
        if (listview == null || adapter == null) return null;

        return adapter.getItem(position);
    }

    public SideBarListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SideBarListView(Context context) {
        this(context, null);
    }

    public SideBarListViewAdapter getMyAdapter() {
        return adapter;
    }

    public void setMyAdapter(SideBarListViewAdapter adapter) {
        this.adapter = adapter;
    }

    public void setAdapterCallBack(SideBarListViewAdapter.ICoallBack iCoallBack){
        this.adapter.setiCoallBack(iCoallBack);
    }

    public SideBarListViewAdapter getAdapter() {
        return adapter;
    }

    public ListView getListview() {
        return listview;
    }

    public void updateListView(List lists, List addList, SideBarListViewAdapter.ICoallBack iCoallBack){
        adapter = new SideBarListViewAdapter(context, lists, addList, fromType);
        listview.setAdapter(adapter);
        setAdapterCallBack(iCoallBack);
    }
}
