package com.example.imgviewdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/11/26.
 */
public class ImgView extends View {

    // 位图对象
    private Bitmap mBitmap;

    public ImgView(Context context) {
        this(context, null);
    }

    public ImgView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制位图
//        canvas.drawBitmap(mBitmap, 0, 0, null);
        canvas.drawBitmap(mBitmap, getPaddingLeft(), getPaddingTop(), null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 声明一个临时变量来存储计算出的测量值
        int resultWidth = 0;
        // 获取宽度测量规格中的mode
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        // 获取宽度测量规格中的size
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        /*
         * 如果爹心里有数
         */
        if (modeWidth == MeasureSpec.EXACTLY) {
            // 那么儿子也不要让爹难做就取爹给的大小吧
            resultWidth = sizeWidth;
        /*
         * 如果爹心里没数
         */
        } else {
            // 那么儿子可要自己看看自己需要多大了
//            resultWidth = mBitmap.getWidth();
            // 在计算控件尺寸时考虑内边距的大小
            resultWidth = mBitmap.getWidth() + getPaddingLeft() + getPaddingRight();
            /*
             * 如果爹给儿子的是一个限制值
             */
            if (modeWidth == MeasureSpec.AT_MOST) {
                // 那么儿子自己的需求就要跟爹的限制比比看谁小要谁
                resultWidth = Math.min(resultWidth, sizeWidth);
            }
        }
        int resultHeight = 0;
        int modeHeigtht = MeasureSpec.getMode(heightMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        if (modeHeigtht == MeasureSpec.EXACTLY) {
            resultHeight = sizeHeight;
        } else {
//            resultHeight = mBitmap.getHeight();
            resultHeight = mBitmap.getHeight() + getPaddingTop() + getPaddingBottom();
            if (modeHeigtht == MeasureSpec.AT_MOST) {
                resultHeight = Math.min(resultHeight, sizeHeight);
            }
        }
        // 设置测量尺寸
        setMeasuredDimension(resultWidth, resultHeight);
    }

    /**
     * 设置位图
     * @param bitmap 位图对象
     */
    public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
    }

}
