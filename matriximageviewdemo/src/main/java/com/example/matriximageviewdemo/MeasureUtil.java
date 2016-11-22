package com.example.matriximageviewdemo;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by Administrator on 2016/11/20.
 *
 * 测绘工具类
 */
public class MeasureUtil {

    /**
     * 获取屏幕尺寸
     * @param activity
     * @return 屏幕尺寸像素值，下标为0的值为宽，下标为1的值为高
     */
    public static int[] getScreenSize(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return new int[] {metrics.widthPixels, metrics.heightPixels};
    }

}
