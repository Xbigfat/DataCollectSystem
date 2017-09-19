package xyw.com.datacollectsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import xyw.com.datacollectsystem.BaseActivity;
import xyw.com.datacollectsystem.R;

/**
 * Created by 31429 on 2017/9/7.
 */

public class Splash extends BaseActivity {
    private Splash mThis = Splash.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*        CheckVersion checkVersion = new CheckVersion(mThis);
        checkVersion.checkVersion();*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(mThis, LoginActivitySecond.class);
                mThis.startActivity(mainIntent);
                mThis.finish();
            }
        }, 2500);
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

    public void checkError() {

    }
}
