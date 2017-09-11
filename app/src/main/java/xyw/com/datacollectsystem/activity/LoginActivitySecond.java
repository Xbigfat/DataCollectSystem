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
import xyw.com.datacollectsystem.myviews.EditTextWithClear;
import xyw.com.datacollectsystem.myviews.PasswordEditText;

import static xyw.com.datacollectsystem.utils.GlobalMethod.changeServerGlobal;

/**
 * Created by 31429 on 2017/9/11.
 */

public class LoginActivitySecond extends BaseActivity {
    private EditText username_edtx, pwd_edtx;
    private Button login_btn;
    private LoginActivitySecond mThis;
    private TextView change_server;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        setContentView(R.layout.login_2_design_name_pwd);
        mThis = LoginActivitySecond.this;
    }

    @Override
    protected void findViewsByID() {
        username_edtx = (EditTextWithClear) findViewById(R.id.login_2_username);
        pwd_edtx = (PasswordEditText) findViewById(R.id.login_2_pwd);
        change_server = (TextView) findViewById(R.id.login_2_change_server);
        login_btn = (Button) findViewById(R.id.login_2_log_btn);
    }

    @Override
    protected void setListener() {
        login_btn.setOnClickListener(new loginBtnListener());
        change_server.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeServerGlobal(mThis);
            }
        });
    }

    class loginBtnListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mThis, MainActivity.class);
            startActivity(intent);
        }
    }

}
