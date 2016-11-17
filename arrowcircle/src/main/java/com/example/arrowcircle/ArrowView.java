package com.example.arrowcircle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.View;

/**
 * Created by Administrator on 2016/11/17.
 */
public class ArrowView extends View {

    // 用于纪录当前的位置,取值范围[0,1]映射Path的整个长度
    private float currentValue = 0;

    // 当前点的实际位置
    private float[] pos;

    // 当前点的tangent值,用于计算图片所需旋转的角度
    private float[] tan;

    // 箭头图片
    private Bitmap mBitmap;

    // 矩阵,用于对图片进行一些操作
    private Matrix mMatrix;

    public ArrowView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        pos = new float[2];
        tan = new float[2];
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.arrow, options);
        mMatrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint mDefaultPaint = new Paint();
        mDefaultPaint.setColor(Color.BLACK);
        mDefaultPaint.setStyle(Paint.Style.STROKE);
        mDefaultPaint.setStrokeWidth(10);
        canvas.translate(getWidth() / 2, getHeight() / 2);
        Path path = new Path();
        path.addCircle(0, 0, 200, Path.Direction.CW);
        PathMeasure measure = new PathMeasure(path, false);
        // 计算当前的位置在总长度上的比例[0,1]
        currentValue += 0.005;
        if (currentValue >= 1) {
            currentValue = 0;
        }

//        // 获取当前位置的坐标以及趋势
//        measure.getPosTan(measure.getLength() * currentValue, pos, tan);
//        mMatrix.reset();
//        // 计算图片旋转角度
//        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI);
//        // 旋转图片
//        mMatrix.postRotate(degrees, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);
//        // 将图片绘制中心调整到与当前点重合
//        mMatrix.postTranslate(pos[0] - mBitmap.getWidth() / 2, pos[1] - mBitmap.getHeight() / 2);

        // 获取当前位置的坐标以及趋势的矩阵
        measure.getMatrix(measure.getLength() * currentValue, mMatrix,
                PathMeasure.TANGENT_MATRIX_FLAG | PathMeasure.POSITION_MATRIX_FLAG);
        // 1.对 matrix 的操作必须要在 getMatrix 之后进行，否则会被 getMatrix 重置而导致无效。
        // 2.矩阵对旋转角度默认为图片的左上角，我们此处需要使用 preTranslate 调整为图片中心。
        mMatrix.preTranslate(-mBitmap.getWidth() / 2, -mBitmap.getHeight() / 2);

        canvas.drawPath(path, mDefaultPaint);
        canvas.drawBitmap(mBitmap, mMatrix, mDefaultPaint);
        invalidate();
    }

}
