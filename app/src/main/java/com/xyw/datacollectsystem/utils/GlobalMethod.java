package com.xyw.datacollectsystem.utils;

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

import com.xyw.datacollectsystem.R;

import static android.content.Context.MODE_PRIVATE;
import static com.xyw.datacollectsystem.BaseActivity.makeToast;

/**
 * Created by 31429 on 2017/9/8.
 */

public class GlobalMethod {

    private static final Pattern IPV4_PATTERN = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");

    /**
     * 正则表达式判断是否是合法的IPv4地址
     *
     * @param input 传入的地址信息
     * @return 合法返回 true
     */
    public static boolean isIPv4Address(final String input) {
        return IPV4_PATTERN.matcher(input).matches();
    }


    /**
     * 全局修改系统服务的地址信息
     *
     * @param context 修改的上下文位置
     */
    public static void changeServerGlobal(final Context context) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        final View serverDialog = layoutInflater.inflate(R.layout.global_change_server_dialog, null);
        final SharedPreferences serviceip = context.getSharedPreferences("ip", MODE_PRIVATE);
        final EditText setIp = (EditText) serverDialog.findViewById(R.id.dialog_server_adress);
        final EditText setPort = (EditText) serverDialog.findViewById(R.id.dialog_server_port);
        final EditText setService = (EditText) serverDialog.findViewById(R.id.dialog_method_name);
        if (serviceip == null) {
            setIp.setText(ServiceConstant.IP);
            setPort.setText(ServiceConstant.PORT);
            setService.setText(ServiceConstant.SSERVICE);
        } else {
            if (!serviceip.contains("ip") || !serviceip.contains("port") || !serviceip.contains("service")) {
                setIp.setText(ServiceConstant.IP);
                setPort.setText(ServiceConstant.PORT);
                setService.setText(ServiceConstant.SSERVICE);
            } else {
                setIp.setText(serviceip.getString("ip", ServiceConstant.IP));
                setPort.setText(serviceip.getString("port", ServiceConstant.PORT));
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
                if ("".equals(port) || "".equals(service)) {
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
                makeToast(context, "服务器地址成功更改为：\n http://" + ip + ":" + port + "/" + service + "\n请重新打开应用", Toast.LENGTH_LONG);
                ActivityController.finishAll();
            }
        });
    }


    /**
     * 判断网络状态
     *
     * @param context 当前执行需要判断的上下文环境
     * @return 如果可用返回true，不可用返回false
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

    /**
     * 获取设备信息，登陆流程中添加到备注中上传
     *
     * @param cxt 执行的上下文环境
     * @return
     */
    public static String getDeviceInfo(Context cxt) {
        TelephonyManager tm = (TelephonyManager) cxt.getSystemService(Context.TELEPHONY_SERVICE);
        String str = "";
        str += "DeviceId(IMEI) = " + tm.getDeviceId() + "\n";
        str += "DeviceSoftwareVersion = " + tm.getDeviceSoftwareVersion()
                + "\n";
        str += "Line1Number = " + tm.getLine1Number() + "\n";
        str += "NetworkCountryIso = " + tm.getNetworkCountryIso() + "\n";
        str += "NetworkOperator = " + tm.getNetworkOperator() + "\n";
        str += "NetworkOperatorName = " + tm.getNetworkOperatorName() + "\n";
        str += "NetworkType = " + tm.getNetworkType() + "\n";
        str += "PhoneType = " + tm.getPhoneType() + "\n";
        str += "SimCountryIso = " + tm.getSimCountryIso() + "\n";
        str += "SimOperator = " + tm.getSimOperator() + "\n";
        str += "SimOperatorName = " + tm.getSimOperatorName() + "\n";
        str += "SimSerialNumber = " + tm.getSimSerialNumber() + "\n";
        str += "SimState = " + tm.getSimState() + "\n";
        str += "SubscriberId(IMSI) = " + tm.getSubscriberId() + "\n";
        str += "VoiceMailNumber = " + tm.getVoiceMailNumber() + "\n";
        str += "OSModel = " + android.os.Build.MODEL + "\n";
        return str;
    }
}
