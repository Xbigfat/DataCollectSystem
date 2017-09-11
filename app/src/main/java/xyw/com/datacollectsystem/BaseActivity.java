package xyw.com.datacollectsystem;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
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


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_HOME) {
            ActivityController.finishAll();
        }
        return super.onKeyDown(keyCode, event);
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

    public static void makeToast(Context context, String msg, int time) {
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, time);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }
}

