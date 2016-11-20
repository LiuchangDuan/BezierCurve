package com.example.customviewdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/11/20.
 */
public class CustomView extends View implements Runnable {

    private Paint mPaint;

    private Context mContext;

    private Bitmap bitmap;

    // 位图绘制时左上角的起点坐标
    private int x, y;

    // 圆环半径
    private int radius;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;

        initPaint();

        initRes(context);
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

//        ColorMatrix colorMatrix = new ColorMatrix(new float[] {
//                0.5F, 0, 0, 0, 0,
//                0, 0.5F, 0, 0, 0,
//                0, 0, 0.5F, 0, 0,
//                0, 0, 0, 1, 0
//        }); // ------------> 图片变暗了

//        ColorMatrix colorMatrix = new ColorMatrix(new float[] {
//                0.33F, 0.59F, 0.11F, 0, 0,
//                0.33F, 0.59F, 0.11F, 0, 0,
//                0.33F, 0.59F, 0.11F, 0, 0,
//                0, 0, 0, 1, 0
//        }); // --------------> 变灰

//        ColorMatrix colorMatrix = new ColorMatrix(new float[] {
//                -1, 0, 0, 1, 1,
//                0, -1, 0, 1, 1,
//                0, 0, -1, 1, 1,
//                0, 0, 0, 1, 0
//        }); // -------------> 类似于反相的效果

//        ColorMatrix colorMatrix = new ColorMatrix(new float[] {
//                0, 0, 1, 0, 0,
//                0, 1, 0, 0, 0,
//                1, 0, 0, 0, 0,
//                0, 0, 0, 1, 0
//        }); // -------------> RGB -> BGR

//        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
//                0.393F, 0.769F, 0.189F, 0, 0,
//                0.349F, 0.686F, 0.168F, 0, 0,
//                0.272F, 0.534F, 0.131F, 0, 0,
//                0, 0, 0, 1, 0,
//        }); // ----------------> 老旧照片效果

//        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
//                1.5F, 1.5F, 1.5F, 0, -1,
//                1.5F, 1.5F, 1.5F, 0, -1,
//                1.5F, 1.5F, 1.5F, 0, -1,
//                0, 0, 0, 1, 0,
//        }); // --------------> 类似去色后高对比度的效果

        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                1.438F, -0.122F, -0.016F, 0, -0.03F,
                -0.062F, 1.378F, -0.016F, 0, 0.05F,
                -0.062F, -0.122F, 1.483F, 0, -0.02F,
                0, 0, 0, 1, 0,
        }); // -------------> 饱和度对比度加强

        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));

//        mPaint.setStyle(Paint.Style.STROKE);

//
////        mPaint.setColor(Color.LTGRAY);
//
//        // 设置画笔颜色为自定义颜色
//        mPaint.setColor(Color.argb(255, 255, 128, 103));
//
//        // 生成色彩矩阵
//        ColorMatrix colorMatrix = new ColorMatrix(new float[] {
//                0.5F, 0, 0, 0, 0,
//                0, 0.5F, 0, 0, 0,
//                0, 0, 0.5F, 0, 0,
//                0, 0, 0, 1, 0
//        });
//
//        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
//
//        // 当setStrokeWidth(0)的时候描边宽度并不为0而是只占一个像素
//        mPaint.setStrokeWidth(10);
//        mPaint.setStyle(Paint.Style.FILL);
    }

    private void initRes(Context context) {
        // 获取位图
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.meizi);

        /*
         * 计算位图绘制时左上角的坐标使其位于屏幕中心
         * 屏幕坐标x轴向左偏移位图一半的宽度
         * 屏幕坐标y轴向上偏移位图一半的高度
         */
        x = MeasureUtil.getScreenSize((Activity) mContext)[0] / 2 - bitmap.getWidth() / 2;
        y = MeasureUtil.getScreenSize((Activity) mContext)[1] / 2 - bitmap.getHeight() / 2;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Paint paint = new Paint();
//        paint.setAntiAlias(true);

        // 绘制位图
        canvas.drawBitmap(bitmap, x, y, mPaint);

//        // 绘制圆环
//        canvas.drawCircle(MeasureUtil.getScreenSize((Activity) mContext)[0] / 2,
//                MeasureUtil.getScreenSize((Activity) mContext)[1] / 2, 200, mPaint);
    }

//    public synchronized void setRadius(int radius) {
//        this.radius = radius;
//
//        invalidate();
//    }

    @Override
    public void run() {
        // 确保线程不断执行不断刷新界面
        while (true) {
            try {
                // 如果半径小于200则自加否则大于200后重置半径值以实现往复
                if (radius <= 200) {
                    radius += 10;

                    // 刷新View
                    postInvalidate();
                } else {
                    radius = 0;
                }

                // 每执行一次暂停40毫秒
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
