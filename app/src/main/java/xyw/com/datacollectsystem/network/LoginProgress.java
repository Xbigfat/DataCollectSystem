package xyw.com.datacollectsystem.network;

import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import xyw.com.datacollectsystem.activity.MainActivity;
import xyw.com.datacollectsystem.customviews.CustomProgressBarDialog;
import xyw.com.datacollectsystem.entity.ServiceObj;
import xyw.com.datacollectsystem.entity.UserBean;
import xyw.com.datacollectsystem.entity.workEntity;
import xyw.com.datacollectsystem.utils.BaseDoWorkApi;
import xyw.com.datacollectsystem.utils.GlobalMethod;
import xyw.com.datacollectsystem.utils.OnLocalWorkListener;

/**
 * Created by 31429 on 2017/9/19.
 */

public class LoginProgress {

    /**
     * 暂时不再使用
     */
    /**
     * 通过调用requset()方法，执行login流程
     * 将返回的Json解析为一个UserBean
     */
    private UserBean login_user;
    private Context login_activity;
    private CustomProgressBarDialog pd;

    public LoginProgress(Context context) {
        login_activity = context;
    }

    /**
     * 登陆流程
     *
     * @param username 用户名
     * @param password 密码
     */
    public void request(final String username, final String password) {
        BaseDoWorkApi<UserBean> work = new BaseDoWorkApi<>();
        work.setWorkListener(new BaseDoWorkApi.OnWorkListener<UserBean>() {
            @Override
            public workEntity<UserBean> doWork() {
                ServiceObj obj = new ServiceObj();
                Gson g = new Gson();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("YHDH", username);
                map.put("MM", password);
                map.put("SBXH","");
                map.put("SBDH", "");
                map.put("BZ", "");
                String sendData = g.toJson(map);
                obj.functionId = "";
                obj.curFzjg = "";
                obj.sendData = sendData;
                return null;
            }
        });
        work.setLocalWorkListener(new OnLocalWorkListener<UserBean>() {
            @Override
            public void onRequestCompleted(UserBean obj) {
                pd.dismiss();
                login_activity.startActivity(new Intent(login_activity, MainActivity.class));
            }

            @Override
            public void onRequestNotUiTask(UserBean obj) {

            }

            @Override
            public void onRequestError(UserBean obj, Exception e) {

            }

            @Override
            public void onRequestTimeout(UserBean obj) {

            }

            @Override
            public void onRequestLoading() {
                pd = new CustomProgressBarDialog(login_activity);
                pd.setCancelable(false);
                pd.setMessage("正在登录");
                pd.show();
            }
        });
       // work.doWork();
    }

}
