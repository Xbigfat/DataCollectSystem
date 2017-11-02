package com.xyw.datacollectsystem.utils;

/**
 * Created by 31429 on 2017/9/7.
 */

public interface OnLocalWorkListener<T> {
    /**
     * 请求完成回发事件
     */
    public void onRequestCompleted(T obj);

    /**
     * 非UI事件[处理比较耗时的非UI刷新任务] 注意：如果在此处理UI刷新任务则触发onRequestError
     *
     * @since 注意：如果在此处理UI刷新任务则触发
     */
    public void onRequestNotUiTask(T obj);

    /**
     * 请求出错回发事件
     */
    public void onRequestError(T obj, Exception e);

    /**
     * 请求超时回发事件
     */
    public void onRequestTimeout(T obj);

    /**
     * 请求正在加载回发事件
     */
    public void onRequestLoading();
}
