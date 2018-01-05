package com.xyw.datacollectsystem.utils;

/**
 * Created by 31429 on 2017/10/12.
 */

public class Parameters {
    public static final boolean DEBUG = false;
    /**
     * sdcard根目录
     */
    public static final String SDCARD_ROOT_PATH = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
    /**
     * sdcard数据存储根目录
     */
    public static final String SAVE_PATH_IN_SDCARD = "/DataCollectSysCache/";
    /**
     * 日志存放目录
     */
    public static final String ERROR_LOG_PATH = "errorlog/";
    /**
     * 临时文件目录
     */
    public static final String TEMP = "temp/";
}
