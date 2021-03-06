package com.xyw.datacollectsystem;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.xyw.datacollectsystem.entity.UserBean;

/**
 * Created by 31429 on 2017/9/6.
 *
 * @author 31429
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    private UserBean user;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
}
