package com.manager.listener;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.manager.bean.UserDeclareBean;
import com.manager.common.Constants;
import com.manager.user.site.SiteDeclareRecordToDetailAct;

/**
 * Created by Administrator on 2016/3/23 0023.
 */
public class DeclareRecordShowClickListener implements View.OnClickListener {

    private Context context;
    private int position;
    private boolean isShow;

    public DeclareRecordShowClickListener(Context context, int pos, boolean flag) {
        this.context = context;
        isShow = flag;
        position = getIndex(pos);
    }

    private int getIndex(int pos) {
        for(int i=0;i< Constants.DeclareLists.size();i++)
        {
            UserDeclareBean att = Constants.DeclareLists.get(i);
            if (att.getID() == pos) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public void onClick(View v) {
        if (isShow) {
            //查看申报详情
            if (position == -1) return;
            Intent intent = new Intent(context, SiteDeclareRecordToDetailAct.class);

            Bundle bundle = new Bundle();
            bundle.putInt("ItemsPos", position);
            intent.putExtras(bundle);

            context.startActivity(intent);

        } else {
            //取消未受理的申报

        }
    }
}
