package com.xyw.datacollectsystem.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.xyw.datacollectsystem.R;

/**
 * Created by 31429 on 2017/10/25.
 */

public class VehicleStep3 extends VehicleProcess implements View.OnClickListener {
    private ImageButton vehicleStep3Frontleft;
    private ImageButton vehicleStep3Behindright;
    private ImageButton vehicleStep3Cjh;
    private ImageButton vehicleStep3Fdjh;
    private Uri imageUri;//原图保存地址
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
        View view = inflater.inflate(R.layout.vehicle_add_step3, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        vehicleStep3Frontleft = (ImageButton) view.findViewById(R.id.vehicle_step3_frontleft);
        vehicleStep3Behindright = (ImageButton) view.findViewById(R.id.vehicle_step3_behindright);
        vehicleStep3Cjh = (ImageButton) view.findViewById(R.id.vehicle_step3_cjh);
        vehicleStep3Fdjh = (ImageButton) view.findViewById(R.id.vehicle_step3_fdjh);
        vehicleStep3Commit = (Button) view.findViewById(R.id.vehicle_step3_commit);
        vehicleStep3Previous = (Button) view.findViewById(R.id.vehicle_step3_previous);
        vehicleStep3Commit.setOnClickListener(this);
        vehicleStep3Frontleft.setOnClickListener(this);
        vehicleStep3Behindright.setOnClickListener(this);
        vehicleStep3Cjh.setOnClickListener(this);
        vehicleStep3Fdjh.setOnClickListener(this);
        vehicleStep3Previous.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            //提交，开始上传所有文件
            case R.id.vehicle_step3_commit:
                executeUpload();
                break;
            //拍摄左前方照片
            case R.id.vehicle_step3_frontleft:
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File frontleft = null;
                try {
                    frontleft = createImageFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (frontleft != null) {
                    //FileProvider 是一个特殊的 ContentProvider 的子类，
                    // 它使用 content:// Uri 代替了 file:/// Uri. ，更便利而且安全的为另一个 app 分享文件
                    Uri photoURI = FileProvider.getUriForFile(getActivity(),
                            "xyw.com.datacollectsystem",
                            frontleft);
                    Log.i("xyw", "photoURI:" + photoURI.toString());
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(takePictureIntent, 1);
                }

                break;
            //拍摄右后方照片
            case R.id.vehicle_step3_behindright:
                break;
            //拍摄车架号照片
            case R.id.vehicle_step3_cjh:
                break;
            //拍摄发动机号照片
            case R.id.vehicle_step3_fdjh:
                break;
            //返回上一步
            case R.id.vehicle_step3_previous:
                scroll.onPrevious();
                break;
        }
    }

    private void executeUpload() {
        scroll.onCompleted();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int targetW = vehicleStep3Frontleft.getWidth();
        int targetH = vehicleStep3Frontleft.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        vehicleStep3Frontleft.setImageBitmap(bitmap);
    }

    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        //.getExternalFilesDir() 方法可以获取到 SDCard/Android/data / 你的应用的包名 / files/ 目录，一般放一些长时间保存的数据
        File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        // 创建临时文件, 文件前缀不能少于三个字符, 后缀如果为空默认未 ".tmp"
        File image = File.createTempFile(
                imageFileName,  /* 前缀 */
                ".jpg",         /* 后缀 */
                storageDir      /* 文件夹 */
        );
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
}
