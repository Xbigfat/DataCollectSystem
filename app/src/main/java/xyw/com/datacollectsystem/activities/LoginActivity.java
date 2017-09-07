package xyw.com.datacollectsystem.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import xyw.com.datacollectsystem.BaseActivity;
import xyw.com.datacollectsystem.R;

/**
 * Created by 31429 on 2017/9/7.
 */

public class LoginActivity extends BaseActivity {
    private EditText username_edtx, pwd_edtx;
    private Button login_btn;
    private LoginActivity mThis;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        setContentView(R.layout.login);
        mThis = LoginActivity.this;
    }

    @Override
    protected void findViewsByID() {
        username_edtx = (EditText) findViewById(R.id.login_username);
        pwd_edtx = (EditText) findViewById(R.id.login_pwd);
        login_btn = (Button) findViewById(R.id.login_loginbtn);
    }

    @Override
    protected void setListener() {
        login_btn.setOnClickListener(new loginBtnListener());
    }

    private class loginBtnListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            BaseActivity.makeToast(mThis, "You clicked login button");
            String username = username_edtx.getText().toString();
            String pwd = pwd_edtx.getText().toString();

        }
    }
}
