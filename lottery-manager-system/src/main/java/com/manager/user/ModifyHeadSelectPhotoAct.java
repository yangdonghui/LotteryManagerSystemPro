package com.manager.user;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.manager.common.Constants;
import com.manager.common.Tools;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.photo.AlbumHelper;
import com.manager.photo.ImageGridAdapter;
import com.manager.photo.ImageItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 修改头像选择照片界面
 * Created by Administrator on 2016/3/25 0025.
 */
public class ModifyHeadSelectPhotoAct extends Activity implements View.OnClickListener {

    private final int START_ALBUM_REQUESTCODE = 1;
    private final int CAMERA_WITH_DATA = 2;
    private final int CROP_RESULT_CODE = 3;

    //返回按钮
    private View mBackBtn = null;

    private String mImgPath;
    private List<ImageItem> dataListChild = new ArrayList<>();

    private GridView gridView;
    private ImageGridAdapter adapter;
    private AlbumHelper helper;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    SysApplication.backBtn(ModifyHeadSelectPhotoAct.this, null);
                    break;

                default:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_head_select_photo);

        initTopView();

        helper = AlbumHelper.getHelper();
        helper.init(getApplicationContext());

        dataListChild = helper.getAllImagesInBucket();
        dataListChild.add(0, null);

        gridView = (GridView) findViewById(R.id.photo_gridView);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new ImageGridAdapter(this, dataListChild, mHandler);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    //进入照相机
                    getImageFromCamera();
                } else {
                    //相册
                    startCropImageActivity(dataListChild.get(position).imagePath, 1);
                }
            }

        });

        SysApplication.modifyHeadSelectPhotoAct = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        dataListChild.clear();
        dataListChild = null;
        helper.clear();
        adapter.clear();
        adapter = null;

        SysApplication.modifyHeadSelectPhotoAct = null;
    }

    /**
     * 设置标题
     */
    private void initTopView() {
        ViewGroup topView = (ViewGroup) findViewById(R.id.photo_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText("相册");//R.string.setting_info_1
        mBackBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        mBackBtn.setOnClickListener(this);
    }

    private static String filename; //图片名称
    private Uri imageUri; //图片路径
    private File mPhotoFile;

    /**
     * 进入照相机
     */
    private void startCapture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
                Environment.getExternalStorageDirectory(), Constants.TMP_PATH)));
        startActivityForResult(intent, CAMERA_WITH_DATA);
    }

    /**
     * 进入照相机
     */
    protected void getImageFromCamera() {
        //图片名称 时间命名
        filename = Environment.getExternalStorageDirectory().toString() + File.separator + "Camera" + File.separator + Tools.getPhotoFileName();
        //创建File对象用于存储拍照的图片 SD卡根目录
        mPhotoFile = new File(filename);
        //将File对象转换为Uri并启动照相程序
        imageUri = Uri.fromFile(mPhotoFile);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //下面这句指定调用相机拍照后的照片存储的路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri); //指定图片输出地址
        startActivityForResult(intent, CAMERA_WITH_DATA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            //Toast.makeText(MainActivity.this, ActivityResult resultCode error, Toast.LENGTH_SHORT).show();
            return;
        }
        switch (requestCode) {
            case CAMERA_WITH_DATA:{
                // 照相机程序返回的,再次调用图片剪辑程序去修剪图片
                startCropImageActivity(/*Environment.getExternalStorageDirectory() + "/" + Constants.TMP_PATH*/filename, 0);
            }
                break;
            case START_ALBUM_REQUESTCODE: {

            }
            break;
            case CROP_RESULT_CODE:
                String path = data.getStringExtra(ClipImageActivity.RESULT_PATH);
                SysApplication.mMainAct.updateIconImg(path);

                //退出
                Message msg = new Message();
                msg.what = 0;
                mHandler.handleMessage(msg);

                Log.e("", "");
                break;
            default:
                break;
        }
    }

    // 裁剪图片的Activity
    private void startCropImageActivity(String path, int type) {
        ClipImageActivity.startActivity(this, path, CROP_RESULT_CODE, type);
    }

    /**
     * 按钮点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (v == mBackBtn) {
            //删除当前activity
            SysApplication.backBtn(this, null);
        }
    }
}
