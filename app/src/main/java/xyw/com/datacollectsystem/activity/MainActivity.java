package xyw.com.datacollectsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import xyw.com.datacollectsystem.BaseActivity;
import xyw.com.datacollectsystem.R;

/**
 * Created by 31429 on 2017/9/8.
 */

public class MainActivity extends BaseActivity implements View.OnClickListener {
    TableRow register, collect, query, pwd_change;
    TextView current_user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        current_user.setText(getBaseApplication().getUser().getXm());
    }

    @Override
    protected void init() {
        getSupportActionBar().hide();
        setContentView(R.layout.main_2_tablerow);
    }

    @Override
    protected void findViewsByID() {
        register = (TableRow) findViewById(R.id.main_register_tr);
        collect = (TableRow) findViewById(R.id.main_collect_tr);
        query = (TableRow) findViewById(R.id.main_query_tr);
        pwd_change = (TableRow) findViewById(R.id.main_pwd_change_tr);
        current_user = (TextView) findViewById(R.id.main_current_tv);
    }

    @Override
    protected void setListener() {
        register.setOnClickListener(this);
        collect.setOnClickListener(this);
        query.setOnClickListener(this);
        pwd_change.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.main_register_tr:
                intent = new Intent(this, VehicleDataRegister.class);
                break;
            case R.id.main_collect_tr:
                intent = new Intent(this, VehicleDataRegister.class);
                break;
            case R.id.main_query_tr:
                intent = new Intent(this, VehicleDataRegister.class);
                break;
            case R.id.main_pwd_change_tr:
                intent = new Intent(this, VehicleDataRegister.class);
                break;
            default:
                break;
        }
        startActivity(intent);
    }
}
