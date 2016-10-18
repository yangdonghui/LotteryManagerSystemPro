package com.manager.wallet;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.manager.common.Constants;
import com.manager.common.StringHelper;
import com.manager.common.Tools;
import com.manager.lotterypro.R;
import com.manager.lotterypro.SysApplication;
import com.manager.widgets.PayDialog;

/**
 * 钱包 充值界面
 * @author donghuiyang
 * @create time 2016/4/20 0020.
 */
public class WalletPayAct extends Activity implements View.OnClickListener{
    //返回按钮
    private View backBtn;

    //下一步 按钮
    private Button nextBtn;

    private EditText moneyEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_pay_layout);

        initTopView();
        initView();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //隐藏软键盘
        Tools.hideSoftInput(this);

    }

    /**
     * 设置标题
     */
    private void initTopView() {
        ViewGroup topView = (ViewGroup) findViewById(R.id.wallet_pay_topview);
        TextView titleView = (TextView) topView.findViewById(R.id.title_tv_topview);
        titleView.setText(Constants.PayMenuText[0]);
        backBtn = (View) topView.findViewById(R.id.common_topview_back_btn);
        backBtn.setOnClickListener(this);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        nextBtn = (Button) findViewById(R.id.wallet_pay_next_btn);
        nextBtn.setOnClickListener(this);

        moneyEdit = (EditText) findViewById(R.id.wallet_pay_edit);
    }

    /**
     * 付费提示对话框
     */
    private void showPayDialog() {
        final PayDialog dialog = new PayDialog(this, PayDialog.DialogSitePayType, moneyEdit.getText().toString().trim(), "充值", R.style.CustomDialog);
        dialog.show();

        dialog.setClicklistener(new PayDialog.ClickListenerInterface() {
            @Override
            public void doConfirm() {
                // TODO Auto-generated method stub
                dialog.closeDialog();
                //密码输入后提交处理

            }

            @Override
            public void doCancel() {
                // TODO Auto-generated method stub
                dialog.closeDialog();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (backBtn == v){
            //返回按钮
            SysApplication.backBtn(this, null);
        }else if (nextBtn == v){
            //下一步按钮
            if (moneyEdit.getText().toString().equals("")){
                Toast.makeText(this, "请输入金额！", Toast.LENGTH_SHORT).show();
                return;
            }
            showPayDialog();
        }
    }

    TextWatcher mTextWatcher = new TextWatcher() {
        private CharSequence temp;
        private int editStart ;
        private int editEnd ;
        @Override
        public void beforeTextChanged(CharSequence s, int arg1, int arg2,
                                      int arg3) {
            temp = s;

            String curStr = moneyEdit.getText().toString().trim();
            if (StringHelper.isStringHaveFloat(curStr) && s =="."){
                //包含小数点
            }
        }

        @Override
        public void onTextChanged(CharSequence s, int arg1, int arg2,
                                  int arg3) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            editStart = moneyEdit.getSelectionStart();
            editEnd = moneyEdit.getSelectionEnd();
            if (temp.length() > 10) {
                Toast.makeText(WalletPayAct.this,
                        "你输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                        .show();
                s.delete(editStart-1, editEnd);
                int tempSelection = editStart;
                moneyEdit.setText(s);
                moneyEdit.setSelection(tempSelection);
            }
        }
    };

}
