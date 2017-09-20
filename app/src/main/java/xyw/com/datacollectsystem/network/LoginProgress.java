package xyw.com.datacollectsystem.network;

import android.content.Context;

import xyw.com.datacollectsystem.entity.UserBean;

import static xyw.com.datacollectsystem.utils.GlobalMethod.validateNetworkState;

/**
 * Created by 31429 on 2017/9/19.
 */

public class LoginProgress {
    /**
     * 单一职责原则，此类负责login过程，向服务器发送请求→收到请求处理，将Json解析为UserBean
     * 包含了多个回调
     */
    private String username, password;
    private UserBean login_user;

    public LoginProgress(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void request(Context context, OnNetworkRequest onNetworkRequest) {
        if (!validateNetworkState(context)) {
            onNetworkRequest.networkInvalid();
        }
        onNetworkRequest.onRequestLoading();
        onNetworkRequest.onRequestCompleted(login_user);
        onNetworkRequest.onRequestTimeOut();

    }
}
