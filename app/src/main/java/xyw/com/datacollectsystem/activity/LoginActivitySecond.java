package xyw.com.datacollectsystem.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import xyw.com.datacollectsystem.BaseActivity;
import xyw.com.datacollectsystem.R;
import xyw.com.datacollectsystem.customviews.CustomProgressBarDialog;
import xyw.com.datacollectsystem.customviews.EditTextWithClear;
import xyw.com.datacollectsystem.customviews.PasswordEditText;
import xyw.com.datacollectsystem.network.LoginProgress;
import xyw.com.datacollectsystem.network.OnNetworkRequest;

import static xyw.com.datacollectsystem.utils.GlobalMethod.changeServerGlobal;

/**
 * Created by 31429 on 2017/9/11.
 */

public class LoginActivitySecond extends BaseActivity {
    private EditText username_edtx, pwd_edtx;
    private Button login_btn;
    private LoginActivitySecond mThis;
    private TextView change_server;
    private CustomProgressBarDialog pd;

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
        change_server.setOnClickListener(new changeServerListener());
    }

    private class loginBtnListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            LoginProgress login = new LoginProgress(username_edtx.getText().toString(), pwd_edtx.getText().toString());
            login.request(mThis, new OnNetworkRequest() {
                @Override
                public void networkInvalid() {
                    makeToast(mThis, "网络不可用，请检查网络状态");
                }

                @Override
                public void onRequestError(Exception e) {

                }

                @Override
                public void onRequestCompleted(Object obj) {

                }

                @Override
                public void onRequestLoading() {
                    pd = new CustomProgressBarDialog(mThis);
                    pd.setCancelable(false);
                    pd.setMessage("正在登录");
                    pd.show();
                }

                @Override
                public void onRequestTimeOut() {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pd.dismiss();
                            makeToast(mThis, "网络超时，请稍后再试！");
                        }
                    }, 5000);
                }
            });
        }
    }

    private class changeServerListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            changeServerGlobal(mThis);
        }
    }

}
