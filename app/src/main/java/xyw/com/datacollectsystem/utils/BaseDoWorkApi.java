package xyw.com.datacollectsystem.utils;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import xyw.com.datacollectsystem.entity.workEntity;

/**
 * Created by 31429 on 2017/9/7.
 */

public class BaseDoWorkApi<T> {

    private OnLocalWorkListener<T> localWorkListener;
    private OnWorkListener<T> workListener;

    public interface OnWorkListener<T> {
        workEntity<T> doWork();
    }

    public OnWorkListener<T> getWorkListener() {
        return workListener;
    }

    public void setWorkListener(OnWorkListener<T> workListener) {
        this.workListener = workListener;
    }

    public OnLocalWorkListener<T> getLocalWorkListener() {
        return localWorkListener;
    }

    public void setLocalWorkListener(OnLocalWorkListener<T> localWorkListener) {
        this.localWorkListener = localWorkListener;
    }

    public void doWork() {
        if (workListener == null) {
            return;
        }
        try {
            if (localWorkListener != null) {
                localWorkListener.onRequestLoading();
            }

        } catch (Exception e) {

            if (localWorkListener != null) {
                localWorkListener.onRequestError(null, e);
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
            workEntity<T> entity = workListener.doWork();
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
            workEntity<T> entity = (workEntity<T>) bundle.get("obj");
            switch (msg.what) {
                case workEntity.REQUEST_ERROR:// 请求出错
                    if (localWorkListener != null) {
                        localWorkListener.onRequestError(entity.getData(),
                                entity.getException());
                    }
                    break;
                case workEntity.REQUEST_TIME_OUT:// 请求超时
                    if (localWorkListener != null) {
                        localWorkListener.onRequestTimeout(entity.getData());
                    }
                    break;
                case workEntity.REQUEST_COMPLETED:// 请求完成
                    if (localWorkListener != null) {
                        localWorkListener
                                .onRequestCompleted(entity.getData());
                    }
                    break;
                case workEntity.REQUEST_NOTUI_TASK:// 非UI刷新任务
                    if (localWorkListener != null) {
                        localWorkListener
                                .onRequestNotUiTask(entity.getData());
                    }
                    break;
            }
        }
    };
}

