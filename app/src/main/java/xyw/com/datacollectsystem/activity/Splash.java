package xyw.com.datacollectsystem.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import xyw.com.datacollectsystem.BaseActivity;
import xyw.com.datacollectsystem.R;
import xyw.com.datacollectsystem.entity.AppVersion;
import xyw.com.datacollectsystem.network.CheckVersion;
import xyw.com.datacollectsystem.network.OnNetworkRequest;

/**
 * Created by 31429 on 2017/9/7.
 */

public class Splash extends BaseActivity {
    private Splash mThis = Splash.this;
    AppVersion appVersion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CheckVersion checkVersion = new CheckVersion(mThis);
        checkVersion.request(new OnNetworkRequest() {
            @Override
            public void networkInvalid() {
                makeToast(mThis, "网络请求出错");
            }

            @Override
            public void onRequestError(Exception e) {
                e.printStackTrace();
            }

            @Override
            public void onRequestCompleted(Object obj) {
                appVersion = (AppVersion) obj;
            }

            @Override
            public void onRequestLoading() {

            }

            @Override
            public void onRequestTimeOut() {

            }
        });
    }

    @Override
    protected void init() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_1_default);
    }

    @Override
    protected void findViewsByID() {

    }

    @Override
    protected void setListener() {

    }

}
