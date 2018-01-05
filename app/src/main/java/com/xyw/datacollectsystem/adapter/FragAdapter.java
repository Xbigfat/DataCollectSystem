package com.xyw.datacollectsystem.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import com.xyw.datacollectsystem.newcar.StepController;

/**
 * Created by 31429 on 2017/10/25.
 */

public class FragAdapter extends FragmentPagerAdapter {
    private List<StepController> mFragments;

    public FragAdapter(FragmentManager fm, List<StepController> fragments) {
        super(fm);
        // TODO Auto-generated constructor stub
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mFragments.size();
    }
}
