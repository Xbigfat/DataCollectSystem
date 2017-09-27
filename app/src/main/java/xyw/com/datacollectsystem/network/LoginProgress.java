package xyw.com.datacollectsystem.network;

import android.content.Context;

import xyw.com.datacollectsystem.entity.UserBean;
import xyw.com.datacollectsystem.entity.workEntity;
import xyw.com.datacollectsystem.utils.BaseDoWorkApi;
import xyw.com.datacollectsystem.utils.OnLocalWorkListener;

/**
 * Created by 31429 on 2017/9/19.
 */

public class LoginProgress {
    /**
     * 通过调用requset()方法，执行login流程
     * 将返回的Json解析为一个UserBean
     */
    private UserBean login_user;
    private Context login_activity;

    public LoginProgress(Context context) {
        login_activity = context;
    }

    /**
     * 登陆流程
     *
     * @param username 用户名
     * @param password 密码
     */
    public void request(String username, String password) {
        BaseDoWorkApi<UserBean> work = new BaseDoWorkApi<>();
        work.setWorkListener(new BaseDoWorkApi.OnWorkListener<UserBean>() {
            @Override
            public workEntity<UserBean> doWork() {

                workEntity<UserBean> we = null;
                return we;
            }
        });
        work.setLocalWorkListener(new OnLocalWorkListener<UserBean>() {
            @Override
            public void onRequestCompleted(UserBean obj) {

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

            }
        });
    }
}
