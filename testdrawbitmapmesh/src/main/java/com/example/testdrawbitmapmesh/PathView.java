package com.example.testdrawbitmapmesh;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/11/23.
 */
public class PathView extends View {

    // 路径对象
    private Path mPath;

    // 画笔对象
    private Paint mPaint;

    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 实例化画笔并设置属性
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.CYAN);

        // 实例化路径
        mPath = new Path();

//        // 移动点至[300,300]
//        mPath.moveTo(300, 300);
//
//        // 连接路径到点[100,100]
//        mPath.lineTo(100, 100);

        // 移动点至[100,100]
        mPath.moveTo(100, 100);

//        // 连接路径到点
//        mPath.lineTo(300, 100);
//        mPath.lineTo(400, 200);
//        mPath.lineTo(200, 200);
//
//        // 闭合曲线
//        mPath.close();

        // 连接路径到点
//        mPath.quadTo(200, 200, 300, 100);
        mPath.cubicTo(200, 200, 300, 0, 400, 100);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制路径
        canvas.drawPath(mPath, mPaint);
    }

}
