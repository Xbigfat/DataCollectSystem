package xyw.com.datacollectsystem.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import xyw.com.datacollectsystem.BaseActivity;
import xyw.com.datacollectsystem.R;
import xyw.com.datacollectsystem.utils.CheckVersion;

/**
 * Created by 31429 on 2017/9/7.
 */

public class Splash extends BaseActivity {
    private Splash mThis = Splash.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CheckVersion checkVersion = new CheckVersion(mThis);
        checkVersion.checkVersion();
    }

    @Override
    protected void init() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);
    }

    @Override
    protected void findViewsByID() {

    }

    @Override
    protected void setListener() {

    }

    public void checkError() {

    }
}
