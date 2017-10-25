package xyw.com.datacollectsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import xyw.com.datacollectsystem.BaseActivity;
import xyw.com.datacollectsystem.R;
import xyw.com.datacollectsystem.fragment.RegisterManager;

/**
 * Created by 31429 on 2017/9/19.
 *
 * @author 31429
 */

public class VehicleDataRegister extends BaseActivity {
    VehicleDataRegister mThis;

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
        mThis = VehicleDataRegister.this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.vehicle_register_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_new_vehicle:
                Log.i("xyw", "start register");
                Intent intent = new Intent(mThis, RegisterManager.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }
}
