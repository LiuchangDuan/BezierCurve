package com.example.testdrawbitmapmesh;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/11/22.
 */
public class CanvasView extends View {

    // 区域A和区域B对象
    private Region mRegionA, mRegionB;

    // 绘制边框的Paint
    private Paint mPaint;

//    private Rect mRect;
//
//    private Path mPath;

    public CanvasView(Context context) {
        this(context, null);
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 实例化画笔并设置属性
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(2);

        // 实例化区域A和区域B
        mRegionA = new Region(100, 100, 300, 300);
        mRegionB = new Region(200, 200, 400, 400);

//        mPath = new Path();
//        mPath.moveTo(50, 50);
//        mPath.lineTo(75, 23);
//        mPath.lineTo(150, 100);
//        mPath.lineTo(80, 110);
//        mPath.close();

//        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setColor(Color.GREEN);
//
//        mRect = new Rect(0, 0, 500, 500);
//
////        mRect.intersect(250, 250, 750, 750);
//
//        mRect.union(250, 250, 750, 750);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLUE);

//        canvas.clipPath(mPath);

        canvas.save();

        // 裁剪区域A
        canvas.clipRegion(mRegionA);

        // 再通过组合方式裁剪区域B
        canvas.clipRegion(mRegionB, Region.Op.DIFFERENCE);
        
        canvas.drawColor(Color.RED);

        canvas.restore();

        // 绘制框框帮助我们观察
        canvas.drawRect(100, 100, 300, 300, mPaint);
        canvas.drawRect(200, 200, 400, 400, mPaint);

//        canvas.clipRect(0, 0, 500, 500);
//        canvas.clipRect(mRect);
//        canvas.drawColor(Color.RED);
//        canvas.drawCircle(500, 600, 150, mPaint);
    }

}
