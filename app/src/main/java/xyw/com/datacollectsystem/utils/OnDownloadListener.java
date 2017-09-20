package xyw.com.datacollectsystem.utils;

/**
 * Created by 31429 on 2017/9/20.
 */

public interface OnDownloadListener {
    public void onDownloadCompleted(byte[] data);

    /**
     * 下载出错回发事件
     *
     * @param e
     */
    public void onDownloadError(Exception e);

    /**
     * 下载进度回发事件
     *
     * @param size      总大小
     * @param completed 已经下载
     */
    public void onDownloadProgress(int size, int completed);
}
