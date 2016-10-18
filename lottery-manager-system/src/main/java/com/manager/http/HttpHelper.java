package com.manager.http;

import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * http post请求
 *
 * @author donghuiyang
 * @create time 2016/6/2 0002.
 */
public class HttpHelper {
    public final static String SeverUrl = "http://124.126.224.103:8080/";//192.168.1.203

    public static String excutePost(String targetURL, String urlParameters) {
        String result = "";

        URL url;
        HttpURLConnection connection = null;

        try {
            //Create connection
            url = new java.net.URL(SeverUrl + targetURL);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestMethod("POST");
            connection.setReadTimeout(5000);// 设置超时的时间
            connection.setConnectTimeout(5000);// 设置链接超时的时间
            //因为这个是post请求，需要设置为true
            connection.setDoOutput(true);
            connection.setDoInput(true);

            //设置以Post方法
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));

            //将数据写入流
            byte[] content = urlParameters.getBytes();

            //DataOutputStream流
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            //将要上传的内容写入流中
            out.write(content, 0, content.length);
            //刷新、关闭
            out.flush();
            out.close();

            if (connection.getResponseCode() == 200) {
                //Get Response
                /*InputStream is = connection.getInputStream(); // 获取输入流
                byte[] data = readStream(is); // 把输入流转换成字符串组
                String json = new String(data); // 把字符串组转换成字符串
                // 数据形式：{"total":2,"success":true,"arrayData":[{"id":1,"name":"张三"},{"id":2,"name":"李斯"}]}
                JSONObject jsonObject = new JSONObject(json); // 返回的数据形式是一个Object类型，所以可以直接转换成一个Object
                int total = jsonObject.getInt("count");*/

                // 获得服务端的返回数据
                InputStreamReader read = new InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(read);
                StringBuffer sb = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }

                read.close();
                reader.close();

                result = sb.toString();
                Log.e("", "");

                return result;
            }
        } catch (Exception e) {

            e.printStackTrace();
            return null;

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }

        return null;
    }

    private static byte[] readStream(InputStream inputStream) throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            bout.write(buffer, 0, len);
        }
        bout.close();
        inputStream.close();
        return bout.toByteArray();
    }

    public static String debugHttp() {
        String httpUrl = "http://www.baidu.com";
        StringBuilder resultData = new StringBuilder("");
        String mStrContent = null;

        URL url = null;
        try {
            url = new URL(httpUrl);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            assert url != null;
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setRequestMethod("GET");
            //inputStreamReader一个个字节读取转为字符,可以一个个字符读也可以读到一个buffer
            //getInputStream是真正去连接网络获取数据
            InputStreamReader isr = new InputStreamReader(urlConn.getInputStream());

            if (urlConn.getResponseCode() == 200) {
                //使用缓冲一行行的读入，加速InputStreamReader的速度
                BufferedReader buffer = new BufferedReader(isr);
                String inputLine = null;

                while ((inputLine = buffer.readLine()) != null) {
                    resultData.append(inputLine);
                    resultData.append("\n");
                }
                buffer.close();
                isr.close();

                mStrContent = resultData.toString();
            }

            urlConn.disconnect();
            urlConn = null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return mStrContent;
    }
}
