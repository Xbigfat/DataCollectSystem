package xyw.com.datacollectsystem;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 31429 on 2017/9/6.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private static Toast mToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityController.addActivity(this);
        getSupportActionBar().hide();
        init();
        findViewsByID();
        setListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
    }


    /**
     * 预加载，将setContentView写入init()中，基类按照顺序调用，不会出现nullpointException
     */
    protected abstract void init();

    /**
     * 控件初始化
     */
    protected abstract void findViewsByID();

    /**
     * 设置监听
     */
    protected abstract void setListener();

    /**
     * 设置toast 传入context 和 msg 内容
     */
    public static void makeToast(Context context, String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }
}

class ActivityController {
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