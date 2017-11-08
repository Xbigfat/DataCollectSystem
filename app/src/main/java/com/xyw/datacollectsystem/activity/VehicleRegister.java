package com.xyw.datacollectsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.xyw.datacollectsystem.BaseActivity;
import com.xyw.datacollectsystem.R;
import com.xyw.datacollectsystem.entity.VehicleRegisterRes;
import com.xyw.datacollectsystem.fragment.RegisterManager;
import com.xyw.datacollectsystem.fragment.VehicleProcess;

/**
 * Created by 31429 on 2017/9/19.
 *
 * @author 31429
 *         登记车辆信息界面
 *         进入显示当前已经登记的信息
 *         点击登记添加新车辆信息
 */

public class VehicleRegister extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    private VehicleRegister mThis;
    private SwipeRefreshLayout refresh;
    private TextView emptyTips;
    private ListView dataLv;
    private boolean isFreshing;

    @Override
    protected void init() {
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void findViewsByID() {
        //下拉刷新控件
        refresh = findViewById(R.id.refresh_controller);
        //空数据提示
        emptyTips = findViewById(R.id.tv_empty_view);
        //数据展示的ListView
        dataLv = findViewById(R.id.register_car_data);
    }

    @Override
    protected void setListener() {
        refresh.setOnRefreshListener(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mThis = VehicleRegister.this;

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("xyw", "refresh invoked");
        onRefresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_register_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_new_vehicle:
                Log.i("xyw", "start register");
                Intent intent = new Intent(mThis, RegisterManager.class);
                VehicleProcess.carData = new VehicleRegisterRes();
                startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onRefresh() {
        if (!isFreshing) {
            isFreshing = true;
            refresh.setRefreshing(true);
            refreshData();
        }
    }

    private void refreshData() {
        refresh.setRefreshing(false);
        // ListView do refreshing here
        if (dataLv.getCount() == 0) {
            dataLv.setVisibility(View.INVISIBLE);
            emptyTips.setVisibility(View.VISIBLE);
        } else {
            dataLv.setVisibility(View.VISIBLE);
            emptyTips.setVisibility(View.GONE);
        }
        isFreshing = false;
    }
}
