package xyw.com.datacollectsystem.customviews;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import xyw.com.datacollectsystem.R;

/**
 * Created by 31429 on 2017/9/20.
 */

public class CustomProgressBarDialog extends Dialog {
    private Context mContext;
    private WindowManager.LayoutParams lp;
    private TextView mMessageView;
    private CharSequence mMessage;
    private ProgressBar mProgress;
    private ImageView mRefreshImage;


    private Matrix mHeaderImageMatrix;
    private RotateAnimation mRotateAnimation;

    /**
     * @param context
     */
    public CustomProgressBarDialog(Context context) {
        super(context, R.style.MyDialogStyle);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.mydialog_loading, null);
        mProgress = (ProgressBar) view.findViewById(R.id.progress);
        mMessageView = (TextView) view.findViewById(R.id.message);
        mRefreshImage = (ImageView) view.findViewById(R.id.refresh_image);
        mRefreshImage.setScaleType(ImageView.ScaleType.MATRIX);
        mHeaderImageMatrix = new Matrix();
        mRefreshImage.setImageMatrix(mHeaderImageMatrix);

        mRotateAnimation = new RotateAnimation(0, 720, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateAnimation.setInterpolator(new LinearInterpolator());
        mRotateAnimation.setDuration(1200);
        mRotateAnimation.setRepeatCount(Animation.INFINITE);
        mRotateAnimation.setRepeatMode(Animation.RESTART);

        if (mMessage != null) {
            mMessageView.setText(mMessage);
        }
        setContentView(view);
        // 设置window属性
        lp = getWindow().getAttributes();
        lp.gravity = Gravity.CENTER;
//	        lp.dimAmount = 0; // 去背景遮盖
        lp.alpha = 1.0f;
        getWindow().setAttributes(lp);
    }

    public void setMessage(CharSequence message) {
        if (mProgress != null) {
            mMessageView.setText(message);
        } else {
            mMessage = message;
        }
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
        super.show();
        mRefreshImage.startAnimation(mRotateAnimation);
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        mRefreshImage.clearAnimation();
    }
}
