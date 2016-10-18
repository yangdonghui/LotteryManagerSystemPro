package com.manager.listener;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * @author donghuiyang
 * @create time 2016/6/1 0001.
 */
public class ModifyBuyNumListener  implements View.OnClickListener{
    private Context context;
    private TextView tv;
    private int btnType;

    public ModifyBuyNumListener(Context context, TextView v, int type){
        this.context = context;
        this.tv = v;
        this.btnType = type;
    }

    @Override
    public void onClick(View v) {
        int num = Integer.valueOf(tv.getText().toString().trim());
        if (btnType == 0){
            //sub
            if (num > 1){
                num--;
            }
        }else if (btnType == 1){
            if (num < 99){
                num++;
            }
        }

        tv.setText(String.valueOf(num));
    }
}
