package com.manager.user;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
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


import com.manager.common.Tools;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.photo.AlbumHelper;
import com.manager.photo.Bimp;
import com.manager.photo.ClipPicAct;
import com.manager.photo.ImageGridAdapter;
import com.manager.photo.ImageItem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 修改头像选择照片界面
 * Created by Administrator on 2016/3/25 0025.
 */
public class ModifySelectMorePhotoAct extends Activity implements View.OnClickListener {

    public static final String EXTRA_IMAGE_LIST = "imagelist";
    public static Bitmap bimap;

    private int topH;
    //title
    private TextView mTopTitle = null;
    //返回按钮
    private View mBackBtn = null;

    private String mImgPath;
    private List<ImageItem> dataListChild = new ArrayList<>();

    private GridView gridView;
    private ImageGridAdapter adapter;
    private AlbumHelper helper;

    private Intent intent;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    //Toast.makeText(this, "最多选择9张图片", 400).show();
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

        intent = getIntent();

        initTopView();

        helper = AlbumHelper.getHelper();
        helper.init(getApplicationContext());

        dataListChild = helper.getAllImagesInBucket();
        dataListChild.add(0, null);
        /*bimap = BitmapFactory.decodeResource(
                getResources(),
                R.mipmap.icon_addpic_unfocused);*/

        Log.e("", "111111111111111111111");

        gridView = (GridView) findViewById(R.id.photo_gridView);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new ImageGridAdapter(this, dataListChild, mHandler);
        gridView.setAdapter(adapter);
        adapter.setTextCallback(new ImageGridAdapter.TextCallback() {
            public void onListen(int count) {
                if (count >= 1) {
                    //mBtn0.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAddItemListen(String imgPath1, String imgPath, int index) {

                try {
                    Bitmap bm = Bimp.revitionImageSize(imgPath);
                   /* mImg.setTag(imgPath);
                    mImg.setImageBitmap(bm);*/
                    mImgPath = imgPath;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onRemoveItemListen(String imgPath, int index) {
                /*mImg.setImageResource(-1);
                mImg.setTag(-1);*/
                mImgPath = null;
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    //进入照相机
                    getImageFromCamera();

                } else {
                    adapter.changeState(position);

                    /*int[] a = Tools.getLocation(view);
                    Intent intent = new Intent(ModifyHeadSelectPhotoAct.this, ImageDetailAct.class);
                    intent.putExtra("filename", mImgPath);
                    intent.putExtra("locationX", a[0]);
                    intent.putExtra("locationY", a[1]);
                    intent.putExtra("width", a[2]);
                    intent.putExtra("height", a[3]);
                    startActivity(intent);
                    overridePendingTransition(0, 0);*/
                }
            }

        });


        SysApplication.modifySelectMorePhotoAct = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        dataListChild.clear();
        dataListChild = null;
        helper.clear();
        adapter.clear();
        adapter = null;

        SysApplication.modifySelectMorePhotoAct = null;
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

    //自定义变量
    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;
    private String filename; //图片名称
    private Uri imageUri; //图片路径
    private File mPhotoFile;

    /**
     * 进入照相机
     */
    protected void getImageFromCamera() {
        //图片名称 时间命名
        filename = Environment.getExternalStorageDirectory().toString() + File.separator + "Camera" + File.separator + Tools.getPhotoFileName();
        //创建File对象用于存储拍照的图片 SD卡根目录
        mPhotoFile = new File(filename);
        if (!mPhotoFile.exists()) {
            try {
                mPhotoFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //将File对象转换为Uri并启动照相程序
        imageUri = Uri.fromFile(mPhotoFile);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //下面这句指定调用相机拍照后的照片存储的路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri); //指定图片输出地址
        startActivityForResult(intent, TAKE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            //Toast.makeText(MainActivity.this, ActivityResult resultCode error, Toast.LENGTH_SHORT).show();
            return;
        }
        switch (requestCode) {
            case TAKE_PHOTO:

                /*BitmapFactory.Options options = new BitmapFactory.Options();
                options.inTempStorage = new byte[1024 * 1024 * 2];
                options.inSampleSize = 2;
                ClipPicAct.mBmp = BitmapFactory.decodeFile(filename, options);*/

                //发送广播更新系统相册
                Intent intent1 = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                intent1.setData(imageUri);
                sendBroadcast(intent1);

                Intent intent = new Intent(this, ClipPicAct.class);
                intent.setData(imageUri);
                intent.putExtra("filetype",1);
                intent.putExtra("filename", filename);
                startActivity(intent);

                finish();

                break;
            case CROP_PHOTO: {

            }
            break;
            default:
                break;
        }
    }

    /**
     * 选择好新头像后完成处理
     */
    private void finishSelectPhoto() {
        /*ArrayList<String> list = new ArrayList<String>();
        Collection<String> c = adapter.map.values();
        Iterator<String> it = c.iterator();
        for (; it.hasNext();) {
            list.add(it.next());
        }

        if (Bimp.act_bool) {
            setResult(Activity.RESULT_OK);
            Bimp.act_bool = false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (Bimp.bmp.size() < 1) {
                try {
                    Bitmap bm=Bimp.revitionImageSize(list.get(i));
                    Bimp.bmp.add(bm);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/

        if (mImgPath != null) {
            //跳转修改头像大小
            Intent intent = new Intent(this, ClipPicAct.class);
            intent.putExtra("filetype",0);
            intent.putExtra("filename", mImgPath);
            startActivity(intent);
        }

        finish();
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
