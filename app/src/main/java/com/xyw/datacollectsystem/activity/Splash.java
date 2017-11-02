package com.xyw.datacollectsystem.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.xyw.datacollectsystem.BaseActivity;
import com.xyw.datacollectsystem.R;
import com.xyw.datacollectsystem.network.CheckVersion;

/**
 * Created by 31429 on 2017/9/7.
 *
 * @author 31429
 */

public class Splash extends BaseActivity {
    private Splash mThis = Splash.this;

    @Override
    protected void init() {
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_1_default);
    }

    @Override
    protected void findViewsByID() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CheckVersion checkVersion = new CheckVersion(mThis);
        checkVersion.request();
    }

}
