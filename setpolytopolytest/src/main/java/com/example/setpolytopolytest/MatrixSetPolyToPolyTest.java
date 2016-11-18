package com.example.setpolytopolytest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.View;

/**
 * Created by Administrator on 2016/11/18.
 */
public class MatrixSetPolyToPolyTest extends View {

    // 要绘制的图片
    private Bitmap mBitmap;

    // 测试setPolyToPoly用的Matrix
    private Matrix mPolyMatrix;

    public MatrixSetPolyToPolyTest(Context context) {
        super(context);

        initBitmapAndMatrix();
    }

    private void initBitmapAndMatrix() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.meizi);
        mPolyMatrix = new Matrix();
        float[] src = {0, 0, // 左上
                mBitmap.getWidth(), 0, // 右上
                mBitmap.getWidth(), mBitmap.getHeight(), // 右下
                0, mBitmap.getHeight()}; // 左下
        float[] dst = {0, 0, // 左上
                mBitmap.getWidth(), 400, // 右上
                mBitmap.getWidth(), mBitmap.getHeight() - 200, // 右下
                0, mBitmap.getHeight()}; // 左下
        mPolyMatrix.setPolyToPoly(src, 0, dst, 0, src.length >> 1);
//        mPolyMatrix.postScale(0.26f, 0.26f);
        mPolyMatrix.postTranslate(0, 200);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 根据Matrix绘制一个变换后的图片
        canvas.drawBitmap(mBitmap, mPolyMatrix, null);
    }
}
