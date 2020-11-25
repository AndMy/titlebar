package com.ming.motionlayout.titlebar;

import android.content.res.Resources;

public final class ConvertUtils {
    private ConvertUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }
    public static int dp2px(float dpValue) {
        float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5F);
    }
}
