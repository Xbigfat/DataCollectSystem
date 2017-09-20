package xyw.com.datacollectsystem.network;

import android.content.Context;

import static xyw.com.datacollectsystem.utils.GlobalMethod.validateNetworkState;

/**
 * Created by 31429 on 2017/9/19.
 */

public class LoginProgress {
    private String username, password;

    public LoginProgress(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void request(Context context, OnNetworkRequest onNetworkRequest) {
        if (!validateNetworkState(context)) {
            onNetworkRequest.networkInvalid();
        }
        onNetworkRequest.onRequestLoading();


    }

}
