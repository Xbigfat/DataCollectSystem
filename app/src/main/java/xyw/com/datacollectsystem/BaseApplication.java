package xyw.com.datacollectsystem;

import android.app.Application;

import xyw.com.datacollectsystem.entity.UserBean;

/**
 * Created by 31429 on 2017/9/6.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

    }

    private UserBean user;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
}
