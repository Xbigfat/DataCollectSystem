package xyw.com.datacollectsystem.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import xyw.com.datacollectsystem.BaseActivity;
import xyw.com.datacollectsystem.R;

/**
 * Created by 31429 on 2017/10/23.
 *
 * @author 31429
 */

public class ModifyPwd extends BaseActivity {
    private EditText oldEt, newEt, renewEt;
    private Button submitNewBtn;
    private TextView currentUserTv;
    private Context mThis;
    private String currentPassword;

    @Override
    protected void init() {
        setContentView(R.layout.modify_password);
        mThis = ModifyPwd.this;
    }

    @Override
    protected void findViewsByID() {
        oldEt = (EditText) findViewById(R.id.modify_pwd_old_pwd);
        newEt = (EditText) findViewById(R.id.modify_pwd_new);
        renewEt = (EditText) findViewById(R.id.modify_pwd_renew);
        submitNewBtn = (Button) findViewById(R.id.modify_submit);
        currentUserTv = (TextView) findViewById(R.id.modify_current_username);
    }

    @Override
    protected void setListener() {
        submitNewBtn.setOnClickListener(new ModifyPwdListener());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences userInfo = mThis.getSharedPreferences("user", MODE_PRIVATE);
        currentUserTv.setText("当前登录用户账号： " + userInfo.getString("username", "null"));
        currentPassword = userInfo.getString("pwd", "");
    }

    private class ModifyPwdListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            boolean validate = currentPassword.equals(oldEt.getText().toString());
            if (!validate) {
                Log.i("xyw", "旧密码验证失败");
                return;
            }
            if (newEt.getText().toString().equals(renewEt.getText().toString())) {
                Log.i("xyw", "验证成功，开始更改密码");
            } else {
                makeToast(mThis, "两次输入的密码不一致！");
                Log.i("xyw", "两次密码不一致");
            }
        }
    }

}
