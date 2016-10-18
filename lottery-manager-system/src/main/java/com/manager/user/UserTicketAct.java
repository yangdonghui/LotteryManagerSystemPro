package com.manager.user;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.mining.app.zxing.camera.CameraManager;
import com.mining.app.zxing.decoding.CaptureActivityHandler;
import com.mining.app.zxing.decoding.InactivityTimer;
import com.mining.app.zxing.utils.BeepManager;
import com.mining.app.zxing.view.ViewfinderView;
import com.google.zxing.Result;
import com.google.zxing.BarcodeFormat;

import java.io.IOException;
import java.util.Vector;

/**
 * 我要兑奖界面
 * @author donghuiyang
 * @create time 2016/4/19 0019.
 */
public class UserTicketAct extends Activity implements Callback, View.OnClickListener {

    private int titleID = R.string.me_item_str_1;

    //返回按钮
    private View backBtn;

    private View maskView = null;

    private SurfaceView scanPreview;
    private SurfaceHolder surfaceHolder;
    private ViewfinderView viewfinderView;
    private boolean hasSurface = false, isPause = false;
    private CaptureActivityHandler handler;
    private Vector<BarcodeFormat> decodeFormats;
    private String characterSet;
    private InactivityTimer inactivityTimer;
    private BeepManager beepManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lottery_tick_layout);

        Intent intent = getIntent();
        titleID = intent.getIntExtra("title", titleID);

        initTopView();
        //初始化相机
        InitCapture();

    }


    @Override
    protected void onResume() {
        super.onResume();

        surfaceHolder = scanPreview.getHolder();
        if (hasSurface) {
            initCamera(surfaceHolder);
            handler.start();
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        decodeFormats = null;
        characterSet = null;
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }

        CameraManager.get().closeDriver();

        isPause = true;
    }

    @Override
    protected void onDestroy() {
        inactivityTimer.shutdown();
        inactivityTimer = null;
        handler = null;
        beepManager.close();
        beepManager = null;

        super.onDestroy();
    }

    private void initTopView(){
        ViewGroup topView = (ViewGroup)findViewById(R.id.lottery_tick_topview);

        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(titleID);

        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    /**
     * 初始化相机
     */
    private void InitCapture() {
        CameraManager.init(getApplication());

        maskView = (View) findViewById(R.id.scan_mask_view);
        scanPreview = (SurfaceView) findViewById(R.id.preview_view);
        viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);

        beepManager = new BeepManager(this);
        inactivityTimer = new InactivityTimer(this);
        hasSurface = false;
    }
    /**
     * 处理扫描结果
     * @param result
     * @param barcode
     */
    public void handleDecode(Result result, Bitmap barcode) {
        inactivityTimer.onActivity();
        beepManager.playBeepSoundAndVibrate();

        String resultString = result.getText();
        if (resultString.equals("")) {
            Toast.makeText(UserTicketAct.this, "Scan failed!", Toast.LENGTH_SHORT).show();
        }else {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("result", resultString);
            bundle.putParcelable("bitmap", barcode);
            resultIntent.putExtras(bundle);
            this.setResult(RESULT_OK, resultIntent);
        }
        UserTicketAct.this.finish();
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
            if (handler == null) {
                handler = new CaptureActivityHandler(this, decodeFormats, characterSet);
            }

        } catch (IOException ioe) {
            return;
        } catch (RuntimeException e) {
            return;
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        surfaceHolder = holder;
        if (!hasSurface) {
            hasSurface = true;
            if (!isPause){
                startThread(holder);
            }else{
                initCamera(holder);
                handler.start();
            }

            /*startThread(holder);
            initCamera(holder);*/
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;
    }

    public ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    public Handler getHandler() {
        return handler;
    }

    public void drawViewfinder() {

    }

    public void startThread(SurfaceHolder holder){
        new Thread(new updateThread()).start();
    }

    @Override
    public void onClick(View view) {
        if (view == backBtn){
            //返回按钮
            SysApplication.backBtn(this, null);
        }
    }

    class updateThread implements Runnable {
        long time = 2000; // 开始 的时间，不能为零，否则前面几句歌词没有显示出来
        int i=0;

        public void run() {
            Looper.prepare();

            initCamera(surfaceHolder);

            if (CameraManager.get().isOpen()){
                // Start ourselves capturing previews and decoding.
                handler.start();

                mHandler.post(mUpdateResults);

                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Looper.loop();
        }
    }
    Handler mHandler = new Handler();
    Runnable mUpdateResults = new Runnable() {
        public void run() {
            // 更新视图
            maskView.setVisibility(View.GONE);

        }
    };
}