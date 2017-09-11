package xyw.com.datacollectsystem.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import xyw.com.datacollectsystem.BaseActivity;
import xyw.com.datacollectsystem.R;

/**
 * Created by 31429 on 2017/9/8.
 */

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        setContentView(R.layout.main_constraint_layout);
    }

    @Override
    protected void findViewsByID() {

    }

    @Override
    protected void setListener() {

    }
}
