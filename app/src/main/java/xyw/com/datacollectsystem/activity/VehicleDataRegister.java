package xyw.com.datacollectsystem.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;

import xyw.com.datacollectsystem.BaseActivity;
import xyw.com.datacollectsystem.R;

/**
 * Created by 31429 on 2017/9/19.
 *
 * @author 31429
 */

public class VehicleDataRegister extends BaseActivity {
    @Override
    protected void init() {
        setContentView(R.layout.vehicle_register_default);
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.vehicle_register_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
