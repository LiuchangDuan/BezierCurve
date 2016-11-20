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
public class CustomView extends View {

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

    public synchronized void setRadius(int radius) {
        this.radius = radius;

        invalidate();
    }

}
