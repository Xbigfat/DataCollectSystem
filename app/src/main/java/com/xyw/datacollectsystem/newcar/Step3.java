package com.xyw.datacollectsystem.newcar;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.xyw.datacollectsystem.R;
import com.xyw.datacollectsystem.utils.GlobalMethod;
import com.xyw.datacollectsystem.utils.Parameters;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by 31429 on 2017/10/25.
 */

public class Step3 extends StepController implements View.OnClickListener {
    private ImageButton vehicleStep3Frontleft;
    private ImageButton vehicleStep3Behindright;
    private ImageButton vehicleStep3Cjh;
    private ImageButton vehicleStep3Fdjh;
    private String tempImgPath;
    public File tempImgFile;
    private final int FRONT_LEFT = 1;
    private final int BEHIND_RIGHT = 2;
    private final int CJH = 3;
    private final int FDJH = 4;
    private boolean p1, p2, p3, p4;
    /**
     * 提交登记信息
     */
    private Button vehicleStep3Commit;
    /**
     * 返回上一步
     */
    private Button vehicleStep3Previous;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.holder_sub_step3, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        vehicleStep3Frontleft = (ImageButton) view.findViewById(R.id.step3_frontleft);
        vehicleStep3Behindright = (ImageButton) view.findViewById(R.id.step3_behindright);
        vehicleStep3Cjh = (ImageButton) view.findViewById(R.id.step3_cjh);
        vehicleStep3Fdjh = (ImageButton) view.findViewById(R.id.step3_fdjh);
        vehicleStep3Commit = (Button) view.findViewById(R.id.step3_commit);
        vehicleStep3Previous = (Button) view.findViewById(R.id.step3_previous);
        vehicleStep3Commit.setOnClickListener(this);
        vehicleStep3Frontleft.setOnClickListener(this);
        vehicleStep3Behindright.setOnClickListener(this);
        vehicleStep3Cjh.setOnClickListener(this);
        vehicleStep3Fdjh.setOnClickListener(this);
        vehicleStep3Previous.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        switch (v.getId()) {
            default:
                break;
            //提交，开始上传所有文件
            case R.id.step3_commit:
                if (!(p1 & p2 & p3 & p4)) {
                    Toast.makeText(getContext(), "照片拍摄不完全！", Toast.LENGTH_LONG).show();
                    return;
                }
                executeUpload();
                break;
            //拍摄左前方照片
            case R.id.step3_frontleft:
                createFile(FRONT_LEFT);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempImgFile));
                startActivityForResult(intent, FRONT_LEFT);
                break;
            //拍摄右后方照片
            case R.id.step3_behindright:
                createFile(BEHIND_RIGHT);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempImgFile));
                startActivityForResult(intent, BEHIND_RIGHT);
                break;
            //拍摄车架号照片
            case R.id.step3_cjh:
                createFile(CJH);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempImgFile));
                startActivityForResult(intent, CJH);
                break;
            //拍摄发动机号照片
            case R.id.step3_fdjh:
                createFile(FDJH);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempImgFile));
                startActivityForResult(intent, FDJH);
                break;
            //返回上一步
            case R.id.step3_previous:
                scroll.onPrevious();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*ContentResolver cr = getContext().getContentResolver();
        Bitmap bm;
        try {
            bm = BitmapFactory.decodeStream(cr.openInputStream(Uri.fromFile(tempImgFile)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int dgree = GlobalMethod.readPictureDegree(tempImgPath);
        bm = GlobalMethod.rotaingImageView(dgree, bm);
        Bitmap display = GlobalMethod.fixBitmap(bm, 500);
        vehicleStep3Frontleft.setImageBitmap(display);*/
        if (resultCode != Activity.RESULT_OK) {
            Log.i("xyw", "拍照取消");
            return;
        }
        ContentResolver cr = getContext().getContentResolver();
        Bitmap bm;
        try {
            bm = BitmapFactory.decodeStream(cr.openInputStream(Uri.fromFile(tempImgFile)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int dgree = GlobalMethod.readPictureDegree(tempImgPath);
        bm = GlobalMethod.rotaingImageView(dgree, bm);
        Bitmap display = GlobalMethod.fixBitmap(bm, 500);
        switch (requestCode) {
            case FRONT_LEFT:
                vehicleStep3Frontleft.setImageBitmap(display);
                p1 = true;
                break;
            case BEHIND_RIGHT:
                vehicleStep3Behindright.setImageBitmap(display);
                p2 = true;
                break;
            case CJH:
                vehicleStep3Cjh.setImageBitmap(display);
                p3 = true;
                break;
            case FDJH:
                vehicleStep3Fdjh.setImageBitmap(display);
                p4 = true;
                break;
        }
        bm.recycle();
        tempImgFile = null;
    }

    private void createFile(int mode) {

        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(getContext(), "未装载SD卡！", Toast.LENGTH_SHORT).show();
            return;
        }
        String fileName = "";
        String path = Parameters.SDCARD_ROOT_PATH + Parameters.SAVE_PATH_IN_SDCARD + Parameters.TEMP + "cachephoto/" + carData.getHphm();
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        switch (mode) {
            case FRONT_LEFT:
                fileName = "左前方.jpg";
                break;
            case BEHIND_RIGHT:
                fileName = "右后方.jpg";
                break;
            case CJH:
                fileName = "车架号.jpg";
                break;
            case FDJH:
                fileName = "发动机号.jpg";
                break;

        }
        tempImgPath = path + "//" + carData.getHphm() + fileName;
        tempImgFile = new File(tempImgPath);
    }

    private void executeUpload() {
        scroll.onCompleted();
    }

}
