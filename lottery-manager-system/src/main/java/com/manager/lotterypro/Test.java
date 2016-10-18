package com.manager.lotterypro;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

/**
 * @author donghuiyang
 * @create time 2016/7/8 0008.
 */
public class Test extends Activity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout srl = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        srl = (SwipeRefreshLayout) findViewById(R.id.srl);
        srl.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        Toast.makeText(this, "onRefresh", Toast.LENGTH_SHORT).show();
        new Thread(){

            @Override
            public void run() {
                try {
                    sleep(3000);
                    srl.setRefreshing(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
