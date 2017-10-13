package xyw.com.datacollectsystem.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.LinkedHashMap;

import xyw.com.datacollectsystem.BaseActivity;
import xyw.com.datacollectsystem.R;
import xyw.com.datacollectsystem.customviews.EditTextWithClear;
import xyw.com.datacollectsystem.customviews.PasswordEditText;
import xyw.com.datacollectsystem.entity.ServiceObj;
import xyw.com.datacollectsystem.entity.SvcResult;
import xyw.com.datacollectsystem.entity.UserBean;
import xyw.com.datacollectsystem.entity.workEntity;
import xyw.com.datacollectsystem.network.LoginProgress;
import xyw.com.datacollectsystem.utils.BaseDoWorkApi;
import xyw.com.datacollectsystem.utils.ServiceConstant;
import xyw.com.datacollectsystem.utils.SoapActionApi;

import static xyw.com.datacollectsystem.utils.GlobalMethod.changeServerGlobal;

/**
 * Created by 31429 on 2017/9/11.
 */

public class LoginActivitySecond extends BaseActivity {
    private EditTextWithClear username_edtx;
    private PasswordEditText pwd_edtx;
    private Button login_btn;
    private LoginActivitySecond mThis;
    private TextView change_server;
    private LoginProgress login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        getSupportActionBar().hide();
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
            executeLogin();
        }
    }

    private class changeServerListener implements View.OnClickListener {
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
                ServiceObj obj = new ServiceObj();
                Gson g = new Gson();
                LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
                map.put("YHDH", "xyw111");
                map.put("MM", "12311111");
                map.put("KHDBBH", "20170929");
                map.put("SBXH", "");
                map.put("SBDH", "");
                map.put("BZ", "");
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
        work.doWork();
    }
}
