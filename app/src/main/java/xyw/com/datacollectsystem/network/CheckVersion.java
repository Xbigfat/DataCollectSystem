package xyw.com.datacollectsystem.network;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Xml;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import xyw.com.datacollectsystem.R;
import xyw.com.datacollectsystem.activity.LoginActivity;
import xyw.com.datacollectsystem.entity.AppVersion;
import xyw.com.datacollectsystem.entity.workEntity;
import xyw.com.datacollectsystem.utils.ActivityController;
import xyw.com.datacollectsystem.utils.BaseDoWorkApi;
import xyw.com.datacollectsystem.utils.OnDownloadListener;
import xyw.com.datacollectsystem.utils.OnLocalWorkListener;
import xyw.com.datacollectsystem.utils.ServiceConstant;

import static android.content.Context.MODE_PRIVATE;
import static xyw.com.datacollectsystem.BaseActivity.makeToast;

/**
 * Created by 31429 on 2017/9/20.
 */

public class CheckVersion {
    private Activity mContext;
    private ProgressDialog progressBar;
    private static AppVersion version = new AppVersion();


    public CheckVersion(Activity Context) {
        mContext = Context;
        progressBar = new ProgressDialog(Context);
    }

    public void request() {
        BaseDoWorkApi<AppVersion> work = new BaseDoWorkApi<>();
        work.setWorkListener(new BaseDoWorkApi.OnWorkListener<AppVersion>() {
            @Override
            public workEntity<AppVersion> doWork() {
                workEntity<AppVersion> we = new workEntity<AppVersion>();
                try {
                    String path = mContext.getResources().getString(R.string.serverurl);
                    String url;
                    SharedPreferences serviceip = mContext.getSharedPreferences("ip", MODE_PRIVATE);
                    if (serviceip == null) {
                        url = "http://" + ServiceConstant.IP + ":" + ServiceConstant.PORT + "/" + ServiceConstant.SSERVICE;
                    } else {
                        if (!serviceip.contains("ip") || !serviceip.contains("port") || !serviceip.contains("service")) {
                            url = "http://" + ServiceConstant.IP + ":" + ServiceConstant.PORT + "/" + ServiceConstant.SSERVICE;
                        } else {
                            url = "http://" + serviceip.getString("ip", ServiceConstant.IP) + ":"
                                    + serviceip.getString("port", ServiceConstant.PORT) + "/"
                                    + serviceip.getString("service", ServiceConstant.SSERVICE);
                        }
                    }
                    URL updateUrl = new URL(url + path);
                    HttpURLConnection conn = (HttpURLConnection) updateUrl.openConnection();
                    conn.setConnectTimeout(5000);
                    InputStream is = conn.getInputStream();
                    version = getAppVersion(is);
                    we.setData(version);
                    we.setResultState(workEntity.REQUEST_COMPLETED);
                } catch (Exception e) {
                    e.printStackTrace();
                    we.setException(e);
                    we.setResultState(workEntity.REQUEST_ERROR);
                }
                return we;
            }
        });
        work.setLocalWorkListener(new OnLocalWorkListener<AppVersion>() {
            @Override
            public void onRequestCompleted(AppVersion obj) {
                if (!getCurrentVersion().equals(obj.getVersion())) {
                    update(obj.getUrl(), obj.getDescription());
                } else {
                    makeToast(mContext, "已经是最新版本");
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(intent);
                    mContext.finish();
                }
            }

            @Override
            public void onRequestNotUiTask(AppVersion obj) {

            }

            @Override
            public void onRequestError(AppVersion obj, Exception e) {
                makeToast(mContext, "版本更新检查失败");
                e.printStackTrace();
                Intent intent = new Intent(mContext, LoginActivity.class);
                mContext.startActivity(intent);
                mContext.finish();
            }

            @Override
            public void onRequestTimeout(AppVersion obj) {

            }

            @Override
            public void onRequestLoading() {
            }
        });
        work.doWork();
    }

    /**
     * 将联网获取的version.xml文件解析为AppVersion对象
     *
     * @param is 获取的response
     * @return AppVersion 版本信息对象
     * @throws Exception
     */
    private AppVersion getAppVersion(InputStream is) throws Exception {
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is, "utf-8");
        String url;
        SharedPreferences serviceip = mContext.getSharedPreferences("ip", MODE_PRIVATE);
        if (serviceip == null) {
            url = ServiceConstant.SADDRESS;
        } else {
            if (!serviceip.contains("ip") || !serviceip.contains("port") || !serviceip.contains("service")) {
                url = ServiceConstant.SADDRESS;
            } else {
                url = "http://" + serviceip.getString("ip", ServiceConstant.IP) + ":"
                        + serviceip.getString("port", ServiceConstant.PORT) + "/"
                        + serviceip.getString("service", ServiceConstant.SSERVICE)
                        + ServiceConstant.SPATH;
            }
        }
        int type = parser.getEventType();
        while (type != XmlPullParser.END_DOCUMENT) {
            switch (type) {
                case XmlPullParser.START_TAG:
                    if ("version".equals(parser.getName())) {
                        version.setVersion(parser.nextText());
                    } else if ("url".equals(parser.getName())) {
                        String surl = parser.nextText();
                        String curl = surl.substring(surl.lastIndexOf("/"));
                        version.setUrl(url + curl);
                    } else if ("description".equals(parser.getName())) {
                        version.setDescription(parser.nextText());
                    }
                    break;
            }
            type = parser.next();
        }
        return version;
    }

    /**
     * 更新软件版本
     *
     * @param url     下载的地址
     * @param message 显示的更新说明
     */
    public void update(final String url, String message) {

        new AlertDialog.Builder(mContext).setIcon(R.drawable.update)
                .setTitle("系统更新").setMessage(message).setCancelable(false)
                .setNegativeButton("浏览器下载", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.VIEW");
                        Uri content_url = Uri.parse(url);
                        intent.setData(content_url);
                        mContext.startActivity(intent);
                        ActivityController.finishAll();
                    }
                })
                .setPositiveButton("更新", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        progressBar.setMessage("开始下载...");
                        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        progressBar.setMax(100);
                        progressBar.setProgress(0);
                        if (!progressBar.isShowing())
                            progressBar.show();
                        download(url);
                    }
                }).show();
    }

    /**
     * 从网络下载安装
     *
     * @param url 文件地址
     */
    void download(String url) {
        // 从网络下载安装包
        final String apkPath = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/datacollectsystem/" + "test.apk";
        Downloader download = new Downloader(mContext, url, apkPath, 6);
        download.isReturnByteData = false;
        download.setOnDownloadListener(new OnDownloadListener() {

            @Override
            public void onDownloadCompleted(byte[] data) {
                // TODO Auto-generated method stub
                progressBar.dismiss();
                // Toast.makeText(mContext, "下载完成", Toast.LENGTH_SHORT).show();
                install(apkPath);
            }

            @Override
            public void onDownloadError(Exception e) {
                // TODO Auto-generated method stub
                progressBar.dismiss();
                Toast.makeText(mContext, "下载出错", Toast.LENGTH_SHORT).show();
                ActivityController.finishAll();
            }

            @Override
            public void onDownloadProgress(int size, int completed) {
                // TODO Auto-generated method stub

                // 当收到更新视图消息时，计算已完成下载百分比，同时更新进度条信息
                int progress = 0;
                if (size > 0) {
                    progress = (Double.valueOf((completed * 1.0 / size * 100)))
                            .intValue();
                    progressBar.setProgress(progress);
                }
                progressBar.setMessage("文件大小: " + Downloader.GetRealSize(size)
                        + "\n已下载: " + Downloader.GetRealSize(completed));
                if (!progressBar.isShowing())
                    progressBar.show();
            }

        });

        download.download();
    }

    /**
     * 启动安装程序
     *
     * @param apkPath 下载好的文件路径
     */
    void install(String apkPath) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(apkPath)),
                "application/vnd.android.package-archive");
        mContext.startActivity(intent);
        // 结束程序
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * 获取当前版本号
     */
    private String getCurrentVersion() {
        PackageManager packageManager = mContext.getPackageManager();
        PackageInfo packInfo;
        try {
            packInfo = packageManager.getPackageInfo(mContext.getPackageName(), 0);
            return packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "-1";
        }
    }

}
