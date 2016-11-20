package com.example.customviewdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/11/20.
 */
public class CustomView extends View implements Runnable {

    private Paint mPaint;

    private Context mContext;

    // 圆环半径
    private int radius;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;

        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mPaint.setStyle(Paint.Style.STROKE);

        mPaint.setColor(Color.LTGRAY);

        // 当setStrokeWidth(0)的时候描边宽度并不为0而是只占一个像素
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Paint paint = new Paint();
//        paint.setAntiAlias(true);

        // 绘制圆环
        canvas.drawCircle(MeasureUtil.getScreenSize((Activity) mContext)[0] / 2,
                MeasureUtil.getScreenSize((Activity) mContext)[1] / 2, radius, mPaint);
    }

//    public synchronized void setRadius(int radius) {
//        this.radius = radius;
//
//        invalidate();
//    }

    @Override
    public void run() {
        // 确保线程不断执行不断刷新界面
        while (true) {
            try {
                // 如果半径小于200则自加否则大于200后重置半径值以实现往复
                if (radius <= 200) {
                    radius += 10;

                    // 刷新View
                    postInvalidate();
                } else {
                    radius = 0;
                }

                // 每执行一次暂停40毫秒
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
