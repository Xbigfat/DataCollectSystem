package xyw.com.datacollectsystem.utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import xyw.com.datacollectsystem.entity.WorkEntity;

/**
 * Created by 31429 on 2017/9/7.
 */

public class BaseDoWorkApi<T> {
    private OnLocalWorkListener<T> onUiListener;
    private OnWorkListener<T> onListener;

    public interface OnWorkListener<T> {
        WorkEntity<T> doWork();
    }

    public OnWorkListener<T> getOnListener() {
        return onListener;
    }

    public void setOnListener(OnWorkListener<T> onListener) {
        this.onListener = onListener;
    }

    public OnLocalWorkListener<T> getOnUiListener() {
        return onUiListener;
    }

    public void setOnUiListener(OnLocalWorkListener<T> onUiListener) {
        this.onUiListener = onUiListener;
    }

    public void doWork() {
        if (onListener == null) {
            return;
        }
        try {
            if (onUiListener != null) {
                onUiListener.onRequestLoading();
            }

        } catch (Exception e) {

            if (onUiListener != null) {
                onUiListener.onRequestError(null, e);
            }

            return;
        }
        backGroundThread thread = new backGroundThread();
        thread.setName("doWork");
        thread.start();
    }

    class backGroundThread extends Thread {
        @Override
        public void run() {
            WorkEntity<T> entity = onListener.doWork();
            Bundle bundle = new Bundle();
            bundle.putSerializable("obj", entity);
            Message msg = mHandler.obtainMessage();
            msg.what = entity.getResultState();
            msg.setData(bundle);
            mHandler.sendMessage(msg);
        }
    }

    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            WorkEntity<T> entity = (WorkEntity<T>) bundle.get("obj");
            switch (msg.what) {
                case WorkEntity.REQUEST_ERROR:// 请求出错
                    if (onUiListener != null) {
                        onUiListener.onRequestError(entity.getData(),
                                entity.getException());
                    }
                    break;
                case WorkEntity.REQUEST_TIME_OUT:// 请求超时
                    if (onUiListener != null) {
                        onUiListener.onRequestTimeout(entity.getData());
                    }
                    break;
                case WorkEntity.REQUEST_COMPLETED:// 请求完成
                    if (onUiListener != null) {
                        onUiListener
                                .onRequestCompleted(entity.getData());
                    }
                    break;
                case WorkEntity.REQUEST_NOTUI_TASK:// 非UI刷新任务
                    if (onUiListener != null) {
                        onUiListener
                                .onRequestNotUiTask(entity.getData());
                    }
                    break;
            }
        }
    };
}

