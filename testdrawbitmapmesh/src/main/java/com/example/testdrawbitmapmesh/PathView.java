package com.example.testdrawbitmapmesh;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/11/23.
 *
 * 椭圆内外围写字
 */
public class PathView extends View {

    // 路径对象
    private Path mPath;

    // 画笔对象
    private Paint mPaint;

    // 文本画笔对象
    private TextPaint mTextPaint;

    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 实例化画笔并设置属性
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.CYAN);
        mPaint.setStrokeWidth(5);

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG |
                    Paint.LINEAR_TEXT_FLAG);
        mTextPaint.setColor(Color.DKGRAY);
        mTextPaint.setTextSize(20);

        // 实例化路径
        mPath = new Path();

//        // 移动点至[300,300]
//        mPath.moveTo(300, 300);
//
//        // 连接路径到点[100,100]
//        mPath.lineTo(100, 100);

        // 移动点至[100,100]
//        mPath.moveTo(100, 100);

//        // 连接路径到点
//        mPath.lineTo(300, 100);
//        mPath.lineTo(400, 200);
//        mPath.lineTo(200, 200);
//
//        // 闭合曲线
//        mPath.close();

        // 连接路径到点
//        mPath.quadTo(200, 200, 300, 100);
//        mPath.cubicTo(200, 200, 300, 0, 400, 100);

//        RectF oval = new RectF(100, 100, 200, 200);
//        // 虽然我们使用arcTo绘制的是一段弧但其最终都会与我们的起始点[100,100]连接起来
//        // Path生成的路径默认是连贯的
//        mPath.arcTo(oval, 0, 90);
//        mPath.arcTo(oval, 0, 90, true);
        // 添加一条弧线到Path中
        RectF oval = new RectF(100, 100, 300, 400);
//        mPath.addOval(oval, Path.Direction.CW); // 顺时针 字在椭圆外围
        mPath.addOval(oval, Path.Direction.CCW); // 逆时针 字在椭圆内围
//        mPath.addArc(oval, 0, 90);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制路径
        canvas.drawPath(mPath, mPaint);

        // 绘制路径上的文字
        canvas.drawTextOnPath("ad撒发射点发放士大夫斯蒂芬斯蒂芬森啊打扫打扫打扫达发达省份撒旦发射的",
                mPath, 0, 0, mTextPaint);
    }

}
