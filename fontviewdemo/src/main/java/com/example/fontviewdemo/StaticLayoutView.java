package com.example.fontviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/11/21.
 *
 * 使用StaticLayout结合TextPaint实现换行
 *
 * StaticLayout内部实现了文本绘制换行的处理
 */
public class StaticLayoutView extends View {

    private static final String TEXT = "This is used by widgets to control text layout. You should not need to use this class directly unless you are implementing your own widget or custom display object, or would be tempted to call Canvas.drawText() directly.";

    // 文本的画笔
    private TextPaint mTextPaint;

    // 文本布局
    private StaticLayout mStaticLayout;

    public StaticLayoutView(Context context) {
        this(context, null);
    }

    public StaticLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 初始化画笔
        initPaint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        // 实例化画笔
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(50);
        mTextPaint.setColor(Color.BLACK);

        // Android中字体有四种样式：
        // BOLD（加粗）,BOLD_ITALIC（加粗并倾斜）,ITALIC（倾斜）,NORMAL（正常）；
        // 而其为我们提供的字体有五种：DEFAULT,DEFAULT_BOLD,MONOSPACE,SANS_SERIF和SERIF
//        mTextPaint.setTypeface(Typeface.create("SERIF", Typeface.NORMAL));
        mTextPaint.setTypeface(Typeface.create(Typeface.SERIF, Typeface.NORMAL));

//        // 获取字体并设置画笔字体
//        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "kt.ttf");
//        mTextPaint.setTypeface(typeface);

        // 设置画笔文本倾斜
        // 这个倾斜值没有具体的范围，
        // 但是官方推崇的值为-0.25可以得到比较好的倾斜文本效果，
        // 值为负右倾值为正左倾，默认值为0
        mTextPaint.setTextSkewX(-0.25F);

        // 将文本沿X轴水平缩放，默认值为1，
        // 当值大于1会沿X轴水平放大文本，当值小于1会沿X轴水平缩放文本
//        mTextPaint.setTextScaleX(0.5F);
        // setTextScaleX不仅放大了文本宽度同时还拉伸了字符
        mTextPaint.setTextScaleX(1.5F);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mStaticLayout = new StaticLayout(TEXT, mTextPaint, canvas.getWidth(),
                Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, false);
        mStaticLayout.draw(canvas);
//        canvas.restore();
    }
}
