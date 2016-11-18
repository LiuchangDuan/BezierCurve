package com.example.setpolytopolytest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/11/18.
 */
public class SetPolyToPolyView extends View {

    private static final String TAG = "SetPolyToPolyView";

    private int testPoint = 0;
    private int triggerRadius = 180; // 触发半径为180px

    // 要绘制的图片
    private Bitmap mBitmap;

    // 测试setPolyToPoly用的Matrix
    private Matrix mPolyMatrix;

    private float[] src = new float[8];
    private float[] dst = new float[8];

    private Paint pointPaint;

    public SetPolyToPolyView(Context context) {
        this(context, null);
    }

    public SetPolyToPolyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SetPolyToPolyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initBitmapAndMatrix();
    }

    private void initBitmapAndMatrix() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.meizi);
        float[] temp = {0, 0, // 左上
                mBitmap.getWidth(), 0, // 右上
                mBitmap.getWidth(), mBitmap.getHeight(), // 右下
                0, mBitmap.getHeight()}; // 左下
        src = temp.clone();
        dst = temp.clone();

        pointPaint = new Paint();
        pointPaint.setAntiAlias(true);
        pointPaint.setStrokeWidth(50);
        pointPaint.setColor(0xffd19165);
        pointPaint.setStrokeCap(Paint.Cap.ROUND);

        mPolyMatrix = new Matrix();
        mPolyMatrix.setPolyToPoly(src, 0, src, 0, 4);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float tempX = event.getX();
                float tempY = event.getY();
                // 根据触控位置改变dst
                for (int i = 0; i < testPoint * 2; i += 2) {
                    if (Math.abs(tempX - dst[i]) <= triggerRadius && Math.abs(tempY - dst[i + 1]) <= triggerRadius) {
                        dst[i] = tempX - 100;
                        dst[i + 1] = tempY - 100;
                        break;
                    }
                }
                resetPolyMatrix(testPoint);
                invalidate();
                break;
        }
        return true;
    }

    private void resetPolyMatrix(int pointCount) {
        mPolyMatrix.reset();
        mPolyMatrix.setPolyToPoly(src, 0, dst, 0, pointCount);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(100, 100);
        // 根据Matrix绘制一个变换后的图片
        canvas.drawBitmap(mBitmap, mPolyMatrix, null);
        float[] dst = new float[8];
        mPolyMatrix.mapPoints(dst, src);
        super.onDraw(canvas);
        // 绘制触控点
        for (int i = 0; i < testPoint * 2; i += 2) {
            canvas.drawPoint(dst[i], dst[i + 1], pointPaint);
        }
    }

    public void setTestPoint(int testPoint) {
        this.testPoint = testPoint > 4 || testPoint < 0 ? 4 : testPoint;
        dst = src.clone();
        resetPolyMatrix(this.testPoint);
        invalidate();
    }

}
