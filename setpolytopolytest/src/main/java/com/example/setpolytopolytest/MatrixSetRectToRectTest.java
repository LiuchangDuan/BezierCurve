package com.example.setpolytopolytest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by Administrator on 2016/11/18.
 */
public class MatrixSetRectToRectTest extends View {

    private static final String TAG = "MatrixSetRectToRectTest";

    private int mViewWidth, mViewHeight;

    // 要绘制的图片
    private Bitmap mBitmap;

    // 测试SetRectToRect用的Matrix
    private Matrix mRectMatrix;

    public MatrixSetRectToRectTest(Context context) {
        super(context);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.meizi);
        mRectMatrix = new Matrix();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF src = new RectF(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
        RectF dst = new RectF(0, 0, mViewWidth, mViewHeight);
        mRectMatrix.setRectToRect(src, dst, Matrix.ScaleToFit.CENTER);
        canvas.drawBitmap(mBitmap, mRectMatrix, new Paint());
    }

}
