package xyw.com.datacollectsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import xyw.com.datacollectsystem.BaseActivity;
import xyw.com.datacollectsystem.R;

import static xyw.com.datacollectsystem.utils.GlobalMethod.changeServerGlobal;

/**
 * Created by 31429 on 2017/9/7.
 */

public class LoginActivity extends BaseActivity {
    private EditText username_edtx, pwd_edtx;
    private Button login_btn;
    private LoginActivity mThis;
    private TextView change_server;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        setContentView(R.layout.login_1_default);
        mThis = LoginActivity.this;
    }

    @Override
    protected void findViewsByID() {
        username_edtx = (EditText) findViewById(R.id.login_username);
        pwd_edtx = (EditText) findViewById(R.id.login_pwd);
        login_btn = (Button) findViewById(R.id.login_login_btn);
        change_server = (TextView) findViewById(R.id.login_change_server_tv);
    }

    @Override
    protected void setListener() {
        login_btn.setOnClickListener(new loginBtnListener());
        change_server.setOnClickListener(new changeServerListener());
    }

    private class loginBtnListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            makeToast(mThis, "You clicked login_1_default button");
            String username = username_edtx.getText().toString();
            String pwd = pwd_edtx.getText().toString();
            Intent intent = new Intent(mThis, LoginActivitySecond.class);
            startActivity(intent);
            //mThis.finish();
        }
    }

    private class changeServerListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            changeServerGlobal(mThis);
        }
    }
}
