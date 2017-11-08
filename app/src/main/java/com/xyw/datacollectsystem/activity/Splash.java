package com.xyw.datacollectsystem.activity;

import android.content.SharedPreferences;
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
 *         <p>
 *         检查更新，splash
 */

public class Splash extends BaseActivity {
    private Splash mThis = Splash.this;

    @Override
    protected void init() {
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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
        setContentView(R.layout.activity_splash);
        SharedPreferences s = getApplicationContext().getSharedPreferences("loginfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = s.edit();
        editor.putString("last run time", String.valueOf(System.currentTimeMillis()));
        editor.apply();
        CheckVersion checkVersion = new CheckVersion(mThis);
        checkVersion.request();
    }


}
