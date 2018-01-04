package com.hackernews.reader.util;

/**
 * Created by HassanUsman on 11/11/2017.
 */

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.provider.Settings;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class TestableIndeterminateProgressBar extends ProgressBar {

    public TestableIndeterminateProgressBar(Context context) {
        super(context);
    }

    public TestableIndeterminateProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestableIndeterminateProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public TestableIndeterminateProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setIndeterminateDrawable(Drawable drawable) {
        super.setIndeterminateDrawable(hideIndeterminateDrawable() ? null : drawable);
    }

    private boolean hideIndeterminateDrawable() {
        return android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                Settings.Global.getFloat(getContext().getContentResolver(), Settings.Global.ANIMATOR_DURATION_SCALE, 1) == 0;
    }
}
