package com.xyw.datacollectsystem.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 31429 on 2017/9/8.
 *
 * @author xyw
 */

public class ActivityController {

    private static List<Activity> list = new ArrayList<>();

    public static void addActivity(Activity activity) {
        list.add(activity);
    }

    public static void removeActivity(Activity activity) {
        list.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : list) {
            activity.finish();
        }
    }

}


