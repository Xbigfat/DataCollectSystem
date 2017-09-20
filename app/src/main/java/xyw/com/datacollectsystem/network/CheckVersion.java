package xyw.com.datacollectsystem.network;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import xyw.com.datacollectsystem.entity.AppVersion;
import xyw.com.datacollectsystem.utils.GlobalMethod;

/**
 * Created by 31429 on 2017/9/7.
 */

public class CheckVersion {
    private Activity mContext;
    private ProgressDialog progressBar;
    private static AppVersion appVersion = new AppVersion();

    public CheckVersion(Activity context) {
        mContext = context;
        progressBar = new ProgressDialog(context);
        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    }

    public void request(final OnNetworkRequest appVersionRequest) {
        if (!GlobalMethod.validateNetworkState(mContext)) {
            appVersionRequest.networkInvalid();
            return;
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL updateUrl = new URL("http://192.168.0.248/AhpsService/version.xml");
                    HttpURLConnection conn = (HttpURLConnection) updateUrl.openConnection();
                    conn.setConnectTimeout(5000);
                    InputStream is = conn.getInputStream();
                    appVersion = getAppVersion(is);
                    appVersionRequest.onRequestCompleted(appVersion);
                } catch (Exception e) {
                    e.printStackTrace();
                    appVersionRequest.onRequestError(e);
                }
            }
        }, "AppVersionCheck");
        appVersionRequest.onRequestLoading();
        thread.start();
    }

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

    private AppVersion getAppVersion(InputStream is) throws Exception {
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is, "utf-8");
        int type = parser.getEventType();
        while (type != XmlPullParser.END_DOCUMENT) {
            switch (type) {
                case XmlPullParser.START_TAG:
                    if ("version".equals(parser.getName())) {
                        appVersion.setVersion(parser.nextText()); //获取版本号
                    } else if ("url".equals(parser.getName())) {
                        String surl = parser.nextText();
                        String curl = surl.substring(surl.lastIndexOf("/"));
                        appVersion.setUrl(surl); //获取要升级的APK文件
                    } else if ("description".equals(parser.getName())) {
                        appVersion.setDescription(parser.nextText()); //获取该文件的信息
                    }
                    break;
            }
            type = parser.next();
        }
        return appVersion;
    }
}
