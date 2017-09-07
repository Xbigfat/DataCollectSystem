package xyw.com.datacollectsystem.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import xyw.com.datacollectsystem.BaseActivity;
import xyw.com.datacollectsystem.R;

/**
 * Created by 31429 on 2017/9/7.
 */

public class Splash extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);

    }

    @Override
    protected void findViewByID() {

    }

    @Override
    protected void setListener() {

    }
}
