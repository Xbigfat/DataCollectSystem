package xyw.com.datacollectsystem.network;

/**
 * Created by 31429 on 2017/9/20.
 */

public interface OnNetworkRequest<T> {
    /**
     * 网络不可用回调
     */
    void networkInvalid();

    /**
     * 请求出错回调
     *
     * @param e
     */
    void onRequestError(Exception e);

    /**
     * 请求完成回调
     *
     * @param obj
     */
    void onRequestCompleted(T obj);

    /**
     * 加载资源动画
     */
    void onRequestLoading();

    /**
     * 网络请求超时
     */
    void onRequestTimeOut();
}
