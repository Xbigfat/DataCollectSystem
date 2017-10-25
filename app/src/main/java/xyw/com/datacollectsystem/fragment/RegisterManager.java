package xyw.com.datacollectsystem.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

import xyw.com.datacollectsystem.R;
import xyw.com.datacollectsystem.adapter.FragAdapter;
import xyw.com.datacollectsystem.customviews.NoScrollViewPager;
import xyw.com.datacollectsystem.utils.ActivityController;

/**
 * Created by 31429 on 2017/10/25.
 */

public class RegisterManager extends FragmentActivity {
    List<VehicleProcess> fragmentList;
    NoScrollViewPager viewPager;
    VehicleProcess step1, step2, step3;

    /**
     * 此类是在报废车辆登记页面，添加按钮弹出的 登记界面。
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle_add_new);
        //添加到 活动管理器中
        ActivityController.addActivity(this);
        step1 = new VehicleStep1();
        step2 = new VehicleStep2();
        step3 = new VehicleStep3();
        fragmentList = new ArrayList<>();
        fragmentList.add(step1);
        fragmentList.add(step2);
        fragmentList.add(step3);
        addProcess();
        viewPager = (NoScrollViewPager) findViewById(R.id.viewPager);
        FragAdapter fragAdapter = new FragAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(fragAdapter);
    }

    @Override
    protected void onDestroy() {
        //从活动管理器中移除
        ActivityController.removeActivity(this);
        super.onDestroy();
    }

    private void addProcess() {
        for (VehicleProcess v : fragmentList) {
            v.setScroll(new yewuluoji());
        }
    }

    private class yewuluoji implements onRegisterCallback {
        @Override
        public void onNextStep() {
            viewPager.arrowScroll(2);
        }

        @Override
        public void onPrevious() {
            viewPager.arrowScroll(1);
        }

        @Override
        public void onCompleted() {
            RegisterManager.this.finish();
        }
    }
}
