package com.example.multicircleviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/11/22.
 */
public class MultiCircleView extends View {

    // 描边宽度占比
    private static final float STROKE_WIDTH = 1F / 256F;
    // 大圆半径
    private static final float CIRCLE_LARGER_RADIUS = 3F / 32F;
    // 线段长度占比
    private static final float LINE_LENGTH = 3F / 32F;
    // 小圆半径
    private static final float CIRCLE_SMALL_RADIUS = 5F / 64F;
    // 大圆小圆线段两端间隔占比
    private static final float SPACE = 1F / 64F;
    // 弧半径
    private static final float ARC_RADIUS = 1F / 8F;
    // 弧围绕文字半径
    private static final float ARC_TEXT_RADIUS = 5F / 32F;

    // 描边画笔和文字画笔
    private Paint strokePaint, textPaint;

    // 弧形画笔
    private Paint arcPaint;

    // 控件边长
    private int size;

    // 描边宽度
    private float strokeWidth;

    // 中心圆圆心坐标
    private float ccX, ccY;

    // 大圆半径和小圆半径
    private float largeCircleRadius, smallCircleRadius;

    // 线段长度
    private float lineLength;

    // 大圆小圆线段两端间隔
    private float space;

    // 文本的Y轴偏移值
    private float textOffsetY;

    public MultiCircleView(Context context) {
        this(context, null);
    }

    public MultiCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 初始化画笔
        initPaint(context);

    }

    /**
     * 初始化画笔
     *
     * @param context
     */
    private void initPaint(Context context) {
        // 初始化描边画笔
        strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setColor(Color.WHITE);
        strokePaint.setStrokeCap(Paint.Cap.ROUND);

        // 初始化文字画笔
        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.SUBPIXEL_TEXT_FLAG);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(30);
        textPaint.setTextAlign(Paint.Align.CENTER);

        // 初始化弧形画笔
        arcPaint = new Paint();

        textOffsetY = (textPaint.descent() + textPaint.ascent()) / 2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 强制长宽一致
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // 获取控件边长
        size = w;

        // 参数计算
        calculation();
    }

    /**
     * 参数计算
     */
    private void calculation() {
        // 计算描边宽度
        strokeWidth = STROKE_WIDTH * size;

        // 计算大圆半径
        largeCircleRadius = CIRCLE_LARGER_RADIUS * size;

        // 计算小圆半径
        smallCircleRadius = CIRCLE_SMALL_RADIUS * size;

        // 计算线段长度
        lineLength = size * LINE_LENGTH;

        // 计算大圆小圆线段两端间隔
        space = size * SPACE;

        // 计算中心圆圆心坐标
        ccX = size / 2;
        ccY = size / 2 + CIRCLE_LARGER_RADIUS * size;

        // 设置参数
        setPara();

    }

    /**
     * 设置参数
     */
    private void setPara() {
        // 设置描边宽度
        strokePaint.setStrokeWidth(strokeWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制背景
        canvas.drawColor(0xFFF29B76);

        // 绘制中心圆
        canvas.drawCircle(ccX, ccY, largeCircleRadius, strokePaint);
        canvas.drawText("Aliga", ccX, ccY - textOffsetY, textPaint);

        // 绘制左上方图形
        drawTopLeft(canvas);
        // 绘制右上方图形
        drawTopRight(canvas);
        // 绘制左下方图形
        drawBottomLeft(canvas);
        // 绘制下方图形
        drawBottom(canvas);
        // 绘制右下方图形
        drawBottomRight(canvas);
    }

    /**
     * 绘制左上方图形
     * @param canvas
     */
    private void drawTopLeft(Canvas canvas) {
        // 锁定画布
        canvas.save();

        // 平移和旋转画布
        canvas.translate(ccX, ccY);
        canvas.rotate(-30);

        // 依次画：线-圈-线-圈
        canvas.drawLine(0, -largeCircleRadius, 0, -lineLength * 2, strokePaint);
        canvas.drawCircle(0, -lineLength * 3, largeCircleRadius, strokePaint);
        canvas.drawText("Apple", 0, -lineLength * 3 - textOffsetY, textPaint);

        canvas.drawLine(0, -largeCircleRadius * 4, 0, -lineLength * 5, strokePaint);
        canvas.drawCircle(0, -lineLength * 6, largeCircleRadius, strokePaint);
        canvas.drawText("Orange", 0, -lineLength * 6 - textOffsetY, textPaint);

        // 释放画布
        canvas.restore();
    }

    /**
     * 绘制右上方图形
     * @param canvas
     */
    private void drawTopRight(Canvas canvas) {

        float circleY = -lineLength * 3;

        // 锁定画布
        canvas.save();

        // 平移和旋转画布
        canvas.translate(ccX, ccY);
        canvas.rotate(30);

        // 依次画：线-圈
        canvas.drawLine(0, -largeCircleRadius, 0, -lineLength * 2, strokePaint);
        canvas.drawCircle(0, circleY, largeCircleRadius, strokePaint);
        canvas.drawText("Tropical", 0, circleY - textOffsetY, textPaint);

        // 画弧形
        drawTopRightArc(canvas, circleY);

        // 释放画布
        canvas.restore();
    }

    /**
     * 绘制右上角画弧形
     * @param canvas
     * @param circleY
     */
    private void drawTopRightArc(Canvas canvas, float circleY) {
        // 锁定画布
        canvas.save();

        // 平移和旋转画布
        canvas.translate(0, circleY);
        canvas.rotate(-30);

        float arcRadius = size * ARC_RADIUS;

        RectF oval = new RectF(-arcRadius, -arcRadius, arcRadius, arcRadius);

        arcPaint.setStyle(Paint.Style.FILL);
        arcPaint.setColor(0x55EC6941);
        canvas.drawArc(oval, -22.5F, -135, true, arcPaint);

        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setColor(Color.WHITE);
        canvas.drawArc(oval, -22.5F, -135, false, arcPaint);

        float arcTextRadius = size * ARC_TEXT_RADIUS;

        canvas.save();

        // 把画布旋转到扇形左端的方向
        canvas.rotate(-135F / 2F);

        // 每隔33.75度角画一次文本
        for (float i = 0; i < 5 * 33.75F; i += 33.75F) {
            canvas.save();
            canvas.rotate(i);

            canvas.drawText("Aliga", 0, -arcTextRadius, textPaint);

            canvas.restore();

        }

        canvas.restore();

        canvas.restore();

    }

    /**
     * 绘制左下方图形
     * @param canvas
     */
    public void drawBottomLeft(Canvas canvas) {

        float circleY = -lineLength * 2 - smallCircleRadius - space * 2;

        // 锁定画布
        canvas.save();

        // 平移和旋转画布
        canvas.translate(ccX, ccY);
        canvas.rotate(-100);

        // 依次画：(间隔)线(间隔)-圈
        canvas.drawLine(0, -largeCircleRadius - space, 0, -lineLength * 2 - space, strokePaint);
        canvas.drawCircle(0, circleY, smallCircleRadius, strokePaint);
        canvas.drawText("Banana", 0, circleY - textOffsetY, textPaint);

        // 释放画布
        canvas.restore();
    }

    /**
     * 绘制下方图形
     * @param canvas
     */
    private void drawBottom(Canvas canvas) {

        float circleY = -lineLength * 2 - smallCircleRadius - space * 2;

        // 锁定画布
        canvas.save();

        // 平移和旋转画布
        canvas.translate(ccX, ccY);
        canvas.rotate(180);

        // 依次画：(间隔)线(间隔)-圈
        canvas.drawLine(0, -largeCircleRadius - space, 0, -lineLength * 2 - space, strokePaint);
        canvas.drawCircle(0, circleY, smallCircleRadius, strokePaint);
        canvas.drawText("Cucumber", 0, circleY - textOffsetY, textPaint);

        // 释放画布
        canvas.restore();
    }

    /**
     * 绘制右下方图形
     * @param canvas
     */
    private void drawBottomRight(Canvas canvas) {

        float circleY = -lineLength * 2 - smallCircleRadius - space * 2;

        // 锁定画布
        canvas.save();

        // 平移和旋转画布
        canvas.translate(ccX, ccY);
        canvas.rotate(100);

        // 依次画：(间隔)线(间隔)-圈
        canvas.drawLine(0, -largeCircleRadius - space, 0, -lineLength * 2 - space, strokePaint);
        canvas.drawCircle(0, circleY, smallCircleRadius, strokePaint);
        canvas.drawText("Vibrators", 0, circleY - textOffsetY, textPaint);

        // 释放画布
        canvas.restore();
    }

}
