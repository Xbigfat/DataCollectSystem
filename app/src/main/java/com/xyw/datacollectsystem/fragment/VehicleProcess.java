package com.xyw.datacollectsystem.fragment;

import android.support.v4.app.Fragment;

import com.xyw.datacollectsystem.entity.VehicleRegisterRes;

/**
 * Created by 31429 on 2017/10/25.
 * 此类为登陆流程的父类，持有一个静态 车辆信息的 成员变量和1个接口
 * <p>
 * 每个step中直接操作静态变量即可
 */


public class VehicleProcess extends Fragment {

    protected static onRegisterCallback scroll;
    public static VehicleRegisterRes carData;

}
