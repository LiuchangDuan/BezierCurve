package com.example.testdrawbitmapmesh;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/11/22.
 */
public class BitmapMeshView extends View {

    // 横向分割成的网格数量
    private static final int WIDTH = 19;
    // 纵向分割成的网格数量
    private static final int HEIGHT = 19;
    // 横纵向网格交织产生的点数量
    private static final int COUNT = (WIDTH + 1) * (HEIGHT + 1);

    // 位图资源
    private Bitmap mBitmap;

    // 交点的坐标数组
    private float[] verts;

    public BitmapMeshView(Context context) {
        this(context, null);
    }

    public BitmapMeshView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 获取位图资源
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.girl);

        // 实例化数组
        verts = new float[COUNT * 2];

//        // 生成各个交点坐标
//        int index = 0;
//        float multiple = mBitmap.getWidth();
//        for (int y = 0; y <= HEIGHT; y++) {
//            float fy = mBitmap.getHeight() * y / HEIGHT;
//            for (int x = 0; x <= WIDTH; x++) {
//                // 类错切效果
//                float fx = mBitmap.getWidth() * x / WIDTH + ((HEIGHT - y) * 1.0F / HEIGHT * multiple);
//                setXY(fx, fy, index);
//                index += 1;
//            }
//        }

        // 生成各个交点坐标
        int index = 0;
        float multipleY = mBitmap.getHeight() / HEIGHT;
        float multipleX = mBitmap.getWidth() / WIDTH;
        for (int y = 0; y <= HEIGHT; y++) {
            float fy = multipleY * y;
            for (int x = 0; x <= WIDTH; x++) {
                float fx = multipleX * x;

                setXY(fx, fy, index);

                // 类似放大镜效果
                // 将图片眼睛附近的四个点外移到临近的四个点上
                // 图像该区域就会被像放大一样
                if (5 == y) {
                    if (8 == x) {
                        setXY(fx - multipleX, fy - multipleY, index);
                    }
                    if (9 == x) {
                        setXY(fx + multipleX, fy - multipleY, index);
                    }
                }
                if (6 == y) {
                    if (8 == x) {
                        setXY(fx - multipleX, fy + multipleY, index);
                    }
                    if (9 == x) {
                        setXY(fx + multipleX, fy + multipleY, index);
                    }
                }

                index += 1;

            }
        }

    }

    /**
     * 将计算后的交点坐标存入数组
     * @param fx x坐标
     * @param fy y坐标
     * @param index 标识值
     */
    private void setXY(float fx, float fy, int index) {
        verts[index * 2 + 0] = fx;
        verts[index * 2 + 1] = fy;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制网格位图
        canvas.drawBitmapMesh(mBitmap, WIDTH, HEIGHT, verts, 0, null, 0, null);
    }

}
