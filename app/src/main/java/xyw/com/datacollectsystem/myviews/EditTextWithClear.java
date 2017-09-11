package xyw.com.datacollectsystem.myviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import xyw.com.datacollectsystem.R;

/**
 * Created by 31429 on 2017/9/11.
 */

public class EditTextWithClear extends EditText implements View.OnFocusChangeListener, TextWatcher {

    private boolean hasFocus;
    private Drawable mClearDrawable;

    public EditTextWithClear(Context context) {
        super(context, null);
        initCustomEditText();
    }

    public EditTextWithClear(Context context, AttributeSet attrs) {
        super(context, attrs);
        initCustomEditText();
    }

    public EditTextWithClear(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initCustomEditText();
    }

    private void initCustomEditText() {
        mClearDrawable = getCompoundDrawablesRelative()[2];
        if (mClearDrawable == null) {
            mClearDrawable = getResources().getDrawable(R.drawable.edittext_clear_logo);
        }
        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(),
                mClearDrawable.getIntrinsicHeight());
        setClearIconVisible(false);
        setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }

    public void initThemeColor(int color) {

        Drawable[] drawables = this.getCompoundDrawablesRelative();

        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawables[0].mutate();
        if (bitmapDrawable != null) {
            Bitmap b = replaceColorPix(color, bitmapDrawable.getBitmap());
            bitmapDrawable = new BitmapDrawable(getResources(), b);

            bitmapDrawable.setBounds(0, 0, b.getWidth(), b.getHeight());
            this.setCompoundDrawablesRelative(bitmapDrawable, null, null, null);
        }
    }

    public Bitmap replaceColorPix(int themeColor, Bitmap src) {
        int width = src.getWidth();
        int height = src.getHeight();
        int[] colors = new int[width * height];
        int tc = themeColor & 0x00FFFFFF;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int color = src.getPixel(i, j);
                int alpha = color & 0xFF000000;
                if (alpha != 0) {
                    colors[j * width + i] = alpha | tc;
                }
            }
        }
        return Bitmap.createBitmap(colors, width, height, src.getConfig());
    }

    private void setClearIconVisible(boolean isShowClearIcon) {
        Drawable locationRight = isShowClearIcon ? mClearDrawable : null;
        setCompoundDrawablesRelative(getCompoundDrawablesRelative()[0],
                getCompoundDrawablesRelative()[1], locationRight,
                getCompoundDrawablesRelative()[3]);

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        this.hasFocus = hasFocus;
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) { // 内容改变之前调用 (实际上内容并没有发生改变)
    }

    @Override
    public void afterTextChanged(Editable s) { // 在 Edittext 中的内容被改变之后就被调用
    }

    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore,
                              int lengthAfter) { // text 是原有的文本
        // 从 start 开始的 lengthAfter 个字符替换旧的长度为 lengthBefore 的旧文本

        if (hasFocus) {
            setClearIconVisible(text.length() > 0);

        } else {

            setClearIconVisible(false);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (mClearDrawable != null
                && event.getAction() == MotionEvent.ACTION_UP) {

            int x = (int) event.getX();
            boolean horizontalWidth = x > (this.getWidth() - getTotalPaddingRight())
                    && x < (this.getWidth() - getPaddingRight());

            // 获取删除图标的边界位置
            Rect recf = new Rect();

            int ClearIconHeight = recf.height();
            int y = (int) event.getY();
            int distance = (getHeight() - ClearIconHeight) / 2; // 获取底部之间的距离

            boolean VerticalHeight = y > distance
                    && y < (distance + ClearIconHeight);

            if (horizontalWidth) {
                this.setText("");
            }
        }
        return super.onTouchEvent(event);
    }
}
