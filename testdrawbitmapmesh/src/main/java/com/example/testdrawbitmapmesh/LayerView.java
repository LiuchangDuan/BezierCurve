package com.example.testdrawbitmapmesh;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/11/24.
 */
public class LayerView extends View {

    // 画笔对象
    private Paint mPaint;

    // 控件宽高
    private int mViewWidth, mViewHeight;

    public LayerView(Context context) {
        this(context, null);
    }

    public LayerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 实例化画笔对象并设置其标识值
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        /*
         * 获取控件宽高
         */
        mViewWidth = w;
        mViewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // 旋转画布
//        canvas.rotate(30);

        /*
         * 绘制一个红色矩形
         */
        mPaint.setColor(Color.RED);
        canvas.drawRect(mViewWidth / 2F - 200, mViewHeight / 2F - 200,
                mViewWidth / 2F + 200, mViewHeight / 2F + 200, mPaint);

        /*
         * 保存并裁剪画布填充绿色
         */
        int saveID1 = canvas.save(Canvas.CLIP_SAVE_FLAG);
        canvas.clipRect(mViewWidth / 2F - 200, mViewHeight / 2F - 200,
                mViewWidth / 2F + 200, mViewHeight / 2F + 200);
        canvas.drawColor(Color.GREEN);

        /*
         * 保存画布并旋转后绘制一个蓝色的矩形
         */
        int saveID2 = canvas.save(Canvas.MATRIX_SAVE_FLAG);

        /*
         * 保存画布并绘制一个蓝色的矩形
         */
//        canvas.save();
//        canvas.saveLayer(0, 0, mViewWidth, mViewHeight, null, Canvas.ALL_SAVE_FLAG);
//        canvas.saveLayer(mViewWidth / 2F - 100, mViewHeight / 2F - 100,
//                mViewWidth / 2F + 100, mViewHeight / 2F + 100, null, Canvas.ALL_SAVE_FLAG);
//        canvas.saveLayerAlpha(mViewWidth / 2F - 100, mViewHeight / 2F - 100,
//                mViewHeight / 2F + 100, mViewHeight / 2F + 100, 0x55, Canvas.ALL_SAVE_FLAG);


        // 旋转画布
        canvas.rotate(5);

        mPaint.setColor(Color.BLUE);

        canvas.drawRect(mViewWidth / 2F - 100, mViewHeight / 2F - 100,
                mViewWidth / 2F + 100, mViewHeight / 2F + 100, mPaint);
//        canvas.restore();

//        canvas.restoreToCount(saveID1);
        canvas.restoreToCount(saveID2);

        mPaint.setColor(Color.YELLOW);

        canvas.drawRect(mViewWidth / 2F - 400, mViewHeight / 2F - 400, mViewWidth / 2F + 400,
                mViewHeight / 2F + 400, mPaint);

    }

}
