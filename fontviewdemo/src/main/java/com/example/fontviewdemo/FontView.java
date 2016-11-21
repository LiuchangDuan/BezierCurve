package com.example.fontviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/11/21.
 */
public class FontView extends View {

    private static final String TEXT = "ap爱哥ξτβбпшㄎㄊěǔぬも┰┠№＠↓";

    // 文本的画笔和中心线的画笔
    private Paint textPaint, linePaint;

    // Baseline绘制的XY坐标
    private int baseX, baseY;

    // 画笔
//    private Paint mPaint;

    // 文本测量对象
//    private Paint.FontMetrics mFontMetrics;

    public FontView(Context context) {
        this(context, null);
    }

    public FontView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setLayerType(LAYER_TYPE_SOFTWARE, null);

        initPaint();
    }

    private void initPaint() {

        // 实例化画笔
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(70);
        textPaint.setColor(Color.BLACK);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(1);
        linePaint.setColor(Color.RED);

//        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mPaint.setTextSize(70);
////        mPaint.setTypeface(Typeface.SERIF);
//        mPaint.setColor(Color.BLACK);
//
//
//        mFontMetrics = mPaint.getFontMetrics();
//
//        Log.d("TAG1", "ascent : " + mFontMetrics.ascent);
//        Log.d("TAG1", "top : " + mFontMetrics.top);
//        Log.d("TAG1", "leading : " + mFontMetrics.leading);
//        Log.d("TAG1", "descent : " + mFontMetrics.descent);
//        Log.d("TAG1", "bottom : " + mFontMetrics.bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        // 计算Baseline绘制的起点X轴坐标
//        baseX = (int) (canvas.getWidth() / 2 - textPaint.measureText(TEXT) / 2);
//
//        // 计算Baseline绘制的Y坐标
        baseY = (int) (canvas.getHeight() / 2 - ((textPaint.descent() + textPaint.ascent()) / 2));
//
//        canvas.drawText(TEXT, baseX, baseY, textPaint);

        textPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(TEXT, canvas.getWidth() / 2, baseY, textPaint);

        // 为了便于理解我们在画布中心处绘制一条中线
        canvas.drawLine(0, canvas.getHeight() / 2, canvas.getWidth(), canvas.getHeight() / 2, linePaint);

//        canvas.drawText(TEXT, 0, Math.abs(mFontMetrics.top), mPaint);
    }
}
