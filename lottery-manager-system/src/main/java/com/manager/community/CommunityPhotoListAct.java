package com.manager.community;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.manager.adapter.CommonAdapter;
import com.manager.adapter.ViewHolder;
import com.manager.common.Tools;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.widgets.PhotoMenuDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * 自己的 相册 界面
 * @author donghuiyang
 * @create time 2016/4/20 0020.
 */
public class CommunityPhotoListAct extends Activity implements View.OnClickListener{

    //发表类型
    private int sendType = 0;

    //返回按钮
    private View backBtn;
    //发帖入口
    private ImageButton photoBtn;

    private List<Integer> datas = new ArrayList<Integer>(){
        {
            add(R.mipmap.debug_community1);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_photo_list_ayout);

        initTopView();
        initView();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //隐藏键盘
        Tools.hideSoftInput(this);
    }

    /**
     * 获取控件对象
     */
    private void initTopView() {
        ViewGroup topView = (ViewGroup)findViewById(R.id.community_photo_list_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(R.string.comunity_string8);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    private void initView() {
        photoBtn = (ImageButton) findViewById(R.id.community_photo_list_send_post_btn);
        photoBtn.setOnClickListener(this);
        photoBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.e("", "");

                Intent intent = new Intent(CommunityPhotoListAct.this, SendPostAct.class);
                intent.putExtra("sendType", sendType);
                startActivity(intent);

                return true;
            }
        });

        ListView listView = (ListView) findViewById(R.id.community_photo_listview);
        CommonAdapter<Integer> adapter = new CommonAdapter<Integer>(this, datas, R.layout.list_item_debug) {
            @Override
            public void convert(ViewHolder helper, Integer item) {
                helper.setImageResource(R.id.list_item_debug_icon, item.intValue());
            }
        };
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SysApplication.enterNext1(CommunityPhotoListAct.this, PostContentAct.class);
            }
        });
    }

    /**
     * 发帖类型选择菜单
     */
    private void showMenu(){
        PhotoMenuDialog menuDialog = new PhotoMenuDialog(this, R.style.CustomDialog1);
        Window window = menuDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.popupAnimation);  //添加动画
        menuDialog.show();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int)(Tools.getWindowWidth(this)); //设置宽度
        window.setAttributes(lp);

        menuDialog.setClicklistener(new PhotoMenuDialog.ClickListenerInterface() {
            @Override
            public void doConfirm(int type) {
                switch (type) {
                    case 0: {
                        //小视频
                    }
                    break;
                    case 1: {
                        //拍照
                    }
                    break;
                    case 2: {
                        //从手机相册选择
                    }
                    break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //返回按钮
            SysApplication.backBtn(this, null);
        }else if (v == photoBtn){
            //发帖入口
            showMenu();
        }
    }
}
