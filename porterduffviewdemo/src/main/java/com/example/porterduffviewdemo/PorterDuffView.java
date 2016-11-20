package com.example.porterduffviewdemo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/11/20.
 *
 * 测试不同PorterDuff模式的View
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PorterDuffView extends View {

    /**
     * PorterDuff模式常量
     * 可以在此更改不同的模式测试
     */
//    // 对图像饱和度进行相加
//    // 计算方式：Saturate(S + D)
//    private static final PorterDuff.Mode MODE = PorterDuff.Mode.ADD;

//    // 清除
//    private static final PorterDuff.Mode MODE = PorterDuff.Mode.CLEAR;

//    // 变暗
//    // 两个图像混合，较深的颜色总是会覆盖较浅的颜色，如果两者深浅相同则混合
//    private static final PorterDuff.Mode MODE = PorterDuff.Mode.DARKEN;

//    // 只绘制目标图像
//    private static final PorterDuff.Mode MODE = PorterDuff.Mode.DST;

//    // 在源图像和目标图像相交的地方绘制目标图像而在不相交的地方绘制源图像
//    private static final PorterDuff.Mode MODE = PorterDuff.Mode.DST_ATOP;

//    // 只在源图像和目标图像相交的地方绘制目标图像
//    private static final PorterDuff.Mode MODE = PorterDuff.Mode.DST_IN;

//    // 只在源图像和目标图像不相交的地方绘制目标图像
//    private static final PorterDuff.Mode MODE = PorterDuff.Mode.DST_OUT;

//    // 在源图像的上方绘制目标图像
//    // 就是两个图片谁在上谁在下的意思
//    private static final PorterDuff.Mode MODE = PorterDuff.Mode.DST_OVER;

//    // 变亮
//    // 与DARKEN相反
//    private static final PorterDuff.Mode MODE = PorterDuff.Mode.LIGHTEN;

//    // 正片叠底
//    // 计算方式：[Sa * Da, Sc * Dc]
//    // 源图像素颜色值乘以目标图像素颜色值除以255即得混合后图像像素的颜色值
//    // 黑色与任何颜色混合都会得黑色
//    private static final PorterDuff.Mode MODE = PorterDuff.Mode.MULTIPLY;

//    // 叠加
//    // 在实际效果中其对亮色和暗色不起作用，也就是说黑白色无效
//    // 它会将源色与目标色混合产生一种中间色
//    // 如果源色比目标色暗，那么让目标色的颜色倍增否则颜色递减
//    private static final PorterDuff.Mode MODE = PorterDuff.Mode.OVERLAY;

//    // 滤色
//    // 让图像焦媃幻化，有一种色调均和的感觉
//    private static final PorterDuff.Mode MODE = PorterDuff.Mode.SCREEN;

//    // 显示源图
//    private static final PorterDuff.Mode MODE = PorterDuff.Mode.SRC;

//    // 在源图像和目标图像相交的地方绘制源图像，在不相交的地方绘制目标图像
//    private static final PorterDuff.Mode MODE = PorterDuff.Mode.SRC_ATOP;

//    // 只在源图像和目标图像相交的地方绘制源图像
//    private static final PorterDuff.Mode MODE = PorterDuff.Mode.SRC_IN;

//    // 只在源图像和目标图像不相交的地方绘制源图像
//    private static final PorterDuff.Mode MODE = PorterDuff.Mode.SRC_OUT;

//    // 在目标图像的顶部绘制源图像
//    private static final PorterDuff.Mode MODE = PorterDuff.Mode.SRC_OVER;

    // 在源图像和目标图像重叠之外的任何地方绘制他们，而在不重叠的地方不绘制任何内容
    private static final PorterDuff.Mode MODE = PorterDuff.Mode.XOR;

    // 左右上方示例渐变正方形的尺寸大小
    private static final int RECT_SIZE_SMALL = 400;

    // 中间测试渐变正方形的尺寸大小
    private static final int RECT_SIZE_BIG = 800;

    private Paint mPaint;

    // PorterDuffView类的业务对象
    private PorterDuffBO porterDuffBO;

    // 图形混合模式
    private PorterDuffXfermode porterDuffXfermode;

    // 屏幕尺寸
    private int screenW, screenH;

    // 左上方正方形的原点坐标
    private int s_l, s_t;

    // 右上方正方形的原点坐标
    private int d_l, d_t;

    // 中间正方形的原点坐标
    private int rectX, rectY;

    public PorterDuffView(Context context) {
        this(context, null);
    }

    public PorterDuffView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 实例化画笔并设置抗锯齿
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        // 实例化业务对象
        porterDuffBO = new PorterDuffBO();

        // 实例化混合模式
        porterDuffXfermode = new PorterDuffXfermode(MODE);

        calu(context);
    }

    private void calu(Context context) {
        // 获取包含屏幕尺寸的数组
        int[] screenSize = MeasureUtil.getScreenSize((Activity) context);

        // 获取屏幕尺寸
        screenW = screenSize[0];
        screenH = screenSize[1];

        // 计算左上方正方形原点坐标
        s_l = 0;
        s_t = 0;

        // 计算右上方正方形原点坐标
        d_l = screenW - RECT_SIZE_SMALL;
        d_t = 0;

        // 计算中间方正方形原点坐标
        rectX = screenW / 2 - RECT_SIZE_BIG / 2;
        rectY = RECT_SIZE_SMALL + (screenH - RECT_SIZE_SMALL) / 2 - RECT_SIZE_BIG / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 设置画布颜色为黑色以便我们更好地观察
        canvas.drawColor(Color.BLACK);

        // 设置业务对象尺寸值计算生成左右上方的渐变方形
        porterDuffBO.setSize(RECT_SIZE_SMALL);

        /**
         * 画出左右上方两个正方形
         * 其中左边的的为src右边的为dis
         */
        canvas.drawBitmap(porterDuffBO.initSrcBitmap(), s_l, s_t, mPaint);
        canvas.drawBitmap(porterDuffBO.initDisBitmap(), d_l, d_t, mPaint);

        // 将绘制操作保存到新的图层（更官方的说法应该是离屏缓存）
        int sc = canvas.saveLayer(0, 0, screenW, screenH, null, Canvas.ALL_SAVE_FLAG);

        // 重新设置业务对象尺寸值计算生成中间的渐变方形
        porterDuffBO.setSize(RECT_SIZE_BIG);

        // 先绘制dis目标图
        canvas.drawBitmap(porterDuffBO.initDisBitmap(), rectX, rectY, mPaint);

        // 设置混合模式
        mPaint.setXfermode(porterDuffXfermode);

        // 再绘制src源图
        canvas.drawBitmap(porterDuffBO.initSrcBitmap(), rectX, rectY, mPaint);

        // 还原混合模式
        mPaint.setXfermode(null);

        // 还原画布
        canvas.restoreToCount(sc);
    }
}
