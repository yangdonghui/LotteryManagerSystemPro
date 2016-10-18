package com.manager.common;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Tools for handler picture
 *
 * @author Ryan.Tang
 */
public final class ImageTools {

    /**
     * Transfer drawable to bitmap
     *
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();

        Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888
                : Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * Bitmap to drawable
     *
     * @param bitmap
     * @return
     */
    public static Drawable bitmapToDrawable(Bitmap bitmap) {
        return new BitmapDrawable(bitmap);
    }

    /**
     * Input stream to bitmap
     *
     * @param inputStream
     * @return
     * @throws Exception
     */
    public static Bitmap inputStreamToBitmap(InputStream inputStream)
            throws Exception {
        return BitmapFactory.decodeStream(inputStream);
    }

    /**
     * Byte transfer to bitmap
     *
     * @param byteArray
     * @return
     */
    public static Bitmap byteToBitmap(byte[] byteArray) {
        if (byteArray.length != 0) {
            return BitmapFactory
                    .decodeByteArray(byteArray, 0, byteArray.length);
        } else {
            return null;
        }
    }

    /**
     * Byte transfer to drawable
     *
     * @param byteArray
     * @return
     */
    public static Drawable byteToDrawable(byte[] byteArray) {
        ByteArrayInputStream ins = null;
        if (byteArray != null) {
            ins = new ByteArrayInputStream(byteArray);
        }
        return Drawable.createFromStream(ins, null);
    }

    /**
     * Bitmap transfer to bytes
     *
     * @param bm
     * @return
     */
    public static byte[] bitmapToBytes(Bitmap bm) {
        byte[] bytes = null;
        if (bm != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
            bytes = baos.toByteArray();
        }
        return bytes;
    }

    /**
     * Drawable transfer to bytes
     *
     * @param drawable
     * @return
     */
    public static byte[] drawableToBytes(Drawable drawable) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap bitmap = bitmapDrawable.getBitmap();
        byte[] bytes = bitmapToBytes(bitmap);
        ;
        return bytes;
    }

    /**
     * Base64 to byte[]
     //     */
//    public static byte[] base64ToBytes(String base64) throws IOException {
//        byte[] bytes = Base64.decode(base64);
//        return bytes;
//    }
//
//    /**
//     * Byte[] to base64
//     */
//    public static String bytesTobase64(byte[] bytes) {
//        String base64 = Base64.encode(bytes);
//        return base64;
//    }

    /**
     * Create reflection images
     *
     * @param bitmap
     * @return
     */
    public static Bitmap createReflectionImageWithOrigin(Bitmap bitmap) {
        final int reflectionGap = 4;
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);

        Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, h / 2, w,
                h / 2, matrix, false);

        Bitmap bitmapWithReflection = Bitmap.createBitmap(w, (h + h / 2),
                Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmapWithReflection);
        canvas.drawBitmap(bitmap, 0, 0, null);
        Paint deafalutPaint = new Paint();
        canvas.drawRect(0, h, w, h + reflectionGap, deafalutPaint);

        canvas.drawBitmap(reflectionImage, 0, h + reflectionGap, null);

        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0,
                bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff,
                0x00ffffff, TileMode.CLAMP);
        paint.setShader(shader);
        // Set the Transfer mode to be porter duff and destination in
        paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        // Draw a rectangle using the paint with our linear gradient
        canvas.drawRect(0, h, w, bitmapWithReflection.getHeight()
                + reflectionGap, paint);

        return bitmapWithReflection;
    }

    /**
     * Get rounded corner images
     *
     * @param bitmap
     * @param roundPx 5 10
     * @return
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Bitmap output = Bitmap.createBitmap(w, h, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, w, h);
        final RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    /**
     * Resize the bitmap
     *
     * @param bitmap
     * @param width
     * @param height
     * @return
     */
    public static Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) width / w);
        float scaleHeight = ((float) height / h);
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
    }

    /**
     * Resize the bitmap
     *
     * @param bitmap
     * @param width
     * @param height
     * @return
     */
    public static Bitmap zoomBitmapUniform(Bitmap bitmap, int width, int height, Matrix matrix) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        //Matrix matrixNew = new Matrix();
        float scaleWidth = ((float) width / w);
        float scaleHeight = ((float) height / h);
        if (scaleWidth < scaleHeight) {
            matrix.postScale(scaleWidth, scaleWidth);
        } else if (scaleHeight < scaleWidth) {
            matrix.postScale(scaleHeight, scaleHeight);
        } else {
            matrix.postScale(scaleWidth, scaleHeight);
        }

        return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
    }

    /**
     * Resize the drawable
     *
     * @param drawable
     * @param w
     * @param h
     * @return
     */
    public static Drawable zoomDrawable(Drawable drawable, int w, int h) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap oldbmp = drawableToBitmap(drawable);
        Matrix matrix = new Matrix();
        float sx = ((float) w / width);
        float sy = ((float) h / height);
        matrix.postScale(sx, sy);
        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height,
                matrix, true);
        return new BitmapDrawable(newbmp);
    }

    /**
     * Get images from SD card by path and the name of image
     *
     * @param photoName
     * @return
     */
    public static Bitmap getPhotoFromSDCard(String path, String photoName) {
        Bitmap photoBitmap = BitmapFactory.decodeFile(path + "/" + photoName + ".png");
        if (photoBitmap == null) {
            return null;
        } else {
            return photoBitmap;
        }
    }

    /**
     * Check the SD card
     *
     * @return
     */
    public static boolean checkSDCardAvailable() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    /**
     * Get image from SD card by path and the name of image
     *
     * @param photoName
     * @return
     */
    public static boolean findPhotoFromSDCard(String path, String photoName) {
        boolean flag = false;

        if (checkSDCardAvailable()) {
            File dir = new File(path);
            if (dir.exists()) {
                File folders = new File(path);
                File photoFile[] = folders.listFiles();
                for (int i = 0; i < photoFile.length; i++) {
                    String fileName = photoFile[i].getName().split("\\.")[0];
                    if (fileName.equals(photoName)) {
                        flag = true;
                    }
                }
            } else {
                flag = false;
            }
//            File file = new File(path + "/" + photoName  + ".jpg" );
//            if (file.exists()) {
//                flag = true;
//            }else {
//                flag = false;
//            }

        } else {
            flag = false;
        }
        return flag;
    }

    /**
     * Save image to the SD card
     *
     * @param photoBitmap
     * @param photoName
     * @param path
     */
    public static void savePhotoToSDCard(Bitmap photoBitmap, String path, String photoName) {
        if (checkSDCardAvailable()) {
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File photoFile = new File(path, photoName + ".png");
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(photoFile);
                if (photoBitmap != null) {
                    if (photoBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)) {
                        fileOutputStream.flush();
//                        fileOutputStream.close();
                    }
                }
            } catch (FileNotFoundException e) {
                photoFile.delete();
                e.printStackTrace();
            } catch (IOException e) {
                photoFile.delete();
                e.printStackTrace();
            } finally {
                try {
                    assert fileOutputStream != null;
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Delete the image from SD card
     *
     * @param path file:///sdcard/temp.jpg
     */
    public static void deleteAllPhoto(String path) {
        if (checkSDCardAvailable()) {
            File folder = new File(path);
            File[] files = folder.listFiles();
            for (int i = 0; i < files.length; i++) {
                files[i].delete();
            }
        }
    }

    public static void deletePhotoAtPathAndName(String path, String fileName) {
        if (checkSDCardAvailable()) {
            File folder = new File(path);
            File[] files = folder.listFiles();
            for (int i = 0; i < files.length; i++) {
                System.out.println(files[i].getName());
                if (files[i].getName().equals(fileName)) {
                    files[i].delete();
                }
            }
        }
    }

    public static boolean saveImage(Bitmap photo, String spath) {
        try {
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(spath, false));
            photo.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth) {
        // 源图片的宽度
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (width > reqWidth) {
            // 计算出实际宽度和目标宽度的比率
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = widthRatio;
        }
        return inSampleSize;
    }

    /*
* 设置控件所在的位置YY，并且不改变宽高，
* XY为绝对位置
*/
    public static void setLayout(View view, int x, int y) {
        ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(view.getLayoutParams());
        margin.setMargins(x, y, x + margin.width, y + margin.height);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);
        view.setLayoutParams(layoutParams);
    }

    /**
     * 合并bitmap
     *
     * @param bgBmp
     * @param iconBmp
     * @param titleID
     * @return
     */
    public static Bitmap createBitmap(Bitmap bgBmp, Bitmap iconBmp, int titleID) {
        // 创建空得背景bitmap

        // 生成画布图像
        Bitmap resultBitmap = Bitmap.createBitmap(bgBmp.getWidth(),
                bgBmp.getHeight(), Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(resultBitmap);// 使用空白图片生成canvas

        // 将bmp1绘制在画布上
        Rect srcRect = new Rect(0, 0, bgBmp.getWidth(), bgBmp.getHeight());// 截取bmp1中的矩形区域
        Rect dstRect = new Rect(0, 0, bgBmp.getWidth() / 2, bgBmp.getHeight());// bmp1在目标画布中的位置
        canvas.drawBitmap(bgBmp, 0, 0, null);

        srcRect = new Rect(0, 0, iconBmp.getWidth(), iconBmp.getHeight());// 截取bmp1中的矩形区域
        canvas.drawBitmap(iconBmp, dstRect.centerX(), dstRect.centerY() - srcRect.height() / 2, null);

        // 将bmp1,bmp2合并显示
        return resultBitmap;

    }

    // 水平且垂直倾斜
    public static void testSkewXY(ImageView imgv) {
        Matrix matrix = new Matrix();
        matrix.setSkew(0.5f, 0.5f);
        imgv.setImageMatrix(matrix);
        showMatrixEveryValue(matrix);
    }

    // 水平对称--图片关于X轴对称
    public static void testSymmetryX(ImageView imgv) {
        Matrix matrix = new Matrix();
        Bitmap bmp = drawableToBitmap(imgv.getDrawable());
        int height = bmp.getHeight();
        float matrixValues[] = { 1f, 0f, 0f, 0f, -1f, 0f, 0f, 0f, 1f };
        matrix.setValues(matrixValues);
        //若是matrix.postTranslate(0, height);
        //表示将图片上下倒置
        matrix.postTranslate(0, height * 2);
        imgv.setImageMatrix(matrix);
        showMatrixEveryValue(matrix);
    }

    // 垂直对称--图片关于Y轴对称
    public static void testSymmetryY(ImageView imgv) {
        Matrix matrix = new Matrix();
        Bitmap bmp = drawableToBitmap(imgv.getDrawable());
        int width=bmp.getWidth();
        float matrixValues[] = {-1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f};
        matrix.setValues(matrixValues);
        //若是matrix.postTranslate(width,0);
        //表示将图片左右倒置
        matrix.postTranslate(width*2, 0);
        imgv.setImageMatrix(matrix);
        showMatrixEveryValue(matrix);

    }

    // 关于X=Y对称--图片关于X=Y轴对称
    public static void testSymmetryXY(ImageView imgv) {
        Matrix matrix = new Matrix();
        Bitmap bmp = drawableToBitmap(imgv.getDrawable());
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        float matrixValues[] = { 0f, -1f, 0f, -1f, 0f, 0f, 0f, 0f, 1f };
        matrix.setValues(matrixValues);
        matrix.postTranslate(width+height, width+height);
        imgv.setImageMatrix(matrix);
        showMatrixEveryValue(matrix);
    }


    //获取变换矩阵Matrix中的每个值
    public static void showMatrixEveryValue(Matrix matrix) {
        float matrixValues[] = new float[9];
        matrix.getValues(matrixValues);
        for (int i = 0; i < 3; i++) {
            String valueString = "";
            for (int j = 0; j < 3; j++) {
                valueString = matrixValues[3 * i + j] + "";
                System.out.println("第" + (i + 1) + "行的第" + (j + 1) + "列的值为" + valueString);
            }
        }
    }

    /**
     * 水平翻转
     * @param a
     * @param width
     * @param height
     * @return
     */
    public static Bitmap convertScaleX(Bitmap a, int width, int height)
    {
        int w = a.getWidth();
        int h = a.getHeight();
        Bitmap newb = Bitmap.createBitmap(w, h, Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
        Canvas cv = new Canvas(newb);
        Matrix m = new Matrix();
        //m.postScale(1, -1);   //镜像垂直翻转
        m.postScale(-1, 1);   //镜像水平翻转
        //m.postRotate(-90);  //旋转-90度
        Bitmap new2 = Bitmap.createBitmap(a, 0, 0, w, h, m, true);
        cv.drawBitmap(new2, new Rect(0, 0, new2.getWidth(), new2.getHeight()),new Rect(0, 0, w, h), null);
        return newb;
    }

    /**
     * 创建反射图片
     * @param srcBitmap
     * @return
     */
    public static Bitmap createReflectedBitmap(Bitmap srcBitmap) {
        if (null == srcBitmap) {
            return null;
        }

        // The gap between the reflection bitmap and original bitmap.
        final int REFLECTION_GAP = 4;

        int srcWidth  = srcBitmap.getWidth();
        int srcHeight = srcBitmap.getHeight();
        int reflectionWidth  = srcBitmap.getWidth();
        int reflectionHeight = srcBitmap.getHeight() / 2;

        if (0 == srcWidth || srcHeight == 0) {
            return null;
        }

        // The matrix
        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);

        try {
            // The reflection bitmap, width is same with original's, height is half of original's.
            Bitmap reflectionBitmap = Bitmap.createBitmap(
                    srcBitmap,
                    0,
                    srcHeight / 2,
                    srcWidth,
                    srcHeight / 2,
                    matrix,
                    false);

            if (null == reflectionBitmap) {
                return null;
            }

            // Create the bitmap which contains original and reflection bitmap.
            Bitmap bitmapWithReflection = Bitmap.createBitmap(
                    reflectionWidth,
                    srcHeight + reflectionHeight + REFLECTION_GAP,
                    Config.ARGB_8888);

            if (null == bitmapWithReflection) {
                return null;
            }

            // Prepare the canvas to draw stuff.
            Canvas canvas = new Canvas(bitmapWithReflection);

            // Draw the original bitmap.
            canvas.drawBitmap(srcBitmap, 0, 0, null);

            // Draw the reflection bitmap.
            canvas.drawBitmap(reflectionBitmap, 0, srcHeight + REFLECTION_GAP, null);

            Paint paint = new Paint();
            paint.setAntiAlias(true);
            LinearGradient shader = new LinearGradient(
                    0,
                    srcHeight,
                    0,
                    bitmapWithReflection.getHeight() + REFLECTION_GAP,
                    0x70FFFFFF,
                    0x00FFFFFF,
                    TileMode.MIRROR);
            paint.setShader(shader);
            paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.DST_IN));

            // Draw the linear shader.
            canvas.drawRect(
                    0,
                    srcHeight,
                    srcWidth,
                    bitmapWithReflection.getHeight() + REFLECTION_GAP,
                    paint);

            return bitmapWithReflection;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}