package com.manager.widgets;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.manager.lotterypro.R;

/**
 * 扫描彩票完成提示 界面
 * @author donghuiyang
 * @create time 2016/8/4 0004.
 */
public class ScanTicketDialog extends Dialog {
    public ScanTicketDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.scan_lottery_ticket_finish_tip);
    }
}
