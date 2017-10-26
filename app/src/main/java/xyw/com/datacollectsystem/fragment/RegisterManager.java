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
        addInterface();
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

    private void addInterface() {
        for (VehicleProcess v : fragmentList) {
            v.setScroll(new doWork());
        }
    }


    private class doWork implements onRegisterCallback {
        @Override
        public void onStep1to2() {
            /**
             * step1 to step2 回调方法，数据直接通过父类的静态成员变量传递，此处仅仅需要跳转
             */
            viewPager.arrowScroll(2);
        }

        @Override
        public void onStep2to3() {
            /**
             * step2 to step3 回调方法
             */
            viewPager.arrowScroll(2);
        }

        @Override
        public void onPrevious() {
            /**
             * 返回 上一个 step callback ，静态变量 不需要改变，回到 step1 的时候，会被新的数据重置
             */
            viewPager.arrowScroll(1);
        }

        @Override
        public void onCompleted() {
            /**
             * step3 commit 数据，从 fragment 还是这里 提交到服务器还没想好
             */
            RegisterManager.this.finish();
        }
    }
}
