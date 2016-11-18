package com.example.rotatetaiji;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/11/18.
 */
public class TaiJiView extends View {

    // 白色画笔
    private Paint whitePaint;

    // 黑色画笔
    private Paint blackPaint;

    // 旋转角度
    private float degrees = 0;

    public TaiJiView(Context context) {
        this(context, null);
    }

    public TaiJiView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TaiJiView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaints();
    }

    // 初始化画笔函数
    private void initPaints() {
        whitePaint = new Paint();
        whitePaint.setAntiAlias(true);
        whitePaint.setColor(Color.WHITE);

        blackPaint = new Paint(whitePaint);
        blackPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 画布宽度
        int width = canvas.getWidth();
        // 画布高度
        int height = canvas.getHeight();
        // 移动坐标原点到画布中心
        canvas.translate(width / 2, height / 2);
        // 绘制背景色
        canvas.drawColor(Color.GRAY);
        // 旋转画布
        canvas.rotate(degrees);
        // 绘制两个半圆
        // 太极半径
        int radius = Math.min(width, height) / 2 - 100;
        // 绘制区域
        RectF rect = new RectF(-radius, -radius, radius, radius);
        // 绘制黑色半圆
        canvas.drawArc(rect, 90, 180, true, blackPaint);
        // 绘制白色半圆
        canvas.drawArc(rect, -90, 180, true, whitePaint);
        // 绘制两个小圆
        // 小圆半径为大圆的一半
        int smallRadius = radius / 2;
        canvas.drawCircle(0, -smallRadius, smallRadius, blackPaint);
        canvas.drawCircle(0, smallRadius, smallRadius, whitePaint);
        // 绘制鱼眼（两个更小的圆）
        canvas.drawCircle(0, -smallRadius, smallRadius / 4, whitePaint);
        canvas.drawCircle(0, smallRadius, smallRadius / 4, blackPaint);
    }

    public void setRotate(float degrees) {
        this.degrees = degrees;
        invalidate();
    }

}
