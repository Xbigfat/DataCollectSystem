package xyw.com.datacollectsystem.network;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import xyw.com.datacollectsystem.activity.LoginActivity;
import xyw.com.datacollectsystem.activity.Splash;
import xyw.com.datacollectsystem.entity.WorkEntity;
import xyw.com.datacollectsystem.utils.BaseDoWorkApi;
import xyw.com.datacollectsystem.utils.OnLocalWorkListener;
import xyw.com.datacollectsystem.utils.UpDataInfo;

import static xyw.com.datacollectsystem.BaseActivity.makeToast;

/**
 * Created by 31429 on 2017/9/7.
 */

public class CheckVersion {
    private Activity mcontext;
    private ProgressDialog progressBar;
    private Handler handler = new Handler();
    private static UpDataInfo info = new UpDataInfo();


    public CheckVersion(Activity context) {
        mcontext = context;
        progressBar = new ProgressDialog(context);
        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    }

    public void checkVersion() {
        executePost();
    }

    private void executePost() {
        BaseDoWorkApi<UpDataInfo> work = new BaseDoWorkApi<>();
        work.setOnListener(new BaseDoWorkApi.OnWorkListener<UpDataInfo>() {
            @Override
            public WorkEntity<UpDataInfo> doWork() {
                WorkEntity<UpDataInfo> we = new WorkEntity<UpDataInfo>();
                try {
                    String path = "";
                    String url = "192.168.0.248";
                    URL uri;
                    uri = new URL(url + path);
                    HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
                    conn.setReadTimeout(4000);
                    InputStream is = conn.getInputStream();
                    info = getUpdataInfo(is);
                    we.setData(info);
                    we.setResultState(WorkEntity.REQUEST_COMPLETED);
                } catch (Exception e) {
                    e.printStackTrace();
                    we.setException(e);
                    we.setResultState(WorkEntity.REQUEST_ERROR);
                }
                return we;
            }
        });
        work.setOnUiListener(new OnLocalWorkListener<UpDataInfo>() {
            @Override
            public void onRequestCompleted(UpDataInfo obj) {
                makeToast(mcontext, "completed");
                Intent intent = new Intent(mcontext, LoginActivity.class);
                mcontext.startActivity(intent);
                mcontext.finish();
            }

            @Override
            public void onRequestNotUiTask(UpDataInfo obj) {

            }

            @Override
            public void onRequestError(UpDataInfo obj, Exception e) {
                makeToast(mcontext, e.getMessage());
                ((Splash) mcontext).checkError();
            }

            @Override
            public void onRequestTimeout(UpDataInfo obj) {

            }

            @Override
            public void onRequestLoading() {

            }
        });
    }

    private UpDataInfo getUpdataInfo(InputStream is) {
        return null;
    }
}
