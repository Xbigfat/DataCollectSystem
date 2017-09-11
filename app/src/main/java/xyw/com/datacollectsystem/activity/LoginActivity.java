package xyw.com.datacollectsystem.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import xyw.com.datacollectsystem.BaseActivity;
import xyw.com.datacollectsystem.R;

import static xyw.com.datacollectsystem.utils.StaticMethod.isIPv4Address;

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
            Intent intent = new Intent(mThis, MainActivity.class);
            startActivity(intent);
            //mThis.finish();
        }
    }

    private class changeServerListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            LayoutInflater layoutInflater = LayoutInflater.from(mThis);
            final View serverDialog = layoutInflater.inflate(R.layout.login_change_server_dialog, null);
            final SharedPreferences serverIp = mThis.getSharedPreferences("serverIP", MODE_PRIVATE);
            final EditText setIp = (EditText) serverDialog.findViewById(R.id.dialog_server_adress);
            final EditText setPort = (EditText) serverDialog.findViewById(R.id.dialog_server_port);
            final EditText setMethod = (EditText) serverDialog.findViewById(R.id.dialog_method_name);


            if (serverIp == null) {
                setIp.setText(R.string.serverIP);
                setPort.setText(R.string.port);
                setMethod.setText(R.string.method);
            } else {
                if (!serverIp.contains("ip") || !serverIp.contains("port") || !serverIp.contains("method")) {
                    setIp.setText(R.string.serverIP);
                    setPort.setText(R.string.port);
                    setMethod.setText(R.string.method);
                } else {
                    setIp.setText(serverIp.getString("ip", "60.166.5.118"));
                    setPort.setText(serverIp.getString("port", "8087"));
                    setMethod.setText(serverIp.getString("method", "virWeb"));
                }
            }


            final AlertDialog alertDialog = new AlertDialog.Builder(mThis)
                    .setView(serverDialog)
                    .setPositiveButton("确定", null)
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).create();
            alertDialog.show();
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String ip = setIp.getText().toString().trim();
                    String port = setPort.getText().toString().trim();
                    String method = setMethod.getText().toString().trim();
                    if (!isIPv4Address(ip)) {
                        makeToast(mThis, "IP地址不合法！");
                        return;
                    }
                    if (port.equals("") || method.equals("")) {
                        makeToast(mThis, "配置出错，请检查！");
                        return;
                    }
                    SharedPreferences.Editor editor = serverIp.edit();
                    editor.putString("ip", ip);
                    editor.putString("port", port);
                    editor.putString("method", method);
                    editor.apply();
                    alertDialog.dismiss();
                    makeToast(mThis, "服务器地址成功更改为：\n http://" + ip + "/" + port + "/" + method, Toast.LENGTH_LONG);
                }
            });
        }
    }
}
