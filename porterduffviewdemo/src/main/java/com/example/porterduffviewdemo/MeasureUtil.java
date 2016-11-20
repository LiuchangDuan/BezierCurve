package com.example.porterduffviewdemo;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by Administrator on 2016/11/20.
 * 测绘工具类
 */
public final class MeasureUtil {
    public static int[] getScreenSize(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return new int[] {metrics.widthPixels, metrics.heightPixels};
    }
}
