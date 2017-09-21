package xyw.com.datacollectsystem.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import xyw.com.datacollectsystem.BaseActivity;
import xyw.com.datacollectsystem.R;
import xyw.com.datacollectsystem.entity.AppVersion;
import xyw.com.datacollectsystem.network.CheckVersionSecond;

/**
 * Created by 31429 on 2017/9/7.
 */

public class Splash extends BaseActivity {
    private Splash mThis = Splash.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CheckVersionSecond checkVersionSecond = new CheckVersionSecond(mThis);
        checkVersionSecond.request();
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
