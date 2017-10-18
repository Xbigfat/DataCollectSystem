package xyw.com.datacollectsystem;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import xyw.com.datacollectsystem.utils.ActivityController;

/**
 * Created by 31429 on 2017/9/6.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private static Toast mToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityController.addActivity(this);
        //不在baseActivity中隐藏ActionBar 部分Activity需要ActionBar支持
        //getSupportActionBar().hide();
        init();
        findViewsByID();
        setListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
    }

    protected BaseApplication mApplication;

    public BaseApplication getBaseApplication() {
        return (BaseApplication) getApplication();
    }

    /**
     * 基类预加载方法，将 setContentView() 写入此方法中，可正确使用
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
     * 弹出短时间Toast
     *
     * @param context 弹出上下文
     * @param msg     内容
     */
    public static void makeToast(Context context, String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

    /**
     * 弹出自定义时间Toast
     *
     * @param context 弹出上下文
     * @param msg     弹出内容
     * @param time    时间长度
     */
    public static void makeToast(Context context, String msg, int time) {
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, time);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }
}

