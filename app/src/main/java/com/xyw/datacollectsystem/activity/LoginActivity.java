package com.xyw.datacollectsystem.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xyw.datacollectsystem.BaseActivity;
import com.xyw.datacollectsystem.R;
import com.xyw.datacollectsystem.customviews.CustomProgressBarDialog;
import com.xyw.datacollectsystem.customviews.EditTextWithClear;
import com.xyw.datacollectsystem.customviews.PasswordEditText;
import com.xyw.datacollectsystem.entity.ServiceObj;
import com.xyw.datacollectsystem.entity.SvcResult;
import com.xyw.datacollectsystem.entity.UserBean;
import com.xyw.datacollectsystem.entity.workEntity;
import com.xyw.datacollectsystem.utils.BaseDoWorkApi;
import com.xyw.datacollectsystem.utils.GlobalMethod;
import com.xyw.datacollectsystem.utils.OnLocalWorkListener;
import com.xyw.datacollectsystem.utils.ServiceConstant;
import com.xyw.datacollectsystem.utils.SoapActionApi;

import java.util.LinkedHashMap;

import static com.xyw.datacollectsystem.utils.GlobalMethod.changeServerGlobal;

/**
 * Created by 31429 on 2017/9/11.
 *
 * @author 31429
 *         <p>
 *         登陆界面
 */

public class LoginActivity extends BaseActivity {
    private EditTextWithClear usernameEditText;
    private PasswordEditText passwordEditText;
    private Button loginBtn;
    private LoginActivity mThis;
    private TextView changeServer;
    private CustomProgressBarDialog pm;

    @Override
    protected void init() {
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        mThis = LoginActivity.this;
    }

    @Override
    protected void findViewsByID() {
        usernameEditText = (EditTextWithClear) findViewById(R.id.login_username_et);
        passwordEditText = (PasswordEditText) findViewById(R.id.login_pwd_et);
        changeServer = (TextView) findViewById(R.id.login_change_tv);
        loginBtn = (Button) findViewById(R.id.login_btn);
    }

    @Override
    protected void setListener() {
        loginBtn.setOnClickListener(new LoginBtnListener());
        changeServer.setOnClickListener(new ChangeServerListener());
        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                passwordEditText.setText(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initCurrentUser();
    }

    private class LoginBtnListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String username = usernameEditText.getText().toString();
            String pwd = passwordEditText.getText().toString();
            if (GlobalMethod.validateNetworkState(mThis)) {
                if (username.length() == 0) {
                    usernameEditText.setShakeAnim();
                    return;
                }
                if (pwd.length() == 0) {
                    passwordEditText.setShakeAnim();
                    return;
                }
                executeLogin();
            } else {
                makeToast(mThis, "网络不可用，请稍后再试！");
            }
        }
    }

    private class ChangeServerListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            changeServerGlobal(mThis);
        }
    }


    private void executeLogin() {
        BaseDoWorkApi<SvcResult> work = new BaseDoWorkApi<SvcResult>();
        work.setWorkListener(new BaseDoWorkApi.OnWorkListener<SvcResult>() {
            @Override
            public workEntity<SvcResult> doWork() {
                TelephonyManager tm = (TelephonyManager) mThis.getSystemService(Context.TELEPHONY_SERVICE);
                ServiceObj obj = new ServiceObj();
                Gson g = new Gson();
                LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
                map.put("YHDH", usernameEditText.getText().toString());
                map.put("MM", passwordEditText.getText().toString());
                map.put("KHDBBH", "20170929");
                map.put("SBXH", Build.MODEL);
                map.put("SBDH", tm.getDeviceId() + "");
                map.put("BZ", "20170929+开发中软件\n" + GlobalMethod.getDeviceInfo(mThis));
                String sendData = g.toJson(map);
                obj.functionId = "02W51";
                obj.curFzjg = "";
                obj.sendData = sendData;
                SoapActionApi api = new SoapActionApi(mThis, obj, ServiceConstant.WRITE);
                TypeToken<SvcResult> type = new TypeToken<SvcResult>() {
                };
                workEntity<SvcResult> we = api.request(type.getType());
                return we;
            }
        });
        work.setLocalWorkListener(new OnLocalWorkListener<SvcResult>() {
            @Override
            public void onRequestCompleted(SvcResult obj) {
                SharedPreferences s = mThis.getSharedPreferences("user", MODE_PRIVATE);
                SharedPreferences.Editor editor = s.edit();
                pm.dismiss();
                if (obj.getOK()) {
                    Gson g = new Gson();
                    UserBean user = g.fromJson(obj.getMessage(), UserBean.class);
                    getBaseApplication().setUser(user);
                    editor.putString("username", usernameEditText.getText().toString());
                    editor.putString("pwd", passwordEditText.getText().toString());
                    editor.commit();
                    Log.i("xyw", "账号密码已保存");
                    Intent intent = new Intent(mThis, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    editor.putString("username", "");
                    editor.putString("pwd", "");
                    editor.commit();
                    AlertDialog dialog = new AlertDialog.Builder(mThis)
                            .setMessage(obj.getMessage())
                            .show();
                    dialog.show();
                }
            }

            @Override
            public void onRequestNotUiTask(SvcResult obj) {

            }

            @Override
            public void onRequestError(SvcResult obj, Exception e) {
                pm.dismiss();
                makeToast(mThis, e.getMessage());
            }

            @Override
            public void onRequestTimeout(SvcResult obj) {
                pm.dismiss();
                makeToast(mThis, "服务器开小差了……");
            }

            @Override
            public void onRequestLoading() {
                pm = new CustomProgressBarDialog(mThis);
                pm.setCancelable(false);
                pm.setMessage("正在登陆");
                pm.show();
            }
        });
        work.doWork();
    }

    private void initCurrentUser() {
        SharedPreferences s = mThis.getSharedPreferences("user", MODE_PRIVATE);
        if (s == null) {
            usernameEditText.setText("");
            passwordEditText.setText("");
        } else {
            usernameEditText.setText(s.getString("username", ""));
            passwordEditText.setText(s.getString("pwd", ""));
            executeLogin();
        }
    }
}
