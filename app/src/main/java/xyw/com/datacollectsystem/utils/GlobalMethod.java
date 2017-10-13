package xyw.com.datacollectsystem.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

import xyw.com.datacollectsystem.R;

import static android.content.Context.MODE_PRIVATE;
import static xyw.com.datacollectsystem.BaseActivity.makeToast;

/**
 * Created by 31429 on 2017/9/8.
 */

public class GlobalMethod {

    /**
     * isIPv4Address() 方法验证传入的字符串是否为合法的 IPv4 地址
     */
    private static final Pattern IPV4_PATTERN = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");

    public static boolean isIPv4Address(final String input) {
        return IPV4_PATTERN.matcher(input).matches();
    }


    /**
     * changeServerGlobal() 方法全局修改服务器配置信息，传入Context即可
     */
    public static void changeServerGlobal(final Context context) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        final View serverDialog = layoutInflater.inflate(R.layout.login_change_server_dialog, null);
        final SharedPreferences serviceip = context.getSharedPreferences("ip", MODE_PRIVATE);
        final EditText setIp = (EditText) serverDialog.findViewById(R.id.dialog_server_adress);
        final EditText setPort = (EditText) serverDialog.findViewById(R.id.dialog_server_port);
        final EditText setService = (EditText) serverDialog.findViewById(R.id.dialog_method_name);
        if (serviceip == null) {
            setIp.setText(ServiceConstant.IP);//serviceip.getString("ip", "172.172.0.20")
            setPort.setText(ServiceConstant.PORT);//serviceip.getString("port", "8082")
            setService.setText(ServiceConstant.SSERVICE);
        } else {
            if (!serviceip.contains("ip") || !serviceip.contains("port") || !serviceip.contains("service")) {
                setIp.setText(ServiceConstant.IP);//serviceip.getString("ip", "172.172.0.20")
                setPort.setText(ServiceConstant.PORT);//serviceip.getString("port", "8082")
                setService.setText(ServiceConstant.SSERVICE);
            } else {
                setIp.setText(serviceip.getString("ip", ServiceConstant.IP));//serviceip.getString("ip", "172.172.0.20")
                setPort.setText(serviceip.getString("port", ServiceConstant.PORT));//serviceip.getString("port", "8082")
                setService.setText(serviceip.getString("service", ServiceConstant.SSERVICE));
            }
        }
        final AlertDialog alertDialog = new AlertDialog.Builder(context)
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
                String service = setService.getText().toString().trim();
                if (!isIPv4Address(ip)) {
                    makeToast(context, "IP地址不合法！");
                    return;
                }
                if (port.equals("") || service.equals("")) {
                    makeToast(context, "配置出错，请检查！");
                    return;
                }
                try {
                    SharedPreferences.Editor editor = serviceip.edit();
                    editor.putString("ip", ip);
                    editor.putString("port", port);
                    editor.putString("service", service);
                    editor.commit();
                } catch (Exception e) {
                    makeToast(context, e.getMessage(), Toast.LENGTH_LONG);
                } finally {
                    alertDialog.dismiss();
                }
                makeToast(context, "服务器地址成功更改为：\n http://" + ip + ":" + port + "/" + service, Toast.LENGTH_LONG);
            }
        });
    }


    /**
     * validateNetworkState() 判断网络状态，无网络链接返回false，网络请求直接终止
     */
    public static boolean validateNetworkState(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            return (mNetworkInfo != null && mNetworkInfo.isAvailable());
        }
        return false;
    }

    public static String getDeviceId(Context cxt) {
        TelephonyManager tm = (TelephonyManager) cxt.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }
}
