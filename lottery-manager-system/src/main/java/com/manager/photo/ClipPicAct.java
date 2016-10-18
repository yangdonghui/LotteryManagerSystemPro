package com.manager.photo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.manager.clippic.ClipImageLayout;
import com.manager.common.Tools;
import com.manager.lotterypro.R;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;


/**
 *
 * @author
 *
 */
public class ClipPicAct extends Activity implements View.OnClickListener{

	private ClipImageLayout mClipImageLayout;
	public Bitmap mBmp;

	private Button mBtn1, mBtn2;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.clip_pic_layout);

		mBtn1 = (Button) findViewById(R.id.clip_btn1);
		mBtn2 = (Button) findViewById(R.id.clip_btn2);
		mBtn1.setOnClickListener(this);
		mBtn2.setOnClickListener(this);

		mClipImageLayout = (ClipImageLayout) findViewById(R.id.id_clipImageLayout);

		Intent intent = getIntent();
		Uri imageUri = intent.getData();
		int type = intent.getIntExtra("filetype",0);
		String name = intent.getStringExtra("filename");
		try {
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 2;
			if (mBmp != null) mBmp.recycle();
			mBmp = BitmapFactory.decodeFile(name, options);

		} catch (Exception e) {
			e.printStackTrace();
		}

		/*BitmapFactory.Options options = new BitmapFactory.Options();
		//options.inTempStorage = new byte[1024 * 1024*2];
		options.inSampleSize = 2;
		mBmp = BitmapFactory.decodeFile(filename, options);*/

		/*
		if (intent != null) {
			Uri uri =  intent.getData();
			if (uri != null) {
				try {
					BitmapFactory.Options options = new BitmapFactory.Options();
					options.inTempStorage = new byte[1024 * 1024];
					options.inSampleSize = 2;
					mBmp = BitmapFactory.decodeFile(uri.toString(), options);

					//mBmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(intent.hasExtra("data")){
				mBmp = (Bitmap)intent.getParcelableExtra("data");
			}else{

			}
		}*/

		if (mBmp != null) {
			mClipImageLayout.setImageBitmap(mBmp);
		}
	}

	String fileName;
	// 保存拍摄的照片到手机的sd卡
	private void SavePicInLocal(Bitmap bitmap) {
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		ByteArrayOutputStream baos = null; // 字节数组输出流
		try {
			baos = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
			byte[] byteArray = baos.toByteArray();// 字节数组输出流转换成字节数组

			String saveDir = Environment.getExternalStorageDirectory().toString() + File.separator + "Camera";

			File dir = new File(saveDir);
			if (!dir.exists()) {
				dir.mkdir(); // 创建文件夹
			}
			fileName = File.separator + Tools.getPhotoFileName();
			File file = new File(fileName);
			file.delete();
			if (!file.exists()) {
				file.createNewFile();// 创建文件
				Log.e("PicDir", file.getPath());
				Toast.makeText(this, fileName, Toast.LENGTH_LONG).show();
			}
			// 将字节数组写入到刚创建的图片文件中
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(byteArray);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (baos != null) {
				try {
					baos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void onClick(View v) {
		if (v == mBtn1) {
			finish();
		}else if (v == mBtn2) {
			Bitmap bitmap = mClipImageLayout.clip();

			/*ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
			byte[] datas = baos.toByteArray();*/

			//SavePicInLocal(bitmap);
			//裁剪后的图片显示到相册中
			//MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "", "");

			finish();
		}
	}
}
