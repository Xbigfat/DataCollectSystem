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
     * 单一职责原则，此类负责login过程，向服务器发送请求→收到请求处理，将Json解析为UserBean
     * 包含了多个回调
     */
    private UserBean login_user;
    private Context login_activity;

    public LoginProgress(Context context) {
        login_activity = context;
    }

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
