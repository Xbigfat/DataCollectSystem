package com.xyw.datacollectsystem.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.xyw.datacollectsystem.R;
import com.xyw.datacollectsystem.adapter.FragAdapter;
import com.xyw.datacollectsystem.customviews.NoScrollViewPager;
import com.xyw.datacollectsystem.utils.ActivityController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 31429 on 2017/10/25.
 *
 * @author 31429
 *         登记新车的主界面，嵌套一个 viewPager
 *         viewPager 中间嵌套 3个 Fragment 实现滑动
 *         3个 Fragment 共有1个 viewPager 的引用，用来回调 scroll 滑动
 *         共有1个 CarData ，Step1 添加基本信息， Step2 展示基本信息，Step3 添加照片信息
 *         待解决：
 *         照片如何压缩？
 *         CarData中保存 4个 照片的 Base 64 ？ blob ？还是文件URL？
 *         上传的时候前台上传？后台上传？传Base64？传文件？
 */

public class RegisterManager extends FragmentActivity implements onRegisterCallback {
    List<VehicleProcess> fragmentList;
    NoScrollViewPager viewPager;
    VehicleProcess step1, step2, step3;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.holder_car_reg);
        //添加到 活动管理器中
        ActivityController.addActivity(this);
        step1 = new VehicleStep1();
        step2 = new VehicleStep2();
        step3 = new VehicleStep3();
        fragmentList = new ArrayList<>();
        fragmentList.add(step1);
        fragmentList.add(step2);
        fragmentList.add(step3);
        VehicleProcess.scroll = this;
        //addInterface();
        viewPager = (NoScrollViewPager) findViewById(R.id.viewPager);
        FragAdapter fragAdapter = new FragAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(fragAdapter);
    }

    @Override
    protected void onDestroy() {
        //从活动管理器中移除
        VehicleProcess.carData = null;
        VehicleProcess.scroll = null;
        ActivityController.removeActivity(this);
        super.onDestroy();
    }


    @Override
    public void onStep1to2() {
        /**
         * step1 to step2 回调方法，数据直接通过父类的静态成员变量传递，此处仅仅需要跳转
         */
        //viewPager.arrowScroll(2);
        viewPager.setCurrentItem(1);
    }

    @Override
    public void onStep2to3() {
        /**
         * step2 to step3 回调方法
         */
        //viewPager.arrowScroll(2);
        viewPager.setCurrentItem(2);
    }

    @Override
    public void onPrevious() {
        /**
         * 返回 上一个 step callback ，静态变量 不需要改变，回到 step1 的时候，会被新的数据重置
         */
        int cur = viewPager.getCurrentItem();
        //viewPager.arrowScroll(1);
        viewPager.setCurrentItem(cur - 1);
    }

    @Override
    public void onCompleted() {
        /**
         * step3 commit 数据，从 fragment 还是这里 提交到服务器还没想好
         **/
        VehicleProcess.carData = null;
        VehicleProcess.scroll = null;
        RegisterManager.this.finish();
    }

}
