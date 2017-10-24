package xyw.com.datacollectsystem.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import xyw.com.datacollectsystem.R;

/**
 * Created by 31429 on 2017/9/11.
 */

public class PasswordEditText extends EditText {

    private int mShowPwdIcon = R.drawable.ic_visibility_24dp;
    private int mHidePwdIcon = R.drawable.ic_visibility_off_24dp;
    private boolean mIsShowPwdIcon; // 是否显示指示器
    private Drawable mDrawableSide; // 显示隐藏指示器
    private int dimension; // 文字与图形的长度

    public PasswordEditText(Context context) {
        this(context, null);
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PasswordEditText);
        initFields(attrs, a);
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            // 获取属性信息
            TypedArray styles = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.PasswordEditText, defStyleAttr, 0);
            initFields(attrs, styles);
        }
    }

    public void initFields(AttributeSet attrs, TypedArray styles) {
        if (attrs != null) {
            mShowPwdIcon = styles.getResourceId(R.styleable.PasswordEditText_pet_iconShow, mShowPwdIcon);
            mHidePwdIcon = styles.getResourceId(R.styleable.PasswordEditText_pet_iconHide, mHidePwdIcon);
           /* Drawable d = styles.getDrawable(R.styleable.PasswordEditText_start);
            if (d != null) {
                drawableStart = d;
            } else {
                throw new RuntimeException(" 图像资源为空 ");
            }*/

            dimension = styles.getDimensionPixelSize(R.styleable.PasswordEditText_pad, 60);
        }

        setInputType(EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);

        showPasswordVisibilityIndicator(true);

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                } else {
                    mIsShowPwdIcon = false;
                    restorePasswordIconVisibility(mIsShowPwdIcon);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    // 存储状态
    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable state = super.onSaveInstanceState();
        return new PwdSavedState(state, mIsShowPwdIcon);
    }

    // 恢复状态
    @Override
    public void onRestoreInstanceState(Parcelable state) {
        PwdSavedState savedState = (PwdSavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        mIsShowPwdIcon = savedState.isShowingIcon();
        restorePasswordIconVisibility(mIsShowPwdIcon);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mDrawableSide == null) {
            return super.onTouchEvent(event);
        }
        final Rect bounds = mDrawableSide.getBounds();
        final int x = (int) event.getRawX(); // 点击的位置

        int iconX = (int) getTopRightCorner().x;

        // Icon 的位置
        int leftIcon = iconX - bounds.width();

        // 大于 Icon 的位置, 才能触发点击
        if (x >= leftIcon) {
            togglePasswordIconVisibility(); // 变换状态
            event.setAction(MotionEvent.ACTION_CANCEL);
            return false;
        }
        return super.onTouchEvent(event);
    }

    // 获取上右角的距离
    public PointF getTopRightCorner() {
        float src[] = new float[8];
        float[] dst = new float[]{0, 0, getWidth(), 0, 0, getHeight(), getWidth(), getHeight()};
        getMatrix().mapPoints(src, dst);
        return new PointF(getX() + src[2], getY() + src[3]);
    }

    // 显示密码提示标志
    private void showPasswordVisibilityIndicator(boolean shouldShowIcon) {
        Drawable drawablestart = getResources().getDrawable(R.drawable.login_custom_password);
        drawablestart.setBounds(0, 0, 100, 100);
        if (shouldShowIcon) {
            Drawable drawable = mIsShowPwdIcon ?
                    ContextCompat.getDrawable(getContext(), mHidePwdIcon) :
                    ContextCompat.getDrawable(getContext(), mShowPwdIcon);
            drawable.setBounds(0, 0, 70, 70);
            setCompoundDrawablesRelative(drawablestart, null, drawable, null);
            setCompoundDrawablePadding(dimension);
            mDrawableSide = drawable;
        } else {
            setCompoundDrawablesRelative(drawablestart, null, null, null);
            mDrawableSide = null;
        }
    }

    // 变换状态
    private void togglePasswordIconVisibility() {
        mIsShowPwdIcon = !mIsShowPwdIcon;
        restorePasswordIconVisibility(mIsShowPwdIcon);
        showPasswordVisibilityIndicator(true);
    }

    // 设置密码指示器的状态
    private void restorePasswordIconVisibility(boolean isShowPwd) {
        if (isShowPwd) {
            // 可视密码输入
            setInputType(EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            // 非可视密码状态
            setInputType(EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
        }

        // 移动光标
        setSelection(getText().length());
    }

    // 存储密码状态, 显示 Icon 的位置
    protected static class PwdSavedState extends BaseSavedState {

        private final boolean mShowingIcon;

        private PwdSavedState(Parcelable superState, boolean showingIcon) {
            super(superState);
            mShowingIcon = showingIcon;
        }

        private PwdSavedState(Parcel in) {
            super(in);
            mShowingIcon = in.readByte() != 0;
        }

        public boolean isShowingIcon() {
            return mShowingIcon;
        }

        @Override
        public void writeToParcel(Parcel destination, int flags) {
            super.writeToParcel(destination, flags);
            destination.writeByte((byte) (mShowingIcon ? 1 : 0));
        }

        public static final Creator<PwdSavedState> CREATOR = new Creator<PwdSavedState>() {
            public PwdSavedState createFromParcel(Parcel in) {
                return new PwdSavedState(in);
            }

            public PwdSavedState[] newArray(int size) {
                return new PwdSavedState[size];
            }
        };
    }

    public void setShakeAnim() {
        this.setAnimation(shakeAnimation(5));
    }

    private static Animation shakeAnimation(int counts) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }
}
